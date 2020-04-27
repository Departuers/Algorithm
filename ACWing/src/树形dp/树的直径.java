package 树形dp;

import java.util.Scanner;

/**
 * 给定一棵树，树中包含 n个结点（编号1~n）和 n−1 条无向边，每条边都有一个权值。
 * 现在请你找到树中的一条最长路径。
 * 换句话说，要找到一条路径，使得使得路径两端的点的距离最远。
 * 注意：路径中可以只包含一个点。
 * 输入格式
 * 第一行包含整数 n。
 * 接下来 n−1行，每行包含三个整数 ai,bi,ci，表示点 ai和 bi 之间存在一条权值为 ci 的边。
 * 输出格式
 * 输出一个整数，表示树的最长路径的长度。
 * 数据范围
 * 1≤n≤10000
 * 1≤ai,bi≤n,
 * −10^5≤ci≤10^5
 * 输入样例：
 * 6
 * 5 1 6
 * 1 4 5
 * 6 3 9
 * 2 6 8
 * 6 1 7
 * 输出样例：
 * 22
 */
public class 树的直径 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(b, a, c);
            add(a, b, c);
        }
        dfs(1, -1);
        System.out.println(ans);
    }

    static int dfs(int u, int fa) {
        int d1 = 0, d2 = 0;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            int d = dfs(j, u) + w[i];
            if (d > d1) {
                d2 = d1;
                d1 = d;
            } else if (d > d2) {
                d2 = d;
            }
        }
        ans = Math.max(ans, d1 + d2);
        return d1;
    }

    static int ans = 0;

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, cnt = 1;
    static int[] w = new int[10005];
    static int[] he = new int[10005];
    static int[] e = new int[10005];
    static int[] ne = new int[10005];
}