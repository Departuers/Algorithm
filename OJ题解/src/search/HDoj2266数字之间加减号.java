package search;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * 给一个只包含1-9的字符串s,在里面加上+或者-号,
 * 求有多少种方案使其结果为n
 * 比如给定s=21  n=1 则2-1=1只有一种方案
 * <p>
 * 样例
 * 123456789 3
 * 21 1
 * out:
 * 18
 * 1
 */
public class HDoj2266数字之间加减号 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        while (sc.hasNext()) {
            arr = sc.next().toCharArray();
            len = arr.length;
            n = sc.nextLong();
            ans = 0;
            dfs(0, 0);
            System.out.println(ans);
        }
    }

    static int len = 0;
    static long ans, n;
    static char[] arr;

    static void dfs(int pos, int sum) {
        if (pos == arr.length) {
            if (sum == n) ans++;
            return;
        }
        int tem = 0;
        for (int i = pos; i < arr.length; i++) {
            tem = tem * 10 + arr[i] - '0';
            System.out.println(sum + " " + tem);
            dfs(i + 1, sum + tem);
            if (pos != 0) dfs(i + 1, sum - tem);
        }
    }
}
