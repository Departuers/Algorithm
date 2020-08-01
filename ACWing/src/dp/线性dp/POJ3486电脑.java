package dp.线性dp;

import java.util.Arrays;
import java.util.Scanner;

public class POJ3486电脑 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        c = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                m[i][j] = sc.nextInt();
            }
        }
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        f[1] = m[1][1] + c;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = Math.min(f[i], f[j - 1] + m[j][i] + c);
            }
            System.out.println(f[i]);
        }
        System.out.println(f[n]);
    }

    static int n, c;
    static int[] f = new int[1000];
    static int[][] m = new int[1000][1000];
}
