package dp.背包模型;

import java.util.Scanner;

public class 完全背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        for (int i = 1; i < N; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        O3();
    }

    static int N, V;
    static int[] w = new int[1005];
    static int[] v = new int[1005];
    static int[][] dp = new int[1005][1005];
    static int[] f = new int[1005];

    static void O3() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];//至少有前i-1种物品可选,容量为v的价值
                if (j >= w[i])
                    dp[i][j] = Math.max(dp[i][j - w[i]] + v[i], dp[i][j]);
            }
        }
        System.out.println(dp[N][V]);
    }

    static void O2() {
        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        System.out.println(f[V]);
    }
}
