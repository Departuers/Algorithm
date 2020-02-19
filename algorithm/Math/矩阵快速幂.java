package Math;

import java.util.Arrays;

public class 矩阵快速幂 {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 2},
                {1, 1, 5}};

        int[][] b = {{2, 2},
                {2, 3},
                {1, 3}};
        int[][] multiply = multiply(a, b);
        System.out.println(Arrays.toString(multiply[0]));
        System.out.println(Arrays.toString(multiply[1]));

        System.out.println(ex(3, 3));
        System.out.println(3 >> 1);

    }

    /**
     * 两个任意矩阵的乘法
     *
     * @param a a矩阵
     * @param b b矩阵
     * @return 结果矩阵
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) throw new RuntimeException("wufa");

        int R = a.length;
        int C = b[0].length;
        int[][] res = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < b.length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 一个数的快速幂运算,非常巧妙很快
     * 比如指数m=10
     *           8 4 2 1
     * 写成二进制 1 0 1 0   转换成10进制为10
     * 可以写成n的8次方乘以n的2次方
     * 不停地自己乘以自己,翻倍
     *
     * @param m 指数
     * @return 求解n的m次方
     */
    public static long ex(long n, long m) {
        long Temp = n;//n的一次方
        long result = 1;
        while (m != 0) {
            if ((m & 1) == 1) {//&1为1代表选这个倍数,要这个
                result *= Temp;
            }
            Temp = Temp * Temp;
            m >>= 1;//指数/=2
        }
        return result;
    }

}
