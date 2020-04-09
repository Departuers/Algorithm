package 线性dp.背包模型;

import java.util.Scanner;

public class 背包问题求具体方案 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i + 1][j];
                if (j >= v[i])
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - v[i]] + w[i]);
            }
        }
        //f[1][m]是最大值
        int j = m;
        for (int i = 1; i <= n; i++) {//1~n个物品可选
            if (j >= v[i] && f[i][j] == f[i + 1][j - v[i]] + w[i]) {
                System.out.print(i + " ");
                j -= v[i];
            }
        }
    }

    static int n, m;
    static int[] v = new int[1010], w = new int[1010];
    static int[][] f = new int[1010][1010];

}
