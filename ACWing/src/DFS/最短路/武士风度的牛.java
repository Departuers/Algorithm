package DFS.最短路;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104675982
 * 马走日bfs寻最短路
 */
public class 武士风度的牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            g[i] = sc.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 'K') {
                    System.out.println(bfs(i, j));
                }
            }
        }

    }

    static int bfs(int x, int y) {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(x * m + y);
        int a, b;
        while (!q.isEmpty()) {
            int p = q.poll();
            x = p / m;
            y = p % m;
            g[x][y] = '*';
            for (int i = 0; i < 8; i++) {
                a = x + dir[i][0];
                b = y + dir[i][1];
                if (a < 0 || a >= n || b < 0 || b >= m || g[a][b] == '*') continue;
                if (g[a][b] == 'H') return dis[x][y] + 1;
                q.add(a * m + b);
                dis[a][b] = dis[x][y] + 1;
            }
        }
        return -1;
    }

    static int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    static char[][] g = new char[160][160];
    static int n, m;
    static int[][] dis = new int[200][200];
}
