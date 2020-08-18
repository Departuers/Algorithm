package 数学;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/acwing/content/12579/
 * https://www.hzxueyan.com/archives/85/
 * 监狱有连续编号为 11 到 nn 的 nn 个房间，每个房间关押一个犯人。
 * 有 mm 种宗教，每个犯人可能信仰其中一种。
 * 如果相邻房间的犯人信仰的宗教相同，就可能发生越狱。
 * 求有多少种状态可能发生越狱。
 * 输入
 * 共一行，包含两个整数 mm 和 nn。
 * 输出
 * 可能越狱的状态数，对 100003取余。
 * 数据范围
 * 输入样例
 * 2 3
 * 输出样例
 * 6
 * 样例解释
 * 所有可能的 66 种状态为：(000)(001)(011)(100)(110)(111)(000)(001)(011)(100)(110)(111)。
 */
public class 越狱 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        System.out.println((qmi(m, n) - m * qmi(m - 1, n - 1)) % mod);
    }

    static long qmi(long a, long b) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }

    static int n, m, mod = 100003;

}
