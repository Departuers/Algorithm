package dp;

/**
 * https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247486923&idx=2&sn=6c1c8aeb4db68522e67ddf8c1e933660&chksm=fa0e624acd79eb5cdb410808921609a830b9b9221e813e4eb89cf551ca48f317668d44b095d2&scene=21#wechat_redirect
 * LeetCode 第 64 号问题最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class 最小路径和 {
    public static void main(String[] args) {
        int[][] arr = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println(zq(arr));
    }

    /**
     * dp[i][j]代表走到第i行,第j列,走过来的最小路径和,
     * dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1]) + arr[i][j]
     * 该递推方程代表,走到(i,j)的值,应当是(i-1,j)和(i,j-1)之间的较小值,
     * 再加上该位置的值,就是到(i,j)的最小路径和
     *
     * @param grid 该数组
     * @return 最小路径和
     */
    public static int zq(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        //首先判断数组是不是为空
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        {//基础条件代码块
            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }//初始化第一列
            for (int i = 1; i < n; i++) {
                dp[0][i] = grid[0][i] + dp[0][i - 1];
            }//初始化第一行
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}