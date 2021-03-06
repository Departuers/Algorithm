package dp.线性dp.LIS模型;

import java.util.Scanner;

/**
 * 给定一个序列
 * 最少可以拆分成多少个上升子序列与下降子序列
 * LIS+DFS
 * DFS求最小步数,可以用迭代加深/全局变量最小值,一直更新
 * 每一个数字都有4种放法:
 * 1. 放在上升子序列的后面
 * 2. 开一个新的上升子序列
 * 3. 放在下降子序列后面
 * 4. 开一个新的下降子序列
 */
public class 导弹防御系统 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        ans = N;
        dfs(0, 0, 0);
        System.out.println(ans);
    }

    static int ans, N;//ans是全局最小值
    static int[] arr = new int[59];
    static int[] up = new int[59];
    static int[] down = new int[59];

    //u代表遍历到哪一个元素,sup代表有多少个上升子序列
    //sdown代表有多少个下降子序列
    static void dfs(int u, int sup, int sdown) {
        if (sup + sdown >= ans) return;//最优剪枝,约等于迭代加深
        //dfs可以非常快找到一个合法解
        if (u == N) {
            //sup+sdown>=ans已经被判掉
            ans = sup + sdown;
            return;
        }
        //把当前数放在上升子序列里
        int k = 0;
        while (k < sup && up[k] >= arr[u]) k++;
        int t = up[k];//存下来,因为需要搜索回溯,
        up[k] = arr[u];
        if (k < sup) dfs(u + 1, sup, sdown);
            //k<sup代表无需开辟一个新的上升子序列
        else dfs(u + 1, sup + 1, sdown);
        up[k] = t;//回溯

        //把当前数放在下降子序列里
        k = 0;
        while (k < sdown && down[k] <= arr[u]) k++;
        t = down[k];
        down[k] = arr[u];
        if (k < sdown) dfs(u + 1, sup, sdown);
            //k<sdown代表无需开辟一个新的下降子序列
        else dfs(u + 1, sup, sdown + 1);
        down[k] = t;
    }
}
