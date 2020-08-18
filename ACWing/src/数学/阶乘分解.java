package 数学;

import java.util.Scanner;

/**
 * https://blog.csdn.net/aoying6521/article/details/101785682
 * https://www.acwing.com/solution/acwing/content/982/
 * https://www.hzxueyan.com/archives/83/
 * 给定整数 N ，试把阶乘 N!分解质因数，按照算术基本定理的形式输出分解结果中的pi和 ci
 * 即可。
 * 输入样例
 * 5
 * 输出样例
 * 2 3
 * 3 1
 * 5 1
 */
public class 阶乘分解 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        init(n);
        for (int i = 0; i < cnt; i++) {
            int t = p[i];
            int s = 0;
            for (int j = n; j != 0; j /= t) {
                s += j / t;
            }
            System.out.println(t + " " + s);
        }
    }

    static void init(int n) {

        for (int i = 2; i <= n; i++) {
            if (!st[i]) p[cnt++] = i;
            for (int j = 0; p[j] <= n / i; j++) {
                st[p[j] * i] = true;
                if (i % p[j] == 0) break;
            }
        }
    }

    static int n, cnt = 0;
    static boolean[] st = new boolean[(int) (1e6 + 10)];
    static int[] p = new int[(int) (1e6 + 10)];
}
