package graph.复合单源最短路;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * slf优化,但...
 * 比cpp慢一半,hh
 */
public class 道路与航路spfa {
    public static void main(String[] args) throws IOException {
        T = nextInt();
        R = nextInt();
        P = nextInt();
        S = nextInt();
        int a, b, c;
        for (int i = 0; i < R; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        for (int i = 0; i < P; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
        }
        spfa();
    }

    static int n, m, N = 25010, M = 150010, idx = 1, T, R, P, S;
    static int[] h = new int[N];
    static int[] w = new int[M];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int inf = 0x3f3f3f3f;

    static void spfa() throws IOException {
        Arrays.fill(dist, inf);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(S);
        dist[S] = 0;
        while (!q.isEmpty()) {
            int p = q.poll();
            //SLF swap:每当队列改变时,如果队首距离大于队尾,则交换首尾。
            if (q.size() > 2 && dist[q.peekFirst()] > dist[q.peekLast()]) {
                int a = q.pollLast();
                q.add(q.pollFirst());
                q.addFirst(a);
            }
            st[p] = false;
            for (int i = h[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[p] + w[i]) {
                    dist[j] = dist[p] + w[i];
                    if (!st[j]) {
                        st[j] = true;
                        if (!q.isEmpty() && dist[j] < dist[q.peekFirst()]) {
                            q.addFirst(j);
                        } else q.add(j);
                    }
                }
            }
        }
        for (int i = 1; i <= T; i++) {
            if (dist[i] == inf) bw.write("NO PATH\n");
            else bw.write(dist[i] + "\n");
        }
        bw.flush();
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
