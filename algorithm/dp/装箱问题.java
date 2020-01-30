package dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 装箱问题(弱01背包问题,水题)
 * 有一个箱子容量为V（正整数，0＜＝V＜＝20000），同时有n个物品（0＜n＜＝30），每个物品有一个体积（正整数）。
 * 要求n个物品中，任取若干个装入箱内，使箱子的剩余空间为最小。
 * 输入描述  Input Description
 * 一个整数v，表示箱子容量
 * 一个整数n，表示有n个物品
 * 接下来n个整数，分别表示这n 个物品的各自体积
 * 输出描述  Output Description
 * 一个整数，表示箱子剩余空间。
 * 样例输入  Sample Input
 * 24
 * 6
 * 8
 * 3
 * 12
 * 7
 * 9
 * 7
 * 样例输出   Sample Output
 * 0
 */
public class 装箱问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();//箱子容量消除
        int n = sc.nextInt();//物品数量
        int tiji[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tiji[i] = sc.nextInt();
        }
        int dp[][] = new int[n + 1][v + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                if (tiji[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - tiji[i]] + tiji[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(dp[n][v]);
    }
}
