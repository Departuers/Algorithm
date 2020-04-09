package 线性dp.背包模型;

/**
 * 小明手里有n元钱,用来买书:价格为10元,20元,50元,100元,
 * 求有多少总买书方案,每种书可以购买多本
 * 完全背包,和兑换硬币一模一样
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
 * 则是:f[i-1,j-v[i]*k]
 * 最终:f[i,j]=f[i-1,j]+f[i-1,j-v[i]*1]+f[i-1,j-v[i]*2]+...+f[i-1,j-v[i]*k]
 * 易得f[i,j-v]=        f[i-1,j-v[i]*1]+f[i-1,j-v[i]*2]+...+f[i-1,j-v[i]*k]
 * 可以对应:
 * 当然可以替换f[i,j]=f[i-1,j]+f[i,j-v]
 * 当然k要合法
 */
public class 买书 {
    public static void main(String[] args) {
        dp[0] = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = v[i]; j <= n; j++) {
                dp[j] += dp[j - v[i]];
            }
        }
        System.out.println(dp[n]);
        two();
    }

    static void two() {
        f[0][0] = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <= n; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= v[i])
                    f[i][j] += f[i][j - v[i]];
            }
        }
        System.out.println(f[4][n]);
    }

    static int n = 400;
    static int v[] = {0, 10, 20, 50, 100};
    static int[] dp = new int[1010];
    static int[][] f = new int[5][1010];
}
