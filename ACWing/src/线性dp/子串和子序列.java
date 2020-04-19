package 线性dp;

import java.util.Scanner;

public class 子串和子序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.next();
        T = sc.next();
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j))
                    f[i + 1][i + 1][j + 1] = 1;
            }
        }

    }

    static int[][][] f = new int[5010][5010][5010];
    static String S, T;
}
