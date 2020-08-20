package graph;

import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，边权可能为负数。
 * 再给定k个询问，每个询问包含两个整数x和y，表示查询从点x到点y的最短距离，如果路径不存在，则输出“impossible”。
 * 数据保证图中不存在负权回路。
 * 输入格式
 * 第一行包含三个整数n，m，k
 * 接下来m行，每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
 * 接下来k行，每行包含两个整数x，y，表示询问点x到点y的最短距离。
 * 输出格式
 * 共k行，每行输出一个整数，表示询问的结果，若询问两点间不存在路径，则输出“impossible”。
 * 数据范围
 * 1≤n≤200,
 * 1≤k≤n^2
 * 1≤m≤20000,
 * 图中涉及边长绝对值均不超过10000。
 * 输入样例：
 * 3 3 2
 * 1 2 1
 * 2 3 2
 * 1 3 1
 * 2 1
 * 1 3
 * 输出样例：
 * impossible
 * 1
 * 多源最短路问题，采用Floyd算法，时间复杂度为O(n^3)。
 * Floyd算法的思想是动态规划。设d[i][j]表示i点到j点的最短距离，
 * i点到j点中间经过某点k，则状态转移方程为d[i][j] = min(d[i][k] + d[k][j],d[i][j])（1<=k<=n);
 * 然后用三重循环实现该状态扩散的过程即可。注意对中间节点k的枚举需要放在循环的最外层，否则会出错。
 * 使用邻接矩阵存图
 * 边长最多10000最多20000边,则最长路径就是2*10^8
 * 初始化为int/2即可
 * 即使全是负权边,int/2-2*10^8,无穷-常数还是无穷
 * 如何判别无穷呢,显然 int/4>2*10^8
 */
public class floyd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        int x, y, z, t = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) g[i][j] = 0;
                else g[i][j] = t;
            }
        }
        for (int i = 0; i < m; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            g[x][y] = Math.min(g[x][y], z);
        }

        while (k-- != 0) {
            x = sc.nextInt();
            y = sc.nextInt();
            if (g[x][y] > t / 2) System.out.println("impossible");
            else System.out.println(g[x][y]);
        }
    }

    static void floyd(int[][] g) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    g[i][j] = Math.min(g[i][k] + g[k][j], g[i][j]);
                }
            }
        }
    }

    static int n = 5, m, k;

    static int[][] g = new int[210][210];
}
