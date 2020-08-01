import java.util.Arrays;
import java.util.Scanner;

public class P1216数字三角形 {
    public static void main(String[] args) {
        f();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                g[i][j] += Math.max(g[i + 1][j], g[i + 1][j + 1]);
            }
        }
        System.out.println(g[1][1]);
    }

    static void f() {
        n = 9999;
        boolean[] st = new boolean[99999];
        int[] pr = new int[9999];
        int cnt = 0;
        for (int i = 2; i < 9999; i++) {
            if (!st[i]) pr[cnt++] = i;
            for (int j = 0; pr[j] <= n / i; j++) {
                st[pr[j] * i] = true;
                if (i % pr[j] == 0) break;
            }
        }
        System.out.println(Arrays.toString(pr));
    }

    static int[][] g = new int[1010][1010];
    static int n;
}
