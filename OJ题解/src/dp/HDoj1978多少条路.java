package dp;

import java.util.Scanner;

/**
 * http://acm.hdu.edu.cn/showproblem.php?pid=1978
 */
public class HDoj1978多少条路 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        while (t-- != 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    a[i][j] = sc.nextInt();
                    dp[i][j] = -1;
                }
            }
            System.out.println(dfs(1, 1));
        }
    }

    static int dfs(int x, int y) {
        int t = 0;
        if (dp[x][y] >= 0) return dp[x][y];
        if (x == n && y == m) return 1;

        for (int i = 0; i <= a[x][y]; i++) {
            for (int j = 0; j <= a[x][y] - i; j++) {
                if (inarea(x + i, y + j) && i + j != 0) {
                    t += dfs(x + i, y + j);
                    t %= mod;
                }
            }
        }
        return dp[x][y] = t;
    }

    private static boolean inarea(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }

    static int[][] dp = new int[110][110];
    static int[][] a = new int[110][110];
    static int t, n, m, mod = 10000;

}
