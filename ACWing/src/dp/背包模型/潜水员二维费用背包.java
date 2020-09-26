package dp.背包模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 潜水员为了潜水要使用特殊的装备。
 * 他有一个带2种气体的气缸：一个为氧气，一个为氮气。
 * 让潜水员下潜的深度需要各种的数量的氧和氮。
 * 潜水员有一定数量的气缸。每个气缸都有重量和气体容量。
 * 潜水员为了完成他的工作需要特定数量的氧和氮。
 * 他完成工作所需气缸的总重的最低限度的是多少？
 * 例如：潜水员有5个气缸。每行三个数字为：
 * 氧，氮的（升）量和气缸的重量：
 * 3 36 120
 * 10 25 129
 * 5 50 250
 * 1 45 130
 * 4 20 119
 * 如果潜水员需要5升的氧和60升的氮则总重最小为249（1，2或者4，5号气缸）。
 * 你的任务就是计算潜水员为了完成他的工作需要的气缸的重量的最低值。
 * 第一行有2整数m，n（1≤m≤21，1≤n≤79）。它们表示氧，氮各自需要的量。
 * 第二行为整数k（1≤n≤1000）表示气缸的个数。
 * 此后的k行，每行包括ai，bi，ci（1≤ai≤21，1≤bi≤79，1≤ci≤800）3
 * 整数。这些各自是：第i个气缸里的氧和氮的容量及汽缸重量。
 * 【输出】
 * 仅一行包含一个整数，为潜水员完成工作所需的气缸的重量总和的最低值。
 * 【输入样例】
 * 5 60
 * 5
 * 3 36 120
 * 10 25 129
 * 5 50 250
 * 1 45 130
 * 4 20 119
 * 【输出样例】
 * 249
 * 最低状态定义稍有区别,状态定义恰好和至少的区别做法就不一样
 * 显然三个东西,看如何划分:
 * 物品: 氧气   氮气    重量
 * a      b      c
 * 费用1  费用2   价值最小
 * >=m    >=n
 * 价值要求最低
 * 费用1恰好是j,费用2恰好是k
 * 状态定义改变:f[i,j,k]表示为:所有从前i种物品选,总氧气至少是j,总氮气至少是k的选法
 * 属性:Min 最小重量
 * 不选第i种物品:f[i-1,j,k]
 * <p>
 * 背包恰好和不超过,在初始化的时候有区别
 * f[0,j,k]=在状态定义小于等于j,小于等于k的时候是合法的
 * 在状态定义恰好是j,恰好是k的时候
 * f[0,0,0]=0 是合法的
 * f[0,j,k]=是非法的,因为只选0个不可能达到恰好是j,或者k
 * 应该f[0,j,k]=正无穷
 */
public class 潜水员二维费用背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();//需要的氧气
        m = sc.nextInt();//需要的氮气
        shu = sc.nextInt();//有多少个气缸
        for (int i = 1; i <= shu; i++) {
            v1[i] = sc.nextInt();
            v2[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        two();
    }

    static int n, m, shu;
    static int[] v1 = new int[1010], v2 = new int[1010], w = new int[1010];
    static int[][][] f = new int[23][88][1010];
    static int[][] dp = new int[5000][1600];

    static void two() {
        for (int[] ints : dp) {
            Arrays.fill(ints, 0x3f3f3f3f);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= shu; i++) {
            for (int j = n; j >= 0; j--) {
                for (int k = m; k >= 0; k--) {
                    dp[j][k] = Math.min(dp[j][k], dp[Math.max(j - v1[i], 0)][Math.max(k - v2[i], 0)] + w[i]);
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}