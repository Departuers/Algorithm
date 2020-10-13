package 最大流;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 给定一个包含 n 个点 m 条边的有向图，并给定每条边的容量，边的容量非负。
 * 图中可能存在重边和自环。求从点 S 到点 T 的最大流。
 * 输入格式
 * 第一行包含四个整数 n,m,S,T。
 * 接下来 m 行，每行三个整数 u,v,c，表示从点 u 到点 v 存在一条有向边，容量为 c。
 * 点的编号从 1 到 n。
 * 输出格式
 * 输出点 S 到点 T 的最大流。
 * 如果从点 S 无法到达点 T 则输出 0。
 * 数据范围
 * 2≤n≤1000,
 * 1≤m≤10000,
 * 0≤c≤10000,
 * S≠T
 * 输入样例：
 * 7 14 1 7
 * 1 2 5
 * 1 3 6
 * 1 4 5
 * 2 3 2
 * 2 5 3
 * 3 2 2
 * 3 4 3
 * 3 5 3
 * 3 6 7
 * 4 6 5
 * 5 6 1
 * 6 5 1
 * 5 7 8
 * 6 7 7
 * 输出样例：
 * 14
 */
@SuppressWarnings("all")
public class AcWingEK {

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        s = nextInt();
        t = nextInt();
        int a, b, c;
        for (int i = 1; i <= m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
        }
        System.out.println(ek());
    }

    static int idx = 2, n, inf = (int) 1e9, t, s, m;
    static int[] h = new int[2010];
    static int[] e = new int[40010];
    static int[] ne = new int[40010];
    static int[] w = new int[40010];
    static boolean[] st = new boolean[2202];
    static int[] dis = new int[2202];
    static int[] pre = new int[2010];

    //加双向边
    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
        e[idx] = a;
        w[idx] = 0;
        ne[idx] = h[b];
        h[b] = idx++;
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static boolean bfs() {
        Arrays.fill(st, false);
        q.clear();
        q.add(s);
        st[s] = true;
        dis[s] = inf;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = h[v]; i != 0; i = ne[i]) {
                int u = e[i];
                if (!st[u] && w[i] != 0) {
                    dis[u] = Math.min(w[i], dis[v]);
                    pre[u] = i;
                    st[u] = true;
                    q.add(u);
                    if (u == t) return true;
                }
            }
        }
        return false;
    }

    static int ek() {
        int r = 0;
        while (bfs()) {
            r += dis[t];
            //从终点往前找,沿途减去当前曾广路径的最大值
            for (int i = t; i != s; i = e[pre[i] ^ 1]) {
                w[pre[i]] -= dis[t];
                w[pre[i] ^ 1] += dis[t];
            }
        }
        return r;
    }


    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
