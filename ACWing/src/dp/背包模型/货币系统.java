package dp.背包模型;

import java.util.Scanner;

/**
 * 给你一个n种面值的货币系统，求组成面值为m的货币有多少种方案。
 * 输入格式
 * 第一行，包含两个整数n和m。
 * 接下来n行，每行包含一个整数，表示一种货币的面值。
 * 输出格式
 * 共一行，包含一个整数，表示方案数。
 * 数据范围
 * n≤15,m≤3000
 * 输入样例：
 * <p>
 * 3 10
 * 1
 * 2
 * 5
 * 输出样例：
 * 10
 * 分析：
 * 本题与上一题买书问题基本一模一样，只是方案数可能很大，需要用long long来存储。
 * 状态表示：f[i][j]表示用前i种面值的货币组成面值为j的方案数，
 * 状态转移方程为f[i][j] = f[i-1][j] + f[i][j-v]，边界状态为f[0][0] = 1。
 * 完全背包问题
 */
public class 货币系统 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
        }
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = v[i]; j <= m; j++) {
                f[j] += f[j - v[i]];
            }
        }
        System.out.println(f[m]);
    }

    static int[] v = new int[20];
    static long[] f = new long[3010];
    static int n, m;
}
