package dp.背包模型;

import java.util.Scanner;

/**
 * 有一个箱子容量为V（正整数，0＜＝V＜＝20000），同时有n个物品（0＜n＜＝30），每个物品有一个体积（正整数）。
 * 要求n个物品中，任取若干个装入箱内，使箱子的剩余空间为最小。
 * 输入描述 Input Description
 * 一个整数n，表示有n个物品
 * 接下来n个整数，分别表示这n 个物品的各自体积
 * 输出描述 Output Description
 * 一个整数，表示箱子剩余空间。
 * 样例输入 Sample Input
 * 24
 * 6
 * 8
 * 3
 * 12
 * 7
 * 9
 * 7
 * 样例输出 Sample Output
 * 0
 * 弱01背包问题,同时把体积看做价值
 * f[i,j]代表前i个物品可选,体积为j最多选的体积是多少
 * 状态划分:选第i个物品,不选第i个物品
 * 状态计算:不选的话显然就是f[i-1,j]
 * 选的话,结合状态定义加上第i个物品的体积
 * |________|+v
 * 显然前面i的范围是i-1,能装下体积为v的物品的则是j-v
 * 最终则是f[i-1,j-v]
 * 求max ( f[i-1,j-v] ,f[i-1][j] )
 * 结合01背包优化思路,优化成1维
 */
public class 装箱问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        System.out.println(one());
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= V; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j]);
                if (j >= w[i])
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - w[i]] + w[i]);
            }
        }
        System.out.println(V - f[n][V]);
    }

    //优化成1维dp
    static int one() {
        for (int i = 1; i <= n; i++) {
            for (int j = V; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j - w[i]] + w[i], dp[j]);
            }
        }
        return V - dp[V];
    }

    static int[] dp = new int[20010];
    static int[][] f = new int[35][20010];
    static int[] w = new int[35];
    static int n, V;
}
