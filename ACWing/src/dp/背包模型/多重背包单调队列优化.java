package dp.背包模型;

import java.util.Scanner;

/**
 * 完全背包枚举到不能选为止,多重背包枚举到选择上限为止
 * 与完全背包不同的是每个物品最多选s[i]个,可选有限个
 * 状态定义:f[i,j]前i个物品可选,体积最大为j的最大价值
 * 状态划分:选第i种物品的第1个,选第i种物品的第2个,...,选第i种物品的第s[i]个,和不选第i组物品
 * 状态计算:显然不选第i组物品就是f[i-1,j]
 * |________|+w[i][1]
 * |________|+w[i][2]
 * ....
 * |________|+w[i][k]
 * 前面的最大值如何计算
 */
public class 多重背包单调队列优化 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {

        }
    }

    static int n, m;
}
