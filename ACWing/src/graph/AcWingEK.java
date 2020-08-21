package graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

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

    static int cnt = 1, n, inf = (int) 1e9, t, s, m;
    static int[] h = new int[1010];
    static int[] e = new int[20010];
    static int[] ne = new int[20010];
    static int[] w = new int[20010];
    static boolean[] st = new boolean[1202];
    static int[] dis = new int[1202];
    static int[] pre = new int[1010];

    //加双向边
    static void add(int a, int b, int c) {
        e[++cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt;
        e[++cnt] = a;
        w[cnt] = 0;
        ne[cnt] = h[b];
        h[b] = cnt;
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    //使用bfs寻找增广路
    static boolean bfs() {
        Arrays.fill(st, false);
        q.clear();
        q.push(s);
        st[s] = true;
        dis[s] = inf;
        while (!q.isEmpty()) {
            int x = q.pollFirst();
            for (int i = h[x]; i != 0; i = ne[i]) {
                int v = e[i];
                if (!st[v] && w[i] != 0) {
                    dis[v] = Math.min(dis[x], w[i]);
                    pre[v] = i;
                    q.push(v);
                    st[v] = true;
                    if (v == t) return true;
                }
            }
        }
        return false;
    }

    static int ek() {
        int r = 0;
        while (bfs()) {
            r += dis[t];
            for (int i = t; i != s; i = e[pre[i] ^ 1]) {
                w[pre[i]] -= dis[t];
                w[pre[i] ^ 1] += dis[t];
            }
        }
        return r;
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
