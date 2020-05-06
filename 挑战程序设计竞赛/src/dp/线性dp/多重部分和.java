package dp.线性dp;

import java.util.Arrays;

/**
 * 有n种大小不同的数字ai,每种各有mi个,判断是否可以从这些数字中选出若干个,
 * 使他们的和为K
 * 示例:
 * 输入:
 * int n = 3;
 * int[] a = {3, 5, 8};
 * int[] m = {3, 2, 2};
 * int K = 17;
 * 输出
 * Yes(3*3+8=17)
 */

public class 多重部分和 {
    public static void main(String[] args) {
        int n = 3;
        int[] a = {3, 5, 8};
        int[] m = {3, 2, 2};
        int K = 17;
        System.out.println(BuFen(n, a, m, K));
    }

    public static boolean isBuFen(int n, int[] a, int[] m, int K) {
        boolean[][] dp = new boolean[n + 1][K + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= K; j++) {
                for (int l = 0; l <= m[i] && l * a[i] <= j; l++) {
                    dp[i + 1][j] |= dp[i][j - K * a[i]];
                }
            }
        }
        return dp[n][K];
    }

    public static int BuFen(int n, int[] a, int[] m, int K) {
        int[] dp = new int[K + 1];
        Arrays.fill(dp, 1, dp.length, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= K; j++) {
                if (dp[j] >= 0) {
                    dp[j] = m[i];
                } else if (j < a[i] || dp[j - a[i]] <= 0) {
                    dp[j] = -1;
                } else {
                    dp[j] = dp[j - a[i]] - 1;
                }
            }
        }
        return dp[K];
    }
}
