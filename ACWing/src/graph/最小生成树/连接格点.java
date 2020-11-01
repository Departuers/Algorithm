package graph.最小生成树;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/107900698
 * 有一个 m 行 n 列的点阵，相邻两点可以相连。
 * 一条纵向的连线花费一个单位，一条横向的连线花费两个单位。
 * 某些点之间已经有连线了，试问至少还需要花费多少个单位才能使所有的点全部连通。
 * 输入格式
 * 第一行输入两个正整数 m 和 n。
 * 以下若干行每行四个正整数 x1,y1,x2,y2，表示第 x1 行第 y1 列的点和第 x2 行第 y2 列的点已经有连线。
 * 输入保证|x1−x2|+|y1−y2|=1。
 * 输出格式
 * 输出使得连通所有点还需要的最小花费。
 * 数据范围
 * 1≤m,n≤1000
 * 0≤已经存在的连线数≤10000
 * 输入样例：
 * 2 2
 * 1 1 2 1
 * 输出样例：
 * 3
 * 分析：
 * 本题与上一题很类似，图中都是有两类边，处理方法也很类似，考察的其实是想象能力。图中的点是m * n的点阵，
 * 连接纵向的两点花费1个单位，连接横向的两点消耗2个单位。
 * 我们其实并不需要显式的用一个结构体数组或者邻接矩阵去存储这个图，因为点阵是极具规律性的，
 * 自左而右，自上而下点m * n个点依次编号为1到m * n。在读取已有的连线时，获取这两个点的编号，
 * 连接这两点即可。后面要考虑的是如何连接剩下的点？
 * 既然连接纵向边的消耗小，我们自然是先枚举纵向边，再枚举横向边，按顺序尝试加入到并查集中。
 * <p>
 * 如何枚举纵向边，点阵中的纵向边是点i和点i + n的连线，
 * 其中i从1到(m - 1) * n。横向边则是i和i + 1的连线，当遍历到编号为n的倍数的点时跳过即可。具体实现见代码：
 * <p>
 * 依然Kruskal
 * 先选一些,再用Kruskal
 * 把二维坐标转化为一维坐标
 */
public class 连接格点 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i <= n * m + 10; i++) {
            par[i] = i;
        }
        int a, b, c, d;
        while (sc.hasNext()) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            d = sc.nextInt();
            int e = (a - 1) * n + b, f = (c - 1) * n + d;
            union(e, f);
        }
        int c1 = n * m - n;//表示倒数第二行最后一列
        int res = 0;
        for (int i = 1; i <= c1; i++) {
            a = find(i);
            b = find(i + n);//i和i+n在方格编号,表示一条纵向边
            if (a != b) {
                par[a] = b;
                res += 1;
            }
        }
        int c2 = n * m;//最后一行最后一列
        for (int i = 1; i <= c2; i++) {
            if (i % n == 0) continue;//最后一列无法连接横向边
            a = find(i);
            b = find(i + 1);
            if (a != b) {
                par[a] = b;
                res += 2;
            }
        }
        System.out.println(res);
    }

    static void nor() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i <= n * m + 10; i++) {
            par[i] = i;
        }
        for (int i = 1, t = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                g[i][j] = t++;
            }
        }//g[x][y]表示每个格子的编号
        int a, b, c, d;
        while (sc.hasNext()) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            d = sc.nextInt();
            union(g[a][b], g[c][d]);
        }

        int res = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        //枚举横向边,边权为1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int u = 0; u < 4; u++) {
                    a = dx[u] + i;
                    b = dy[u] + j;
                    if (a < 1 || a > n || b < 1 || b > m || u % 2 == 1) continue;
                    if (!is(g[a][b], g[i][j])) {
                        res++;
                        union(g[a][b], g[i][j]);
                    }
                }
            }
        }
        //枚举纵向边,边权为2,满足Kruskal
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int u = 0; u < 4; u++) {
                    a = dx[u] + i;
                    b = dy[u] + j;
                    if (a < 1 || a > n || b < 1 || b > m || u % 2 == 0) continue;
                    if (!is(g[a][b], g[i][j])) {
                        res += 2;
                        union(g[a][b], g[i][j]);
                    }
                }
            }
        }
        System.out.println(res);
    }

    static int N = 1000;
    static int n, m, k;
    static int[][] g = new int[N][N];
    static int[] par = new int[1000000];

    static boolean is(int x, int y) {
        return find(x) == find(y);
    }

    static int find(int x) {
        if (x == par[x]) return x;
        return par[x] = find(par[x]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot != yRoot) par[xRoot] = yRoot;
    }
}
