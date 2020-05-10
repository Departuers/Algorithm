package DFS.floodfill;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104670312
 * 显然寻找有多少个四联通块
 * 并找出最大连通块有多少个
 */
public class 城堡问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        int cnt = 0, area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    area = Math.max(bfs(i, j), area);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(area);
    }

    private static int bfs(int x, int y) {
        int area = 0;
        q.clear();
        q.add(x * m + y);
        while (!q.isEmpty()) {
            int p = q.poll();
            x = p / m;
            y = p % m;
            area++;
            vis[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int a = x + dir[i][0], b = y + dir[i][1];
                if (a < 0 || a >= n || b < 0 || b >= m || vis[a][b]) continue;
                if ((g[x][y] >> i & 1) == 1) continue;
                q.add(a * m + b);
            }
        }
        return area;
    }

    static void dfs(int x, int y) {
        ans++;
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int a = x + dir[i][0], b = y + dir[i][1];
            if (a < 0 || a >= n || b < 0 || b >= m || vis[a][b]) continue;
            if ((g[x][y] >> i & 1) == 1) continue;
            dfs(a, b);
        }
    }

    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int[][] g = new int[51][51];
    static boolean[][] vis = new boolean[51][51];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, ans;
}
