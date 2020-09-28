package UnionFind;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/solution/content/15945/
 * 给出一个 n 个孤立点的图，每个点上的权值都是 0，进行 m 次操作
 * 操作 1：把两个点所在的连通块合并起来
 * 操作 2 ：向某个点所在的连通块的所有点累加一个值
 * n≤104,m≤105n≤104,m≤105
 * 最后输出每个点上的权值
 * 并查集 树形DP
 * 我们通过并查集合并连通块，保证同一个连通块内的点同属一个集合
 * 对于每一个合并操作，找到两个点所属的集合
 * 如果这两个点不在同一连通块，那么我们构造一个新点，使这个新点成为集合合并后的根节点
 * <p>
 * 这样进行 k 次有效合并操作后，就会产生 k 个新点
 * 我们所构造的图是一颗一颗的树，编号为 1−n 的节点都是树的叶子节点
 * <p>
 * 对于每次连通块累加操作，我们只需要向集合的根节点累加一个值即可
 * 最后对我们所构造出来的一堆树DP（只是遍历一下），把每个点的权值下放到子树中的所有节点中
 * 然后依次输出编号为 1−n的节点的权值即可
 */
public class 网络分析 {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < N; i++) {
            p[i] = i;
        }
        n = nextInt();
        m = nextInt();
        int root = n + 1;
        int op, a, b;

        while (m-- != 0) {
            op = nextInt();
            a = nextInt();
            b = nextInt();
            if (op == 1) {
                a = find(a);
                b = find(b);
                if (a != b) {
                    p[a] = p[b] = root;
                    //两个数所在集合都向root连一条边
                    add(root, a);
                    add(root, b);
                    root++;
                }
            } else {
                a = find(a);
                f[a] += b;
            }
        }
        for (int i = n + 1; i < root; i++) {
            if (p[i] == i) dfs(i, 0);
        }
        for (int i = 1; i <= n; i++) {
            bw.write(f[i]+" ");
        }
        bw.flush();
    }

    static int N = 200100, M = N << 1, n, m, idx = 1;
    static int[] h = new int[N], e = new int[M], ne = new int[M], p = new int[N], f = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void dfs(int u, int fa) {
        f[u] += f[fa];
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            dfs(j, u);
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
