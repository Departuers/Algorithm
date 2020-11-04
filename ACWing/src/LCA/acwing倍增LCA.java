package LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/blog/content/2145/
 * tle 3个 1.7秒,胜在好写,其实都差不多慢...这个好写的多
 */
public class acwing倍增LCA {
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
        n = nextInt();
        m = nextInt();
        root = nextInt();
        for (int i = 1; i < n; i++) {
            add(nextInt(), nextInt());
        }
        bfs(root);
        for (int i = 0; i < m; i++) {
            bw.write(query(nextInt(), nextInt()) + "\n");
        }
        bw.flush();
    }

    static int maxn = 500004, inf = 0x3f3f3f3f;
    static int[] e = new int[maxn << 1];
    static int[] h = new int[maxn];
    static int[] ne = new int[maxn << 1];
    static int n, m, cnt = 1, root;
    static int[][] up = new int[maxn][20];
    static int[] depth = new int[maxn];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = h[a];
        h[a] = cnt++;
        e[cnt] = a;
        ne[cnt] = h[b];
        h[b] = cnt++;
    }

    private static int query(int a, int b) {
        if (depth[a] < depth[b]) return query(b, a);
        //depth[a]>depth[b]也就是a的深度更深,a在下面
        //a往上跳,先跳到根b相同高度
        for (int k = 19; k >= 0; k--) {
            if (depth[up[a][k]] >= depth[b]) {
                a = up[a][k];
            }
        }//跳到一个特别高的位置,会使得up[a][k]=0,而0是不存在的节点,depth[0]=0,不会出错
        //从高往下跳,最终a与b处于同一高度
        if (a == b) return a;
        //a与b处于同一高度,从高往低枚举,同时跳
        for (int k = 17; k >= 0; k--) {
            if (up[a][k] != up[b][k]) {
                a = up[a][k];
                b = up[b][k];
            }
        }
        return up[a][0];
    }

    static void bfs(int root) {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[root] = 1;
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(root);

        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (depth[j] > depth[t] + 1) {
                    depth[j] = depth[t] + 1;
                    q.add(j);
                    up[j][0] = t;
                    for (int k = 1; k <= 19; k++) {
                        up[j][k] = up[up[j][k - 1]][k - 1];
                    }
                }
            }
        }
    }

}
