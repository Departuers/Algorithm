package graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的无向图，图中可能存在重边和自环。请你判断这个图是否是二分图。
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含两个整数u和v，表示点u和点v之间存在一条边。
 * 如果给定图是二分图，则输出“Yes”，否则输出“No”。
 * 数据范围
 * 1≤n,m≤10^5
 * 输入样例：
 * 4 4
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 输出样例：
 * Yes
 * 设G=(V,E)是一个无向图，如果顶点V可分割为两个互不相交的子集(A,B)，
 * 并且图中的每条边（i，j）所关联的两个顶点i和j分别属于这两个不同的顶点集(i in A,j in B)，
 * 则称图G为一个二分图。简单的说，就是让图中任意一条边上的两个顶点都属于不同的集合即可，比如下图：
 */
public class 染色判断二分图 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            add(b, a);
        }
        Arrays.fill(vis, -1);
        for (int i = 1; i <= n; i++) {
            if (vis[i] == -1)
                if (dfs(1, 0)) System.out.println("YES");
                else System.out.println("NO");
        }
        System.out.println(Arrays.toString(vis));
    }

    static boolean dfs(int u, int id) {
        vis[u] = id;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int ed = e[i];
            if (vis[ed] == -1) {
                if (!dfs(ed, 1 - id)) return true;
            } else if (vis[ed] == vis[u]) return false;
        }
        return true;
    }

    static int[] vis = new int[100005];
    static int[] he = new int[100005];
    static int[] ne = new int[200005];//无向图开2倍
    static int[] e = new int[200005];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, m, cnt = 1;
}
