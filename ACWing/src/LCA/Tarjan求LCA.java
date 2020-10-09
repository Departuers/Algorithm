package LCA;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.cnblogs.com/hulean/p/11144059.html
 * 离线
 * 预处理O(n)
 * 单次查询O(1)
 * 5 5 4
 * 3 1
 * 2 4
 * 5 1
 * 1 4
 * 2 4
 * 3 2
 * 3 5
 * 1 2
 * 4 5
 * out
 * 4
 * 4
 * 1
 * 4
 * 4
 * 洛谷3379 ac了居然1.23秒也算ac
 * 最好的版本!!!
 * 离线算法
 * https://www.luogu.com.cn/blog/qzh/lca-yang-xie
 * https://www.cnblogs.com/gzh-red/p/11253230.html
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
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
        n = nextInt();
        m = nextInt();
        root = nextInt();
        for (int i = 1; i < n; i++) {
            add(nextInt(), nextInt());
        }
        for (int i = 0; i < m; i++) {
            addq(nextInt(), nextInt(), i);
        }//存储询问,离线算法

        tarjan(root, -1);
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

    static void tarjan(int u, int fa) {
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                tarjan(j, u);
                union(j, u);
                vis[j] = true;
            }
        }

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
