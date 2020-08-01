package 数学;

import java.util.Arrays;

//线性筛质数
//https://blog.csdn.net/qq_30277239/article/details/103687477
public class 线性筛 {
    public static void main(String[] args) {
        ol(100);
        long s = System.nanoTime();
        oldN(3234567);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e9);
    }

    static int cnt = 0;
    static int[] prime = new int[100];
    static boolean[] vis = new boolean[10000];

    //让每个合数只被它的最小质因子筛选一次，以达到不重复的目的。
    static void ol(int n) {
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) prime[cnt++] = i;
            for (int j = 0; prime[j] <= n / i; j++) {
                vis[prime[j] * i] = true;
                if (i % prime[j] == 0) break;
            }
        }
        System.out.println(Arrays.toString(prime));
    }

    static boolean isprimer(int x) {
        if (x == 2) return true;
        if (x < 2 || x % 2 == 0) return false;
        int t = (int) Math.sqrt(x);
        for (int i = 3; i <= t; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static void euler(int n) {
        boolean[] vis = new boolean[n + 1];
        int cnt = 0;
        int[] primer = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) primer[cnt++] = i;
            for (int j = 0; primer[j] <= n / i; j++) {
                vis[primer[j] * i] = true;
                if (i % primer[j] == 0) break;
            }
        }
    }

    //求第N个质数是多少
    static void oldN(int N) {
        int n = (int) 1e7;
        int r = 0;
        while ((n / Math.log(n)) < N) {
            r++;
            n += 100000;
        }
        System.out.println(r);
        int cnt = 0;
        prime = new int[n + 1];
        vis = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) prime[cnt++] = i;
            r = n / i;
            for (int j = 0; prime[j] <= r; j++) {
                vis[prime[j] * i] = true;
                if (i % prime[j] == 0) break;
            }
        }
        System.out.println(prime[N - 1]);
    }
}
