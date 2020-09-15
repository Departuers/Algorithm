package DFS.floodfill;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104672658
 * 不能使用数字代表方阵坐标,
 * 只能矩阵坐标
 * 显然bfs寻路问题,记录路径,从终点往起点走
 */
public class 迷宫问题 {
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
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        ArrayDeque<node> q = new ArrayDeque<node>();
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        q.add(new node(n - 1, n - 1));//终点开始搜
        int x = 0, y = 0, a, b;
        while (!q.isEmpty()) {
            node p = q.poll();
            vis[p.x][p.y] = true;
            if (p.x == 0 && p.y == 0) break;//出队的时候判断,搜到终点
            for (int i = 0; i < 4; i++) {
                a = dir[i][0] + p.x;
                b = dir[i][1] + p.y;
                if (a < 0 || a >= n || b < 0 || b >= n || vis[a][b] || g[a][b] == 1) continue;
                //可以使用pre充当vis,但是没必要
                q.add(new node(a, b));
                pre[a][b] = p;
            }
        }
        node p = pre[0][0];//搜到起点
        while (true) {
            System.out.println(p.x + " " + p.y);
            if (p.x == n - 1 && p.y == n - 1) break;
            p = pre[p.x][p.y];//等于它上一个节点
        }
    }

    static int n;
    static boolean[][] vis = new boolean[1010][1010];
    static node[][] pre = new node[1010][1010];
    static int[][] g = new int[1010][1010];
}
