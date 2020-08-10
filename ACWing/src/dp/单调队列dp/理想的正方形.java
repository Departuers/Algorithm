package dp.单调队列dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104590017
 * 有一个 a×b的整数组成的矩阵，
 * 现请你从中找出一个 n×n的正方形区域，
 * 使得该区域所有数中的最大值和最小值的差最小。
 * 输入格式
 * 第一行为三个整数，分别表示 a,b,n的值；
 * 第二行至第 a+1 行每行为 b 个非负整数，
 * 表示矩阵中相应位置上的数。
 * 输出格式
 * 输出仅一个整数，
 * 为 a×b 矩阵中所有“n×n 正方形区域中的最大整数和最小整数的差值”的最小值。
 * 数据范围
 * 2≤a,b≤1000,
 * n≤a,n≤b,n≤100,
 * 矩阵中的所有数都不超过 10^9。
 * 输入样例：
 * 5 4 2
 * 1 2 5 6
 * 0 17 16 0
 * 16 17 2 1
 * 2 10 2 1
 * 1 2 2 2
 * 输出样例：
 * 1
 * 先预处理求每行的最值,在预处理列
 */
public class 理想的正方形 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                w[i][j] = sc.nextInt();
            }
        }
        for (int i = 1; i <= n; i++) {

        }
    }

    static void getMin(int[] a, int[] b, int tot) {
        int hh = 0, tt = -1;
        for (int i = 1; i <= tot; i++) {
            if (hh <= tt && q[hh] <= i - tot) hh++;
            while (hh <= tt && a[q[tt]] >= a[i]) tt--;
            q[++tt] = i;
            b[i] = a[q[hh]];
        }
    }

    static void getMax(int[] a, int[] b, int tot) {
        int hh = 0, tt = -1;
        for (int i = 1; i <= tot; i++) {
            if (hh <= tt && q[hh] <= i - tot) hh++;
            while (hh <= tt && a[q[tt]] <= a[i]) tt--;
            q[++tt] = i;
            b[i] = a[q[hh]];
        }
    }

    static int n, m, k, N = 1010;
    static int[] q = new int[N];
    static int[][] w = new int[N][N];
    static int[][] row_max = new int[N][N];
    static int[][] row_min = new int[N][N];

}
