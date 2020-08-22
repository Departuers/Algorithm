package LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
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
    }

    static void bfs() {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[1] = 1;
        q.add(1);
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (depth[j] > depth[t] + 1) {
                    depth[j] = depth[t] + 1;
                    q.add(j);
                    fa[j][0] = t;
                    d1[j][0] = w[i];
                    d2[j][0] = -inf;
                    for (int k = 1; k <= 16; k++) {
                        int anc = fa[j][k - 1];
                        fa[j][k] = fa[anc][k - 1];
                        int[] dis = {d1[j][k - 1], d2[j][k - 1], d1[anc][k - 1], d2[anc][k - 1]};
                        d1[j][k] = d2[j][k] = -inf;
                        for (int u = 0; u < 4; u++) {
                            int d = dis[u];
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
        return -1;
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, cnt = 1, N = (int) (1e5 + 10), M = (int) (3e5 + 10), inf = Integer.MAX_VALUE / 2;
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
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
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
