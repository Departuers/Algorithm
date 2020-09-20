package graph.Floyd;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/107301496
 * https://www.cnblogs.com/ctyakwf/p/12829458.html
 * 农民John的农场里有很多牧区，有的路径连接一些特定的牧区。
 * 一片所有连通的牧区称为一个牧场。
 * 但是就目前而言，你能看到至少有两个牧区不连通。
 * 现在，John想在农场里添加一条路径（注意，恰好一条）。
 * 一个牧场的直径就是牧场中最远的两个牧区的距离（本题中所提到的所有距离指的都是最短的距离）。
 * 考虑如下的两个牧场，每一个牧区都有自己的坐标：
 * 图 1 是有 5 个牧区的牧场，牧区用“*”表示，路径用直线表示。
 * 图 1 所示的牧场的直径大约是 12.07106, 最远的两个牧区是 A 和 E，
 * 它们之间的最短路径是 A-B-E。
 * 图 2 是另一个牧场。
 * 这两个牧场都在John的农场上。
 * John将会在两个牧场中各选一个牧区，然后用一条路径连起来，
 * 使得连通后这个新的更大的牧场有最小的直径。
 * 注意，如果两条路径中途相交，我们不认为它们是连通的。
 * 只有两条路径在同一个牧区相交，我们才认为它们是连通的。
 * 现在请你编程找出一条连接两个不同牧场的路径，使得连上这条路径后，所有牧场（生成的新牧场和原有牧场）中直径最大的牧场的直径尽可能小。
 * 输出这个直径最小可能值。
 * 输入格式
 * 第 1 行：一个整数 N, 表示牧区数；
 * 第 2 到 N+1 行：每行两个整数 X,Y， 表示 N 个牧区的坐标。每个牧区的坐标都是不一样的。
 * 第 N+2 行到第 2*N+1 行：每行包括 N 个数字 ( 0或1 ) 表示一个对称邻接矩阵。
 * 例如，题目描述中的两个牧场的矩阵描述如下：
 * A B C D E F G H
 * A 0 1 0 0 0 0 0 0
 * B 1 0 1 1 1 0 0 0
 * C 0 1 0 0 1 0 0 0
 * D 0 1 0 0 1 0 0 0
 * E 0 1 1 1 0 0 0 0
 * F 0 0 0 0 0 0 1 0
 * G 0 0 0 0 0 1 0 1
 * H 0 0 0 0 0 0 1 0
 * 输入数据中至少包括两个不连通的牧区。
 * 输出格式
 * 只有一行，包括一个实数，表示所求答案。
 * 数字保留六位小数。
 * 数据范围
 * 1≤N≤150,
 * 0≤X,Y≤10^5
 * 输入样例：
 * 8
 * 10 10
 * 15 10
 * 20 10
 * 15 15
 * 20 15
 * 30 15
 * 25 10
 * 30 10
 * 01000000
 * 10111000
 * 01001000
 * 01001000
 * 01110000
 * 00000010
 * 00000101
 * 00000010
 * 输出样例：
 * 22.071068
 */
public class 牛的旅行 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b;
        for (int i = 0; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            q[i] = new node(a, b);
        }
        for (int i = 0; i < n; i++) {
            g[i] = sc.next().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (g[i][j] == '1') d[i][j] = get_dist(q[i], q[j]);
                    else
                        d[i][j] = inf;
                }
            }
        }

        //floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (d[i][j] < inf) {
                    maxd[i] = Math.max(maxd[i], d[i][j]);
                }
            }
        }

        double res1 = 0;
        for (int i = 0; i < n; i++) {
            res1 = Math.max(res1, maxd[i]);
        }

        double res2 = inf;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (d[i][j] >= inf) {
                    res2 = Math.min(res2, get_dist(q[i], q[j]) + maxd[i] + maxd[j]);
                }
            }
        }
        System.out.printf("%.6f", Math.max(res1, res2));
    }

    static double get_dist(node a, node b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static int n, N = 150;
    static double inf = 1e20;
    static node[] q = new node[N];
    static char[][] g = new char[N][N];

    static double[][] d = new double[N][N];//邻接矩阵

    static double[] maxd = new double[N];

    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
