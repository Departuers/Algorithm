package dp;

import java.util.Arrays;

/**
 * LeetCode 第 300 号问题
 * 最长递增子序列(最长上升子序列)
 * LIS最优实现:
 * 设dp为动态规划辅助数组,dp[0]不存值,也没有任何意义
 * dp[i]代表:长度为i的最长递增子序列末尾的数(源数组的数)
 * 4, 2, 3, 1, 5, 6
 * 最后最长递增子序列的值,是索引
 */
public class LIS {
    public static void main(String[] args) {
        // int[] arr = {4, 2, 3, 1, 5, 2, 6};
        int i=1;
        System.out.println(-i);
//        for (int i = 0; i < 1000000; i++) {
//            int[] arr = random(200);
//            if (dpBest(arr) != dp(arr, arr.length))
//                System.out.println("no");
//        }
    }


    public static int[] random(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int d = (int) (Math.random() * 100);
            arr[i] = d;
        }
        return arr;
    }

    public static int[] dp;

    /**
     * O(N logN)最优实现,LeetCode官方题解非常牛逼
     *
     * @param nums
     * @return
     */
    public static int dpBest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public static int indexOfFirstBigger(int[] dp, int v, int l, int r) {
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (dp[mid] > v) {
                r = mid;  //保留大于v的下标以防这是第一个
            } else {
                l = mid + 1;
            }
            if (l == r && dp[l] > v)
                return l;
        }
        return -1;
    }

    /**
     * O(n^2)级别的算法,效率比较低,非最优实现
     * 最长递增子序列的长度
     * 比如:4, 2, 3, 1, 5, 6
     * 输出4
     * 4, 2, 3, 1, 5
     * 输出3
     * {60, 1, 69, 68, 29, 97, 5, 10, 3, 64}
     * 输出4(1,5,10,64)
     * [46, 8, 93, 65, 96, 14, 14, 21, 43, 77]
     * 输出5(8,14,21,43,77)
     * 思路:动态规划,一维数组
     * 横排下标所表示,包含当前元素,及其之前的最长递增子序列,
     * dp数组索引含义:一定以当前元素结尾,保存的值就是源数组当前元素和之前元素的最大递增子序列
     * 每次把当前元素当做结尾元素,往前遍历,找到满足条件:也就是当前元素比前一个元素大,就把前一个动态规划产生的值+1作为候选值
     * 当前元素,也要和序列之前的所有元素做比较,如果当前元素比较大,就把那个元素所指向的动态规划数组的值+1,作为候选值
     * 最后当前元素由候选值数组的最大值决定
     * 数组索引: 0    1    2    3   4   5
     * <p>
     * 源数组:  4    2    3    1   5   6
     * dp数组  1    1    2    1   3   4
     * <p>
     * dp[0]初始化为1,有一堆候选值选最大的
     *
     * @param nums 需要求的源数组
     * @param n    数组的长度
     * @return 最长递增子序列的长度
     */
    public static int dp(int[] nums, int n) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {//以i为结尾
            int cnt = 1;
            for (int j = i - 1; j >= 0; j--) {//从当前索引-1往前找
                if (nums[i] > nums[j]) {//从源数组中找到比当前元素还小的值,就把在dp数组中求出的值+1与cnt作比较,并更新cnt
                    //for结束后,最后cnt表示以arr[i]结尾,之前的最长递增子序列的长度
                    cnt = Math.max(cnt, dp[j] + 1);
                }
            }
            dp[i] = cnt;//从候选值中求出最大值后赋值给辅助dp数组
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(dp[i], ans);//寻找dp数组中最大的元素,因为不一定最长递增子序列在最后
        }
        return ans;
    }

    /**
     * ：最长上升子序列。与LIS相同,另一种实现,思路
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 示例:
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     * 思路与上一题类似:
     * dp数组语义,dp[i] 表示以位置 i 结尾的子序列的最大长度
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] -> the longest length sequence from 0 - i, and must include nums[i]
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);//初始化dp数组为1,因为每个子序列可以只有1个元素
        int max = 0;//记录最长的序列

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {//寻找i之前的元素
                if (nums[i] > nums[j])//基准元素,arr[i],找在它之前的比它小的元素
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            max = Math.max(max, dp[i]);//寻找dp数组中最大的元素,因为不一定最长递增子序列在最后
        }
        return max;
    }

    /**
     * 暴力解法,找到打头的数字,i就是打头开始,p就是每次找到的第二个,该算法本身就是bug,这个算法没啥用
     * 没有参考价值,测试一半通不过,AC看脸
     * 打头之后就直接找递增了,无法确定第二个元素,
     * 只有在最长递增子序列求出的元素,打头和第二个相邻,该算法才能正确输出
     *
     * @param arr 需要寻找最长递增子序列的长度的数组
     * @return 长度
     */
    public static int Baoli(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {//打头
            int p = i;
            int cnt = 1;
            for (int j = i + 1; j < arr.length; j++) {//从头往后找
                if (arr[j] > arr[p]) {
                    cnt++;
                    p = j;
                }
            }
            if (max < cnt)
                max = cnt;
        }
        return max;
    }

}
