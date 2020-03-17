package dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 某条街上每一公里就有一汽车站，乘车费用如下表：
 * 公里数 1 2 3 4 5 6 7 8 9 10
 * 费用 12 21 31 40 49 58 69 79 90 101
 * 而一辆汽车从不行驶超过10公里。
 * 某人想行驶n公里，假设他可以任意次换车，请你帮他找到一种乘车方案使费用最小（10公里的费用比1公里小的情况是允许的）。
 * Sample Input
 * 12 21 31 40 49 58 69 79 90 101       1-10公里的费用
 * 15
 * Sample Output
 * 147
 */
public class 最小乘车费用 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] gongli = new int[11];
        for (int i = 1; i <= 10; i++) {
            gongli[i] = sc.nextInt();
        }
        int[] dp = Arrays.copyOf(gongli, 1001);
        Arrays.fill(dp, 11, dp.length, Integer.MAX_VALUE);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {//n是要行驶公里
            for (int j = 10; j >= 1; j--) {
                if (i >= j) {
                    dp[i] = Math.min(dp[i], dp[i - j] + gongli[j]);
                }
            }
        }
        System.out.println(dp[n]);
    }
}
