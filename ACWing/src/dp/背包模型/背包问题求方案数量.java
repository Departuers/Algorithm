package dp.背包模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 * 第 i 件物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出 最优选法的方案数。注意答案可能很大，请输出答案模 10^9+7 的结果。
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
 * 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
 * 输出格式
 * 输出一个整数，表示 方案数 模 109+7 的结果。
 * 数据范围
 * 0<N,V≤1000
 * 0<vi,wi≤1000
 * 输入样例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 6
 * 输出样例：
 * 2
 * 题目问最大价值的方案数量
 * 而不是加满背包的方案数量
 * 最短路(0,0)->通过各种路径到(n,m)最短路径的条数
 * f[i,j]=max ( f[i-1,j]  f[i-1,j-v[i]]+w[i] )
 * 最优解,最短路的,起点到最后一条边的方案数累加到终点
 * 当然有可能又多条最短路
 * g[i,j]代表f[i,j]取最大值的数量
 * g[i,j]的计算对于f[i-1,j]的转移就是g[i-1,j]
 * 对于f[i-1,j-v]+w的转移g[i-1,j-v]
 * 同时转移:g[i-1,j]+g[i-1,j-v]
 */
public class 背包问题求方案数量 {
    static int mod = (int) (1e9 + 7);
    static int n, m;
    static int[] f = new int[1010];
    static int[] g = new int[1010];//存的的恰好
    static int[] v = new int[1010];
    static int[] w = new int[1010];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        Arrays.fill(f, -0x3f3f3f3f);
        f[0] = 0;
        g[0] = 1;
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= v[i]; j--) {
                int maxv = Math.max(f[j], f[j - v[i]] + w[i]);
                int cnt = 0;
                if (maxv == f[j]) cnt += g[j];
                if (maxv == f[j - v[i]] + w[i]) cnt += g[j - v[i]];
                g[j] = cnt % mod;
                f[j] = maxv;
            }
        }
        int res = 0;
        for (int i = 0; i <= m; i++) {
            res = Math.max(res, f[i]);
        }
        int cnt = 0;
        for (int i = 0; i <= m; i++) {
            if (res == f[i]) {
                cnt = (cnt + g[i]) % mod;
            }
        }
        System.out.println(cnt);
    }
}
