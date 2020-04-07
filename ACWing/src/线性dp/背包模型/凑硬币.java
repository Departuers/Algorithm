package 线性dp.背包模型;

import java.util.Scanner;

/**
 * 如果我们有面值为1元、3元和5元的硬币若干枚，
 * 如何用最少的硬币凑够11元？ (表面上这道题可以用贪心算法，
 * 但贪心算法无法保证可以求出解，比如1元换成2元的时候)
 * 状态定义:f[i][j]表示为,前面i中硬币可选,j元最少可以用多少枚硬币表示
 * 属性:min
 * 状态划分:不选第i种硬币 f[i-1,j],选1个f[i-1,j-v[i]]+1,选2个f[i-1,j-2*v[i]]+2 ,选3个...
 * 不失一般性选k个,f[i-1,j-k*v[i]]+k
 * 求min
 * 没想到吧,又是完全背包问题,哈哈哈哈哈
 * 优化成1维
 */
public class 凑硬币 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
        }
        System.out.println(one());


        for (int i = 0; i <= M; i++) {
            f[1][i] = i % v[1] == 0 ? i / v[1] : i / v[1] + 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= M; j++) {
                f[i][j] = f[i - 1][j];
                for (int k = 0; k * v[i] <= j; k++) {
                    f[i][j] = Math.min(f[i - 1][j - k * v[i]] + k, f[i][j]);
                }
            }
        }
        System.out.println(f[n][M]);
    }

    static int one() {
        for (int i = 0; i <= M; i++) {
            dp[i] = i % v[1] == 0 ? i / v[1] : i / v[1] + 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = v[i]; j <= M; j++) {
                dp[j] = Math.min(dp[j - v[i]] + 1, dp[j]);
            }

        }
        return dp[M];
    }

    static int[] dp = new int[13];
    static int[] v = new int[20];
    static int[][] f = new int[20][13];
    static int n, M;//n种硬币,M元
}
