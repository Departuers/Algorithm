package dp.线性dp;

/**
 * 本题涉及两个序列，需要用二维数组来表示状态，
 * 即f[i][j]表示A字符串中前i个字符与B字符串中前j个字符的最长公共子序列长度。
 * 在01背包问题中，我们需要考虑的是第i个物品选与不选的问题，而本题要求f[i][j]，
 * 需要考虑A[i]与B[j]选与不选的问题，如果都不选，则LCS长度等于f[i-1][j-1]；如果A[i]选，
 * B[j]不选，即LCS中包含A[i]但不包含B[j],此时状态为f[i][j-1]；如果A[i]不选，B[j]选，
 * 则可表示为状态f[i-1][j]；如果LCS既包含A[i]又包含B[j]，由于二者均是序列的末尾元素，故A[i] == B[j]，
 * 此时f[i][j] = f[i-1][j-1] + 1。
 * 即f[i][j] = max(f[i-1][j-1],f[i-1][j],f[i][j-1],f[i-1][j-1]+1) = max(f[i-1][j],f[i][j-1],f[i-1][j-1]+1)，
 * 之所以可以去掉对f[i-1][j-1]的比较，可以理解为f[i-1][j-1]是f[i-1][j]的子问题，即f[i-1][j-1]不会超过f[i-1][j]。
 * 更准确的描述状态转移方程是：A[i] != B[j]时，f[i][j] = max(f[i-1][j],f[i][j-1])；A[i] == B[j]时，
 * f[i][j] = max(f[i-1][j],f[i][j-1],f[i-1][j-1] + 1) = f[i-1][j-1] + 1，
 * 这是因为f[i-1][j]与f[i][j-1]最多比f[i-1][j-1]大1，绝对不可能超过f[i-1][j-1]+1。
 * f[i,j]表示 字符串s1的前i位,和字符串s2前j位的最长公共子序列的长度
 * 属性max
 * 状态计算:找最后一个不同点
 * 如果a[i]!=b[j]则显然f[i,j]=max(f[i,j-1],f[i-1,j]);
 * 如果a[i]==b[j] f[i,j]=max(f[i,j],f[i-1,j-1]+1)
 */
public class LCS {
    public static void main(String[] args) {

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

}