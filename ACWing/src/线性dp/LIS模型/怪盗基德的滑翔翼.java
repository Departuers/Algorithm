package 线性dp.LIS模型;

import java.util.Scanner;

/**
 * 初始基德可以任选一个房子降落,跳只能越跳跃低
 * 还能选一个方向,不能换方向,
 * 当确定完方向和起点后,最长的距离是什么呢
 * 起点a[i]
 * 方向向右寻找最长递减序列,方向向左就是求最长上升子序列
 * 求两个方向的LIS取Max
 */
public class 怪盗基德的滑翔翼 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        while (K-- != 0) {
            N = sc.nextInt();
            for (int i = 1; i <= N; i++) {
                h[i] = sc.nextInt();
            }
            int[] dp = new int[104];
            int res = 0;
            for (int i = 1; i <= N; i++) {
                dp[i] = 1;
                for (int j = 1; j < i; j++) {
                    if (h[i] > h[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            //最长下降子序列!!!代码直接倒序就可以了
            for (int i = N; i >= 1; i--) {
                dp[i] = 1;//无需重置dp数组
                for (int j = N; j > i; j--) {
                    if (h[i] > h[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        //倒序在求dp[i]时,dp[j]一定已经求出来了
                    }
                    res = Math.max(res, dp[i]);
                }
            }
            System.out.println(res);
        }
    }

    static int[] h = new int[104];
    static int K, N;
}
