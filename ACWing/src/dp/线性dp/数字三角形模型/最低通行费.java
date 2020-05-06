package dp.线性dp.数字三角形模型;

import java.util.Scanner;

/**
 * 必须在(2N-1)的时间单位内穿过N*N的方格,求最小花费
 * 性质:N*N方格,走过去,最少需要2+2(n-1)也就是不走回头路,只能往右或者往下走
 */
public class 最低通行费 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j == 1) dp[i][j] = arr[i][j];//左上角特判
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    //判2次,是因为有条件
                    if (i > 1) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + arr[i][j]);
                    //如果行数大于1,才可以从上面转移过来
                    if (j > 1) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + arr[i][j]);
                    //列数大于1,才可以从左边过来,特判一下
                }
            }
        }
        System.out.println(dp[N][N]);
    }

    static int N;
    static int[][] arr = new int[104][104];
    static int[][] dp = new int[104][104];
}
