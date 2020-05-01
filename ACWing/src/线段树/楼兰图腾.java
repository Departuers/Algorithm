package 线段树;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目https://www.acwing.com/problem/content/description/243/
 * https://www.acwing.com/solution/acwing/content/1008/
 */
public class 楼兰图腾 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            max_value = Math.max(a[i], max_value);//记录数组最大值
        }
        res1();
        System.out.println(sumans());

    }

    static int N = (int) (2e5 + 200), n, max_value;
    static int[] c = new int[N];
    static int[] a = new int[N];
    static long[] l = new long[N];
    static long[] r = new long[N];

    static long ask(int x) {
        long ans = 0;
        while (x != 0) {
            ans += c[x];
            x -= lowbit(x);
        }
        return ans;
    }

    static void add(int i, int value) {
        for (; i <= n; i += lowbit(i)) {
            c[i] += value;
        }
    }

    private static int lowbit(int x) {
        return x & (-x);
    }

    static void res1() {
        Arrays.fill(c, 0);
        for (int i = n; i != 0; i--) {
            r[i] = ask(max_value) - ask(a[i]);
            System.out.println(r[i]);
            add(a[i], 1);
        }
        System.out.println();
        Arrays.fill(c, 0);
        for (int i = 1; i <= n; i++) {
            l[i] = ask(max_value) - ask(a[i]);
            System.out.println(l[i]);
            add(a[i], 1);
        }
    }

    static void res2() {
        Arrays.fill(c, 0);
        for (int i = n; i != 0; i--) {
            r[i] = ask(a[i - 1]);
            System.out.println(r[i]);
            add(a[i], 1);
        }
        System.out.println();
        Arrays.fill(c, 0);
        for (int i = 1; i <= n; i++) {
            l[i] = ask(a[i] - 1);
            System.out.println(l[i]);
            add(a[i], 1);
        }
    }

    static long sumans() {
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += l[i] * r[i];
        }
        return ans;
    }
}
