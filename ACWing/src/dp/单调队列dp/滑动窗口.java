package dp.单调队列dp;

import java.util.Scanner;

public class 滑动窗口 {
    static int M = 1000000;
    static int[] q = new int[M];
    static int[] a = new int[M];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int hh = 0, tt = -1;
        for (int i = 0; i < n; i++) {
            if (hh <= tt && q[hh] < i - k + 1) hh++;
            while (hh <= tt && a[q[tt]] >= a[i]) tt--;
            q[++tt] = i;
            if (i >= k - 1) System.out.println(a[q[hh]]);
        }
    }
}
