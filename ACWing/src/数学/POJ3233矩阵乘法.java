package 数学;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * 给定一个n*n矩阵A,和一个整数k
 * 求S=A+A^2+A^3+A^k并对k取模
 * https://www.cnblogs.com/hadilo/p/5903514.html
 * 矩阵乘法满足乘法结合律和交换律
 * A+A^2=A(I+A)
 * A+A^2+A^3+A^4=(A+A^2)(I+A^2)
 * A^1+A^2+A^3+A^4+A^5+A^6=(A^1+A^2+A^3)(I+A^3)
 * 如果sum(n)=A+A^2+A^3+A^k
 * 如果n是偶数sum(n)=sum(n/2)(I+(A^n/2))
 * 符合递归的性质,(A^1+A^2+A^3+...+A^k/2)(I+(A^k/2))
 * 如果n是奇数sum(n)=sum(n-1)+A^n 求比它低1次方的再加上A^n
 */
public class POJ3233矩阵乘法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        m = sc.nextInt();
        kk = sc.nextInt();//mod数
        long[][] a = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextLong();
            }
        }
        long[][] res = sum(a, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int m, kk, n = 0;

    static long[][] sum(long[][] a, int k) {
        if (k == 1) return a;
        int R = a.length, C = a[0].length;
        long[][] c = new long[n][n];
        for (int i = 0; i < R; i++) {
            c[i][i] = 1;
        }
        c = add(c, quickPow(a, k >> 1));
        c = mul(c, sum(a, k >> 1));
        if ((k & 1) == 1) {
            c = add(c, quickPow(a, k));
        }
        return c;
    }

    static long[][] mul(long[][] a, long[][] b) {
        int R = a.length, C = b[0].length;
        long[][] res = new long[n][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % kk;
                }
            }
        }
        return res;
    }

    static long[][] add(long[][] a, long[][] b) {
        int R = a.length, C = a[0].length;
        long[][] res = new long[n][n];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[i][j] = b[i][j] + a[i][j];
                res[i][j] %= kk;
            }
        }
        return res;
    }

    static long[][] quickPow(long[][] a, int k) {
        long[][] tem = a.clone();
        int R = a.length, C = a[0].length;
        long[][] res = new long[n][n];
        for (int i = 0; i < R; i++) {
            res[i][i] = 1;
        }
        while (k != 0) {
            if ((k & 1) == 1) {
                res = mul(res, tem);
            }
            tem = mul(tem, tem);
            k >>= 1;
        }
        return res;
    }
}
