package graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为正值。
 * 请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
 * 输出一个整数，表示1号点到n号点的最短距离。
 * 如果路径不存在，则输出-1。
 * 数据范围
 * 1≤n,m≤10^5,
 * 图中涉及边长均不超过10000。
 * 输入样例：
 * 3 3
 * 1 2 2
 * 2 3 1
 * 1 3 4
 * 输出样例：
 * 3
 */
public class dijkstra {
    static class node implements Comparable<node> {
        int to, dis;

        public node(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }

        @Override
        public int compareTo(node node) {
            return dis - node.dis;
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
        }
        Arrays.fill(dis, Integer.MAX_VALUE);
        dij();
    }

    private static void dij() {
        q.add(new node(1, 0));
        dis[1] = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            if (vis[p.to]) continue;
            vis[p.to] = true;
            for (int i = he[p.to]; i != 0; i = ne[i]) {
                int ed = e[i];
                if (dis[p.to] != Integer.MAX_VALUE && dis[ed] > p.dis + w[i]) {
                    dis[ed] = p.dis + w[i];
                    q.add(new node(ed, dis[ed]));
                }
            }
        }
        if (dis[n] != Integer.MAX_VALUE)
            System.out.println(dis[n]);
        else System.out.println("No");
    }

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, m, cnt = 1;
    static PriorityQueue<node> q = new PriorityQueue<node>();
    static int[] he = new int[100005];
    static int[] dis = new int[100005];
    static int[] ne = new int[100005];
    static int[] w = new int[100005];

    static int[] e = new int[100005];
    static boolean[] vis = new boolean[100005];

}
