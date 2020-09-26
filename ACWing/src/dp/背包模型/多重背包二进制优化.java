package dp.背包模型;

import java.util.Scanner;

/**
 * 多重背包转化为01背包问题
 * 比如s[i]为10
 * 转化为二进制,1,2,4和为7还剩3个
 * 那么转化为,1,2,3,4
 * 选5个答案就是1和4
 * 选6个是4和2
 * 选7个是4和3
 * 选8个是4,3,1
 * 选9个是4,3,2
 * 选10个就是四个全部选
 * 这样涵盖所有选择方案,每个就是选和不选的问题
 */
public class 多重背包二进制优化 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        int a, b, c;
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= s[i]; k *= 2) {//直接变成01背包
                for (int j = V; j >= v[i]; j--) {
                    dp[j] = Math.max(dp[j - v[i]] + w[i], dp[j]);
                }
                s[i] -= k;
            }
            if (s[i] > 0) {
                for (int j = V; j >= v[i]; j--) {
                    dp[j] = Math.max(dp[j - v[i]] + w[i], dp[j]);
                }
            }
        }
        System.out.println(dp[V]);
    }

    static void yuchuli() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        int a, b, c;
        for (int i = 1; i <= N; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            int k = 1;
            while (k <= c) {
                cnt++;
                v[cnt] = k * a;
                w[cnt] = k * b;
                c -= k;
                k *= 2;
            }
            if (c > 0) {
                cnt++;
                v[cnt] = a * c;
                w[cnt] = b * c;
            }
        }//二进制预处理
        N = cnt;//二进制优化后,一共有n个物品,01背包
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);
    }

    static int cnt = 0;
    static int[] dp = new int[2010];
    static int[] v = new int[1000 * 13], w = new int[1000 * 13];
    static int[] s = new int[13000];
    static int N, V;
}
