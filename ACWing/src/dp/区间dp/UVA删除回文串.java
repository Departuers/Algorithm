package dp.区间dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个字符串，问有几种删字符的方案使得它变为回文串。
 * 可以删掉B、BA、 AB、两个A、不删。5种
 * 字符串长度≤5000
 * 实际上统计该串有多少个回文子序列
 * f[i,j]表示Si~Sj范围内的回文串个数
 * 转移考虑last
 * S[i]==S[j]那么要统计f[i+1][j]和f[i][j-1]
 * 虽然会把f[i+1,j-1]统计两次,
 * 但是f[i+1,j-1]中的回文串可以加上S[i]和S[j]再形成f[i+1][j-1]个回文串
 * f[i,j]=f[i+1,j]+f[i,j-1]+1
 */
public class UVA删除回文串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.next().toCharArray();
        n = s.length;
        for (int i = 0; i < f.length; i++) {
            Arrays.fill(f[i], -1);
        }
        System.out.println(dp(0, n - 1));
        System.out.println(f[0][n - 1]);
    }

    static int dp(int l, int r) {
        if (l == r) return 1;
        if (l > r) return 0;
        if (f[l][r] != -1) return f[l][r];
        if (s[l] == s[r]) return f[l][r] = dp(l, r - 1) + dp(l + 1, r) + 1;
        else return f[l][r] = dp(l, r - 1) + dp(l + 1, r) - dp(l + 1, r - 1);
    }

    static int n;
    static char[] s;
    static int[][] f = new int[5100][5010];
}
