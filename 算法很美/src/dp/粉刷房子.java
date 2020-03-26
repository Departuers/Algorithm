package dp;

import java.util.Arrays;

/**
 * LeetCode 第 256 号问题：粉刷房子,非常精妙,
 * https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247486936&idx=1&sn=27ec53c1463384edeeee138db23c1c7d&chksm=fa0e6259cd79eb4f240786ad7c00dcd0ed39ad68fd62b72e15d6a8ee0ecbd26b6250ab3b1171&scene=21#wechat_redirect
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
 * 例如，costs[0][0]表示第 0 号房子粉刷成红色的成本花费；costs[1][2]表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 * 所有花费均为正整数。
 * 示例：
 * 输入: [[17,2,17],[16,16,5],[14,3,19]]  //顺序红色、蓝色或者绿色
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 * 最少花费: 2 + 5 + 3 = 10。
 */
public class 粉刷房子 {
    public static void main(String[] args) {
        int[][] arr = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        System.out.println(Fen(arr));
        int[][] arrr = {{17, 2, 17, 4, 6}, {16, 2, 5, 16, 1}, {14, 11, 12, 15, 4}};

        System.out.println(S(arrr));//4,1,4

        System.out.println(minCostII(arrr));////4,1,4


        {//测试
            int[] ran = LIS.random(23);
            System.out.println(Arrays.toString(ran));
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int l = 0; l < ran.length; ++l) {
                if (min1 > ran[l]) {
                    min2 = min1;//如果最小的那个元素在数组末尾,会导致min2,不是次最小元素,所以这么写
                    min1 = ran[l];
                    minIndex = l;
                } else if (min2 > ran[l]) {
                    min2 = ran[l];
                }
            }
            System.out.println("---");
            System.out.println(min1);
            System.out.println(min2);
            System.out.println(minIndex);
            Arrays.sort(ran);
            System.out.println(Arrays.toString(ran));
        }

    }

    public static int[][] dp;

    /**
     * 问题拆解
     * 对于每个房子来说，都可以使用三种油漆当中的一种，如果说不需要保证相邻的房子的颜色必须不同，
     * 那么整个题目会变得非常简单，每个房子直接用最便宜的油漆刷就好了，但是加上这个限制条件，
     * 你会发现刷第 i 个房子的花费其实是和前面 i - 1 个房子的花费以及选择相关，如果说我们需要知道第 i 个房子使用第 k 种油漆的最小花费，
     * 那么你其实可以思考第 i - 1 个房子如果不用该油漆的最小花费，这个最小花费是考虑从 0 到当前位置所有的房子的。
     * 状态定义
     * 通过之前的问题拆解步骤，状态可以定义成 dp[i][k]，表示如果第 i 个房子选择第 k 个颜色，
     * 那么从 0 到 i 个房子的最小花费
     * 递推方程
     * 基于之前的状态定义，以及相邻的房子不能使用相同的油漆，那么递推方程可以表示成：
     * dp[i][k] = Math.min(dp[i - 1][l], ..., dp[i - 1][r]) + costs[i][k]， l != k, r != k
     * 实现
     * 因为我们要考虑 i - 1 的情况，但是第 0 个房子并不存在 i - 1 的情况，因此我们可以把第 0 个房子的最小花费存在状态数组中，当然你也可以多开一格 dp 状态，其实都是一样的。
     * 对于这道题目，你可能会问这不是和矩阵类动态规划类似吗？
     * 如果单从房子来考虑的确是，但是对于颜色的话，我们必须考虑考虑相邻房子的所有颜色，这就有点序列的意思在里面了。
     */
    public static int Fen(int[][] nums) {
        if (nums.length == 0 || nums[0].length == 0) {
            return 0;
        }//没有东西,直接返回0
        int n = nums.length;
        dp = new int[n][3];//由于只有三种油漆,刷第 i 个房子的花费其实是和前面 i - 1 个房子的花费以及选择相关
        for (int i = 0; i < nums[0].length; i++) {
            dp[0][i] = nums[0][i];
        }//先初始化第一行,要理解dp数组语义
        for (int i = 1; i < n; i++) {//找到最优解,动态规划
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + nums[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + nums[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + nums[i][2];
        }//状态可以定义成 dp[i][k]，表示如果第 i 个房子选择第 k 个颜色
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][2], dp[n - 1][1]));//最后的结果选最小的
    }

    /**
     * LeetCode 第 265 号问题：粉刷房子II。
     * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
     * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
     * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
     * 所有花费均为正整数。
     * 示例：
     * 输入: [[1,5,3],[2,9,4]]
     * 输出: 5
     * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
     * 或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
     * 进阶：
     * 您能否在 O(nk) 的时间复杂度下解决此问题？
     * <p>
     * 现在有k种油漆,
     * 对于第 i 个房子的每种颜色，我们对比看第 i - 1 个房子的 k 种油漆，找到不相重的最小值就好，但是这里的时间复杂度是 O(n*k^2)。
     * 其实这是可以优化的，我们只需要在第 i - 1 个位置的状态中找到最大值和次大值，在选择第 i 个房子的颜色的时候，
     * 我们看当前颜色是不是和最大值的颜色相重，不是的话直接加上最大值，如果相重的话，我们就加上次大值，这样一来，
     * 我们把两个嵌套的循环，拆开成两个平行的循环，时间复杂度降至 O(n*k)。
     * dp数组语义不变
     *
     * @param costs
     * @return
     */
    public static int S(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }//合法性判断

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];

        for (int i = 1; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }//初始化

        for (int i = 0; i < k; ++i) {
            dp[0][i] = costs[0][i];
        }//把油漆第一行的元素,赋值给dp数组第一行

        {//算法精髓
            for (int i = 1; i < n; ++i) {//行数
                for (int j = 0; j < k; ++j) {//列数
                    for (int m = 0; m < k; ++m) {//寻找与前一个房子不同的k
                        if (m != j) {//,这里只要保持列数不同,就能保证不同颜色
                            dp[i][m] = Math.min(dp[i][m], dp[i - 1][j] + costs[i][m]);//寻找与上一个房子不同颜色的最小的那个颜色
                        }
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            result = Math.min(result, dp[n - 1][i]);//最后的结果,从所有的颜色中找出最小的花费
        }

        return result;
    }

    /**
     * 最优版本LeetCode 第 265 号问题：粉刷房子II。
     * O(n*k)
     *
     * @param costs
     * @return
     */
    public static int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }//合法性判断

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];

        for (int i = 1; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < k; ++i) {
            dp[0][i] = costs[0][i];
        }

        {//算法精髓,把三个嵌套的循环拆成两个平行的循环,达到O(n*k)的复杂度
            for (int i = 1; i < n; i++) {
                int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
                //min1是这算出来的上面那一排的最小花费,min2是这上面那一排的次最小花费,
                //结合dp数组语义
                int minIndex = -1;
                for (int l = 0; l < k; l++) {
                    if (min1 > dp[i - 1][l]) {
                        min2 = min1;//如果最小的那个元素在数组末尾,会导致min2,不是次最小元素,所以这么写
                        min1 = dp[i - 1][l];//最小元素(花费)算出来
                        minIndex = l;
                    } else if (min2 > dp[i - 1][l]) {
                        min2 = dp[i - 1][l];
                    }
                }//精髓,找出,minIndex代表颜色,是第几列,把最小元素也就是min1的索引赋值给minIndex

                for (int j = 0; j < k; j++) {
                    if (minIndex != j) {
                        dp[i][j] = Math.min(dp[i][j], min1 + costs[i][j]);//把最小的花费,的颜色,算出来第i个房子是什么颜色,
                    } else {
                        dp[i][j] = Math.min(dp[i][j], min2 + costs[i][j]);
                        //如果第i-1个房子与第i个房子颜色相冲突
                        //就把第i-1行,那个次最小花费(min2),来跟第,消费表第i个相加;
                    }
                }

            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            result = Math.min(result, dp[n - 1][i]);
        }

        return result;
    }

}
