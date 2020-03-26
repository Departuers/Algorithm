package dp;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 思路:问题的子数组看做一段区间,由起点或者终点确定一个子数组,两个点中,
 * 我们先确定一个点,再去找另一个点,比方说,如果我们确定一个子数组的截止元素在i这个位置,
 * 所以dp数组语义就是以i结尾的所有子数组中和最大的是多少,
 * 只有两种情况
 * 1.{i这个位置的元素,自己成了一个子数组}
 * 2.{i这个位置的元素,加上,以i-1结尾的所有子数组中的最大和}
 * 当把第i个问题,拆成第i-1个问题,之间的联系变得清晰,最优子结构就体现出来
 * dp[i]就是以i结尾的所有子数组中的最大值
 * dp方程=dp[i]=max(dp[i-1]+array[i],array[i])
 * //化简一下就成了：dp[i] = Math.max(dp[i - 1], 0) + array[i]
 * 我觉得化简,语义就不明显了.
 * 注意:子数组不能为空,所以要初始化dp[0]=array[0]
 */
public class 最大和连续子数组 {
    public static void main(String[] args) {
        int[] are = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr = {2, 4, -7, 5, 2, -1, 2, -4, 3};
        System.out.println(dpp(arr));
    }

    public static int[] dp;

    public static int dpp(int[] arr) {
        int ans = 0;//结果
        dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);//参考dp方程推理过程
            ans = Math.max(ans, dp[i]);//找出最大的结果
        }
        for (int i : dp) {
            System.out.print(i + " ");
        }
        System.out.println();
        return ans;
    }

    /**
     * 分治算法解最大和连续子数组
     * @return
     */
    public static int Fenzhi() {
        return 0;
    }
}
