package 矩阵快速幂;

import java.util.Scanner;

public class 第n项 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            long m = sc.nextLong();
            if (m == 0) {
                System.out.println(0);
                continue;
            }
            if (m == -1) break;
            long[][] a = {{1, 1}, {1, 0}};
            long[][] res = {{1, 0}, {1, 0}};
            for (long i = m - 2; i != 0; i >>= 1) {
                if ((i & 1) == 1) res = mul(a, res);
                a = mul(a, a);
            }
            System.out.println(res[0][0]);
        }
    }

    static long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % 10000;
                }
            }
        }
        return res;
    }
}
