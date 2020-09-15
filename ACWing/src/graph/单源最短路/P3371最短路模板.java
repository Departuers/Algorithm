package graph.单源最短路;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P3371最短路模板 {

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        s = nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
        }
        spfa();
    }

    static void spfa() throws IOException {
        int inf = (1 << 31) - 1;
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        Arrays.fill(dis, inf);
        dis[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int p = q.poll();
            st[p] = false;
            for (int i = h[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[p] + w[i]) {
                    dis[j] = dis[p] + w[i];
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            bw.write(dis[i] + " ");
        }
        bw.flush();
    }

    //单源最短路径（标准版）
    static void dij() throws IOException {
        int inf = (1 << 31) - 1;
        Arrays.fill(dis, inf);
        q.add(new node(s, 0));
        dis[s] = 0;
        while (!q.isEmpty()) {
            int p = q.poll().to;
            if (st[p]) continue;
            st[p] = true;
            for (int i = h[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[p] + w[i]) {
                    dis[j] = dis[p] + w[i];
                    q.add(new node(j, dis[j]));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            bw.write(dis[i] + " ");
        }
        bw.flush();
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static PriorityQueue<node> q = new PriorityQueue<node>();

    static int N = 100100, M = (int) (5e5 + 10), idx = 1;
    static int[] h = new int[N];
    static int[] w = new int[M];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] dis = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int n, m, s;

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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk = new StringTokenizer("");

    static String next() throws IOException {
        while (!stk.hasMoreTokens()) {
            stk = new StringTokenizer(br.readLine());
        }
        return stk.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
