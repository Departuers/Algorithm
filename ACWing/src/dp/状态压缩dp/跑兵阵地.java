package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104206827
 * f[i,j,k]状态表示:所有摆完前i行,且第i-1行状态是j 第i行状态是k 所有摆放方案的集合
 * 属性:max最大数量
 * 划分:倒数第三行的状态来划分,也就是第i-2行的状态来划分
 * 状态计算:
 * f[i,j]查看相邻两行之间的关系
 * 第i行的状态记为b,第i-1行状态记为a,第i-2行是c
 * 1. ( a&b |(a&c) | (b&c) )==0 一列不能有交集
 * 2.( (g[i-1]&a | (g[i]&b) ) 山地不能有炮
 * 5 4
 * PHPP
 * PPHH
 * PPPP
 * PHPP
 * PHHP
 * 输出样例：
 * 6
 */
public class 跑兵阵地 {
    static int n, m, N = 11, M = 1 << 10;
    static int[] g = new int[110];
    static ArrayList<Integer> state = new ArrayList<Integer>();
    static int[][][] f = new int[2][M][M];//滚动数组
    static int[] cnt = new int[M];

    static boolean check(int state) {
        for (int i = 0; i < m; i++) {
            if ((state >> i & 1) == 1 && (((state >> i + 1 & 1) == 1) || ((state >> i + 2 & 1) == 1))) {
                return false;
            }
        }
        return true;
    }

    static int count(int n) {
        int r = 0;
        while (n != 0) {
            n -= n & -n;
            r++;
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == 'H') g[i] += 1 << j;
            }
        }
        for (int i = 0; i < 1 << n; i++) {
            if (check(i)) {
                state.add(i);
                cnt[i] = count(i);
            }
        }
        for (int i = 1; i <= n + 2; i++) {
            for (int j = 0; j < state.size(); j++) {
                for (int k = 0; k < state.size(); k++) {
                    for (int u = 0; u < state.size(); u++) {
                        a = state.get(i);
                        b = state.get(j);
                        c = state.get(u);
                        if ((a & b) != 0 | (b & c) != 0 && (a & c) != 0) continue;
                        if ((g[i - 1] & a) != 0 | (g[i] & b) != 0) continue;
                        f[i & 1][j][k] = Math.max(f[i & 1][j][k], f[i - 1 & 1][u][j] + cnt[b]);
                    }
                }
            }
        }

        System.out.println(f[n + 2 & 1][0][0]);
    }
}
