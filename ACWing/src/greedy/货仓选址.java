package greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/njuptACMcxk/article/details/104337089
 */
public class 货仓选址 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a, 0, n);
        long ans = 0;
        for (int i = 0; i <= (n - 1) / 2; i++) {
            ans += a[n - i - 1] - a[i];
            System.out.println(n-i-1+"  "+i);
        }
        System.out.println(ans);
    }

    static int n;
    static int[] a = new int[100005];

}
