package dp.树形dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104398681
 * 给定一棵树，树中包含 n 个结点（编号1~n）和 n−1 条无向边，每条边都有一个权值。
 * 请你在树中找到一个点，使得该点到树中其他结点的最远距离最近。
 * 输入格式
 * 第一行包含整数 n。
 * 接下来 n−1 行，每行包含三个整数 ai,bi,ci，表示点 ai和 bi之间存在一条权值为 ci的边。
 * 输出格式
 * 输出一个整数，表示所求点到树中其他结点的最远距离。
 * 数据范围
 * 1≤n≤10000,
 * 1≤ai,bi≤n,
 * 1≤ci≤10^5
 * 输入样例：
 * 5
 * 2 1 1
 * 3 2 1
 * 4 3 1
 * 5 1 1
 * 输出样例：
 * 2
 * 分别求出每个点能到达的最远距离是多少
 * 划分成两大类,第一类向上走,第二类向下走
 * 1.向下走的最远距离,直接return,用dist
 * 2.向上走的最远距离,
 * 做两遍dp
 * 1.用子节点更新父节点
 * 2.用父节点更新子节点
 */
public class 树的中心 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        dfs_1(1, -1);
        dfs_2(1, -1);
        int res = inf;
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, Math.max(d1[i], up[i]));
        }
        System.out.println(res);
    }

    static int n, m, N = 10010, M = N << 1, idx = 1, inf = 0x3f3f3f3f;
    static int[] h = new int[N], e = new int[M], ne = new int[M], w = new int[M];
    static int[] d1 = new int[N], d2 = new int[N], up = new int[N], p1 = new int[N], p2 = new int[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int dfs_1(int u, int fa) {
        d1[u] = d2[u] = -inf;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            int d = dfs_1(j, u) + w[i];
            if (d >= d1[u]) {//记录子树中的最长路,和次长路
                d2[u] = d1[u];
                d1[u] = d;
                p2[u] = p1[u];//记录u节点从哪来的.
                p1[u] = j;
            } else if (d > d2[u]) {
                d2[u] = d;
                p2[u] = j;
            }
        }

        if (d1[u] == -inf) d1[u] = d2[u] = 0;
        return d1[u];
    }

    static void dfs_2(int u, int fa) {
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            if (p1[u] == j) up[j] = Math.max(up[u], d2[u]) + w[i];
                //如果向上走是来过的,那么就找次短路
            else up[j] = Math.max(up[u], d1[u]) + w[i];
            dfs_2(j, u);
        }
    }
}
