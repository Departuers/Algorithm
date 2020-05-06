package dp.线性dp;

import java.util.Scanner;

/**
 * 给定n个长度不超过10的字符串以及m次询问，每次询问给出一个字符串和一个操作次数上限。
 * 对于每次询问，请你求出给定的n个字符串中有多少个字符串可以在上限操作次数内经过操作变成询问给出的字符串。
 * 每个对字符串进行的单个字符的插入、删除或替换算作一次操作。
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来n行，每行包含一个字符串，表示给定的字符串。
 * 再接下来m行，每行包含一个字符串和一个整数，表示一次询问。
 * 字符串中只包含小写字母，且长度均不超过10。
 * 输出格式
 * 输出共m行，每行输出一个整数作为结果，表示一次询问中满足条件的字符串个数。
 * 数据范围
 * 1≤n,m≤1000,
 * 输入样例：
 * 3 2
 * abc
 * acd
 * bcd
 * ab 1
 * acbd 2
 * 输出样例：
 * 1
 * 3
 * 分析：
 * 本题可直接调用AcWing 902最短编辑距离中的算法框架。
 * 要求n个字符串中有多少个字符串可以在给定次数内转换成指定的m个字符串，
 * 一共需要调用求最短编辑距离函数nm次，由于字符串长度不超过10，
 * 故单次求最短编辑距离复杂度为100，
 * 一共是1000*1000*100=10^8次，可以在2s内完成。
 */
public class 编辑距离 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.next().toCharArray();
        }
        int ans = 0, cnt;
        char[] t;
        while (m-- != 0) {
            ans = 0;
            t = sc.next().toCharArray();
            cnt = sc.nextInt();
            for (int i = 0; i < n; i++) {
                if (ed(a[i], t) <= cnt) ans++;
            }
            //计算第i个能否在cnt步之内变成t
            System.out.println(ans);
        }

    }

    static int ed(char[] a, char[] b) {
        int n = a.length, m = b.length;
        for (int i = 0; i < n; i++) {
            f[i][0] = i;
        }
        //边界,i个字符转换成0个字符,需要转换i次
        for (int i = 0; i < m; i++) {
            f[0][i] = i;
        }
        //边界,0个字符转换成i个字符,需要转换i次
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i - 1] == b[j - 1]) f[i][j] = f[i - 1][j - 1];
                else f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
            }
        }
        return f[n][m];
    }

    static int n, m, N = 1005;
    static int[] b = new int[N];
    static int[][] f = new int[N][N];
    static char[][] a = new char[N][10];
}
