package graph;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为正值。
 * 请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
 * 输出一个整数，表示1号点到n号点的最短距离。
 * 如果路径不存在，则输出-1。
 * 数据范围
 * 1≤n,m≤10^5,
 * 图中涉及边长均不超过10000。
 * 输入样例：
 * 3 3
 * 1 2 2
 * 2 3 1
 * 1 3 4
 * 输出样例：
 * 3
 */
public class dijkstra {
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


    public static void main(String[] args) throws IOException {
     //   Scanner sc = new Scanner(System.in);
        n = nextInt();
        m = nextInt();
        int s = nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
        }
        Arrays.fill(dis, (1 << 31) - 1);
        dij(s);
    }

    private static void dij(int s) throws IOException {
        q.add(new node(s, 0));
        dis[s] = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            //pq每次取出的边,就是算出最短路径的边
            if (vis[p.to]) continue;
            vis[p.to] = true;
            for (int i = he[p.to]; i != 0; i = ne[i]) {
                int ed = e[i];
                if (dis[p.to] != Integer.MAX_VALUE && dis[ed] > p.dis + w[i]) {
                    dis[ed] = p.dis + w[i];
                    q.add(new node(ed, dis[ed]));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            bw.write(dis[i] + " ");
        }
        bw.flush();
    }

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }


    static int n, m, cnt = 1;
    static int[] e = new int[500005];
    static int[] he = new int[500005];
    static int[] ne = new int[500005];
    static int[] w = new int[500005];
    static boolean[] vis = new boolean[100005];
    static PriorityQueue<node> q = new PriorityQueue<node>();
    static int[] dis = new int[100005];
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

