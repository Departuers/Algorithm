package 线性dp.背包模型;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V的背包。
 * 物品一共有三类：
 * 第一类物品只能用1次（01背包）；
 * 第二类物品可以用无限次（完全背包）；
 * 第三类物品最多只能用 s[i]次(多重背包)
 * 每种体积是 v[i]，价值是 w[i]
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，
 * 且价值总和最大。
 * 输出最大价值。
 */
public class 混合背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        for (int i = 0; i < N; i++) {

        }
    }

    static int N, V;
}
