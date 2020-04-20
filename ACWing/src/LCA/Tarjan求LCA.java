package LCA;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://nekrozcoder.github.io/2019/07/28/%E3%80%90%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0%E3%80%91LCA%20%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/#more
 * 离线
 * 预处理O(n)
 * 单次查询O(1)
 * 1
 * 1
 * 2
 * 2
 * 1
 * 4
 * 7
 * 3
 * 洛谷3379 tel2个
 * 最好的版本!!!
 */
public class Tarjan求LCA {
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
        for (int i = 0; i < m; i++) {
            addq(nextInt(), nextInt(), i);
        }
        targan(root, 0);
        for (int i = 0; i < m; i++) {
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }

    static int maxn = 500001;
    static int[] e = new int[maxn << 1];
    static int[] he = new int[maxn];
    static int[] ne = new int[maxn << 1];
    static int[] parent = new int[maxn];
    static int cnt = 1;
    static int[] qheadedge = new int[maxn];
    static int[] qto = new int[maxn << 1];
    static int[] qnext = new int[maxn << 1];
    static int[] qindex = new int[maxn << 1];
    static int[] result = new int[maxn];
    static int qidx = 1;
    static boolean[] vis = new boolean[maxn];
    static int n, m, root;

    static void add(int a, int b) {
        e[cnt] = a;
        ne[cnt] = he[b];
        he[b] = cnt++;
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static void addq(int a, int b, int i) {
        qto[qidx] = a;
        qindex[qidx] = i;
        qnext[qidx] = qheadedge[b];
        qheadedge[b] = qidx++;
        qto[qidx] = b;
        qindex[qidx] = i;
        qnext[qidx] = qheadedge[a];
        qheadedge[a] = qidx++;
    }

    static {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }


    static void targan(int u, int fa) {
        //vis[u] = true;
        for (int i = he[u]; i != 0; i = ne[i]) {
            if (e[i] != fa) {
                targan(e[i], u);
                union(e[i], u);
                vis[e[i]] = true;
            }
        }
        //  vis[u] = true;
        for (int i = qheadedge[u]; i != 0; i = qnext[i]) {
            int j = qto[i];
            if (vis[j]) {
                result[qindex[i]] = find(j);
                //放到结果里
            }
        }
    }

    static int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    static void union(int v, int u) {
        v = find(v);
        u = find(u);
        if (u != v) parent[v] = u;
    }

}
