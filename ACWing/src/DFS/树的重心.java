package DFS;

import java.util.Scanner;

public class 树的重心 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            add(b, a);
        }
        dfs(1);
        System.out.println(ans);
    }

    private static int dfs(int u) {
        vis[u] = 1;
        int size = 0, sum = 0;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (vis[j] == 1) continue;
            int s = dfs(j);
            size = Math.max(size, s);
            sum += s;//加上孩子节点的个数
        }
        size = Math.max(size, n - sum - 1);
        ans = Math.min(ans, size);
        return sum + 1;
    }

    static int n, cnt, ans=Integer.MAX_VALUE;
    static int[] he = new int[100100];
    static int[] ne = new int[200100];
    static int[] e = new int[200100];
    static int[] vis = new int[100100];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }
}
