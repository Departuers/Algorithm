package graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 不知道为啥有一个过不了,洛谷数据错了...
 */
public class 洛谷负环 {
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
        t = nextInt();
        int a, b, c;
        while (t-- != 0) {
            cnt = 1;
            n = nextInt();
            m = nextInt();
            Arrays.fill(dis, 0);
            Arrays.fill(he, 0);
            Arrays.fill(ne, 0);
            Arrays.fill(count, 0);
            for (int i = 0; i < m; i++) {
                a = nextInt();
                b = nextInt();
                c = nextInt();
                add(a, b, c);
                if (c >= 0) {
                    add(b, a, c);
                }
            }
            if (spfa()) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
    }

    private static boolean spfa() {
        for (int i = 1; i <= n; i++) {
            q.add(i);
            vis[i] = true;
        }
        int t = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            vis[v] = false;
            for (int i = he[v]; i != 0; i = ne[i]) {
                t = e[i];
                if (dis[t] > dis[v] + w[i]) {
                    dis[t] = dis[v] + w[i];
                    count[t] = count[v] + 1;
                    if (count[t] >= n) return true;
                    if (!vis[t]) {
                        vis[t] = true;
                        q.add(t);
                    }
                }
            }
        }
        return false;
    }

    static boolean[] vis = new boolean[2005];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int[] count = new int[2005];
    static int[] dis = new int[2005];
    static int t, n, m, cnt = 1;
    static int[] he = new int[2005];
    static int[] ne = new int[10005];
    static int[] e = new int[10005];
    static int[] w = new int[10005];

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }
}
