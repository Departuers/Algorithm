package 线性dp.背包模型;

/**
 * 有1分2分3分的硬币,把钱N换成零钱有多少种兑换方法,n<3w
 * 状态定义:f[i,j],集合:所有只从前i个物品选,且恰好总体积是j的方案总和
 * 属性:count
 * 状态划分:不包含第i个,选了第i个物品1个,选了第i个物品2个,选了第i个物品3个...
 * 状态计算:不包含第i个->f[i-1,j]
 * 选了第i种物品k个->f[i,j-v[i]]
 * |_____| + 0个i
 * |_____| + 1个i
 * ...
 * 求取左边的和,加上右边的和
 * 左边的方案选择:1~i-1中选择,不包含i的话体积应该是j-k*i
 * 则是:f[i-1,j-v[i]*k ]
 * 最终:f[i,j]=f[i-1,j]+f[i-1,j-v[i]*1]+f[i-1,j-v[i]*2]+...+f[i-1,j-v[i]*k]
 * 易得f[i,j-v]=        f[i-1,j-v[i]*1]+f[i-1,j-v[i]*2]+...+f[i-1,j-v[i]*k]
 * 可以对应:
 * 当然可以替换f[i,j]=f[i-1,j]+f[i,j-v]
 * 当然k要合法
 * 显然:与数字组合题目不同的是,数字组合给定的数字,每一个只能选一次,01背包
 * 这道题,是完全背包问题,每个数字可以选多次
 * 恰好状态定义吻合,但转移方程略有区别
 */
public class 兑换硬币 {
    public static void main(String[] args) {
        System.out.println(one(2934));
        System.out.println(two(2934));

    }

    static int[][] dp = new int[4][33333];
    static int[] A = {1, 1, 2, 3};

    static int two(int size) {
        dp[0][0] = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j <= size; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i])
                    dp[i][j] += dp[i][j - A[i]];
            }
        }
        return dp[3][size];
    }

    static int[] f = new int[333333];

    static int one(int n) {
        f[0] = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= n; j++) {
                f[j] += f[j - i];
            }
        }
        return f[n];
    }
}