package 线性dp.背包模型;

import java.util.Scanner;

/**
 * 01背包变种
 * 时间映射体积,数量就是数量
 */
public class 采药 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= M; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        for (int i = 1; i <= M; i++) {
            for (int j = T; j >= v[i]; j--) {
                f[j] = Math.max(f[j - v[i]] + w[i], f[j]);
            }
        }
        System.out.println(f[T]);
    }

    static int[] w = new int[104], v = new int[105];
    static int[] f = new int[1090];
    static int T, M;
}