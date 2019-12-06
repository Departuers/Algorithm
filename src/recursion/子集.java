package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 子集生成。
 */
public class 子集 {
    public static void main(String[] args) {

        int[] arr = {3, 2, 1};
        System.out.println(getSubsets3(arr, arr.length));
        Integer[] arre = {3, 2, 1};
        System.out.println(getSubsets2(arre, arr.length));
        Character[] da = {'A', 'B', 'C'};
        System.out.println(getSubsets(da, 3));
    }

    /**
     * 逐步生成之递归写法
     * 编写一个方法，确定某集合的所有子集。
     * {A,B,C}  有限集合
     * {A} {B} {C} {A,B} {A,C} {B,C} {A,B,C}自身也是自身的子集
     * <p>
     * C3/1  C3/2  C3/3
     * 子集生成就是选和不选的问题，要或者不要,要或者不要都是它的子集
     * 这个平行状态是个集合，每一个状态又是一个集合。
     * Set<Set<String>>
     * <p>
     * 从没有元素开始推
     * { }
     * {A}                        {}
     * {A}     {A,B}              {B}          {}
     * {A}  {A,C} {A,B} {A,B,C}   {B}    {B,C}   {C} {}
     *
     * @param arr 需要生成子集的源串
     * @param n   数组长度
     * @return 返回去重过后的东西
     */
    public static Set<Set<Integer>> getSubsets3(int[] arr, int n) {
//        Arrays.sort(arr);
        return getSubsets3Core(arr, n, n - 1);//转换成最大索引
    }

    /**
     * @param arr 需要生成子集的源串
     * @param n   源数组长度
     * @param cur 最大索引
     * @return
     */
    public static Set<Set<Integer>> getSubsets3Core(int[] arr, int n, int cur) {
        Set<Set<Integer>> newSet = new HashSet<>();
        if (cur == 0) {//单独处理第一个元素
            Set<Integer> nil = new HashSet<>();
            Set<Integer> first = new HashSet<>();
            first.add(arr[0]);
            newSet.add(nil);
            newSet.add(first);
            return newSet;
        }
        Set<Set<Integer>> oldSet = getSubsets3Core(arr, n, cur - 1);
        for (Set<Integer> set : oldSet) {
            //对于每个子集，cur可以把这个元素加进去，也可以不加进去
            newSet.add(set);//保留原样，
            Set<Integer> clone = (Set<Integer>) ((HashSet) set).clone();
            clone.add(arr[cur]);
            newSet.add(clone);
        }
        return newSet;
    }

    /**
     * 子集，逐步生成之迭代大法,相当重要，非常巧妙
     *
     * @param arr 需要生成子集的源串
     * @param n   上数组长度
     * @return
     */
    public static <T> Set<Set<T>> getSubsets2(T[] arr, int n) {
        Set<Set<T>> res = new HashSet<>();
        res.add(new HashSet<>());//初始化有个空集
        for (int i = 0; i < n; i++) {
            Set<Set<T>> temp = new HashSet<>();
            temp.addAll(res);//要有一个存上一次迭代的结果
            for (Set<T> e : res) {
                Set<T> clone = (Set<T>) ((HashSet) e).clone();
                clone.add(arr[i]);
                temp.add(clone);
            }
            res = temp;
        }
        return res;
    }

    /**
     * 子集生成之二进制，代换ABC
     * 子集生成之二进制(2^n-1)
     * {A,B,C}
     * 子集有2^n个，去掉空集就是(2^n-1)
     * 比如三个都选就是1 1 1，算上0
     * 对于第一个值，选或者不选。
     * 对于第二个值，选或者不选。
     * 对于第三个值，选或者不选。
     * 会形成一个选择树
     * 去掉空集就是2^n-1
     * 从1-2^n-1(去掉空集)把其中的二进制写出来
     * 0   0   1
     * 0   1   0
     * 0   1   1
     * 1   0   0
     * 1   0   1
     * 1   1   0
     * 1   1   1
     * C   B   A
     * 0代表不选，1代表选。映射成A，B，C
     * 所以要生成子集的源数组，需要按字典序倒序排列
     * for(1 -> 2^n-1 )
     * {
     * list.add(对应元素)
     * }
     *
     * @param arr 要生成子集的数组
     * @param n   二进制代换子集(去掉空集)是2^n-1个,所以是元素个数
     * @return
     */
    public static <T> ArrayList<ArrayList<T>> getSubsets(T[] arr, int n) {
        //Arrays.sort(arr);
        ArrayList<ArrayList<T>> res = new ArrayList<>();
        for (int i = (int) (Math.pow(2, n) - 1); i > 0; i--) {//i为子集最大数量
            ArrayList<T> temp = new ArrayList<>();//对于每一个二进制数据，都新建一个list记录
            for (int j = n - 1; j >= 0; j--) {  //检查二进制每一位是否为1
                if (((i >> j) & 1) == 1) {//从j位高位开始检查，添加的也是高位
                    temp.add(arr[j]);
                }
            }
            System.out.println(temp);
            res.add(temp);
        }
        return res;
    }
}
