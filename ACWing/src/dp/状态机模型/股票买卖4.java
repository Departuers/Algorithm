package dp.状态机模型;

import java.util.Scanner;

/**
 * O(nk)
 * https://blog.csdn.net/qq_30277239/article/details/104159301
 * 给定一个长度为 N 的数组，数组中的第 i 个数字表示一个给定股票在第 i天的价格。
 * 设计一个算法来计算你所能获取的最大利润，你最多可以完成 k笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。一次买入卖出合为一笔交易。
 * 输入格式
 * 第一行包含整数 N和 k，表示数组的长度以及你可以完成的最大交易数量。
 * 第二行包含 N个不超过 10000的正整数，表示完整的数组。
 * 输出格式
 * 输出一个整数，表示最大利润。
 * 数据范围
 * 1≤N≤10^5,
 * 1≤k≤100
 * 输入样例1：
 * 3 2
 * 2 4 1
 * 输出样例1：
 * 2
 * 输入样例2：
 * 6 2
 * 3 2 6 5 0 3
 * 输出样例2：
 * 7
 * 样例解释
 * 样例1：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 样例2：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。共计利润 4+3 = 7.
 * 分析：
 * 本题要求交易不超过k次交易赚取的最大利润，一次买卖为一次交易。
 * 根据上图的状态机，如果当前状态持有股票，到下一天可以选择继续持有，
 * 也可以选择卖出；如果当前状态未持仓，到下一天可以继续不持仓也可以买进。
 * 由于有k次交易的限制，状态表示时需要加入一维表示已经交易的次数，
 * 这里买入一次视为开始了一次交易。f[i][j][0]表示到第i天已经进行了j次交易且此时未持仓，
 * f[i][j][1]表示到第i天已经进行了j次交易且此时持有仓位。
 * 首先看要到达f[i][j][0]的状态前一天的状态可以是0或者1，如果前一天的状态是0，
 * 说明前一天未持仓且已经进行了j次交易，即f[i][j][0] = f[i-1][j][0]，
 * 如果前一天有持仓，说明第i天卖出了股票，
 * 第i-1天的状态是f[i-1][j][1]，f[i][j][0] = f[i-1][j][1] + w[i]，
 * 加上w是因为卖出到账w元，故f[i][j][0] = max(f[i-1][j][0],f[i-1][j][1]+w[i])。
 * 同理，要到达状态f[i][j][1]，若前一天状态是0，则第i天买入了股票，进行了第j次交易，
 * 前一天只进行了j-1次交易，即f[i][j][1] = f[i-1][j-1][0] - w[i]，
 * 减去w表示买入股票扣了w元；若前一天的状态是1，则说明第i天未进行操作，
 * f[i][j][1] = f[i-1][j][1]，故f[i][j][1] = max(f[i-1][j][1],f[i-1][j-1][0]-w[i])。
 * 从而状态转移方程就求出来了，下面考虑边界状态，f[i][0][0]表示第i天都未进行交易，
 * 收益是0，除此之外，f[i][j][0]与f[i][0][1]的初始状态都应该是不合法的，设置为-INF。
 * <p>
 * 引入一层状态用来存交易次数
 */
public class 股票买卖4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        o2();
        O3();
    }

    /**
     * f[i,j,0]表示第i天已经进行了j次交易且此时未持仓，
     * f[i,j,1]表示到第i天已经进行了j次交易且此时持有仓位。
     */
    static void O3() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int l = 0; l < 2; l++) {
                    f[i][j][l] = -0x3f3f3f3f;
                }
            }
        }//初始化负无穷
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i][0][0] = 0;//j=0的情况
            for (int j = 1; j <= k; j++) {//j=1~k的情况
                f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1] + a[i]);
                //卖出不消耗次数
                f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - a[i]);
                //昨天买的,今天不动,                   昨天交易次数为j-1没买,今天买上
                //买入消耗次数
            }
        }

        //查看进行i次交易,手中无货能得到利润最大值
        int res = 0;
        for (int i = 0; i <= k; i++) {
            res = Math.max(res, f[n][i][0]);
        }
        System.out.println(res);
    }

    // 优化空间为n^2
    static void o2() {
        for (int j = 0; j <= k; j++) {
            for (int l = 0; l < 2; l++) {
                dp[j][l] = -0x3f3f3f3f;
            }
        }
        dp[0][0] = 0;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= 1; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + a[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - a[i]);
            }
        }
        for (int i = 0; i <= k; i++) {
            res = Math.max(res, dp[i][0]);
        }
        System.out.println(res);
    }

    static int[] a = new int[100005];
    static int n, k, N = 100005, M = 105;
    static int[][][] f = new int[N][M][2];
    static int[][] dp = new int[M][2];
}
