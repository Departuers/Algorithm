package dp.状态压缩dp;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103992712
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
 * 2的整数次幂-1的二进制位全是1
 * f[i,j]状态表示为从0走到顶点j ,走过所有的点状态为i的所有走法
 * 比如i为(11100111)第0~2个点走过了,第4~6个点走过了
 * 属性:Min
 * 划分依据 倒数第2个点是哪一个点来分类
 * 状态计算: 0 1 2 ... n-1 不失一般性记为k
 * 经过的点是i,且倒数第二个点是k
 * 0->k->j 右边是k->j的边记为a(k,j)
 * 0->k最短,
 * 0->k->j  f(i-{j},k)+a(k,j)  取min
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
        for (int i = 1; i < 1 << n; i++) {//二进制枚举
            if ((i & 1) == 0) continue;//路径必然包含起点,最后一位必须为1,快一倍
            for (int j = 0; j < n; j++) {//取出每一位
                if (((i >> j) & 1) == 1) {//取出每一位
                    int t = i - (1 << j);
                    //把第j位置为0
                    int s = t - 1 != 0 ? 1 : 0;
                    //如果只剩下了起点和终点，才需要枚举以k = 0的情况
                    for (int k = s; k < n; k++) {
                        if ((t >> k & 1) == 1) {
                            f[i][j] = min(f[i][j], f[t][k] + g[k][j]);
                        }
                    }
                }
            }
        }

        System.out.println(f[(1 << n) - 1][n - 1]);
        //从0走到j路径为1111111的最短路径,也就是经过所有点到达n-1这个点

    }

    static int N = 20, M = 1 << 20, n;
    static int[][] g = new int[N][N];
    static int[][] f = new int[M][N];

}
