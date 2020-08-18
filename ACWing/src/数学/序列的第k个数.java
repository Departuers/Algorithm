package 数学;

import java.util.Scanner;

/**
 * https://blog.csdn.net/njuptACMcxk/article/details/107328091
 * BSNY 在学等差数列和等比数列，当已知前三项时，就可以知道是等差数列还是等比数列。
 * 现在给你 整数 序列的前三项，这个序列要么是等差序列，要么是等比序列，你能求出第 k 项的值吗。
 * 如果第 k 项的值太大，对其取模 200907。
 * 输入格式
 * 第一行一个整数 T，表示有 T 组测试数据；
 * 对于每组测试数据，输入前三项 a,b,c，然后输入 k。
 * 输出格式
 * 对于每组数据，输出第 k 项取模 200907 的值。
 * 数据范围
 * 1≤T≤100,1≤a≤b≤c≤109,1≤k≤1091≤T≤100,1≤a≤b≤c≤10^9
 * ,1≤k≤10^9
 * 输入样例：
 * 2
 * 1 2 3 5
 * 1 2 4 5
 * 输出样例：
 * 5
 * 16
 */
public class 序列的第k个数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c, d, k;
        d = sc.nextInt();
        for (int i = 0; i < d; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            k = sc.nextInt();
            if (a + c == 2 * b) {
                System.out.println((a + (k - 1) * (b - a)) % mod);
            } else {
                System.out.println((a * qmi((b / a), k-1)) % mod);
            }
        }

    }

    static long qmi(long a, long b) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }

    static int mod = 200907;
}
