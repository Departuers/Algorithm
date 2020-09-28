package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104206827
 * f[i,j]状态表示:所有摆完前i行,且第i行状态是j所有摆放方案的集合
 * 属性:count
 * 划分:倒数第二行的状态来划分,也就是第i-1行的状态来划分
 * 状态计算:
 * f[i,j]查看相邻两行之间的关系
 * 第i行的状态记为a,第i-1行状态记为b
 * 1. a和b的二进制表示中不包含连续的两个1
 * 2. a&b==0  不能上下都有玉米
 * <p>
 * 已经摆完前i行,且第i行的状态是a,第i-1行的状态是b的所有摆放方案
 * 上面的去掉最后一行
 * 已经摆完前i-1行,且第i-1行的状态是b的所有摆放方案  f[ i-1 ,b ]
 */
public class 玉米田 {
    static int n, m, N = 14, M = 1 << 12;
    static int[] g = new int[N];
    static ArrayList<Integer> state = new ArrayList<Integer>();
    static ArrayList<Integer>[] head = new ArrayList[M];
    static long[][] f = new long[N][M];
    static int mod = (int) 1e8;

    public static void main(String[] args) {
        for (int i = 0; i < M; i++) {
            head[i] = new ArrayList<Integer>();
        }
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int a, b, t;
        for (int i = 1; i <= m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                t = sc.nextInt();
                if (t == 0) g[i] += 1 << j;
            }
        }

        for (int i = 0; i < 1 << n; i++) {
            if (checK(i)) {
                state.add(i);
            }
        }

        for (int i = 0; i < state.size(); i++) {
            for (int j = 0; j < state.size(); j++) {
                a = state.get(i);
                b = state.get(j);
                if ((a & b) == 0) {
                    head[i].add(j);
                }
            }
        }

        f[0][0] = 1;
        for (int i = 1; i <= m + 1; i++) {
            for (int j = 0; j < state.size(); j++) {
                if ((state.get(j) & g[i]) == 0) {
                    for (Integer w : head[j]) {
                        f[i][j] = (f[i][j] + f[i - 1][w]) % mod;
                    }
                }
            }
        }
        System.out.println(f[m + 1][0]);
    }

    static void fe() {
        int a, b;
        for (int i = 0; i < state.size(); i++) {
            for (int j = 0; j < state.size(); j++) {
                a = state.get(i);
                b = state.get(j);
                if ((a & b) == 0) {
                    head[a].add(b);
                }
            }
        }

        f[0][0] = 1;
        for (int i = 1; i <= m + 1; i++) {
            for (int j = 0; j < state.size(); j++) {
                a = state.get(j);
                if ((a & g[i]) == 0) {
                    for (Integer w : head[a]) {
                        f[i][a] = (f[i][a] + f[i - 1][w]) % mod;
                    }
                }
            }
        }
        System.out.println(f[m + 1][0]);
    }

    static boolean checK(int state) {
        return (state & state >> 1) == 0;
    }
}
