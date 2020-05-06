package dp.线性dp.LIS模型;

import java.util.Scanner;

/**
 * 状态定义:f[i] 就是以a[i]结尾的最长上升子序列的和
 * f[i]就可以分成j个计算
 * 空 f[1] f[2] ... f[i-1]
 * 空的话就是arr[i],接不了
 * f[i]=max(f[k]+arr[i],f[i])
 * 循环求接在谁后面最大
 * arr[i]可以接在arr[j]后面 ,那么arr[i]一定大于arr[j]才存在
 *
 */

public class 最大上升子序列和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = arr[i];//起码都是它自
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        System.out.println(ans);
    }

    static int N;
    static int[] arr = new int[1005];
    static int[] dp = new int[1005];

}
