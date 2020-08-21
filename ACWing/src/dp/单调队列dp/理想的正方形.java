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
 * 先对矩阵逐行求区间长度是n的滑动窗口的最大最小值，分别存进最大最小值数组。
 * 然后再对求出的最值数组按列再求一次最值，
 * 二者之差就是所有n*n正方形区域内的最大整数与最小整数差了。当然，求滑动区间的最值就是使用单调队列实现的。
 * 如图所示，如果要求3*3正方形中的最值，
 * 可以先将各行中长度为3的区间的最值存在这个区间开始的格子里，比如上图蓝色的区域，
 * 求完后，第一列的前三个格子就存了这个蓝色正方形各行的最值，
 * 再竖着对第一行求下长度为3区间的最值存到第一个单元格里，第一个单元格存储的就是整个正方形区域内的最值了。
 * 当然我们是先把初始矩形各行长度为n区间的最值存到一个新的数组，然后再对这个数组竖着求最值存入又一个数组来实现的，
 * 以避免值被覆盖。实现细节见代码：
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
            getMin(w[i], row_min[i], m);
            getMax(w[i], row_max[i], m);
        }
        int[] a = new int[N];
        int[] b = new int[N];
        int[] c = new int[N];
        int res = (int) 1e9;
        for (int i = k; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                a[j] = row_min[j][i];
            }
            getMin(a, b, n);
            for (int j = 1; j <= n; j++) {
                a[j] = row_max[j][i];
            }
            getMax(a, c, n);
            for (int j = k; j <= n; j++) {
                res = Math.min(res, c[j] - b[j]);
            }
        }
        System.out.println(res);
    }

    static void getMin(int[] a, int[] b, int tot) {
        int hh = 0, tt = -1;
        for (int i = 1; i <= tot; i++) {
            if (hh <= tt && q[hh] <= i - k) hh++;
            while (hh <= tt && a[q[tt]] >= a[i]) tt--;
            q[++tt] = i;
            b[i] = a[q[hh]];
        }
    }

    static void getMax(int[] a, int[] b, int tot) {
        int hh = 0, tt = -1;
        for (int i = 1; i <= tot; i++) {
            if (hh <= tt && q[hh] <= i - k) hh++;
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
