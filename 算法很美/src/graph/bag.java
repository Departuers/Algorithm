package graph;

import java.util.Arrays;

/**
 * 01背包问题和完全背包
 */
public class bag {
    public static void main(String[] args) {
//        System.out.println(dp());
        System.out.println(dpByOne());
//        System.out.println(dpByWan());
//        System.out.println(DpbyWanQuanByTwo());
//        System.out.println(WanQuanByTWo());
//        {//测试价值最高的最小重量,与重量最大的最小价值算法的相同性
//            System.out.println(ZeroOneBag(100, 100));
//            w = new int[100];
//            v = new int[100];
//            for (int j = 0; j < 100000; j++) {
//                for (int i = 0; i < 100; i++) {
//                    v[i] = (int) (Math.random() * 100);
//                    w[i] = (int) (Math.random() * 100);
//                }
//                if (dpByOne() != ZeroOneBag(100, 100))
//                    System.out.println("NOOOOOOO");
//            }
//        }

    }

    public static int n = 4;//物品数量
    public static int W = 9;//背包容量
    public static int[] w = {2, 3, 2, 4};//重量
    public static int[] v = {3, 2, 4, 2};//价值

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
     * 非常重要,dp[i][j]语义代表,0-i号物品作为可选物品,j作为背包容量,求出的最大价值
     *
     * @return 总价值最高
     */
    public static int dp() {
        int[][] dp = new int[n][W + 1];
        //初始化dp的第一行，第一行可选物品只有1个，能装上就装
        for (int i = 0; i <= W; i++) {
            if (i >= w[0]) {// 每种容量-0号物品
                dp[0][i] = v[0];
            } else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                if (j >= w[i]) {//能够选i号物品,要的起
                    //选择当前物品，即i号物品，剩余容量。
                    int i1 = v[i] + dp[i - 1][j - w[i]];//选了i号物品的价值
                    int i2 = dp[i - 1][j];//不选i号物品的价值
                    dp[i][j] = Math.max(i1, i2);//最后结果取,选或者不选i号物品的最大价值
                } else {
                    dp[i][j] = dp[i - 1][j];//无法选当前i号物品,结果应为不选i号物品的价值
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[n - 1][W];
    }

    /**
     * 01背包问题变种,
     * 有n个重量和价值分别为wi,vi的物品,从中挑选重量不超过W的物品,
     * 求其中可挑选方案中价值总和的最大值
     * 限制条件:
     * 1≤n≤100
     * 1≤wi≤10^7
     * 1≤vi≤100
     * 1≤W≤10^9
     * 其中W重量的范围太大,原来的方法使用O(nW)复杂度太高
     * 思路:
     * 改变dp的对象,可以使用dp针对不同的价值计算最小的重量
     * dp[i][j]代表,可选0-i号物品,在j价值的时候的最小重量
     *
     * @return 最大价值
     */
    public static int ZeroOneBag(int max_v, int max_n) {
        int[][] dp = new int[n + 1][max_n * max_v + 1];
        //物品的数量*物品的最大价值=有可能的最大价值,通过这个确定范围
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= max_n * max_v; j++) {
                if (j < v[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = Math.min(dp[i][j],
                            dp[i][j - v[i]] == Integer.MAX_VALUE ?
                                    dp[i][j] : dp[i][j - v[i]] + w[i]);
                    //非常重要由于初始化为Integer.MAX_VALUE,再加上重量会溢出为负数,导致错误
                    //dp[i][j - v[i]] + w[i]
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= max_v * max_n; i++) {
            if (dp[n][i] <= W)
                res = i;//最后的i才是价值
        }
        return res;
    }


    /**
     * 一维数组解动态规划
     * 每次倒序更新,不然数据不能重用。
     * 由于需要的是上次更新的值,正序更新会覆盖上次更新的值,所以只能倒序更新
     * 思路:定义一个数组作为辅助空间,初始为n+1,根据dp公式倒序更新
     * dp[i][j]语义代表,0-i号物品作为可选物品,j作为背包容量,求出的最大价值
     *
     * @return 最大价值
     */
    public static int dpByOne() {
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {//控制循环次数
            for (int j = W; j >= w[i]; j--) {//最大背包容量W作为初始值,如果j >= w[i]也就是可以选
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[W];
    }

    /**
     * 完全背包,一维数组解法
     * 一种商品可选多个
     *
     * @return 最大价值
     */
    public static int dpByWan() {
        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {//选择第i号物品
            for (int j = w[i]; j <= W; j++) {//j初始化为第i号物品的重量,非常重要
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);//从当前值和dp数组中选出较大值,
                //可能会把上次结果覆盖,但符合动态规划策略
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[W];
    }

    /**
     * 完全背包问题
     * O(nW^2)三层for循环
     * 算法复杂度非常高
     *
     * @return 最大价值
     */
    public static int DpbyWanQuanByTwo() {
        int[][] dp = new int[n + 1][W + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j <= W; j++) {
//                for (int k = 0; k * w[i - 1] <= j; k++) {
//                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i - 1]] + k * v[i - 1]);
//                }
//            }
//        }另一种写法,意思是一样的与下面
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                for (int k = 0; k * w[i] <= j; k++) {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j - k * w[i]] + k * v[i]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[n][W];
    }

    /**
     * 完全背包问题,两层for循环
     * O(nW)复杂度
     *
     * @return 最大价值
     */
    public static int WanQuanByTWo() {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i + 1][j - w[i]] + v[i]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(dp[i]));

        }
        return dp[n][W];
    }

}
