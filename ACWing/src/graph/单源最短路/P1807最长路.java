package graph.单源最短路;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1807最长路 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
        }
        spfa();
    }

    private static void spfa() {
        Arrays.fill(dis, -1);
        dis[1] = 0;
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(1);
        while (!q.isEmpty()) {
            int p = q.poll();
            st[p] = false;
            for (int i = h[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] < dis[p] + w[i]) {
                    dis[j] = dis[p] + w[i];
                    if (!st[j]) {
                        st[j] = true;
                        q.add(j);
                    }
                }
            }
        }
        System.out.println(dis[n]);
    }

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
