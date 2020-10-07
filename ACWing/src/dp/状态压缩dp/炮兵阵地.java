package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104210900
 * 司令部的将军们打算在N*M的网格地图上部署他们的炮兵部队。一个N*M的地图由N行M列组成，地图的每一格可能是山地（用”H” 表示），也可能是平原（用”P”表示），如下图。
 * 在每一格平原地形上最多可以布置一支炮兵部队（山地上不能够部署炮兵部队）；一支炮兵部队在地图上的攻击范围如图中黑色区域所示：
 * 如果在地图中的灰色所标识的平原上部署一支炮兵部队，则图中的黑色的网格表示它能够攻击到的区域：沿横向左右各两格，沿纵向上下各两格。
 * 图上其它白色网格均攻击不到。从图上可见炮兵的攻击范围不受地形的影响。
 * 现在，将军们规划如何部署炮兵部队，在防止误伤的前提下（保证任何两支炮兵部队之间不能互相攻击，即任何一支炮兵部队都不在其他支炮兵部队的攻击范围内），在整个地图区域内最多能够摆放多少我军的炮兵部队。
 * 输入格式
 * 第一行包含两个由空格分割开的正整数，分别表示N和M；
 * 接下来的N行，每一行含有连续的M个字符(‘P’或者’H’)，中间没有空格。按顺序表示地图中每一行的数据。
 * 输出格式
 * 仅一行，包含一个整数K，表示最多能摆放的炮兵部队的数量。
 * 数据范围
 * N≤100,M≤10
 * 输入样例：
 * 5 4
 * PHPP
 * PPHH
 * PPPP
 * PHPP
 * PHHP
 * 输出样例：
 * 6
 * f[i,j,k]状态表示:所有摆完前i行,第i行状态是j,且第i-1行状态是k 所有摆放方案的集合
 * 属性:max最大数量
 * 划分:倒数第三行的状态来划分,也就是第i-2行的状态来划分
 * 状态计算:
 * f[i,j]查看相邻两行之间的关系
 * 第i行的状态记为a,第i-1行状态记为b,第i-2行是c
 * 1. ( a&b |(a&c) | (b&c) )==0 一列不能有交集,a,b,c两两之间不能攻击到
 * 2.( (g[i-1]&b) | ( g[i]&a ) ) 山地不能有炮,意大利炮只能放在平地上,
 */
public class 炮兵阵地 {
    static int n, m, N = 11, M = 1 << 10;
    static int[] g = new int[110];
    static ArrayList<Integer> state = new ArrayList<Integer>();
    static int[][][] f = new int[2][M][M];//滚动数组
    static HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();

    static boolean check(int state) {
        int a = state & state >> 1, b = state & state >> 2;
        return a == b && b == 0;
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
        for (int i = 1; i <= n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == 'H') g[i] += 1 << j;
            }
        }
        for (int i = 0; i < 1 << m; i++) {
            if (check(i)) {
                state.add(i);
                cnt.put(i, count(i));
            }
        }
        for (int i = 1; i <= n + 2; i++) {
            for (int j = 0; j < state.size(); j++) {
                for (int k = 0; k < state.size(); k++) {
                    for (int u = 0; u < state.size(); u++) {
                        a = state.get(j);//第i行
                        b = state.get(k);//第i-1行
                        c = state.get(u);//第i-2行
                        if ((a & b) != 0 | (b & c) != 0 || (a & c) != 0) continue;
                        if ((g[i - 1] & b) != 0 | (g[i] & a) != 0) continue;
                        f[i & 1][j][k] = max(f[i & 1][j][k], f[i - 1 & 1][k][u] + cnt.get(a));//加上第i行的炮数量
                    }
                }
            }
        }

        System.out.println(f[n + 2 & 1][0][0]);
    }

    public static int max(int var0, int var1) {
        return var0 >= var1 ? var0 : var1;
    }
}
