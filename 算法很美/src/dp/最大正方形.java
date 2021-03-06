package dp;

import java.util.Arrays;

/**
 * https://www.acwing.com/file_system/file/content/whole/index/content/521410/
 * https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247486923&idx=2&sn=6c1c8aeb4db68522e67ddf8c1e933660&chksm=fa0e624acd79eb5cdb410808921609a830b9b9221e813e4eb89cf551ca48f317668d44b095d2&scene=21#wechat_redirect
 * LeetCode 第 221 号问题：最大正方形。
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * 题目给定一个字符矩阵，字符矩阵中只有两种字符，分别是 ‘0’ 和 ‘1’
 * 题目要在矩阵中找全为 ‘1’ 的，面积最大的正方形。
 * <p>
 * 因为我们考虑的是正方形的右下方的顶点，
 * 因此状态可以定义成 “当前点为正方形的右下方的顶点时，正方形的最大面积”
 */
public class 最大正方形 {
    public static void main(String[] args) {
        int[][] arr = new int[3][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (Math.random() * 15 < 12)
                    arr[i][j] = 1;
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(maximalSquare(arr));

    }

    /**
     * 非常巧妙,注意思路,非常牛逼
     * 因为我们考虑的是正方形的右下方的顶点，因此状态可以定义成 “当前点为正方形的右下方的顶点时，正方形的最大面积”
     * 有了状态，我们再来看看递推方程如何写，前面说到我们可以从当前点向三个方向延伸，我们看相邻的位置的状态，
     * <p>
     * 这里我们需要取三个方向的状态的最小值才能确保我们延伸的是全为 ‘1’ 的正方形，也就是
     * dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
     * <p>
     * 两个例子
     * [1, 1, 1, 1]
     * [1, 1, 1, 0]    源数组
     * [1, 1, 1, 0]
     * ------------
     * [1, 1, 1, 1]
     * [1, 2, 2, 0]     动态规划数组
     * [1, 2, 3, 0]
     * <p>
     * [1, 1, 1, 1]
     * [1, 1, 0, 1]     源数组
     * [1, 0, 1, 1]
     * ------------
     * [1, 1, 1, 1]
     * [1, 2, 0, 1]     动态规划数组
     * [1, 0, 1, 1]
     * 首先看状态定义:把dp[i][j]语义定义为,把(i,j)作为正方形的右下角顶点时,这个正方形的最大面积(直接得出的是边长,不是面积)
     * 则直接找上面,左边,左上角来组成正方形,则在源数组中必须要这三个位置都为1,才能组成正方形
     * 所以寻找这三个值中最小的+1,就符合(i,j)作为正方形的右下角顶点时,这个正方形的边长
     * 寻找状态转移方程,
     *
     * @param matrix 矩阵
     * @return 最大面积
     */
    public static int maximalSquare(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        int maxLength = 0;//边长
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0)
                        dp[i][j] = matrix[i][j] == 1 ? 1 : 0;//第一排,第一列,没有上面,左边,左上角来组成大正方形,直接给1,特判
                    else
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    maxLength = Math.max(dp[i][j], maxLength);
                }
            }
        }
        System.out.println("-----");
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return maxLength * maxLength;
    }

}
/**
 * 题目给定一个字符矩阵，字符矩阵中只有两种字符，分别是 ‘0’ 和 ‘1’，题目要在矩阵中找全为 ‘1’ 的，面积最大的正方形。
 * 首先一个正方形是由四个顶点构成的，如果说我们在矩阵中随机找四个点，然后判断该四个点组成的是不是正方形，如果是正方形，然后看组成正方形的每个位置的元素是不是都是 ‘1’，这种方式也是可行的，但是比较暴力，这么弄下来，时间复杂度是 O((m*n)^4)。
 * 那我们就会思考，组成一个正方形是不是必须要四个点都找到？如果我们找出其中的三个点，甚至说两个点，能不能确定这个正方形呢？
 * 你会发现，这里我们只需要考虑 正方形对角线的两个点 即可，这两个点确定了，另外的两个点也就确定了，因此我们可以把时间复杂度降为 O((m*n)^2)。
 * 但是这里还是会有一些重复计算在里面，我们和之前一样，本质还是在做暴力枚举，只是说枚举的个数变少了，我们能不能记录我们之前得到过的答案，通过牺牲空间换取时间呢，这里正是动态规划所要做的事情！
 * <p>
 * 我们可以思考，如果我们从左到右，然后从上到下遍历矩阵，假设我们遍历到的当前位置是正方形的右下方的点，那其实我们可以看之前我们遍历过的点有没有可能和当前点组成符合条件的正方形，除了这个点以外，无非是要找另外三个点，这三个点分别在当前点的上方，左方，以及左上方，也就是从这个点往这三个方向去做延伸，具体延伸的距离是和其相邻的三个点中的状态有关
 * <p>
 * 状态定义
 * <p>
 * 因为我们考虑的是正方形的右下方的顶点，因此状态可以定义成 “当前点为正方形的右下方的顶点时，正方形的最大面积”
 * <p>
 * 递推方程
 * <p>
 * 有了状态，我们再来看看递推方程如何写，前面说到我们可以从当前点向三个方向延伸，我们看相邻的位置的状态，这里我们需要取三个方向的状态的最小值才能确保我们延伸的是全为 ‘1’ 的正方形，也就是
 * <p>
 * dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
 * <p>
 * 在实现上，我们需要单独考虑两种情况，就是当前位置是 ‘1’，还有就是当前位置是 ‘0’，如果是 ‘0’ 的话，状态就是 0，表示不能组成正方形，如果是 ‘1’ 的话，我们也需要考虑位置，如果是第一行的元素，以及第一列的元素，表明该位置无法同时向三个方向延伸，状态直接给为 1 即可，其他情况就按我们上面得出的递推方程来计算当前状态。
 **/