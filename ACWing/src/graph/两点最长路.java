package graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * lougu
 * https://www.luogu.com.cn/problem/P1807
 * 设 G为有 n 个顶点的带权有向无环图，G 中各顶点的编号为 1 到 n，
 * 请设计算法，计算图 G 中 <1,n>间的最长路径。
 * 输入的第一行有两个整数，分别代表图的点数 nn 和边数 mm。
 * <p>
 * 第 2到第(m+1) 行，每行 3 个整数 u, v, w
 * 代表存在一条从 u 到v 边权为 w 的边。
 * 输出格
 * 输出一行一个整数，代表 1 到 n 的最长路。
 * 若 1与 n 不联通，请输出 −1。
 * 输入输出样例
 * 输入
 * 2 1
 * 1 2 1
 * 输出
 * 1
 */
public class 两点最长路 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b, c;
        h = new int[n + 10];
        st = new boolean[n + 10];
        dis = new int[n + 10];
        e = new int[m + 10];
        ne = new int[m + 10];
        w = new int[m + 10];

        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
        }
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(1);
        st[1] = true;
        while (!q.isEmpty()) {
            a = q.poll();
            st[a] = false;
            for (int i = h[a]; i != 0; i = ne[i]) {
                b = e[i];
                if (dis[b] < dis[a] + w[i]) {
                    dis[b] = dis[a] + w[i];
                    if (!st[b]) {
                        q.add(b);
                        st[b] = true;
                    }
                }
            }
        }
        if (dis[n] == 0) System.out.println(-1);
        else System.out.println(dis[n]);
    }

    static int n, m, N = 2040, M = (int) (5e4 + 10), ct = 1;
    static boolean[] st;
    static int[] dis;
    static int[] h;
    static int[] e;
    static int[] w;
    static int[] ne;

    static void add(int a, int b, int c) {
        e[ct] = b;
        w[ct] = c;
        ne[ct] = h[a];
        h[a] = ct++;
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
