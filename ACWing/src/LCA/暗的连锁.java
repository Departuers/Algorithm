package LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.cnblogs.com/TEoS/p/11376676.html
 * https://www.cnblogs.com/gzh-red/p/11185914.html 秦淮大佬
 * https://www.acwing.com/problem/content/354/
 * 树上差分
 * 传说中的暗之连锁被人们称为 Dark。
 * Dark 是人类内心的黑暗的产物，古今中外的勇者们都试图打倒它。
 * 经过研究，你发现 Dark 呈现无向图的结构，图中有 N 个节点和两类边，
 * 一类边被称为主要边，而另一类被称为附加边。
 * Dark 有 N – 1 条主要边，并且 Dark 的任意两个节点之间都存在一条只由主要边构成的路径。
 * 另外，Dark 还有 M 条附加边。
 * 你的任务是把 Dark 斩为不连通的两部分。
 * 一开始 Dark 的附加边都处于无敌状态，你只能选择一条主要边切断。
 * 一旦你切断了一条主要边，Dark 就会进入防御模式，主要边会变为无敌的而附加边可以被切断。
 * 但是你的能力只能再切断 Dark 的一条附加边。
 * 现在你想要知道，一共有多少种方案可以击败 Dark。
 * 注意，就算你第一步切断主要边之后就已经把 Dark 斩为两截，你也需要切断一条附加边才算击败了 Dark。
 * 输入格式
 * 第一行包含两个整数 N 和 M。
 * 之后 N – 1 行，每行包括两个整数 A 和 B，表示 A 和 B 之间有一条主要边。
 * 之后 M 行以同样的格式给出附加边。
 * 输出格式
 * 输出一个整数表示答案。
 * 数据范围
 * N≤100000,M≤200000,数据保证答案不超过2^31−1
 * 输入样例：
 * 4 1
 * 1 2
 * 2 3
 * 1 4
 * 3 4
 * 输出样例：
 * 3
 * 树上差分
 * 可以砍两刀,第一刀只能砍树边(主要边),第二刀只能砍非树边(附加边)
 * 问有多少种方案,可以使其不连通
 * 首先是一棵树.
 * 显然:如果只有树边,随便砍哪条都可以使其不连通,
 * 如果有非树边存在,使其连通,可以对路径上进行区间增加,
 * 树上差分,可以O(1)给某一个路径上的所有边加上权值
 * 再用O(n)的时间算出来每个点的权值是多少
 * lca(x,y)
 * 遍历每颗子树,对于权值和为0   +m
 * 权值和为1的  +1
 * 大于1的不操作
 */
public class 暗的连锁 {
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

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b, c;
        for (int i = 0; i < n - 1; i++) {
            a = nextInt();
            b = nextInt();
            add(a, b);
            add(b, a);
        }
        bfs();
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = lca(a, b);
            d[a]++;
            d[b]++;
            d[c] -= 2;
        }
        dfs(1, -1);
        System.out.println(ans);//求出每颗子树的总和是多少
    }

    private static void bfs() {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[1] = 1;
        q.add(1);

        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (depth[j] > depth[t] + 1) {
                    depth[j] = depth[t] + 1;
                    q.add(j);

                    fa[j][0] = t;
                    for (int k = 1; k <= 16; k++) {
                        fa[j][k] = fa[fa[j][k - 1]][k - 1];
                    }
                }
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }
        for (int k = 16; k >= 0; k--) {
            if (depth[fa[a][k]] >= depth[b]) {
                a = fa[a][k];
            }
        }

        if (a == b) return a;

        for (int k = 16; k >= 0; k--) {
            if (fa[a][k] != fa[b][k]) {
                a = fa[a][k];
                b = fa[b][k];
            }
        }
        return fa[a][0];
    }

    static int dfs(int u, int fa) {
        int res = d[u];
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                int s = dfs(j, u);
                if (s == 0) ans += m;
                else if (s == 1) ans++;
                res += s;
            }
        }
        return res;
    }

    static int N = 100010, M = N * 2, n, m, idx = 1, inf = 0x3f3f3f3f, ans = 0;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] d = new int[M];//差分数组
    static int[] depth = new int[N];
    static int[][] fa = new int[N][17];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
