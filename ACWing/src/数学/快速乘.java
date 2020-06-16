package 数学;

import java.util.Scanner;

/**
 * 90. 64位整数乘法
 * 求 a 乘 b 对 p 取模的值。
 * 输入格式
 * 第一行输入整数a，第二行输入整数b，第三行输入整数p。
 * 输出格式
 * 输出一个整数，表示a*b mod p的值。
 * 数据范围
 * 1≤a,b,p≤1018
 * 输入样例：
 * 3
 * 4
 * 5
 * 输出样例：
 * 2
 * 类似快速幂:
 * a*b转换成加法
 * a+a+a 加法操作b次
 * b次转换成2进制
 * a 2a 4a 8a 16a 32a
 * 每次翻2倍,转换成2进制
 * 乘法转加法,快速幂思想
 */
public class 快速乘 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long p = sc.nextLong();
        System.out.println(ks(a, b, p));
        System.out.println(ca(2, 20));
    }
    //递归快速乘
    static int ca(int a, int b) {
        int res = 0;
        if (b == 0) return res;
        if ((b & 1) == 1) {
            res += a;
        }
        return res + ca(a << 1, b >> 1);
    }

    //转换为快速加法,a*b
    static long ks(long a, long b, long p) {
        long tem = a;
        long res = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res += tem % p;
                res %= p;
            }
            tem *= 2 % p;
            b >>= 1;
        }
        return res;
    }


}
