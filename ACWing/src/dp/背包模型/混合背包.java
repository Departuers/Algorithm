package dp.背包模型;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V的背包。
 * 物品一共有三类：
 * 第一类物品只能用1次（01背包）；
 * 第二类物品可以用无限次（完全背包）；
 * 第三类物品最多只能用 s[i]次(多重背包)
 * 每种体积是 v[i]，价值是 w[i]
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，
 * 且价值总和最大。
 * 输出最大价值。
 * 状态定义:f[i,j]:只从前i种物品选,且总体积不超过j的选法
 * 属性max:最大价值
 * 状态计算:
 * 01背包:f[i,j]=max( f[i-1,j] ,f[i-1][j-v[i]]+w[i])
 * 完全背包:f[i,j]=max(f[i-1,j] ,f[i-1,j-v[i]+w[i] , f[i-1,j-v[i]*2+w[i]*2 ... f[i-1,j-v[i]*k+w[i]*k)
 * 易得f[i,j-v]=            max(f[i-1,j-v[i]+w[i] , f[i-1,j-v[i]*2+w[i]*2 ... f[i-1,j-v[i]*k+w[i]*k)
 * 可以对应:
 * 当然可以替换f[i,j]=max( f[i-1,j],f[i,j-v] )
 * 多重背包:f[i,j]=max(f[i-1,j] ,f[i-1,j-v[i]+w[i] , f[i-1,j-v[i]*2+w[i]*2 ... f[i-1,j-v[i]*s[i]+w[i]*s[i])
 */
public class 混合背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            t[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            if (t[i] == 0) {
                for (int j = v[i]; j <= V; j++) {
                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
                }
            } else {
                if (t[i] == -1) t[i] = 1;
                for (int k = 1; k <= t[i]; k *= 2) {
                    for (int j = V; j >= k * v[i]; j--) {//把k个物品看成可选可不选的一个物品,01背包
                        f[j] = Math.max(f[j], f[j - k * v[i]] + w[i] * k);
                    }
                    t[i] -= k;
                }
                if (t[i] > 0) {
                    for (int j = V; j >= t[i] * v[i]; j--) {
                        f[j] = Math.max(f[j], f[j - t[i] * v[i]] + w[i] * t[i]);
                    }
                }
            }
        }
        System.out.println(f[V]);
    }

    static int[] v = new int[1010], w = new int[1010], t = new int[1010], f = new int[1010];
    static int N, V;
}
