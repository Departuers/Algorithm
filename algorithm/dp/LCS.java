package dp;

import java.util.Arrays;

/**
 * 最长公共子序列dp
 * 辅助数组为dp,则dp[i][j]的语义就是当前两个串范围内LCS的长度
 *     B   A   3   4   C        源串,下面那一列也是源串
 * A   0   1   1   1   1        这一行语义,BA34C的所有前缀跟A这个字母的最长公共子序列,填入dp表      第一个0代表B之前的,和A之前的最长公共子序列,因为都只有1个字母,比较他们自己就可以,
 * 1   0   1   1   1   1       代表1和B没对上,并且A1和B没有公共子序列,并且所以还是0,   第二个代表BA和A1这个串有1个公共子序列
 * B   1   1   1   1   1
 * C   1   1   1   1   2        这里2的语义,各退一步,BA34和A1B的最长公共子序列已经计算出来了,是左上角的1,C和C又有一个公共子序列,所以由左上角的值+1,作为该dp数组该填的值
 * 2   1   1   1   1   2
 * 3   1   1   2   2   2
 *
 * [0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 1, 1, 1, 1]
 * [0, 1, 1, 1, 1, 1, 1]
 * [0, 1, 1, 1, 1, 1, 2]
 * [0, 1, 1, 1, 1, 1, 2]
 * [0, 1, 1, 1, 2, 2, 2]
 *
 * 代码思路:先初始化第一行第一列,作为基本条件,然后找出dp方程
 * 初始化第一行:第一个源串跟第二个源串的第一个字母,对比,如果之前的值有算出来1,后面的都是1,因为第一个源串的所有前缀,与第二个源串的第一个字母做比较
 * 初始化第一列:如果之前的值有算出来1,后面的都是1,同理:第二个源串的所有前缀,与第一个源串的第一个字母对比,因为只有1个字母,所以最长公共子序列为1,
 */
public class LCS {
    public static void main(String[] args) {
        String s1 = "BA34C23";
        String s2 = "A1BC23";
        System.out.println(dp(s1, s2));
        System.out.println(parseDp(dpp,s1,s2));
    }
    public static int[][] dpp;
    public static int dp(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        dpp = new int[len1 + 1][len2 + 1];
        int flag = 0;//标志
        for (int i = 1; i <= len1; i++) {//初始化第一列,如果之前的值有算出来1,后面的都是1,符合标志,就不用算了,
            if (flag == 1) {
                dpp[i][1] = 1;
            } else if (s1.charAt(i - 1) == s2.charAt(0)) {
                dpp[i][1] = 1;
                flag = 1;
            } else {
                dpp[i][1] = 0;
            }
        }
        flag = 0;
        for (int i = 1; i <= len2; i++) {//初始化第一行
            if (flag == 1) {
                dpp[1][i] = 1;
            } else if (s2.charAt(i - 1) == s1.charAt(0)) {
                dpp[1][i] = 1;
                flag = 1;
            } else {
                dpp[1][i] = 0;
            }
        }

        for (int i = 2; i <= len1; i++) {
            for (int j = 2; j <= len2; j++) {
                int temp = Math.max(dpp[i - 1][j], dpp[i][j - 1]);
                if (s1.charAt(i-1) == s2.charAt(j-1)) {//要把dp数组索引语义和源串索引语义联系起来,只能是-1
                    dpp[i][j] = Math.max(temp, dpp[i - 1][j - 1] + 1);
                } else {
                    dpp[i][j] = temp;
                }
            }
        }
        for (int[] ints : dpp) {
            System.out.println(Arrays.toString(ints));
        }
        return dpp[len1][len2];
    }

    /**
     * 解析dp表,把最长公共子序列解析出来
     *
     * @param dp 需要解析的dp表
     * @param s1 第一个源串
     * @param s2 第二个源串
     * @return 解析最长公共子序列的结果
     */
    public static String parseDp(int[][] dp, String s1, String s2) {
        int N = s2.length();//有点玄学
        int M = s1.length();
        StringBuilder sb = new StringBuilder();
        while (M > 0 && N > 0) {
            //如果这个位置算出来的值,比左和上大,那么当前的字符相等
            if (dp[M][N] > Math.max(dp[M - 1][N], dp[M][N - 1])) {
                sb.insert(0, s1.charAt(M - 1));
                M--;
                N--;
            } else {
                if (dp[M - 1][N] > dp[M][N - 1]) {
                    M--;
                } else {
                    N--;
                }
            }
        }
        return sb.toString();
    }

}
