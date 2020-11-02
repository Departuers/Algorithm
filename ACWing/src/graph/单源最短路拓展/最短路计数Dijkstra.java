package graph.单源最短路拓展;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.luogu.com.cn/problem/solution/P1144
 */
public class 最短路计数Dijkstra {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b;
        while (m-- > 0) {
            a = nextInt();
            b = nextInt();
            add(a, b);
            add(b, a);
        }
        dij();
    }

    static class node implements Comparable<node> {
        int dis, to;

        public node(int dis, int to) {
            this.dis = dis;
            this.to = to;
        }

        @Override
        public int compareTo(node o) {
            return dis - o.dis;
        }
    }

    static int N = 1000010, M = 4000010, idx = 1, n, m, mod = 100003;
    static int[] h = new int[N], e = new int[M], ne = new int[M];
    static boolean[] st = new boolean[N];
    static int[] dis = new int[N], cnt = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void dij() throws IOException {
        PriorityQueue<node> q = new PriorityQueue<>();
        q.add(new node(0, 1));
        Arrays.fill(dis, 0x3f3f3f3f);
        cnt[1] = 1;
        dis[1] = 0;
        int t;
        while (!q.isEmpty()) {
            t = q.poll().to;
            if (st[t]) continue;
            st[t] = true;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[t] + 1) {
                    dis[j] = dis[t] + 1;
                    cnt[j] = cnt[t];
                    q.add(new node(dis[j], j));
                } else if (dis[j] == dis[t] + 1) {
                    cnt[j] += cnt[t];
                    cnt[j] %= mod;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            bw.write(cnt[i] + "\n");
        }
        bw.flush();
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
