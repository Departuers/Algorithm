package 矩阵快速幂;

import java.util.Scanner;

public class 斐波那契前n项和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = 1;
//        long m = sc.nextLong();
//        p = sc.nextLong();
        long[][] a = {{1, 1}, {1, 0}};
        long[][] res = {{1, 0}, {1, 0}};
        // 1 1 2 3 5 8 13 21 34
        // 1 2 4 7 12 20 33 67
        long m = 3;
        for (long i = m - 2; i != 0; i >>= 1) {
            if ((i & 1) == 1) res = mul(a, res);
            a = mul(a, a);
        }
        mod = res[0][0];
        System.out.println(mod);

        long[][] b = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 1}};
        long[][] r = {{1, 0, 0},
                {1, 0, 0},
                {2, 0, 0}};
        for (long i = n - 2; i != 0; i >>= 1) {
            if ((i & 1) == 1) r = mull(b, r);
            b = mull(b, b);
        }
        System.out.println(r[2][0]);
    }

    static long p, mod;

    static long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) ;
                }
            }
        }
        return res;
    }

    static long[][] mull(long[][] a, long[][] b) {
        long[][] res = new long[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % p;
                }
            }
        }
        return res;
    }
}
