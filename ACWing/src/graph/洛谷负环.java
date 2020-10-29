package graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 从1开始到达,不能用那个模板!!!
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
        int t = nextInt();
        int a, b, c;
        while (t-- != 0) {
            idx = 1;
            n = nextInt();
            m = nextInt();
            Arrays.fill(he, 0);
            Arrays.fill(ne, 0);
            Arrays.fill(cnt, 0);
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
        Arrays.fill(st, false);
        q.clear();
        Arrays.fill(dis, 0x3f3f3f3f);
        q.add(1);
        dis[1] = 0;
        int t = 0;
        while (!q.isEmpty()) {
            int v = q.pollLast();
            st[v] = false;
            for (int i = he[v]; i != 0; i = ne[i]) {
                t = e[i];
                if (dis[t] > dis[v] + w[i]) {
                    dis[t] = dis[v] + w[i];
                    cnt[t] = cnt[v] + 1;
                    if (cnt[t] >= n) return true;
                    if (!st[t]) {
                        st[t] = true;
                        q.add(t);
                    }
                }
            }
        }
        return false;
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static boolean[] st = new boolean[6005];
    static int[] cnt = new int[6005];
    static int[] dis = new int[6005];
    static int n, m, idx = 1;
    static int[] he = new int[6005];
    static int[] ne = new int[6005];
    static int[] e = new int[6005];
    static int[] w = new int[6005];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = he[a];
        he[a] = idx++;
    }
}
