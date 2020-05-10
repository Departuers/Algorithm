package DFS.最短路;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1
 * 5 4 0 0
 */
public class 马走日 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        while (t-- != 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            ans = 0;
            for (int i = 0; i < vis.length; i++) {
                Arrays.fill(vis[i], false);
            }
            vis[x][y] = true;
            dfs(x, y, 1);
            System.out.println(ans);
        }
    }

    static void dfs(int x, int y, int cnt) {
        if (cnt == n * m) {
            ans++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            int a = x + dir[i][0], b = y + dir[i][1];
            if (a < 0 || a >= n || b < 0 || b >= m || vis[a][b]) continue;
            vis[a][b] = true;
            dfs(a, b, cnt + 1);
            vis[a][b] = false;
        }
    }

    static int t, n, m, x, y, ans;
    static boolean[][] vis = new boolean[10][10];
    static int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

}
