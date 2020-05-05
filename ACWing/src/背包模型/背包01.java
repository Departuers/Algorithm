package 背包模型;

import java.util.Scanner;

/**
 * https://blog.csdn.net/xiji333/article/details/104226993
 * 2. 01背包问题
 * 状态定义:集合所有只从前i个物品选,且总体积不超过j的所有选法的集合
 * f[i,j]存的是最大价值
 * 属性:Max 价值最大
 * 状态计算:分成2个部分
 * 不选选第i个物品的最大价值  自然是f[i-1,j]
 * 选第i个物品的最大价值:
 * 当前价值为j,最少要有w[i]的体积装第i个物品,
 * 那么体积为为j-w[i]的且能选前i-1个物品的状态为 f[i-1,j-w[i]+v[i]]
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
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        System.out.println(f[V]);
    }

    static int N, V;//N是物品数量,V是背包容量
    static int[] w = new int[1005], v = new int[1005];
    static int[][] dp = new int[1005][1005];
    static int[] f = new int[1005];

}
