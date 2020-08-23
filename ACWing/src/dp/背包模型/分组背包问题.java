package dp.背包模型;

import java.util.Scanner;

/**
 * 有N组物品,每个组只能选1个,
 * 求最大价值
 * 转换成01背包问题,
 * 状态定义为f[i,j]:前i组物品可选,体积不超过j的所有选法最大值
 * 状态划分:选第i组物品的第1个,选第i组物品的第2个,选第i组物品的第s[i]个,不选第i组物品
 * 状态计算:显然不选第i组物品就是f[i-1,j]
 * |________|+w[i][1]
 * |________|+w[i][2]
 * ....
 * |________|+w[i][k]
 * k代表地i组一共有多少物品
 * 前面的最大值如何计算
 * 前面的可以取的范围i-1组的物品,要选第i组的第k个物品,就要腾出来这么多
 * 那么结合状态定义,最大价值就是:f[i-1,j-v[i][k]]+w[i][k]
 * 选第i组物品的计算:选第i组物品的第k个,显然就是f[i-1,j-v[i][k]]+w[i][k]
 * 空间复杂度可以优化成1维,最后还是O(n^2LogN)
 */
public class 分组背包问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        V = sc.nextInt();
        int t;
        for (int i = 1; i <= n; i++) {//n组
            s[i] = sc.nextInt();//每一组有t个
            for (int j = 0; j < s[i]; j++) {
                v[i][j] = sc.nextInt();
                //第i组,第j个
                w[i][j] = sc.nextInt();
            }
        }
        for (int i = 1; i <= n; i++) {//枚举每一组
            for (int j = V; j >= 0; j--) {//枚举体积
                for (int k = 0; k < s[i]; k++) {//枚举第i组
                    if (v[i][k] <= j) {
                        //可以选第i组,第k个值
                        dp[j] = Math.max(dp[j - v[i][k]] + w[i][k], dp[j]);
                    }
                }
            }
        }
        System.out.println(dp[V]);
    }

    static int[] s = new int[105];
    static int[][] v = new int[104][105];
    static int[][] w = new int[104][105];
    static int[] dp = new int[105];
    static int n, V;
}
