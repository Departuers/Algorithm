package search;

import java.util.Scanner;

/**
 * 5 1
 * 5 1 3
 * 5 1 2
 * 5 1 1
 * 5 1 3 1
 * 5 1 3 1 1
 * 5 2 1
 * 5 2
 * 5 2 2
 * 5 3 1
 * 5 3 1 2
 * 5 3
 * 5 4
 * 5 5
 */
public class 序列计数 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        long s = System.nanoTime();
        int n = 1000;
        int ans[][] = new int[n + 1][n + 1];
        int jg = 0;
        for (int i = 1; i <= n; i++) {
            jg += jie(ans, n, i);
            jg %= 10000;
        }
        System.out.println(jg % 10000);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e9);
    }

    static int[] c = new int[1005];

    private static int jie(int[][] ans, int n, int m) {
        if (Math.abs(n - m) <= 1) return 1;
        if (ans[n][m] != 0) {
            return ans[n][m];
        }
        int te = 1;
        for (int i = 1; i < Math.abs(n - m); i++) {
            te = (te + jie(ans, m, i)) % 10000;
        }
        return ans[n][m] = te;
    }

    static int st(int n, int m) {
        return 0;
    }
}
