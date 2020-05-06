package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104210900
 * 如果在地图中的灰色所标识的平原上部署一支炮兵部队，则图中的黑色的网格表示它能够攻击到的区域：沿横向左右各两格，沿纵向上下各两格。
 * 图上其它白色网格均攻击不到。从图上可见炮兵的攻击范围不受地形的影响。
 * 现在，将军们规划如何部署炮兵部队，在防止误伤的前提下（保证任何两支炮兵部队之间不能互相攻击，
 * 即任何一支炮兵部队都不在其他支炮兵部队的攻击范围内），在整个地图区域内最多能够摆放多少我军的炮兵部队。
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
 */
public class 炮兵阵地 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        String tem;
        int c = 0;
        for (int i = 1; i <= n; i++) {
            tem = sc.next();
            c = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (tem.charAt(c++) == 'H') g[i] += 1 << j;
            }
            System.out.println(Integer.toBinaryString(g[i]));
        }
        for (int i = 0; i < 1 << m; i++) {
            if (judge(i)) {
                a.add(i);
                cnt[i] = count(i);
            }
        }
        int s = 0, t = 0, r = 0;
        //第i行,状态为j
        for (int i = 1; i <= n; i++) {
            int u = i & 1, v = u == 0 ? 1 : 0;
            for (int j = 0; j < a.size(); j++) {
                if ((g[i] & a.get(j)) != 0) continue;
                for (int k = 0; k < a.size(); k++) {
                    if ((g[i - 1] & a.get(k)) != 0) continue;
                    for (int l = 0; l < a.size(); l++) {
                        if (i > 2 && (g[i - 1] & a.get(l)) != 0) continue;
                        s = a.get(j);
                        t = a.get(k);
                        r = a.get(l);
                        if ((s & t | s & r | t & r) == 0) f[u][j][k] = Math.max(f[u][j][k], f[v][k][l] + cnt[s]);
                    }
                }
            }
        }
        int res = 0, u = n & 1;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                res = Math.max(res, f[u][i][j]);
            }
        }
        System.out.println(res);
    }

    static int[][][] f = new int[2][1 << 11][1 << 11];
    static int[] cnt = new int[1 << 11];
    static ArrayList<Integer> a = new ArrayList<Integer>();

    static boolean judge(int st) {
        int s = st & (st >> 1), t = st & (st >> 2);
        //右移一位
        return (s | t) == 0;
    }

    static int count(int st) {
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += (1 & st >> i);
        }
        return res;
    }

    static int n, m, N = 105;
    static int[] g = new int[N];

}
