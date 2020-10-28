package dp.状态机模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 不限制买入卖出次数
 * https://www.acwing.com/solution/content/5037/
 * f[i,0]表示考虑前i天,且手中没有股票的最大利润
 * f[i,1]表示考虑前i天,且手中有股票的最大利润
 * f[i,1]=max(f[i-1,1]  f[i-1][0]-a[i]) 昨天没买今天买
 * f[i,0]=max(f[i-1][0],f[i-1][1]+a[i]) 昨天买了今天卖出去
 * 注意到f[1][i]可能是负数，要先初始化成负无穷
 * O(n)
 */
public class 股票买卖2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int[][] f = new int[n + 2][2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < 2; j++) {
                f[i][j] = -0x3f3f3f3f;
            }
        }
        f[0][0] = 0;
        f[0][1] = -a[0];
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + a[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] + a[i]);
        }
        System.out.println(Math.max(f[n][0], f[n][1]));
    }
}
