package DFS.floodfill;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104672658
 * 给定一个 n×n 的二维数组，如下所示：
 * int maze[5][5] = {
 * 0, 1, 0, 0, 0,
 * 0, 1, 0, 1, 0,
 * 0, 0, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 1, 0,
 * };
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的最短路线。
 * 数据保证至少存在一条从左上角走到右下角的路径。
 * 输入格式
 * 第一行包含整数 n。
 * 接下来 n 行，每行包含 n 个整数 0 或 1，表示迷宫。
 * 输出格式
 * 输出从左上角到右下角的最短路线，如果答案不唯一，输出任意一条路径均可。
 * 按顺序，每行输出一个路径中经过的单元格的坐标，左上角坐标为 (0,0)，右下角坐标为 (n−1,n−1)。
 * 数据范围
 * 0≤n≤1000
 * 输入样例：
 * 5
 * 0 1 0 0 0
 * 0 1 0 1 0
 * 0 0 0 0 0
 * 0 1 1 1 0
 * 0 0 0 1 0
 * 输出样例：
 * 0 0
 * 1 0
 * 2 0
 * 2 1
 * 2 2
 * 2 3
 * 2 4
 * 3 4
 * 4 4
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
