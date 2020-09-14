package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * P3386 【模板】二分图最大匹配
 * 给定一个二分图，其左部点的个数为 nn，右部点的个数为 mm，边数为 ee，求其最大匹配的边数。
 * 输入输出样例
 * 输入
 * 1 1 1
 * 1 1
 * 输出
 * 1
 * 输入
 * 4 2 7
 * 3 1
 * 1 2
 * 3 2
 * 1 1
 * 4 2
 * 4 1
 * 1 1
 * 输出
 * 2
 * 洛谷ac,最快的
 */
public class 洛谷二分图最大匹配 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        t = nextInt();
        int a, b, ans = 0;
        while (t-- != 0) {
            a = nextInt();
            b = nextInt();
            add(a, b);
        }
        for (int i = 1; i <= n; i++) {
            if (dfs(i, i)) ans++;
        }
        System.out.println(ans);
    }

    private static boolean dfs(int u, int tag) {
        if (st[u] == tag) return false;
        st[u] = tag;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int w = e[i];
            if (match[w] == 0 || dfs(match[w], tag)) {
                match[w] = u;
                return true;
            }
        }
        return false;
    }

    static int n, m, N = 1010, M = 50000, t = 0, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int[] match = new int[N];
    static int[] st = new int[N];


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stt = new StringTokenizer("");

    static String next() throws IOException {
        while (!stt.hasMoreTokens()) {
            stt = new StringTokenizer(br.readLine());
        }
        return stt.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
