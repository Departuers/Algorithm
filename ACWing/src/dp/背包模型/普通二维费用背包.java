package dp.背包模型;

import java.util.Scanner;

/**
 * 有 N件物品和一个容量是 V 的背包，背包能承受的最大重量是 M
 * 每件物品只能用一次。体积是 vi
 * ，重量是 mi，价值是 wi
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，总重量不超过背包可承受的最大重量，且价值总和最大。
 * 输出最大价值。
 * 输入格式
 * 第一行两个整数，N，V, M
 * ，用空格隔开，分别表示物品件数、背包容积和背包可承受的最大重量。
 * 接下来有 N
 * 行，每行三个整数 vi,mi,wi，用空格隔开，分别表示第 i
 * 件物品的体积、重量和价值。
 * 输出格式
 * 输出一个整数，表示最大价值。
 * 数据范围
 * 0<N≤1000
 * 0<V,M≤100
 * 0<vi,mi≤100
 * 0<wi≤1000
 * 输入样例
 * 4 5 6
 * 1 2 3
 * 2 4 4
 * 3 4 5
 * 4 5 6
 * 输出样例：
 * 8
 * 分析:背包有重量和体积限制,每个物品可选可不选,求最大价值
 * 状态表示:f[i,j,k] 所有只从前i种物品选,总体积超过j,总重量不超过k的选法
 * 属性: max 最大价值
 * 状态划分:所有包含第i个物品的选法,所有不包含物品i的选法
 * 不选第i个物品:f[i-1,j,k]
 * 包含第i个物品:f[i-1,j-v[i],k-m[i]]]+w[i]
 * |_______| + 物品i
 * |_______| + 物品i
 * |_______| + 物品i
 * ...
 * |_______| + 物品i
 * 左边的最大值,选择范围是i-1,选上物品i合法,起码有j-v[i]的体积,k-m[i]的重量
 * 所以包含第i个物品:f[i-1,j-v[i],k-m[i]]+w[i]
 */
public class 普通二维费用背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            m[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        System.out.println(two());
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                for (int k = 0; k <= M; k++) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= v[i] && k >= m[i]) {
                        f[i][j][k] = Math.max(f[i - 1][j - v[i]][k - m[i]] + w[i], f[i][j][k]);
                    }
                }
            }
        }
        System.out.println(f[N][V][M]);
    }

    static int two() {
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = M; k >= m[i]; k--) {
                    dp[j][k] = Math.max(dp[j - v[i]][k - m[i]] + w[i], dp[j][k]);
                }
            }
        }
        return dp[V][M];
    }

    static int[] v = new int[1010], w = new int[1010], m = new int[1010];
    static int N, V, M;
    static int[][][] f = new int[1010][110][110];
    static int[][] dp = new int[110][110];
}
