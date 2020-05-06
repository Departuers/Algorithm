package dp.线性dp;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * https://www.acwing.com/problem/content/273/
 * 题意可知:1-N的身高,1最大,N最小,满足左到有递减,上到下递减
 * 则显然1只能放在,左上角,因为他是最大的,放在其他位置不合适,
 * 则2只能放在第(1,2)或者(2,1),因为它是次大的,
 * 如果放在(1,3)那么(1,2)位置元素比(1,3)大,显然无法构成递减序列
 * 性质1:从上到下,每排人数单调递减
 * 性质2:每一排中,当前排好位置的人一直在最左边连续的一段,
 * 意思就是从左到右放:先放第一个,再放第二个,再放第三个,显然先放第三个再放第一个,就与前文矛盾
 * 1 2 3
 * 4 5
 * 6
 * 最后结论显然:第一排3个,第二排2个,第三排1个,类推,第一排最多5个
 * 这种轮廓作为状态,因为人谁高谁矮不重要,只要他是线性递减的这么多人,
 * 只要是符合该性质,则就是这么多方案
 */
public class 杨老师的照相排列271 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        int t = 0;
        while ((t = sc.nextInt()) != 0) {
            Arrays.fill(arr, 0);
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    for (int c = 0; c < N; c++) {
                        for (int d = 0; d < N; d++) {
                            for (int e = 0; e < N; e++) {
                                dp[a][b][c][d][e] = 0;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < t; i++) {
                arr[i] = sc.nextInt();
            }
            f();
        }
    }

    static int N = 31;
    static long[][][][][] dp = new long[N][N][N][N][N];
    static int[] arr = new int[5];

    static void f() {
        dp[0][0][0][0][0] = 1;
        for (int a = 0; a <= arr[0]; a++) {
            for (int b = 0; b <= Math.min(arr[1], a); b++) {
                for (int c = 0; c <= Math.min(arr[2], b); c++) {
                    for (int d = 0; d <= Math.min(arr[3], c); d++) {
                        for (int e = 0; e <= Math.min(arr[4], d); e++) {
                            if (a != 0 && a - 1 >= b) dp[a][b][c][d][e] += dp[a - 1][b][c][d][e];
                            if (b != 0 && b - 1 >= c) dp[a][b][c][d][e] += dp[a][b - 1][c][d][e];
                            if (c != 0 && c - 1 >= d) dp[a][b][c][d][e] += dp[a][b][c - 1][d][e];
                            if (d != 0 && d - 1 >= e) dp[a][b][c][d][e] += dp[a][b][c][d - 1][e];
                            if (e != 0) dp[a][b][c][d][e] += dp[a][b][c][d][e - 1];
                        }
                    }
                }
            }
        }
        System.out.println(dp[arr[0]][arr[1]][arr[2]][arr[3]][arr[4]]);
    }
}