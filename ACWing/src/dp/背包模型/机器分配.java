package dp.背包模型;

import java.util.Scanner;

/**
 * 总公司拥有M台 相同 的高效设备，准备分给下属的N个分公司。
 * 各分公司若获得这些设备，可以为国家提供一定的盈利。盈利与分配的设备数量有关。
 * 问：如何分配这M台设备才能使国家得到的盈利最大？
 * 求出最大盈利值。
 * 分配原则：每个公司有权获得任意数目的设备，但总台数不超过设备数M。
 * 输入格式
 * 第一行有两个数，第一个数是分公司数N，第二个数是设备台数M；
 * 接下来是一个N*M的矩阵，矩阵中的第 i 行第 j 列的整数表示第 i 个公司分配 j 台机器时的盈利。
 * 输出格式
 * 第一行输出最大盈利值；
 * 接下N行，每行有2个数，即分公司编号和该分公司获得设备台数。
 * 答案不唯一，输入任意合法方案即可。
 * 数据范围
 * 1≤N≤10,
 * 1≤M≤15
 * 输入样例：
 * 3 3
 * 30 40 50
 * 20 30 50
 * 20 25 30
 * 输出样例：
 * 70
 * 1 1
 * 2 1
 * 3 1
 * 分析：
 * 抽象为分组背包问题
 * 本题相当于AcWing 9 分组背包问题问题。每组物品要么一个不选，
 * 要么同一组最多选择其中的一个，
 * 本题每个公司可获得的设备数量也是只能选择一个，
 * 显然每个公司,可以选0个,1个,2个...m个 把每种选择看成一种物品,每一个物品的体积都是1
 *
 * 故状态表示f[i][j]表示在前i家公司中选择分配不超过j台设备的最大盈利。
 * 状态转移方程为f[i][j] = max(f[i-1][j-k]+w[i][k])，其中j >= k。
 * 另外题目要求输出任意一组合法方案，所以用个数组存储方案即可，具体第i组物品选与不选，
 * 如果选，选几个，只需要找出其中的一个k，
 * 划分依据第i组物品选第几个
 * 使得f[i][j] == f[i-1][j-k]+w[i][k]即可使得第i个物品选k个是合法的方案。
 */
public class 机器分配 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                w[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];//一个都不选
                for (int k = 1; k <= j; k++) {
                    //最多选k个,把k个物品
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - k] + w[i][k]);
                }
            }
        }
        System.out.println(f[n][m]);
        int j = m;
        for (int i = n; i != 0; i--) {//倒序往前找决策
            for (int k = 0; k <= j; k++) {
                //k从0开始使得存在不选第i组物品的方案
                if (f[i][j] == f[i - 1][j - k] + w[i][k]) {
                    way[i] = k;//这个是倒序存的,所以最终可以正序输出
                    j -= k;
                    break;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + way[i]);
        }

    }

    static int[] way = new int[20];
    static int[][] f = new int[20][20];
    static int n, m;
    static int[][] w = new int[20][20];
}
