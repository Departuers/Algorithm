package dp.背包模型;

import java.util.Scanner;

/**
 * f[i,j]表示所有只从前i个物品选,且总体积不超过j的所有选法的集合
 * 属性max
 * 划分依据,第i个物品选多少个
 * 状态计算,0,1,2,3...s-1,s
 * 0 代表不选第i个物品,f[i-1,j]
 * 1 代表选1个第i个物品f[i-1,j-1*v[i]]+1*w[i]
 * 2 代表选2个第i个物品f[i-1,j-2*v[i]]+2*w[i]
 * ...
 * s 代表选s个第i个物品f[i-1,j-s*v[i]]+s*w[i]
 * 当然j-s*v[i]要合法
 * 不失一般性选i个物品s个
 * O(n^3)
 * 考虑优化
 * f[i,j]=max( f[i-1,j] ,f[i-1,j-v]+w,f[i-1,j-2v]+2w .... f[i-1,j-sv]+sw )  s=j/v上取整
 * f[i,j-v] =max(        f[i-1,j-v]  ,f[i-1,j-2v]+w  .... f[i-1,j-sv]+(s-1)w)
 * 第一个式子,从第二项开始,每一项相差一个w
 * 替换一下
 * f[i,j]=max( f[i-1,j] , f[i,j-v]+w )
 * 当空间优化成1维过后,只有完全背包问题的体积是从小到大循环的
 */
public class 完全背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        for (int i = 1; i < N; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        O3();
    }

    static int N, V;
    static int[] w = new int[1005];
    static int[] v = new int[1005];
    static int[][] dp = new int[1005][1005];
    static int[] f = new int[1005];

    static void O3() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];//至少有前i-1种物品可选,容量为v的价值
                if (j >= w[i])
                    dp[i][j] = Math.max(dp[i][j - w[i]] + v[i], dp[i][j]);
            }
        }
        System.out.println(dp[N][V]);
    }

    static void O2() {
        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        System.out.println(f[V]);
    }
}
