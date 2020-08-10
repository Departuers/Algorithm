package 数学;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/tomjobs/article/details/104804802
 * 区间筛法:改进筛法筛区间
 */
public class 质数距离 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            f(50000);
            l = sc.nextInt();
            r = sc.nextInt();
            System.out.println(r);
            Arrays.fill(st, false);
            for (int i = 0; i < cnt; i++) {
                int p = primer[i];
                for (long j = Math.max(p * 2, (l + p - 1) / p * p); j <= r; j += p) {
                    st[(int) (j - l)] = true;
                    //下标很大,偏移量很小
                }
            }
            cnt = 0;
            for (int i = 0; i <= r - l; i++) {
                if (!st[i] && i + l >= 2) primer[cnt++] = (int) (i + l);
            }
            if (cnt < 2) System.out.println("There are no adjacent primes.");
            else {
                int minp = 0, maxp = 0;
                for (int i = 0; i + 1 < cnt; i++) {
                    int d = primer[i + 1] - primer[i];
                    if (d < primer[minp + 1] - primer[minp]) minp = i;
                    if (d > primer[maxp + 1] - primer[maxp]) maxp = i;
                }
                System.out.printf("%d,%d are closest, %d,%d are most distant.\n", primer[minp], primer[minp + 1], primer[maxp], primer[maxp + 1]);
            }
        }

    }

    static int cnt = 0;
    static boolean[] st = new boolean[(int) 1e6+100];
    static int[] primer = new int[(int) 1e5];

    //筛出前根号n的素数,这些素数的倍数落在(l,r)之间就是合数,
    // 而r-l距离很小,这样就可以统计出素数了
    static void f(int n) {
        Arrays.fill(st, false);
        cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primer[cnt++] = i;
            for (int j = 0; primer[j] * i <= n; j++) {
                st[primer[j] * i] = true;
                if (i % primer[j] == 0) break;
            }
        }
    }

    static long l, r;
}
