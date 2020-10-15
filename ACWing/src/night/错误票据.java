package night;

import java.util.Scanner;

public class 错误票据 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int max = -0x3f3f3f3f;
        int min = 0x3f3f3f3f;
        int duan = 0, chong = 0;
        while (sc.hasNext()) {
            int t = sc.nextInt();
            max = Math.max(max, t);
            min = Math.min(min, t);
            if (st[t]) chong = t;
            st[t] = true;
        }
        for (int i = min; i <= max; i++) {
            if (!st[i]) {
                duan = i;
                break;
            }
        }
        System.out.println(duan + " " + chong);
    }

    static int n;
    static int[] a = new int[110];
    static boolean[] st = new boolean[110];
}
