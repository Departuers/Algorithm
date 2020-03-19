package search;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 这道题太难 AC代码,抄了很久
 */
public class HDoj4403组方程 {

    private static char[] arr = new char[20];
    static int[][] pre = new int[20][20];//预处理
    static int len, ans, start;

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        while (true) {

            String n = sc.next();
            if (n.equals("END")) break;
            arr = n.toCharArray();
            len = arr.length;
            for (int i = 0; i < 20; i++) {
                Arrays.fill(pre[i], 0);
            }
            for (int i = 0; i < len; i++) {//预处理
                for (int j = i; j < len; j++) {
                    for (int k = i; k <= j; k++) {
                        pre[i][j] = pre[i][j] * 10 + arr[k] - '0';
                    }
                }
            }
            ans = 0;
            for (start = 0; start < len; start++) {
                dfsL(0, 0);
            }
            System.out.println(ans);
        }
    }

    //plus是加号所在的位置,ansL是等号左边的和
    static void dfsL(int plus, int ansL) {
        if (plus > start) dfsR(start + 1, 0, ansL);
        for (int k = plus; k <= start; k++) {
            dfsL(k + 1, ansL + pre[plus][k]);
        }
        return;
    }

    static void dfsR(int plus, int ansR, int ansL) {
        if (plus >= len) {
            if (ansR == ansL) {
                ans++;
            }
            return;
        }
        for (int k = plus; k < len; k++) {
            dfsR(k + 1, ansR + pre[plus][k], ansL);
        }
        return;
    }
}
