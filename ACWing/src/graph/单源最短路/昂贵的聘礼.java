package graph.单源最短路;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/a1097304791/article/details/88971315
 * POJ - 1062
 * 每一种购买方式
 * 都可以对应虚拟源点到到达终点的一条路径
 * 寻找最短路
 * 等级问题的话,枚举等级区间
 */
public class 昂贵的聘礼 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        for (int i = 0; i < w.length; i++) {
            Arrays.fill(w[i], Integer.MAX_VALUE / 2);
            w[i][i] = 0;
        }
        int price, cnt;
        for (int i = 1; i <= n; i++) {
            price = sc.nextInt();
            level[i] = sc.nextInt();
            cnt = sc.nextInt();
            w[0][i] = Math.min(price, w[0][i]);
            while (cnt-- != 0) {
                int id, cost;
                id = sc.nextInt();
                cost = sc.nextInt();
                w[id][i] = Math.min(w[id][i], cost);
            }
        }
        int res = Integer.MAX_VALUE / 2;
        for (int i = level[1] - m; i <=  level[1]; i++) {
            res = Math.min(res, dijkstra(i, i + m));
        }
        System.out.println(res);
    }

    static int dijkstra(int down, int up) {
        Arrays.fill(dist, 0x3f3f3f3f);
        Arrays.fill(vis, false);
        dist[0] = 0;
        for (int i = 1; i <= n; i++) {
            int t = -1;
            for (int j = 0; j <= n; j++) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) t = j;
            }
            vis[t] = true;
            for (int j = 1; j <= n; j++) {
                if (level[j] >= down && level[j] <= up) {
                    dist[j] = Math.min(dist[j], dist[t] + w[t][j]);
                }
            }
        }
        return dist[1];
    }

    static int[][] w = new int[110][110];
    static int[] level = new int[110];
    static int[] dist = new int[110];
    static boolean[] vis = new boolean[110];
    static int n, m;
}
