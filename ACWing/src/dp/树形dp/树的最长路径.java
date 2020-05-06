package dp.树形dp;

import java.util.Scanner;

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
