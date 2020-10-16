package 数学;

/**
 *
 */
public class 埃氏筛法 {
    public static void main(String[] args) {
        long s = System.nanoTime();
        e(32345678);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        s = System.nanoTime();
        euler(32345678);
//        e(12345678);
        t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        System.out.println();
    }

    static int[] primer = new int[99999991];

    /**
     * 埃氏筛法求n以内的质数
     * 调和级数极限为 ln n+C  C为euler 常数
     * 不加优化是O(n log n) 加上是O(n log log n)
     * 1+1/2+1/3+...
     *
     * @param n
     */
    static void f(int n) {
        int cnt = 0;
        boolean[] vis = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) primer[cnt++] = i;
            else continue;//只需要筛所有质数的倍数
            for (int j = 2 * i; j <= n; j += i) {
                vis[j] = true;
            }
        }
        System.out.println(primer[cnt - 1]);
    }

    static void e(int n) {
        int cnt = 0;
        boolean[] st = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                primer[cnt++] = i;
                for (int j = 2 * i; j <= n; j += i) {
                    st[j] = true;
                }
            }
        }
        System.out.println(primer[cnt - 1]);

    }

    /**
     * @param n
     */
    static void euler(int n) {
        boolean[] vis = new boolean[n + 1];
        int cnt = 0;
        System.out.println();
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) primer[cnt++] = i;
            //把i的质数倍筛出去
            for (int j = 0; primer[j] * i <= n; j++) {
                vis[primer[j] * i] = true;
                if (i % primer[j] == 0) break;
            }
        }
        System.out.println(primer[cnt - 1]);

    }
}
