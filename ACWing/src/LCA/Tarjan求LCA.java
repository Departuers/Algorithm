package LCA;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.cnblogs.com/hulean/p/11144059.html
 * 离线
 * 预处理O(n)
 * 单次查询O(1)
 * 5 5 4
 * 3 1
 * 2 4
 * 5 1
 * 1 4
 * 2 4
 * 3 2
 * 3 5
 * 1 2
 * 4 5
 * out
 * 4
 * 4
 * 1
 * 4
 * 4
 * 洛谷3379 ac了
 * 最好的版本!!!
 * 离线算法
 * https://www.luogu.com.cn/blog/qzh/lca-yang-xie
 * https://www.cnblogs.com/gzh-red/p/11253230.html
 */
public class Tarjan求LCA {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
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
        for (int i = 1; i < p.length; i++) {
            p[i] = i;
        }
        n = nextInt();
        m = nextInt();
        root = nextInt();
        int a, b;
        for (int i = 1; i < n; i++) {
            a = nextInt();
            b = nextInt();
            add(h, a, b, -1);
            add(h, b, a, -1);
        }
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            add(rh, a, b, i);
            add(rh, b, a, i);
        }//存储询问,离线算法

        tarjan(root, -1);
        for (int i = 0; i < m; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.flush();
    }

    static int maxn = 500002;
    static int[] e = new int[maxn << 2];
    static int[] h = new int[maxn], rh = new int[maxn];
    static int[] ne = new int[maxn << 2];
    static int[] id = new int[maxn << 2];
    static int[] p = new int[maxn];
    static int[] ans = new int[maxn];
    static int idx = 1;
    static boolean[] st = new boolean[maxn];
    static int n, m, root;

    static void add(int[] h, int a, int b, int i) {
        e[idx] = b;
        id[idx] = i;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void tarjan(int u, int fa) {
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                tarjan(j, u);
                union(j, u);
                st[j] = true;
            }
        }

        for (int i = rh[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (st[j]) {
                ans[id[i]] = find(j);
            }
        }
    }

    static int find(int x) {
        while (x != p[x]) {
            p[x] = p[p[x]];
            x = p[x];
        }
        return x;
    }

    static void union(int v, int u) {
        v = find(v);
        u = find(u);
        if (u != v) p[v] = u;
    }
}
