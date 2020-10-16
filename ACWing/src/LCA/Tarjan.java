package LCA;

import java.io.*;
import java.util.StringTokenizer;

public class Tarjan {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < N; i++) {
            p[i] = i;
        }
        n = nextInt();
        m = nextInt();
        root = nextInt();
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            a = nextInt();
            b = nextInt();
            add(h, a, b, -1);
            add(h, b, a, -1);
        }
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            add(rh, a, b, i);
            add(rh, b, a, i);
        }

        tarjan(root, -1);
        for (int i = 0; i < m; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.flush();
    }

    static int n, N = 500010, m, root, idx = 1;
    static int[] p = new int[N];
    static int[] h = new int[N], e = new int[N << 2], ne = new int[N << 2], rh = new int[N], ans = new int[N], id = new int[N << 2];
    static boolean[] st = new boolean[N];

    static void tarjan(int u, int fa) {
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                tarjan(j, u);
                st[j] = true;
                union(j, u);
            }
        }
        for (int i = rh[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (st[j]) {
                ans[id[i]] = find(j);
            }
        }
    }

    static void add(int[] h, int a, int b, int i) {
        e[idx] = b;
        id[idx] = i;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void union(int a, int b) {
        int r = find(a), rb = find(b);
        if (r != rb) p[r] = rb;
    }
}
