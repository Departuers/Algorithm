package 树状数组;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目
 * https://www.acwing.com/problem/content/description/243/
 * https://www.acwing.com/solution/acwing/content/1008/
 * 巧妙想法,也可以求逆序数!!!
 * 建树状数组O(nlogn)
 * 天才想法太巧妙啊
 */
public class 楼兰图腾 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
//            max_value = Math.max(a[i], max_value);//记录数组最大值
        }
        /**
         * 正序循环,巧妙想法,也可以求逆序数
         * 这里great记录左边有多少个比它大的,
         * lower记录左边有多少比它小的
         */
        for (int i = 1; i <= n; i++) {
            int y=a[i];
            great[i] = ask(n) - ask(y);
            lower[i] = ask(y-1);
            add(y, 1);
        }
        Arrays.fill(c, 0);
        /**
         * 倒序循环这里great记录右边有多少个比它大的,
         * lower记录右边有多少比它小的
         */
        long res1 = 0, res2 = 0;
        for (int i = n; i != 0; i--) {
            int y = a[i];
            res1 += great[i] * (ask(n) - ask(y));
            res2 += lower[i] * ask(y - 1);
            add(y, 1);
        }
        System.out.println(res1 + " " + res2);

    }

    static int N = (int) (2e5 + 200), n, max_value;
    static int[] c = new int[N];
    static int[] a = new int[N];
    static long[] great = new long[N];
    static long[] lower = new long[N];

    static long ask(int x) {
        long ans = 0;
        while (x != 0) {
            ans += c[x];
            x -= lowbit(x);
        }
        return ans;
    }

    static void add(int x, int value) {
        for (int i = x; i <= n; i += lowbit(i)) {
            c[i] += value;
        }
    }

    private static int lowbit(int x) {
        return x & (-x);
    }

}
