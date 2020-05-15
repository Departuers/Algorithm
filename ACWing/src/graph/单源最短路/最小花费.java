package graph.单源最短路;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/LTH060226/article/details/86535023
 * n个人,某些人的银行账号可以相互转账
 * 这些人的转账手续费各不相同
 * 给定这些人转账时需要从转账金额里扣除百分之几的手续费,请问A最少需要多少钱使得转账后B收到100元。
 * 输出A使得B到账100元最少需要的总费用。精确到小数点后8位。
 * Sample Input
 * 3 3
 * 1 2 1
 * 2 3 2
 * 1 3 3
 * 1 3
 * Sample Output
 * 103.07153164
 */
public class 最小花费 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, 1.0 - c / 100.0);
            add(b, a, 1.0 - c / 100.0);
        }
        s = sc.nextInt();
        end = sc.nextInt();
        System.out.printf("%.8f", 100.0 / dijkstra(s));
    }

    static class node implements Comparable<node> {
        double x;
        int y;

        public node(double x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return (int) (x * 1000 - node.x * 10000);
        }
    }

    static double dijkstra(int s) {
        PriorityQueue<node> q = new PriorityQueue<node>();
        q.add(new node(1, s));
        dis[s] = 1;
        while (!q.isEmpty()) {
            node p = q.poll();
            int t = p.y;
            if (vis[t]) continue;
            vis[t] = true;
            for (int i = he[t]; i != 0; i = ne[i]) {
                if (dis[e[i]] < dis[t] * w[i]) {
                    dis[e[i]] = dis[t] * w[i];
                    q.add(new node(w[i], e[i]));
                }
            }
        }
        return dis[end];
    }

    static void add(int a, int b, double c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int[] he = new int[2550];
    static int[] ne = new int[6550 * 2];
    static int[] e = new int[6550 * 2];
    static double[] w = new double[6550 * 2];
    static boolean[] vis = new boolean[2560];
    static double[] dis = new double[2560];
    static int n, m, cnt = 1, s, end;
}
