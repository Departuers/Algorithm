package DFS.搜索;

import java.util.TreeSet;

/**
 * 枚举每个坑填什么
 * 第一个数只能填1
 * 输入正整数n，把整数1、2、3……、n组成一个环，使得相邻两个整数之和均为素数，输出时从整数1开始逆时针排序。同一个环应恰好输出一次。 n<==16。
 */
public class 素数环 {
    static int[] a = new int[1000];
    static int n = 20, ans = 0;
    static TreeSet<Integer> primer = new TreeSet<Integer>();

    static boolean isprimer(int p) {
        int q = (int) Math.sqrt(p);
        for (int i = 2; i <= q; i++) {
            if (p % i == 0) return false;
        }
        return true;
    }

    static boolean[] vis = new boolean[9999];
    static boolean[] st = new boolean[9999];

    static void dfs(int u) {
        if (u == n && !st[a[n - 1] + a[0]]) {
            ans++;
            return;
        }
        for (int i = 2; i <= n; i++) {
            if (check(u, i) && !vis[i]) {
                a[u] = i;
                vis[i] = true;
                dfs(u + 1);
                a[u] = 0;
                vis[i] = false;
            }
        }
    }

    /**
     * @param u
     * @param k
     * @return
     */
    private static boolean check(int u, int k) {
        if (u == 0) return true;
        if (!st[a[u - 1] + k])
            return true;
        return false;
    }

    static void init(int p) {
        for (int i = 2; i < p; i++) {
            if (!st[i]) {
                primer.add(i);
                for (int j = i * 2; j < p; j += i) {
                    st[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        init(9999);
        a[0] = 1;
        dfs(1);
        System.out.println(ans);
    }
}
