package 背包模型;

import java.util.Scanner;

/**
 * 一个正整数n可以表示成若干个正整数之和，形如：n=n1+n2+…+nk，其中n1≥n2≥…≥nk,k≥1。
 * 我们将这样的一种表示称为正整数n的一种划分。
 * 现在给定一个正整数n，请你求出n共有多少种不同的划分方法。
 * 输入格式
 * 共一行，包含一个整数n。
 * 输出格式
 * 共一行，包含一个整数，表示总划分数量。
 * 由于答案可能很大，输出结果请对10^9+7
 * 取模。
 * 数据范围
 * 1≤n≤1000
 * 输入样例:
 * 5
 * 输出样例：
 * 7
 * 可以看成完全背包问题求方案数
 * 定义状态为:f[i,j]为前i个物品可选(1-i)总和为j的方案数
 * 状态划分,考虑last
 * 划分成两个部分:
 * 不选第i个物品:f[i-1,j]
 * 选上第i个物品k个:f[i-1,j]+f[i-1,j-i]+f[i-1,j-2i]+...f[i-1,j-ki]  (j>=ki)
 *              f[i-1,j-i]=          f[i-1,j-2i]+...f[i-1,j-ki]
 * 则f[i,j]=f[i-1,j]+f[i-1,j-i]
 */
public class 整数划分 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp[0]=1;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = (dp[j]+dp[j - i])%mod;
            }
        }
        System.out.println(dp[n]);
    }

    static int[] dp = new int[1005];
    static int n, mod = (int) (1e9 + 7);
}
