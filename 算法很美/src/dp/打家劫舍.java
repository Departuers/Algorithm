package dp;

import java.util.Arrays;

/**
 * LeetCode 第 198 号问题：打家劫舍。
 * https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247486936&idx=1&sn=27ec53c1463384edeeee138db23c1c7d&chksm=fa0e6259cd79eb4f240786ad7c00dcd0ed39ad68fd62b72e15d6a8ee0ecbd26b6250ab3b1171&scene=21#wechat_redirect
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下
 * ，能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 如果我们要求解抢完 n 个房子所获得的最大收入，因为题目的要求，我们可以思考第 i 个房子是否应该抢，
 * 如果要抢，那么第 i - 1 个房子就不能抢，我们只能考虑抢第 i - 2 个房子。如果不抢，
 * 那么就可以抢第 i - 1 个房子，这样一来，
 * 第 i 个房子就和第 i - 1 个房子，以及第 i - 2 个房子联系上了。
 * <p>
 * 状态定义
 * 通过之前的问题拆解，我们知道，如果我们从左到右去抢房子，
 * 抢到当前房子可以获得的最大值其实是和抢到前两个房子可以获得的最大值有关，
 * 因此我们可以用 dp[i] 表示抢到第 i 个房子可以获得的最大值
 * <p>
 * 递推方程
 * 如果我们抢第 i 个房子，那么我们就只能去考虑第 i - 2 个房子，如果不抢，
 * 那么我们可以考虑第 i - 1 个房子，于是递推方程就有了：
 * <p>
 * dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
 * 实现
 * 因为第 i 个位置和前面的两个位置都有关，这个时候我们可以把状态多开一格，
 * dp[0] 表示的是一个房子都不抢的状态，dp[1] 就是最左边的房子获得的最大价值，
 * 这个房子之前也没有其他的房子，直接抢即可。
 */
public class 打家劫舍 {
    public static void main(String[] args) {
        int[] tem = {1, 2, 3, 1};
        int[] arr = {2, 7, 9, 3, 1};
        System.out.println(Qing(tem));

        int[] a1 = {2, 3, 2};
        int[] a2 = {1, 2, 3, 1};
        System.out.println(Jie(a1));
        System.out.println(Jie(a2));
    }

    public static int[] dp;

    /**
     * @param nums 每个房屋能抢劫到的钱的表
     */
    public static int Qing(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }

    /**
     * LeetCode 第 213 号问题：打家劫舍II。
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都
     * 围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     * 示例 1:
     * 输入: [2,3,2]
     * 输出: 3
     * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * <p>
     * 思路:单独处理第一个房间和最后一个房间,
     * 房子排列方式是一个圆圈意味着之前的最后一个房子和第一个房子之间产生了联系，
     * 这里有一个小技巧就是我们线性考虑 [0, n - 2] 和 [1, n - 1]，然后求二者的最大值。
     * 其实这么做的目的很明显，把第一个房子和最后一个房子分开来考虑。
     * <p>
     * 非常精巧的思想,
     * 如果首尾相连,那分成两个数组,(0,nums.length-1)还有(1,nums.length)
     * 这样就不会冲突
     *
     * @param nums 每个房屋能抢劫到的钱的表
     * @return 最多能抢到多少钱
     */
    public static int Jie(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        return Math.max(RoBi(Arrays.copyOfRange(nums, 0, n - 1)),
                RoBi(Arrays.copyOfRange(nums, 1, n)));
        //非常精巧的思想,
        //如果首尾相连,那分成两个数组,(0,nums.length-1)还有(1,nums.length)
        //这样就不会冲突,可以完美处理冲突
    }

    public static int RoBi(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] dp = new int[n + 1];

        dp[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }// dp[i] 表示抢到第 i 个房子可以获得的最大值
        // 如果要当前第i个,再要i-2,就不冲突,(dp[i]+dp[i-2])和(dp[i-1])取较大值
        return dp[n];
    }
}
