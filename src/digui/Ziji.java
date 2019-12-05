package digui;

import java.util.HashSet;
import java.util.Set;

/**
 * 子集生成。
 */
public class Ziji {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1};
        System.out.println(getSubsets3(arr, arr.length));
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
    public static Set<Set<Integer>> getSubsets2(int[] arr, int n) {
        Set<Set<Integer>> res = new HashSet<>();
        res.add(new HashSet<>());//初始化有个空集
        for (int i = 0; i < n; i++) {
            Set<Set<Integer>> temp = new HashSet<>();
            temp.addAll(res);//要有一个存上一次迭代的结果
            for (Set<Integer> e : res) {
                Set<Integer> clone = (Set<Integer>) ((HashSet) e).clone();
                clone.add(arr[i]);
                temp.add(clone);
            }
            res = temp;
        }
        return res;
    }
}
