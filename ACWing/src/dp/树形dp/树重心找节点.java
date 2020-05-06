package dp.树形dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_43326267/article/details/89789239
 * 把每个节点看做根
 * 9
 * 1 2
 * 2 3
 * 2 4
 * 4 5
 * 1 6
 * 6 7
 * 7 8
 * 7 9
 */
public class 树重心找节点 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i < n; i++) {
            add(sc.nextInt(), sc.nextInt());
        }
        dfs(1);
        System.out.println(Arrays.toString(size));
        System.out.println(Arrays.toString(maxpart));
    }

    private static void dfs(int u) {
        vis[u] = true;
        size[u] = 1;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (vis[j]) continue;
            dfs(j);
            size[u] += size[j];
            maxpart[u] = Math.max(maxpart[u], size[j]);

        }
        maxpart[u] = Math.max(maxpart[u], n - size[u]);
        //算出以u为根的子树,u的父节点所在联通分量的节点数目
        ans = Math.min(ans, maxpart[u]);
    }
    //4, 5, 8, 7, 8, 5, 6, 8, 8,

    static int ans = Integer.MAX_VALUE;

    static int[] size = new int[10010];
    static int[] maxpart = new int[10010];
    static int[] he = new int[10900];
    static boolean[] vis = new boolean[10010];
    static int[] ne = new int[10900 * 2];
    static int[] e = new int[10900 * 2];
    static int cnt = 1, n;

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
        e[cnt] = a;
        ne[cnt] = he[b];
        he[b] = cnt++;
    }
}
