package 状态压缩dp;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

/**
 * * https://blog.csdn.net/qq_30277239/article/details/103992712
 * 给定一张 n个点的带权无向图，点从 0~n-1 标号，求起点 0 到终点 n-1 的最短Hamilton路径。
 * Hamilton路径的定义是从 0 到 n-1 不重不漏地经过每个点恰好一次。
 * 输入格式
 * 第一行输入整数n。
 * 接下来n行每行n个整数，其中第i行第j个整数表示点i到j的距离（记为a[i,j]）。
 * 对于任意的x,y,z，数据保证 a[x,x]=0，a[x,y]=a[y,x] 并且 a[x,y]+a[y,z]>=a[x,z]。
 * 输出格式
 * 输出一个整数，表示最短Hamilton路径的长度。
 * 数据范围
 * 1≤n≤20
 * 0≤a[i,j]≤10^7
 * 输入样例：
 * 5
 * 0 2 4 5 1
 * 2 0 6 5 3
 * 4 6 0 8 3
 * 5 5 8 0 5
 * 1 3 3 5 0
 * 输出样例：
 * 18
 * 状压dp
 * 该图是完全图
 *
 */
public class 哈密尔顿回路 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < f.length; i++) {
            Arrays.fill(f[i], 0x3f3f3f3f);
        }
        f[1][0] = 0;
        for (int i = 1; i < 1 << n; i++) {
            if ((i & 1) == 0) continue;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    int t = i - (1 << j);
                    int s = t - 1 != 0 ? 1 : 0;
                    for (int k = s; k < n; k++) {
                        if ((t >> k & 1) == 1) {
                            f[i][j] = min(f[i][j], f[t][k] + g[k][j]);
                        }
                    }
                }
            }
        }
        System.out.println(f[(1 << n) - 1][n - 1]);
    }

    static int N = 20, M = 1 << 20, n;
    static int[][] g = new int[N][N];
    static int[][] f = new int[M][N];

}
