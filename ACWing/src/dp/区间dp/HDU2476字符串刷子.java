package dp.区间dp;

import java.util.Scanner;

/**
 * f[i,j]表示从A到B的最小步数,不方便转移
 * 用g[i,j]表示i~j从空串变成B的最小步数,
 * 然后确定哪些部分保留原来的A,哪些部分重新刷
 * 考虑s[i]==s[j] s[i]=s[i+1] s[j]=s[j-1],分2种情况
 * 在g[i,j]=min{ g[i,j],g[i+1,j] }  染i~j或者i+1~j的时候顺便染
 * 否则g[i,j]=min{ g[i,j],g[i+1,j]+1}
 */
public class HDU2476字符串刷子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= n; i++) {
            g[i][i] = 1;
        }
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                g[l][r] = (int) 1e9;
                if (s[l] != s[l + 1] && s[l] != s[r]) {
                    g[l][r] = Math.min(g[l][r], g[l + 1][r] + 1);
                } else {
                    g[l][r] = Math.min(g[l][r], g[l + 1][r]);
                }
                if (s[r - 1] != s[r] && s[r] != s[l]) {
                    g[l][r] = Math.min(g[l][r], g[l][r - 1] + 1);
                } else g[l][r] = Math.min(g[l][r], g[l][r - 1]);
                for (int k = l; k < r; k++) {
                    g[l][r] = Math.min(g[l][k] + g[k + 1][r], g[l][r]);
                }
            }
        }
    }

    static char[] s;
    static int n;
    static int[][] g = new int[110][110];
    static int[][] f = new int[110][110];

}