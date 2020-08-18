package graph.最小生成树拓展;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_41661919/article/details/86565228
 * https://blog.csdn.net/qq_44828887/article/details/107305636
 * 给定一张 N 个点 M 条边的无向图，求无向图的严格次小生成树。
 * 设最小生成树的边权之和为sum，严格次小生成树就是指边权之和大于sum的生成树中最小的一个。
 * 输入格式
 * 第一行包含两个整数N和M。
 * 接下来M行，每行包含三个整数x，y，z，表示点x和点y之前存在一条边，边的权值为z。
 * 输出格式
 * 包含一行，仅一个数，表示严格次小生成树的边权和。(数据保证必定存在严格次小生成树)
 * 数据范围
 * N≤105,M≤3∗105
 * 思路
 * lca次小生成树。倍增找树上路径最大边即可。
 */
public class 牛奶运输 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, w;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w = sc.nextInt();
            edge.add(new node(a, b, w));
        }
        Collections.sort(edge);
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        long sum = 0;
        for (int i = 0; i < m; i++) {
            a = find(edge.get(i).a);
            b = find(edge.get(i).b);
            w = edge.get(i).w;
            if (a != b) {
                p[a] = b;
                sum += w;
                add(a, b, w);
                add(b, a, w);
                edge.get(i).isShu = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            dfs(i, -1, 0, dis[i]);
        }
        long res = (long) 1e18;
        for (int i = 0; i < m; i++) {
            if (!edge.get(i).isShu) {
                a = edge.get(i).a;
                b = edge.get(i).b;
                w = edge.get(i).w;
                if (w > dis[a][b]) {
                    res = Math.min(res, sum + w - dis[a][b]);
                }
            }
        }
        System.out.println(res);
    }

    static void dfs(int u, int fa, int maxd, int[] d) {
        d[u] = maxd;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                dfs(j, u, Math.max(maxd, w[i]), d);
            }
        }
    }

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
    }

    static int find(int a) {
        if (p[a] != a) return p[a] = find(p[a]);
        return a;
    }

    static class node implements Comparable<node> {
        int a, b, w;
        boolean isShu;

        public node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(node node) {
            return w - node.w;
        }
    }

    static int n, m, N = 510, cnt = 1, M = 10010;
    static ArrayList<node> edge = new ArrayList<node>();
    static int[] p = new int[N];//并查集
    static int[][] dis = new int[N][N];
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[M];
}
