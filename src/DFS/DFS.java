package DFS;

import java.util.ArrayList;

public class DFS {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 8, 6, 21, 13, 52};
        dfs(arr, 32, 0, new ArrayList<>());
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
     * @param k   剩余多少交给下次递归
     * @param cur 数组索引走到哪了
     */
    public static void dfs(int[] arr, int k, int cur, ArrayList<Integer> intS) {
        if (k == 0) {
            System.out.println("Yes");
            for (Integer anInt : intS) {
                System.out.print(anInt + " ");
            }
            System.exit(0);
        }
        if (k < 0 || cur == arr.length) return;
        //这两个状态平行
        dfs(arr, k, cur + 1, intS);//不要cur这个元素
        intS.add(arr[cur]);

        dfs(arr, k - arr[cur], cur + 1, intS);//要k这个元素
        intS.remove(intS.size() - 1);//回溯
    }
}
