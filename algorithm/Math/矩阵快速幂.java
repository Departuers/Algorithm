package Math;

public class 矩阵快速幂 {
    public static void main(String[] args) {
//        long[][] a = {{1, 2, 2},
//                {1, 1, 5}};
//
//        long[][] b = {{2, 2},
//                {2, 3},
//                {1, 3}};
//        long[][] multiply = multiply(a, b);
//        System.out.println(Arrays.toString(multiply[0]));
//        System.out.println(Arrays.toString(multiply[1]));
//
//        System.out.println(ex(3, 3));
//        System.out.println(3 >> 1);
        for (int i = 1; i < 66; i++) {
            System.out.println(fib(i));
        }
    }


    /**
     * 两个任意矩阵的乘法
     *
     * @param a a矩阵
     * @param b b矩阵
     * @return 结果矩阵
     */
    public static long[][] multiply(long[][] a, long[][] b) {
        if (a[0].length != b.length) throw new RuntimeException("wufa");

        int R = a.length;
        int C = b[0].length;
        long[][] res = new long[R][C];
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
     * 8 4 2 1
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

    /**
     * 矩阵快速幂运算
     *
     * @param matrix 矩阵
     * @param p      指数
     * @return 矩阵快速幂运算
     */
    public static long[][] matrixPower(long[][] matrix, long p) {
        long[][] result = new long[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            result[i][i] = 1;
        }//初始化为单位矩阵,也就是对角线都为1,相对于整数的1

        long[][] Temp = matrix;
        while (p != 0) {
            if ((p & 1) == 1) {
                result = multiply(result, Temp);
            }
            Temp = multiply(Temp, Temp);
            p >>= 1;
        }
        return result;
    }

    /**
     * 使用矩阵快速幂求,斐波拉切数列第n项
     *
     * @param n 斐波拉切数列第n项
     * @return 使用矩阵快速幂求, 斐波拉切数列第n项
     */
    public static long fib(int n) {
        long[][] c = {{0, 1}, {1, 1}};//f(n)=f(n-1)+f(n-2)
        long[][] res = matrixPower(c, n - 1);
        res = multiply(new long[][]{{2, 3}}, res);//前2个基础数字,基本条件
        return res[0][0];
    }

}
