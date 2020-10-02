package dp.状态压缩dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103986576
 * 求把N*M的棋盘分成1*2的长方形有多少种方案
 * 输入包含多组测试用例。
 * 每组测试用例占一行，包含两个整数N和M。
 * 当输入用例N=0，M=0时，表示输入终止，且该用例无需处理。
 * 输出格式
 * 每个测试用例输出一个结果，每个结果占一行。
 * 数据范围
 * 1≤N,M≤11
 * 输入样例：
 * 1 2
 * 1 3
 * 1 4
 * 2 2
 * 2 3
 * 2 4
 * 2 11
 * 4 11
 * 0 0
 * 输出样例：
 * 1
 * 0
 * 1
 * 2
 * 3
 * 5
 * 144
 * 51205
 * 本题要求将n*m的格子划分为若干个1*2的长方形，
 * 求总的划分方案数，或者说在棋盘上放置若干个1*2的长方形使得它们刚好能够覆盖整个棋盘的方案数。
 * 长方形要么横放要么竖放，我们知道了所有放置横向方格的位置，其它空的地方自然全部放上竖的方格，
 * 也就是说，求放置的方案数等同于求放置横向方格的方案数。
 * 状态表示：这里将二维压缩为一维，如果方格有五行，则第i列的状态可以表示为11011，
 * 其中1表示从该位置开始横向放置1*2的长方形，
 * 0则表示没有放置。则f[i][j]表示第i列状态为j的方案数。首先考虑状态转移的约束条件。
 */
public class 蒙德里安的梦想 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b;
        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == m && n == 0) break;
            for (int i = 0; i <= 1 << n; i++) {
                int cnt = 0;
                vis[i] = true;
                for (int j = 0; j < n; j++) {
                    if ((i >> j & 1) == 1) {
                        if ((cnt & 1) == 1) vis[i] = false;
                        cnt = 0;
                    } else cnt++;
                }
                if ((cnt & 1) == 1) vis[i] = false;
            }
            for (int i = 0; i < f.length; i++) {
                Arrays.fill(f[i], 0);
            }
            
            f[0][0] = 1;
            for (int i = 1; i <= m; i++) {
                for (int j = 0; j < 1 << n; j++) {
                    for (int k = 0; k < 1 << n; k++) {
                        if ((j & k) == 0 && vis[(j | k)]) f[i][j] += f[i - 1][k];
                    }
                }
            }
            System.out.println(f[m][0]);
        }
    }

    static int N = 12, M = 1 << N, n, m;
    static long[][] f = new long[N][M];
    static boolean[] vis = new boolean[M];
}
