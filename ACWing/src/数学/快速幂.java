package 数学;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * 89. a^b
 * 求 a 的 b 次方对 p 取模的值。
 * 输入格式
 * 三个整数 a,b,p ,在同一行用空格隔开。
 * 输出格式
 * 输出一个整数，表示a^b mod p的值。
 * 数据范围
 * 0≤a,b,p≤109
 * 输入样例：
 * 3 2 7
 * 输出样例：
 * 2
 * 如3^7
 * 7的二进制表示111
 * 3^1=3
 * 3^2=9
 * 3^4=81
 * 3^8=...
 * 可以化成3次操作
 */

public class 快速幂 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long p = sc.nextLong();

        System.out.println(ks(a, b, p));
    }

    static long ks(long a, long b, long p) {
        long res = 1 % p;//初始化为1,因为0*任何数都为0
        //有一个很坑的数据,p为0就不会进入循环,则初始1就mod p
        long tem = a;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = res * tem % p;//要这一位,res就加上结果
                res %= p;
            }
            tem *= tem % p;//自增
            tem %= p;//多次mod防越界
            b >>= 1;//去掉最低位
        }
        return res;
    }
}
