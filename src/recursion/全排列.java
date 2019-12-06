package recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 全排列
 */
public class 全排列 {
    public static void main(String[] args) {
        System.out.println(Quanpailie("abc"));
        System.out.println(getPermutation("abc"));
    }


    /**
     * 全排列(N!)
     * 全排列就没有要和不要的概念，全都要，对排列顺序有要求
     * 如果有N个元素，那么有N!种排列方式
     * 某一位上，选谁的概念。
     * {A,B,C}的全排列
     * {A,B,C}     {A,C,B}
     * {B,A,C}     {B,C,A}
     * {C,A,B}     {C,B,A}
     * 规律:第一位有3个选择，第二位有2个选择，第一位只有一个选择
     * 3*2*1=6
     *
     * 整体上来说N!比2^n-1增长速度快
     * 在4的时候，N!就超过2^n-1，超过子集
     * 增量构造，(前缀法)
     * 1           {A}
     * 2     {B A}     {A  B}      把B放在A的左边，右边
     * 3   {C,B,A}     {C,A,B}   把C放在前一排,每个小集合的左边，右边，中间
     *     {B,A,C}     {B,C,A}   比如把C放在第二排小集合的左边。
     *     {A,C,B}     {A,B,C}
     * 代码细节需要深刻理解
     * @param arr 需要进行全排列的字符串
     * @return    返回排序好的ArrayList
     */
    public static ArrayList<String> Quanpailie(String arr) {
        int n = arr.length();
        ArrayList<String> res = new ArrayList<>();
        res.add(arr.charAt(0) + "");//初始化，第一个字符
        for (int i = 1; i < n; i++) {
            ArrayList<String> temp = new ArrayList<>();
            char c = arr.charAt(i);
            for (String str : res) {
                String newTemp = c + str;//加在前面
                temp.add(newTemp);

                newTemp = str + c;
                temp.add(newTemp);//加在后面

                for (int j = 1; j < str.length(); j++) {
                    newTemp = str.substring(0, j) + c + str.substring(j);
                    temp.add(newTemp);//加在中间
                }
            }
            res = temp;
        }
        return res;
    }

    public static ArrayList<String> res = new ArrayList<>();

    /**
     * 回溯   (交换法)    全排列
     * {A,B,C}  全排列就是交换顺序，得到所有的排列方式
     * 把每一个元素都放到第一个，
     *                           {}
     *           A[B,C]        B[A,C]       C[A,B]       右边[]中的元素代表这条支路还剩的元素
     *        B[C]   C[B]    A[C]  C[A]    A[B]   B[A]
     *       ABC     ACB     BAC   BCA     CAB     CBA
     * 这种方法使用递归，若有N个元素，就有N路递归，每个递归深度为N
     * 先纵后横
     * 数组是共享的，因为不停的交换，已经乱了，所以每棵子树遍历结束，要回溯
     * 回溯求全排列需要多路分支，多路递归
     *
     * @param A     需要进行全排列的字符串
     * @return      返回一个ArrayList
     */
    public static ArrayList<String> getPermutation(String A) {
        char[] arr = A.toCharArray();
        Arrays.sort(arr);//从小到大排序
        getPermutationCore(arr, 0);
        return res;
    }
    public static void getPermutationCore(char[] arr, int k) {
        //排好了一种情况
        if (k == arr.length) {
            res.add(new String(arr));
        }
        for (int i = k; i < arr.length; i++) {
            swap(arr, k, i);
            getPermutationCore(arr, k + 1);
            swap(arr, k, i);//回溯
        }
    }
    public static void swap(char[] arr, int k, int p) {
        if (k==p)
            return;
        char temp = arr[k];
        arr[k] = arr[p];
        arr[p] = temp;
    }


}
