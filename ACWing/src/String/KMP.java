package String;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/100881221
 */
public class KMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = sc.next().toCharArray();
        m = sc.nextInt();
        p = sc.next().toCharArray();

    }

    static char[] a, p;
    static int n, m;
    static int[] ne = new int[100005];

    //next数组
    static void init() {
        ne[0] = -1;
        int t = 0;
        for (int i = 1; i <= n; i++) {
            t = ne[i - 1];
            while (t != -1 && p[i - 1] != p[t]) t = ne[t];
            ne[i] = t + 1;
        }
    }
}
