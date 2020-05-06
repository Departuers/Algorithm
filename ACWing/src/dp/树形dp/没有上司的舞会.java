package dp.树形dp;

import java.util.Scanner;

public class 没有上司的舞会 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            f[i][1] = sc.nextInt();
        }
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(b, a);
            hasf[a] = true;
        }
        int root = 1;
        while (hasf[root]) root++;
        dfs(root);
        System.out.println(Math.max(f[root][1], f[root][0]));
    }

    private static void dfs(int u) {
       // f[u][1] = happy[u];//选择了u就加上u的幸福指数
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            dfs(j);
            f[u][1] += f[j][0];//选当前节点,但不选子节点
            f[u][0] += Math.max(f[j][0], f[j][1]);
            //不选当前节点,是选还是不选子节点
        }
    }

    static int[][] f = new int[6010][6010];

    static void add(int u, int v) {
        e[cnt] = v;
        ne[cnt] = he[u];
        he[u] = cnt++;
    }

    static int n, cnt = 1;
    static int[] he = new int[6010];
    static int[] ne = new int[6010];
    static int[] e = new int[6010];
    static int[] happy = new int[6010];
    static boolean[] hasf = new boolean[6010];

}
