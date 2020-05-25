package 递归;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.cnblogs.com/jiamian/p/12173894.html
 * 搜索两个人能到的位置
 * 1 9
 * w....#.g.
 */
public class 蒜头君救公主 {
    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            g[i] = sc.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 'w') {
                    bfs(i, j);
                }
            }
        }
        System.out.println(Arrays.toString(vis[0]));
        System.out.println(Arrays.toString(g[0]));
        boolean f = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 'g') {
                    if (dfs(i, j)) {
                        System.out.println("yes");
                        f = true;
                    }
                }
            }
        }
        if (!f) {
            System.out.println("NO");
        }
    }

    static void bfs(int x, int y) {
        ArrayDeque<node> q = new ArrayDeque<node>();
        q.add(new node(x, y));
        node p;
        while (!q.isEmpty()) {
            p = q.poll();
            x = p.x;
            y = p.y;
            vis[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + d[i][0], ny = y + d[i][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny] && g[nx][ny] != '#') {
                    q.add(new node(nx, ny));
                }
            }
        }
    }

    static boolean dfs(int x, int y) {
        if (vis[x][y]) return true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && g[nx][ny] != '#')
                if (dfs(nx, ny)) return true;
        }
        return false;
    }

    static int[][] d = {{0, 2}, {2, 0}, {-2, 0}, {0, -2}};
    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static boolean[][] vis = new boolean[110][110];
    static int n, m;
    static char[][] g = new char[110][110];
}
