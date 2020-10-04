package dp.树形dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104396460
 * 给定一棵树，树中包含 n个结点（编号1~n）和 n−1 条无向边，每条边都有一个权值。
 * 现在请你找到树中的一条最长路径。
 * 换句话说，要找到一条路径，使得使得路径两端的点的距离最远。
 * 注意：路径中可以只包含一个点。
 * 输入格式
 * 第一行包含整数 n。
 * 接下来 n−1行，每行包含三个整数 ai,bi,ci，表示点
 * ai和 bi 之间存在一条权值为 ci 的边。
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
 * 平凡做法:从一个点u,找离u的点v
 * 再从v开始走,走到离v最远的点z
 * 那么v,z就是直径
 */
public class 树的最长路径 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        n = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        dfs(1, -1);
        System.out.println(ans);
    }

    //树只能往下走,不能往上走
    private static int dfs(int u, int fa) {
        int dist = 0;//表示从当前点往下走的最大长度
        int d1 = 0, d2 = 0;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (fa == j) continue;
            int d = dfs(j, u) + w[i];
            dist = Math.max(dist, d);
            if (d >= d1) {
                d2 = d1;
                d1 = d;
            } else if (d > d2) d2 = d;
        }
        ans = Math.max(d1 + d2, ans);
        return dist;
    }

    static int n, N = 10010, M = N * 2, cnt = 1, ans;
    static int[] he = new int[N];
    static int[] ne = new int[M];
    static int[] e = new int[M];
    static int[] w = new int[M];

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

}
