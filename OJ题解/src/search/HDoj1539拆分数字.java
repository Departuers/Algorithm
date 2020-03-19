package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 给定一个数n, 和一个数m， 要求将m拆分成若干段，
 * 并且要使这若干段之和最接近于n，如果只有一种拆法，
 * 就输出这种拆法。如果对于同一个最接近的和，
 * 有多种拆法，就输出rejected。
 * 如果所有拆法得到的和都比n大，那么就输出error。
 * https://blog.csdn.net/weixin_40163242/article/details/86760142
 * <p>
 * Sample Input
 * 50 12346
 * 376 144139
 * 927438 927438
 * 18 3312
 * 9 3142
 * 25 1299
 * 111 33333
 * 103 862150
 * 6 1104
 * 0 0
 * <p>
 * Sample Output
 * 43 1 2 34 6
 * 283 144 139
 * 927438 927438
 * 18 3 3 12
 * error
 * 21 1 2 9 9
 * rejected
 * 103 86 2 15 0
 */

public class HDoj1539拆分数字 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        while (true){
            n = sc.nextInt();
            m = sc.nextInt();
            if (n==m&&m==0)break;
            arr = len(m);
            len = arr.size();
            mark = new boolean[arr.size()];
            space = new boolean[arr.size()];
            dfs(1, 0, arr.get(0));
            System.out.print(ans+" ");
            for (int i = 0; i < len; i++) {
                if (space[i]) System.out.print(" ");
                System.out.print(arr.get(i));
            }
            System.out.println();
        }
    }

    static int n, m, ans, len;
    static ArrayList<Integer> arr;
    static boolean flag;
    static boolean[] mark;
    static boolean[] space;


    //pos 代表搜索字符串的位置,sum代表当前和,num代表当前数字
    static void dfs(int pos, int sum, int num) {
        if (sum + num > n) return;
        if (pos == len) {
            sum += num;
            if (sum <= n && sum > ans) {
                ans = sum;
                flag = false;
                for (int i = 0; i < len; i++) {
                    space[i] = mark[i];
                }
                return;
            }
            if (sum == ans) {
                flag = true;
            }
            return;

        }

        dfs(pos + 1, sum, num * 10 + arr.get(pos));
        mark[pos] = true;
        dfs(pos + 1, sum + num, arr.get(pos));
        mark[pos] = false;
    }

    static ArrayList<Integer> len(int t) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (t != 0) {
            list.add(t % 10);
            t /= 10;
        }
        Collections.reverse(list);
        return list;
    }
}
