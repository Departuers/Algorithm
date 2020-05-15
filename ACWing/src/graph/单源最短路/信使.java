package graph.单源最短路;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/chrisblogtk/article/details/51099957?utm_source=blogxgwz7
 * 对于每个点的时间等于他到指挥部的最短距离
 * 1号点是指挥部
 * 如果有点无法到达输出-1
 * 然后最短距离的最大值为最大
 * 4 4
 * 1 2 4
 * 2 3 7
 * 2 4 1
 * 3 4 6
 * out:
 * 11
 */
public class 信使 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < 110; i++) {
            Arrays.fill(g[i], Integer.MAX_VALUE / 3);
        }
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            g[a][b] = g[b][a] = Math.min(g[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (g[1][i] == Integer.MAX_VALUE / 2) {
                res = -1;
                break;
            } else res = Math.max(res, g[1][i]);
        }
        System.out.println(res);
    }

    static int[][] g = new int[110][110];
    static int n, m;
}
