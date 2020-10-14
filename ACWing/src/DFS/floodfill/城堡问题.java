package DFS.floodfill;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104670312
 * 请你编写一个程序，计算城堡一共有多少房间，最大的房间有多大。
 * 城堡被分割成 m∗n个方格区域，每个方格区域可以有0~4面墙。
 * 输入格式
 * 第一行包含两个整数 m 和 n，分别表示城堡南北方向的长度和东西方向的长度。
 * 接下来 m 行，每行包含 n 个整数，每个整数都表示平面图对应位置的方块的墙的特征。
 * 每个方块中墙的特征由数字 P 来描述，我们用1表示西墙，2表示北墙，4表示东墙，8表示南墙，P 为该方块包含墙的数字之和。
 * 例如，如果一个方块的 P 为3，则 3 = 1 + 2，该方块包含西墙和北墙。
 * 城堡的内墙被计算两次，方块(1,1)的南墙同时也是方块(2,1)的北墙。
 * 输入的数据保证城堡至少有两个房间。
 * 输出格式
 * 共两行，第一行输出房间总数，第二行输出最大房间的面积（方块数）。
 * 数据范围
 * 1≤m,n≤50,
 * 0≤P≤15
 * 输入样例：
 * 4 7
 * 11 6 11 6 3 10 6
 * 7 9 6 13 5 15 5
 * 1 10 12 7 13 7 5
 * 13 11 10 8 10 12 13
 * 输出样例：
 * 5
 * 9
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
        int s = 0, area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    ans = 0;
                    dfs(i, j);
                    area = Math.max(ans, area);
                    s++;
                }
            }
        }
        System.out.println(s);
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
            if ((g[x][y] >> i & 1) == 1) continue;//有墙
            dfs(a, b);
        }
    }

    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int[][] g = new int[51][51];
    static boolean[][] vis = new boolean[51][51];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, ans;
}
