package 区间dp;

import java.util.Scanner;

public class 能量项链 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            a[i + n] = a[i];
        }
        for (int len = 3; len <= n + 1; len++) {//枚举长度
            for (int l = 1; l + len - 1 <= n * 2; l++) {
                int r = l + len - 1;
                for (int k = l; k < r; k++) {
                    f[l][r] = Math.max(f[l][r], f[l][k] + f[k][r] + a[l] * a[r] * a[k]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, f[i][i + n]);
        }
        System.out.println(res);
    }

    static int[] a = new int[210];
    static int[][] f = new int[210][210];
    static int n;

}
