package 基础练习;

import java.util.Scanner;

public class 分解质因数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int t = sc.nextInt();
        int[] pr = new int[10101];
        boolean[] st = new boolean[10100];
        int cnt = 0;
        for (int i = 2; i <= t; i++) {
            if (!st[i]) {
                pr[cnt++] = i;
            }
            for (int j = 0; pr[j] * i <= t; j++) {
                st[pr[j] * i] = true;
                if (i % pr[j] == 0) break;
            }
        }
        for (int i = s; i <= t; i++) {
            if (!st[i]) {
                System.out.println(i + "=" + i);
            } else {
                StringBuilder f = new StringBuilder();
                int g = i;
                for (int j = 0; pr[j] <= g; j++) {
                    while (g % pr[j] == 0) {
                        f.append("*").append(pr[j]);
                        g /= pr[j];
                    }
                }
                System.out.println(i + "=" + f.substring(1, f.length()));
            }
        }
    }
}
