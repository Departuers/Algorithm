package 数学;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/content/9536/
 * 夏洛克有了一个新女友（这太不像他了！）。
 * 情人节到了，他想送给女友一些珠宝当做礼物。
 * 他买了 n 件珠宝，第 i 件的价值是 i+1，也就是说，珠宝的价值分别为 2,3,…,n+1。
 * 华生挑战夏洛克，让他给这些珠宝染色，使得一件珠宝的价格是另一件珠宝的价格的质因子时，两件珠宝的颜色不同。
 * 并且，华生要求他使用的颜色数尽可能少。
 * 请帮助夏洛克完成这个简单的任务。
 * 输入格式
 * 只有一行一个整数 n，表示珠宝件数。
 * 输出格式
 * 第一行一个整数 k，表示所使用的颜色数；
 * 第二行 n 个整数，表示第 1 到第 n 件珠宝被染成的颜色。
 * 若有多种答案，输出任意一种。
 * 请用 1 到 k 表示你用到的颜色。
 * 数据范围
 * 1≤n≤100000
 * 输入样例1：
 * 3
 * 输出样例1：
 * 2
 * 1 1 2
 * 输入样例2：
 * 4
 * 输出样例2：
 * 2
 * 2 1 1 2
 * 分析:质数连合数,连一条边,是二分图,最多需要2种颜色
 * 左边是质数,右边是合数,合数必然有质因子,可能不止一个,但满足二分图!!!
 */
public class 夏洛克和他的女朋友 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        if (n <= 2) {
            System.out.println(1);
        } else System.out.println(2);
        n++;
        f();
        for (int i = 2; i <= n; i++) {
            if (!st[i]) System.out.print("1 ");
            else System.out.print("2 ");
        }

    }

    static boolean[] st = new boolean[(int) 1e6];
    static int[] primer = new int[(int) 1e6];
    static int n;

    static void f() {
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primer[cnt++] = i;
            //枚举i的质数倍
            for (int j = 0; primer[j] <= n / i; j++) {
                st[primer[j] * i] = true;
                if (i % primer[j] == 0) break;
            }
        }
    }
}
