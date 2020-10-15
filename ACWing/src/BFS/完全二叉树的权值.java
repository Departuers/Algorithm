package BFS;

import java.util.Scanner;

public class 完全二叉树的权值 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        long maxn = -9;
        int depth = 1;
        /*
         * d表示深度   ,i代表每层节点的数量
         */
        for (int i = 1, d = 1; i <= n; i *= 2, d++) {
            long sum = 0;
            for (int j = i; j < i + (1 << d - 1) && j <= n; j++) {
                sum += a[j];
            }
            if (maxn < sum) {
                depth = d;
                maxn = sum;
            }
        }
        System.out.println(depth);
    }

    static int N = (int) (1e5 + 10), n;
    static int[] a = new int[N];
}
