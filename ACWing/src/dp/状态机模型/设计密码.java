package dp.状态机模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acwing.com/solution/content/16353/
 * https://blog.csdn.net/qq_30277239/article/details/104189713
 */
public class 设计密码 {
    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        f[0][0] = 1;
        n = sc.nextInt();
        p = sc.next().toCharArray();
        m = p.length;
        f();
        int t = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (char k = 'a'; k <= 'z'; k++) {
                    t = j;
                    while (t != -1 && p[t] != k) t = ne[t];
                    t++;
                    if (t < m) f[i + 1][t] = (f[i + 1][t] + f[i][j])%mod;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = (res+f[n][i])%mod;
        }
        System.out.println(res);
    }

    static int[] ne = new int[55];
    static int n, m;
    static char[] p;
    static int[][] f = new int[66][66];

    static void f() {
        ne[0] = -1;
        int t = 0;
        for (int i = 1; i <= m; i++) {
            t = ne[i - 1];
            while (t != -1 && p[i - 1] != p[t]) t = ne[t];
            ne[i] = t + 1;
        }
    }
}
