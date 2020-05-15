package DFS.搜索顺序;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104799213
 * 6
 * 14 20 33 117 143 175
 */
public class 分成互质组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        dfs(1, 0, 0, 0);
        System.out.println(ans);
    }

    static boolean check(int[] g, int gc, int i) {
        for (int j = 0; j < gc; j++) {
            if (gcd(a[g[j]], a[i]) > 1) return false;
        }
        return true;
    }

    //g代表当前哪一组,gc代表枚举到组内第几个元素tc表示当前一共处理多少数,start代表组内从哪个数开始枚举
    static void dfs(int g, int gc, int tc, int start) {
        if (g >= ans) return;
        if (tc == n) {
            ans = g;
        }
        boolean f = true;
        for (int i = start; i < n; i++) {
            if (!vis[i] && check(group[g], gc, i)) {
                vis[i] = true;
                group[g][gc] = i;
                dfs(g, gc + 1, tc + 1, i + 1);
                vis[i] = false;
                f = false;
            }
        }
        if (f) dfs(g + 1, 0, tc, 0);
    }

    static int n, ans = 10;
    static int[] a = new int[10];
    static int[][] group = new int[10][10];
    static boolean[] vis = new boolean[10];

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
