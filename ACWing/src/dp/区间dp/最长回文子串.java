package dp.区间dp;

import java.util.Scanner;

/**
 * 最长回文子串
 * 给定一个字符串，请你求出其中的最长回文子串的长度。
 * 例如，给定字符串 Is PAT&TAP symmetric?，最长回文子串为 s PAT&TAP s，其长度是 11。
 * 输入格式
 * 包含一个非空字符串。
 * 输出格式
 * 输出一个整数，表示给定字符串的最长回文子串的长度。
 * 数据范围
 * 给定字符串的长度不超过 1000。
 * 输入样例：
 * Is PAT&TAP symmetric?
 * 输出样例：
 * 11
 * 包含空格
 * 使用区间dp
 * f[i,j]表示字符串第i到j个字母组成的串是不是回文串
 * 如果s[i,j]是回文串为1,反之为0
 * 状态划分:s[i]是否等于s[j],即第i个是否等于第j个
 * 1. s[i]==s[j] 那么 f[i,j]=f[i+1,j-1]
 * 2. 如果s[i]!=s[j] 那么f[i,j]=0;
 * 根据从边界出发的原理, 注意到边界都是长度为1或2的子串,
 * 每次转移都对子串的长度-1, dp[i][j] = dp[i+1][j-1]
 * 不妨考虑按子串的长度和子串的初始位置进行枚举
 * 第一遍先枚举长度为3的子串的dp值, 然后第二遍枚举长度为4的子串的dp值
 * O(n^2)时空复杂度
 */
public class 最长回文子串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        boolean[][] f = new boolean[10090][10009];
        int length = s.length(), ans = 1;//起码为1
        for (int i = 0; i < length; i++) {
            f[i][i] = true;//处理长度为1的回文串
            if (i < length - 1) {
                f[i][i + 1] = true;
                ans = 2;//处理长度为2的回文串
            }
        }

        for (int len = 3; len <= length; len++) {//从长度3开始处理回文串
            for (int l = 0; l + len - 1 < length; l++) {
                int r = l + len - 1;
                if (s.charAt(l) == s.charAt(r) && f[l + 1][r - 1]) {
                    f[l][r] = true;
                    ans = l;
                }
            }
        }
        System.out.println(ans);
    }
}