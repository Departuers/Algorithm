package dp;

/**
 * 最长公共子序列
 */
public class LCS {
    public static void main(String[] args) {

    }

    public static String dp(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int flag = 0;//标志
        for (int i = 0; i <= len1; i++) {//初始化第一列,如果之前的值有算出来1,后面的都是1,符合标志,就不用算了,
            if (flag == 1) {
                dp[i][1] = 1;
            } else if (s1.charAt(i - 1) == s2.charAt(0)) {
                dp[i][1] = 1;
                flag = 1;
            } else {
                dp[i][1] = 0;
            }
        }
        flag = 0;
        for (int i = 0; i <= len2; i++) {//初始化第一行
            if (flag == 1) {
                dp[1][i] = 1;
            } else if (s2.charAt(i - 1) == s1.charAt(0)) {
                dp[1][i] = 1;
                flag = 1;
            } else {
                dp[1][i] = 0;
            }
        }
        return "";
    }

}
