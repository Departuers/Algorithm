package 线性dp.LIS模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最长上升子序列 O(n^2)写法
 * 状态表示:f[i] 集合:所有以a[i]结尾的严格单调子序列
 * 属性肯定是max 最长的那个
 * <p>
 * 状态计算: f[i]
 * a[1] a[2] a[3] ...a[i-1] 空 表示所有以a[i]结尾的上升子序列
 * 空的话,就只有它自己也就是1
 * <p>
 * 不失一般性考虑a[k] 特判空
 * 集合划分就是求last
 * ......a[k] a[i]
 * ......a[k] a[i]
 * ......a[k] a[i]
 * ...
 * ......a[k] a[i]
 * <p>
 * 求的是上面那一堆的最大值,先求左半部分max就是f[k],再加上1
 * 如何描述左半部分呢,所有以a[k]结尾的最长上升子序列的最大值
 * a[k]和a[i]有可能不是单调上升
 * 只有a[k]<a[i]才构成上升子序列
 */
public class LIS {
    public static void main(String[] args) {
        int[] arr = {2, 4, 2, 3, 4};
        System.out.println(LIS(arr));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= N; i++) {//锁定以arr[i]结尾
            dp[i] = 1;
            for (int j = 1; j < i; j++) {//从1往后找直到arr[i]的前一个
                if (arr[i] > arr[j]) {//如果序列合法,才进行dp
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

    static int[] dp = new int[1010];
    static int[] arr = new int[1010];
    static int N;

    /**
     * 最牛逼的LeetCode题解
     *
     * @param nums
     * @return
     */
    static int LIS(int[] nums) {
        int t, len = 0;
        int[] dp = new int[nums.length + 1];
        for (int w : nums) {
            t = Arrays.binarySearch(dp, 0, len, w);
            if (t < 0) t = -(t + 1);
            dp[t] = w;
            if (t == len) len++;
        }
        return len;
    }
}
