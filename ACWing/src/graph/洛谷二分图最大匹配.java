package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
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
            g[a].add(b);
        }
        for (int i = 1; i <= n; i++) {
            if (dfs(i, i)) ans++;
        }
        System.out.println(ans);
    }

    private static boolean dfs(int u, int tag) {
        if (st[u] == tag) return false;
        st[u] = tag;
        for (Integer w : g[u]) {
            if (match[w] == 0 || dfs(match[w], tag)) {
                match[w] = u;
                return true;
            }
        }
        return false;
    }

    static int n, m, N = 1010, M = 50000, t = 0;
    static TreeSet<Integer>[] g = new TreeSet[N];
    static int[] match = new int[N];
    static int[] st = new int[N];

    static {
        for (int i = 0; i < g.length; i++) {
            g[i] = new TreeSet<Integer>();
        }
    }

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
