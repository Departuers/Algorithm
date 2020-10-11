package dp.区间dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 破环为链
 */
public class 环形石子合并 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            a[i + n] = a[i];
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(f[i], inf);
            Arrays.fill(g[i], inf);
        }
        for (int i = 1; i <= n * 2; i++) {
            s[i] += s[i - 1] + a[i];
        }
        for (int len = 1; len <= n; len++) {//可以len为1
            for (int l = 1; l + len - 1 <= n * 2; l++) {
                int r = l + len - 1;
                if (len == 1) f[l][r] = g[l][r] = 0;
                else {
                    for (int i = l; i < r; i++) {
                        f[l][r] = Math.min(f[l][r], f[l][i] + f[i + 1][r] + s[r] - s[l - 1]);
                        g[l][r] = Math.max(g[l][r], g[l][i] + g[i + 1][r] + s[r] - s[l - 1]);
                    }
                }
            }
        }
        int minv = inf, maxv = inf;
        for (int i = 1; i <= n; i++) {
            minv = Math.min(f[i][i + n - 1], minv);
            maxv = Math.max(g[i][i + n - 1], maxv);
        }
        System.out.println(minv);
        System.out.println(maxv);
    }

    static int n, N = 410, inf = 0x3f3f3f3f;
    static int[] s = new int[N];
    static int[] a = new int[N];
    static int[][] f = new int[N][N];
    static int[][] g = new int[N][N];

}
