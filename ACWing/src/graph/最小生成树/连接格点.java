package graph.最小生成树;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/103091446
 * 依然Kruskal
 * 先选一些,再用Kruskal
 * 把二维坐标转化为一维坐标
 */
public class 连接格点 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i <= n * m + 10; i++) {
            par[i] = i;
        }
        int a, b, c, d;
        for (int i = 1, t = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                g[i][j] = t++;
            }
        }
        while (sc.hasNext()) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            d = sc.nextInt();
            union(g[a][b], g[c][d]);
        }
        int res = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] dw = {1, 2, 1, 2};
        //枚举横向边,边权为1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int u = 0; u < 4; u++) {
                    a = dx[u] + i;
                    b = dy[u] + j;
                    if (a < 1 || a > n || b < 1 || b > m || u % 2 == 1) continue;
                    if (!is(g[a][b], g[i][j])) {
                        res++;
                        union(g[a][b], g[i][j]);
                    }
                }
            }
        }
        //枚举纵向边,边权为2,满足Kruskal
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int u = 0; u < 4; u++) {
                    a = dx[u] + i;
                    b = dy[u] + j;
                    if (a < 1 || a > n || b < 1 || b > m || u % 2 == 0) continue;
                    if (!is(g[a][b], g[i][j])) {
                        res += 2;
                        union(g[a][b], g[i][j]);
                    }
                }
            }
        }
        System.out.println(res);
    }

//    private static void getedge() {
//        int[] dx = {-1, 0, 1, 0};
//        int[] dy = {0, 1, 0, -1};
//        int[] dw = {1, 2, 1, 2};
//        for (int z = 0; z < 2; z++) {
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= m; j++) {
//                    for (int u = 0; u < 4; u++) {
//                        if (u % 2 == z) {
//                            int x = i + dx[u], y = j + dy[u], w = dw[u];
//                            if (x != 0 && x <= n && y != 0 && y <= m) {
//                                int a = g[i][j], b = g[x][y];
//                                if (a < b) edge[k++] = new node(a, b, w);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    static class node {
        int x, y, w;

        public node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    static int N = 1000;

    static node[] edge = new node[N];
    static int n, m, k;
    static int[][] g = new int[N][N];
    static int[] par = new int[1000000];

    static boolean is(int x, int y) {
        return find(x) == find(y);
    }

    static int find(int x) {
        if (x == par[x]) return x;
        return par[x] = find(par[x]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot != yRoot) par[xRoot] = yRoot;
    }
}
