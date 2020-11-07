package graph.二分图;

import java.util.Scanner;

/**
 * https://www.acwing.com/problem/content/380/
 * 最大独立集
 */
public class 骑士放置 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        int x, y;
        for (int i = 0; i < k; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            g[x][y] = true;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((i + j) % 2 != 0 || g[i][j]) continue;
                if (find(i, j, i * 10 + j)) res++;
            }
        }
        System.out.println(n * m - k - res);
    }

    static int n, m, k, N = 111;
    static node[][] match = new node[N][N];
    static int[][] st = new int[N][N];
    static boolean[][] g = new boolean[N][N];
    static int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    static boolean find(int x, int y, int tag) {
        if (st[x][y] == tag) return false;
        st[x][y] = tag;
        for (int i = 0; i < 8; i++) {
            int a = x + dir[i][0], b = y + dir[i][1];
            if (a < 1 || a > n || b < 1 || b > n) continue;
            if (g[a][b] || st[a][b] != 0) continue;
            node t = match[a][b];
            if (t == null || find(t.x, t.y, tag)) {
                match[a][b] = new node(x, y);
                return true;
            }
        }
        return false;
    }

    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
