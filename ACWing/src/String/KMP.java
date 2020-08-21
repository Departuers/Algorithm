package String;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/100881221
 * 3
 * aba
 * 5
 * ababa
 * out:
 * 0
 * 2
 */
public class KMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        p = sc.next().toCharArray();
        m = sc.nextInt();
        a = sc.next().toCharArray();
        int i = 0, j = 0;
        init();
        System.out.println(Arrays.toString(ne));
        //i是源串,j是匹配串
        while (i < m && j < n) {
            if (j == -1 || a[i] == p[j]) {
                //到了开头或者匹配成功
                i++;
                j++;
            } else j = ne[j];//回到适配位置
            if (j == n) {//匹配成功!找到了
                j = ne[j];
                System.out.println(i - n);
                //找到每个匹配成功的起始位置
            }
        }
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
