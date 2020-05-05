package 背包模型;

import java.util.Scanner;

/**
 * 完全背包变形多重背包:每样物品最多选s[i]个
 * 同理状态定义:f[i,j]代表体积为j前i个物品可选的最大价值
 * 状态划分:选0个第i个物品,选1个第i个物品,选2个第i个物品,选3个第i个物品....选s[i]个第i个物品
 * 状态计算,不失一般性,选k个:f[i-1,j-k*v[i]]+w[i] 只有在j>=k*v[i]并且k<=s[i]的时候才合法
 */
public class 多重背包1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k <= s[i] && j >= k * v[i]; k++) {
                    dp[i][j] = Math.max(dp[i - 1][j - k * v[i]] + k * w[i], dp[i][j]);
                }
            }
        }
        System.out.println(dp[N][V]);
    }

    static int[][] dp = new int[105][105];
    static int[] s = new int[105], w = new int[105], v = new int[105];
    static int N, V;
}
