package 线性dp.背包模型;

import java.util.Scanner;

/**
 * 给定N个正整数A1,A2,…,AN
 * 从中选出若干个数，使它们的和为M，求有多少种选择方案。
 * 输入格式
 * 第一行包含两个整数N和M。
 * 第二行包含N个整数，表示A1,A2,…,AN
 * 输出格式
 * 包含一个整数，表示可选方案数。
 * 数据范围
 * 1≤N≤100
 * 1≤M≤10000,
 * 1≤Ai≤1000
 * 输入样例：
 * 4 4
 * 1 1 2 2
 * 输出样例：
 * 3
 * M看做背包容量,每一个数看作物品,把Ai看做体积
 * 求出总体积为M的所有方案数量
 * 状态定义:f[i,j],集合:所有只从前i个物品选,且恰好总体积是j的方案总和
 * 属性:count
 * 状态划分:包含第i个,不包含第i个
 * 状态计算:不包含第i个->f[i-1,j]
 * 包含第i个->f[i-1,j-v[i]]
 * 状态合并:
 * 所以f[i,j]=f[i-1,j]+f[i-1,j-v[i]]
 */
public class 数字组合 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            A[i] = sc.nextInt();
        }
       // one();
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i])
                    f[i][j] += f[i - 1][j - A[i]];
            }
        }
        System.out.println(f[n][m]);
        //二维做法
    }

    static void one() {
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] += dp[j - A[i]];
            }
        }
        System.out.println(dp[m]);
    }

    static int[] A = new int[110];
    static int[][] f = new int[110][10010];
    static int[] dp = new int[10010];
    static int n, m;
}
