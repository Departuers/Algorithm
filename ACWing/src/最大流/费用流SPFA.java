package 最大流;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.luogu.com.cn/problem/P3381
 * https://www.acwing.com/activity/content/code/content/447948/
 * https://blog.csdn.net/lym940928/article/details/90209172
 * tle1个很快了
 */
public class 费用流SPFA {
    static int N = 5003, M = 100003, mincost, maxflow, n, m, idx = 2, inf = 0x3fffffff, S, T;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        S = nextInt();
        T = nextInt();
        for (int i = 0; i < m; i++) {
            add(nextInt(), nextInt(), nextInt(), nextInt());
        }
        min_cost_flow();
        bw.write(maxflow + " " + mincost);
        bw.flush();
    }

    private static void min_cost_flow() {
        mincost = 0;
        maxflow = 0;
        while (spfa()) {
            for (int i = T; i != S; i = e[pre[i] ^ 1]) {
                w[pre[i]] -= f[T];
                w[pre[i] ^ 1] += f[T];
            }
            maxflow += f[T];
            mincost += f[T] * d[T];
        }
    }

    static int[] h = new int[N];
    static int[] w = new int[M];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] cost = new int[M];

    static int[] f = new int[N];
    static int[] d = new int[N];
    static boolean[] st = new boolean[N];
    static int[] pre = new int[N];

    static void addEdge(int a, int b, int c, int d) {
        e[idx] = b;
        w[idx] = c;
        cost[idx] = d;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void add(int a, int b, int c, int d) {
        addEdge(a, b, c, d);
        addEdge(b, a, 0, -d);
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static boolean spfa() {
        q.clear();
        Arrays.fill(d, inf);
        Arrays.fill(st, false);
        d[S] = 0;
        f[S] = inf;
        q.add(S);
        while (!q.isEmpty()) {
            int u = q.poll();
            st[u] = false;
            for (int i = h[u]; i != 0; i = ne[i]) {
                int j = e[i];
                if (d[j] > d[u] + cost[i] && w[i] != 0) {
                    pre[j] = i;
                    d[j] = d[u] + cost[i];
                    f[j] = Math.min(f[u], w[i]);
                    if (!st[j]) {
                        st[j] = true;
                        q.add(j);
                    }
                }
            }
        }
        if (d[T] != inf) return true;
        else return false;
    }

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
}
