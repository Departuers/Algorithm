package dp;

/**
 * 01背包问题
 */
public class bag {
    public static void main(String[] args) {
        System.out.println(dfs(0, 5));
        System.out.println(dp());
    }

    public static int n = 4;//物品数量
    public static int W = 5;//背包容量
    public static int[] w = {2, 1, 3, 2};
    public static int[] v = {3, 2, 4, 2};

    /**
     * 01背包问题
     * O(2^n)复杂度
     * 深度优先搜索
     *
     * @param i  从哪个物品开始选
     * @param ww 剩余容量
     * @return 最高总价值
     */
    public static int dfs(int i, int ww) {
        if (W <= 0) return 0;//有东西可以选但没有容量了
        if (i == n) return 0;//没有东西可以选了
        int v2 = dfs(i + 1, ww);
        if (ww >= w[i]) {
            int v1 = v[i] + dfs(i + 1, ww - w[i]);
            return Math.max(v1, v2);
        } else {
            return v2;
        }
    }

    /**
     * 非常重要
     * @return 总价值最高
     */
    public static int dp() {
        int[][] dp = new int[n][W + 1];
        //初始化dp的第一行，第一行可选物品只有1个，能装上就装
        for (int i = 0; i < W + 1; i++) {
            if (i >= w[0]) {// 每种容量-0号物品
                dp[0][i] = v[0];
            } else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (j >= w[i]) {//要的起
                    //选择当前物品，即i号物品，剩余容量。
                    int i1 = v[i] + dp[i - 1][j - w[i]];
                    int i2 = dp[i - 1][j];
                    dp[i][j] = Math.max(i1, i2);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "   ");
            }
            System.out.println();
        }
        return dp[n - 1][W];
    }
}
