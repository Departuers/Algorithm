package LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.luogu.com.cn/problem/solution/P4180
 * 倍增LCA+Kruskal
 * O(n log n+ mlog m)预处理+求解
 * kruskalkruskal 的本质是什么？
 * 贪心
 * kruskal 算法被证明，对于任何的u,v
 * 有u到v之间边权最大值小于等于u到v未选入的边的边权
 * 所以说，不严格次小生成树只要
 * 遍历每条未选的边(u,v,d)，用它替换u和v之间的最大边即可
 * 现在我们的任务就是把不严格的不去掉
 * 为什么它不严格？
 * 因为
 * u到v之间边权最大值小于等于u到v未选入的边的边权
 * 等于！
 * 是不是感觉自己被坑了？
 * 没关系，我们只要多存一个次大值即可
 * 指出一句，attack的题解对次大值合并时有一处疏忽，他的代码会在合并两个相等的最大值时最大=次大
 * 一切最大次大都在倍增时处理
 */
public class 洛谷严格次小生成树 {
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

    static int n, m, N = 100010, M = 300010, inf = 0x3f3f3f3f, idx = 1;

    static class node implements Comparable<node>{
        int a, b, w;
        boolean used = false;

        public node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(node node) {
            return w-node.w;
        }
    }

    static node[] edge = new node[M];
    static int[] h = new int[N];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static int[] e = new int[M];
    static int[] p = new int[N];
    static int[] depth = new int[N];
    static int[][] fa = new int[N][17], d1 = new int[N][17], d2 = new int[N][17];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static long kruskal() {
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        Arrays.sort(edge, 0, m);
        long res = 0;
        for (int i = 0; i < m; i++) {
            int a = find(edge[i].a);
            int b = find(edge[i].b);
            int c = edge[i].w;
            if (a != b) {
                p[a] = b;
                res += c;
                edge[i].used = true;
            }
        }
        return res;
    }

    static void build() {
        for (int i = 0; i < m; i++) {
            if (edge[i].used) {
                int a = edge[i].a, b = edge[i].b, w = edge[i].w;
                add(a, b, w);
                add(b, a, w);
            }
        }
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int[] dis = new int[N * 2];

    static void bfs() {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[1] = 1;
        q.clear();
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
                        dis[0] = d1[j][k - 1];
                        dis[1] = d2[j][k - 1];
                        dis[2] = d1[anc][k - 1];
                        dis[3] = d2[anc][k - 1];
                        for (int l = 0; l < 4; l++) {
                            int d = dis[l];
                            if (d > d1[j][k]) {
                                d2[j][k] = d1[j][k];
                                d1[j][k] = d;
                            } else if (d != d1[j][k] && d > d2[j][k]) {
                                d2[j][k] = d;
                            }
                        }
                    }
                }
            }
        }
    }

    static int lca(int a, int b, int w) {
        if (depth[a] < depth[b]) return lca(b, a, w);
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
            dis[cnt++] = d2[b][0];
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

}
