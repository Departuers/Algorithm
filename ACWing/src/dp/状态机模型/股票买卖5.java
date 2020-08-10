package dp.状态机模型;

import java.util.Scanner;

/**
 * 给定一个长度为 N 的数组，数组中的第 i 个数字表示一个给定股票在第 i天的价格。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为1天)。
 * 输入格式
 * 第一行包含整数 N，表示数组长度。
 * 第二行包含 N个不超过 10000的正整数，表示完整的数组。
 * 输出格式
 * 输出一个整数，表示最大利润。
 * 数据范围
 * 1≤N≤10^5
 * 输入样例：
 * 5
 * 1 2 3 0 2
 * 输出样例：
 * 3
 * 样例解释
 * 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]，
 * 第一笔交易可得利润 2-1 = 1，第二笔交易可得利润 2-0 = 2，共得利润 1+2 = 3。
 */
public class 股票买卖5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        time();
    }

    /**
     * dp.线性dp
     */
    static void linear() {
        f[1][1] = -a[1];
        //第一天买入,就是-a[1],同理f[1][0]=0
        for (int i = 2; i <= n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + a[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 2][0] - a[i]);
        }
        System.out.println(f[n][0]);
    }

    /**
     * dp.状态机模型
     * https://blog.csdn.net/qq_30277239/article/details/104161014
     * f[i,0] 第i天手中有货
     * f[i,1] 手中无货的第1天,无法购买
     * f[i,2] 手中无货的第>=2天,可以购买
     */
    static void time() {
        dp[0][0] = dp[0][1] = (int) -1e9;//非法方案
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - a[i]);
            dp[i][1] = dp[i - 1][0] + a[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        System.out.println(Math.max(dp[n][1], dp[n][2]));//两个出口
    }

    static int[][] dp = new int[(int) (1e5 + 10)][3];
    static int[][] f = new int[(int) 1e5][2];
    static int[] a = new int[(int) (1e5 + 10)];
    static int n;
}
