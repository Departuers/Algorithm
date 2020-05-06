package dp.组合dp;

/**
 * 有n个无区别的物品,将它们划分成不超过m组,
 * 求出划分方法数模M的余数
 * <p>
 * 1≤m≤n≤1000
 * 2≤M≤10000
 * <p>
 * 输入示例:
 * n=4
 * m=3
 * M=10000
 * 输出:
 * 4(1+1+2=1+3=2+2)
 */
public class 划分数 {
    public static void main(String[] args) {
        HuaFen(4, 3, 10000);
    }

    public static int HuaFen(int n, int m, int M) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j - i >= 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - i]) % M;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[m][n]);
        return dp[m][n];
    }
}
