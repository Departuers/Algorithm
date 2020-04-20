package LCA;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://nekrozcoder.github.io/2019/07/28/%E3%80%90%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0%E3%80%91LCA%20%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/#more
 * 欧拉序是指对树进行dfs的一种序列
 * 到达每个节点就把它加入队列
 * 而dfs序则是第一次遇见该节点才加入队列
 * 如果求u和v的LCA就是u第一次出现的位置,和v第一次出现的位置
 * 中区间中深度最小的点
 * 对欧拉序列对应的深度数组建立ST表,除了维护最小值,还维护最小值对应的顶点
 * O(n log n)预处理
 * O(1)查询
 * 非常优秀的算法
 * 注意欧拉序要开2倍!
 * 预处理O(n log n) 在线
 * 查询O(1)
 * 洛谷3379 re4个..tle3个..
 */
public class 欧拉序ST求LCA {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        root = nextInt();
        for (int i = 1; i < n; i++) {
            add(nextInt(), nextInt());
        }
        init(root);
        for (int i = 0; i < m; i++) {
            bw.write(query(nextInt(), nextInt()) + "\n");
        }
        bw.flush();
    }

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

    static int[] log = new int[25];
    static int n, m, len = 0, cnt = 1, root;
    static final int maxn = 500005;
    static int[][] st = new int[maxn << 1][20];
    static int[] depth = new int[maxn << 1];//深度
    static int[] euler = new int[maxn << 1];//欧拉序
    static int[] first = new int[maxn];//第一次出现的位置
    static int[] e = new int[maxn << 1];
    static int[] he = new int[maxn];
    static int[] ne = new int[maxn << 1];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
        e[cnt] = a;
        ne[cnt] = he[b];
        he[b] = cnt++;
    }

    static void init(int root) {
        dfs(root, root, 1);
        log[1] = 0;
        for (int i = 2; i < 25; i++) {
            log[i] = log[i / 2] + 1;
        }
        for (int i = 1; i <= len; i++) {
            st[i][0] = i;
        }
        for (int j = 1; 1 << j <= len; j++) {
            for (int i = 1; i + (1 << j) - 1 <= len; i++) {
                st[i][j] = calc(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                //记录最浅的那个节点
            }
        }
    }

    static int calc(int x, int y) {
        return depth[x] < depth[y] ? x : y;
    }

    static void dfs(int u, int fa, int dep) {
        euler[++len] = u;
        depth[len] = dep;
        first[u] = len;
        for (int i = he[u]; i != 0; i = ne[i]) {
            if (e[i] != fa) {
                dfs(e[i], u, dep + 1);
                euler[++len] = u;
                depth[len] = dep;//回溯的时候将节点加入欧拉序列中
            }
        }
    }

    static int query(int x, int y) {
        int l = first[x], r = first[y];
        if (l > r) {
            int t = l;
            l = r;
            r = t;
        }
        int k = log[r - l + 1];
        return euler[calc(st[l][k], st[r - (1 << k) + 1][k])];
    }
}