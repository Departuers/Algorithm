package dp.线性dp;

/**
 * f[i,j]表示 字符串s1的前i位,和字符串s2前j位的最长公共子序列的长度
 * 属性max
 * 状态计算:找最后一个不同点
 *
 */
public class LCS {
    public static void main(String[] args) {

    }
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

}