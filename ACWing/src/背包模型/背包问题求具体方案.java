package 背包模型;

import java.util.Scanner;

/**
 * 有 N 件物品和一个容量是 V
 * 的背包。每件物品只能使用一次。
 * 第 i件物品的体积是 vi，价值是 wi
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出 字典序最小的方案。这里的字典序是指：所选物品的编号所构成的序列。物品的编号范围是 1…N
 * 输入格式
 * 第一行两个整数，N，V
 * ，用空格隔开，分别表示物品数量和背包容积。
 * 接下来有 N
 * 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i
 * 件物品的体积和价值。
 * 输出格式
 * 输出一行，包含若干个用空格隔开的整数，表示最优解中所选物品的编号序列，且该编号序列的字典序最小。
 * 物品编号范围是 1…N
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
 * 1 4
 * 对应图论最短路问题
 * f[n-1,m]->f[n,m]边权重为0
 * f[n-1,m-v[i]]+w[i]->f[n,m] 边权重为w[i]
 * 要求字典序最小:
 * 可能最大价值有多种方案
 * 对于一个物品:有三种情况,必选,可选,不能选
 * 如果物品可选那就一定选则是字典序最小方案
 * 因为是1..n顺序判断的最大价值
 * 要倒序从下面到上面做dp
 */
public class 背包问题求具体方案 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i + 1][j];
                if (j >= v[i])
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - v[i]] + w[i]);
            }
        }
        //f[1][m]是最大值
        int j = m;
        for (int i = 1; i <= n; i++) {//1~n个物品可选
            if (j >= v[i] && f[i][j] == f[i + 1][j - v[i]] + w[i]) {
                System.out.print(i + " ");
                j -= v[i];
            }
        }
    }

    static int n, m;
    static int[] v = new int[1010], w = new int[1010];
    static int[][] f = new int[1010][1010];

}
