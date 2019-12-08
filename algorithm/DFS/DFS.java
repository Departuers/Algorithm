package DFS;

import java.util.ArrayList;

public class DFS {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3,15,23,13,12};
//        buFenHe(arr, 46, 0, new ArrayList<>());
//        int[] data = new int[6];
//        data[0] = 1;
//        suShuHuan(6,data,1);
    }

    /**
     * 2.部分数之和
     * 给定一个数组a，长度为n，判断是否可以从中选择出若干数，使他们的和恰好为K
     * 输入: n=4
     * a={1,2,4,7}
     * K=13
     * <p>
     * 输出:Yes{13=2+4+7}
     * 思路:要这个a[0]，这个1，然后把12交给下一次DFS来凑，或者不要这个a[0],把13交给其他元素来凑
     * 平行状态这是
     * <p>
     * 13 ，cur=0   cur代表找到哪个上面了，a[cur]
     * 12，cur=1   13，cur=1
     * 10,cur=2
     * 6,cur=3  10,cur=3
     * 一条路走到完，发现没有找到解，回退，一步继续找
     *
     * @param arr 源数组
     * @param k   剩余多少交给下次递归,
     * @param cur 数组索引走到哪了
     */
    public static void buFenHe(int[] arr, int k, int cur, ArrayList<Integer> intS) {
        if (k == 0) {//出口
            for (Integer anInt : intS) {
                System.out.print(anInt + " ");
            }
            System.exit(0);
        }
        if (k < 0 || cur == arr.length)//剪枝
            return;
        buFenHe(arr, k, cur + 1, intS);
        intS.add(arr[cur]);
        buFenHe(arr, k - arr[cur], cur + 1, intS);
        intS.remove(intS.size() - 1);
    }

    /**
     * @param n
     * @param arr
     * @param cur
     */
    public static void suShuHuan(int n, int[] arr, int cur) {
        if (cur == n && isP(arr[0] + arr[n - 1])) {
            print(arr);
            return;
        }
        for (int i = 2; i <= n; i++) {
            if (check(arr, i, cur)) {
                arr[cur] = i;
                suShuHuan(n, arr, cur + 1);
                arr[cur] = 0;
            }
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "" : " "));
        }
        System.out.println();
    }
    /**
     * 判断是否
     *
     * @param arr
     * @param k
     * @param cur
     * @return
     */
    private static boolean check(int[] arr, int k, int cur) {
        for (int i : arr) {
            if (i == k || !isP(arr[cur - 1] + k)) return false;
        }
        return true;
    }

    /**
     * 判断是否为素数
     *
     * @param k 需要判断是不是为素数的数字
     * @return b
     */
    private static boolean isP(int k) {
        for (int i = 2; i * i <= k; i++) {
            if (k % i == 0)
                return false;
        }
        return true;
    }

}