package 线性dp.背包模型;

import java.util.Scanner;

/**
 * 2. 01背包问题
 */
public class 背包01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        int a, b;
        for (int i = 1; i <= N; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w[i] = a;
            v[i] = b;
        }
        // zi();
        you();
    }

    //朴素做法
    private static void zi() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        System.out.println(dp[N][V]);
    }

    static void you() {
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= w[i]; j--) {
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }
        System.out.println(f[V]);
    }

    static int N, V;//N是物品数量,V是背包容量
    static int[] w = new int[1005], v = new int[1005];
    static int[][] dp = new int[1005][1005];
    static int[] f = new int[1005];

}
