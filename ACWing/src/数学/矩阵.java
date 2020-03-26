package 数学;

/**
 * 矩阵的加减法
 * 把矩阵对应的数字相加或相加就行了
 * 矩阵乘以一个数字,把该数字乘以矩阵的所有数字就可以了
 * <p>
 * 矩阵乘法:
 * 一个大小为n*m的矩阵和一个大小为m*r的矩阵可以相乘
 * 最终得到一个n*r的矩阵,不可以交换位置,不满足交换律
 * 第一个矩阵的列数,必须等于第二个矩阵的行数
 * 如何第一行乘以第一列的值相加就是结果矩阵的第一个数
 * 单位矩阵,就是对角线为1的矩阵,乘以任何一个非零矩阵都等于它自己
 */
public class 矩阵 {
    public static void main(String[] args) {

    }

    //矩阵乘法
    static long[][] mul(long[][] a, long[][] b) {
        int R = a.length, C = b[0].length;
        long[][] res = new long[R][C];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    //矩阵加法
    static long[][] add(long[][] a, long[][] b) {
        int R = a.length, C = a[0].length;
        long[][] res = new long[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[i][j] = b[i][j] + a[i][j];
            }
        }
        return res;
    }

    //矩阵快速幂
    static long[][] quickPow(long[][] a, int k) {
        long[][] tem = a;
        int R = a.length, C = a[0].length;
        long[][] res = new long[R][C];
        for (int i = 0; i < R; i++) {
            if (i < C)
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
