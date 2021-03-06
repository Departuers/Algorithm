package LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://blog.csdn.net/qq_41661919/article/details/86565228
 * https://blog.csdn.net/qq_44828887/article/details/107305636
 * 给定一张 N 个点 M 条边的无向图，求无向图的严格次小生成树。
 * 设最小生成树的边权之和为sum，
 * 严格次小生成树就是指边权之和大于sum的生成树中最小的一个。
 * lca次小生成树。倍增找树上路径最大边即可。
 * 输入样例：
 * 5 6
 * 1 2 1
 * 1 3 2
 * 2 4 3
 * 3 5 4
 * 3 4 3
 * 4 5 6
 * 输出样例：
 * 11
 * 已经ac,泪目
 */
public class 次小生成树 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            edge[i] = new node(a, b, c);
        }

        long sum = kruskal();
        build();
        bfs();

        long res = (long) 1e19;
        for (int i = 0; i < m; i++) {
            if (!edge[i].used) {
                a = edge[i].a;
                b = edge[i].b;
                c = edge[i].w;
                res = Math.min(res, sum + lca(a, b, c));
            }
        }
        System.out.println(res);
    }

    static void bfs() {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[1] = 1;
        q.add(1);
        while (!q.isEmpty()) {
            int p = q.poll();
            for (int i = h[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (depth[j] > depth[p] + 1) {
                    depth[j] = depth[p] + 1;
                    q.add(j);

                    fa[j][0] = p;
                    d1[j][0] = w[i];
                    d2[j][0] = -inf;
                    for (int k = 1; k <= 16; k++) {
                        int anc = fa[j][k - 1];
                        fa[j][k] = fa[anc][k - 1];

                        dis[0] = d1[j][k - 1];
                        dis[1] = d2[j][k - 1];
                        dis[2] = d1[anc][k - 1];
                        dis[3] = d2[anc][k - 1];
                        d1[j][k] = d2[j][k] = -inf;
                        for (int l = 0; l < 4; l++) {
                            int d = dis[l];
                            if (d > d1[j][k]) {
                                d2[j][k] = d1[j][k];
                                d1[j][k] = d;
                            } else if (d != d1[j][k] && d > d2[j][k]) d2[j][k] = d;
                        }
                    }
                }
            }
        }
    }

    static int lca(int a, int b, int w) {
        if (depth[a] < depth[b]) {
            return lca(b, a, w);
        }
        int cnt = 0;
        for (int k = 16; k >= 0; k--) {
            if (depth[fa[a][k]] >= depth[b]) {
                dis[cnt++] = d1[a][k];
                dis[cnt++] = d2[a][k];
                a = fa[a][k];
            }
        }

        if (a != b) {
            for (int k = 16; k >= 0; k--) {
                if (fa[a][k] != fa[b][k]) {
                    dis[cnt++] = d1[a][k];
                    dis[cnt++] = d2[a][k];
                    dis[cnt++] = d1[b][k];
                    dis[cnt++] = d2[b][k];
                    a = fa[a][k];
                    b = fa[b][k];
                }
            }
            dis[cnt++] = d1[a][0];
            dis[cnt++] = d1[b][0];
        }

        int dis1 = -inf, dis2 = -inf;
        for (int i = 0; i < cnt; i++) {
            int d = dis[i];
            if (d > dis1) {
                dis2 = dis1;
                dis1 = d;
            } else if (d != dis1 && d > dis2) {
                dis2 = d;
            }
        }
        if (w > dis1) return w - dis1;
        if (w > dis2) return w - dis2;
        return inf;
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, idx = 1, N = (int) (1e5 + 10), M = (int) (3e5 + 10), inf = 0x3f3f3f3f;
    static int[] h = new int[N];
    static node[] edge = new node[N];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static int[] e = new int[M];
    static int[] p = new int[N];
    static int[] depth = new int[N];
    static int[][] fa = new int[N][17];
    static int[][] d1 = new int[N][17];
    static int[][] d2 = new int[N][17];
    static int[] dis = new int[N * 2];

    static class node implements Comparable<node> {
        int a, b, w;
        boolean used;//标记是否被用过

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

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static long kruskal() {
        Arrays.sort(edge, 0, m);
        long res = 0;
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int a = find(edge[i].a), b = find(edge[i].b), c = edge[i].w;
            if (a != b) {
                p[a] = b;
                res += c;
                edge[i].used = true;
            }
        }

        return res;
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void build() {
        for (int i = 0; i < m; i++) {
            if (edge[i].used) {
                int a = edge[i].a, b = edge[i].b, c = edge[i].w;
                add(a, b, c);
                add(b, a, c);
            }
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}
