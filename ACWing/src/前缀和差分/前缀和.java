package 前缀和差分;

import java.util.Scanner;

public class 前缀和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        t = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            t[i] = sc.nextInt() + t[i - 1];
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            System.out.println(get(sc.nextInt(), sc.nextInt()));
        }
    }

    static int[] t;
    static int n, m;

    static int get(int l, int r) {
        return t[r] - t[l - 1];
    }
}
