package graph.二分图;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acwing.com/problem/content/374/
 * 给定一个N行N列的棋盘，已知某些格子禁止放置。
 * 求最多能往棋盘上放多少块的长度为2、宽度为1的骨牌，骨牌的边界与格线重合（骨牌占用两个格子），并且任意两张骨牌都不重叠。
 * 输入格式
 * 第一行包含两个整数N和t，其中t为禁止放置的格子的数量。
 * 接下来t行每行包含两个整数x和y，表示位于第x行第y列的格子禁止放置，行列数从1开始。
 * 输出格式
 * 输出一个整数，表示结果。
 * 数据范围
 * 1≤N≤100
 * 输出样例：
 * 8 0
 * 输出样例：
 * 32
 * 考虑状态压缩,数据范围,2^100一定超时
 * 把所有能放卡片的相邻格子看成一条边
 * 把格子看成一个点,
 * 最多取出来多少条边,使得所有选出的边没有公共点,
 * 要看是不是二分图,是二分图才能用匈牙利算法
 * 就是一个二分图问题,问题转化为最大匹配
 * 考虑把所有的格子进行奇偶染色,所以一定是二分图
 * n*m个格子染色,给每个格子标上下标,1234..从1开始
 * 每个白色格子的横纵坐标之和都是偶数,
 * 每个黑色格子的横纵坐标都是奇数,
 * 我们可以横着放,竖着放,所以所有的边都是二分图的边
 * 所以是二分图
 */
public class 棋盘覆盖 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int x, y;
        while (m-- > 0) {
            x = sc.nextInt();
            y = sc.nextInt();
            g[x][y] = true;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if ((i + j) % 2 != 0 && !g[i][j]) {
//                    for (int k = 0; k < N; k++) {
//                        Arrays.fill(st[k], false);
//                    }
                    if (find(i, j, i *10+ j )) res++;
                }
            }
        }
        System.out.println(res);
    }

    static int n, m, N = 110;
    static boolean[][] g = new boolean[N][N];//, st = new boolean[N][N];
    static int[][] vis = new int[N][N];
    static node[][] match = new node[N][N];
    static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
//
//    static boolean dfs(int x, int y) {
//        for (int i = 0; i < 4; i++) {
//            int a = x + dir[i][0], b = y + dir[i][1];
//            if (a < 1 || a > n || b < 1 || b > n) continue;
//            if (st[a][b] || g[a][b]) continue;
//            st[a][b] = true;
//            node t = match[a][b];
//            if (t == null || dfs(t.x, t.y)) {
//                match[a][b] = new node(x, y);
//                return true;
//            }
//        }
//        return false;
//    }

    static boolean find(int x, int y, int tag) {
        if (vis[x][y] == tag) return false;
        vis[x][y] = tag;
        for (int i = 0; i < 4; i++) {
            int a = x + dir[i][0], b = y + dir[i][1];
            if (a < 1 || a > n || b < 1 || b > n) continue;
            if (vis[a][b] != 0 || g[a][b]) continue;
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
