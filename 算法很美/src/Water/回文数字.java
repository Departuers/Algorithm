package Water;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//https://www.dotcpp.com/oj/problem1434.html
//问题 1434: [蓝桥杯][历届试题]回文数字
public class 回文数字 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        ArrayList<Integer> st = new ArrayList<Integer>();
        for (int i = 10000; i < 1000000; i++) {
            if (SD(i, N) && is(i))
                st.add(i);
        }
        Collections.sort(st);
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i));
        }
        if (st.size() == 0)
            System.out.println(-1);
    }

    public static boolean SD(int n, int K) {
        int res = 0;
        while (n != 0) {
            res += n % 10;
            n /= 10;
        }
        return res == K;
    }

    public static boolean is(int n) {
        return new StringBuilder(n + "").reverse().toString().equals(n + "");
    }
}