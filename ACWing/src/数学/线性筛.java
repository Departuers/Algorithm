package 数学;

//线性筛质数
//https://blog.csdn.net/qq_30277239/article/details/103687477
public class 线性筛 {
    public static void main(String[] args) {
        long s = System.nanoTime();

        oldN(4234567);
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
    }

    //求第N个质数是多少
    static void oldN(int N) {
        int n = 6000000;
        int r = 0;
        while ((n / Math.log(n)) < N) {
            r++;
            n += 5000;
        }
        System.out.println(r);
        int cnt = 0;
        prime = new int[n + 1];
        vis = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) prime[cnt++] = i;
            for (int j = 0; prime[j] <= n / i; j++) {
                vis[prime[j] * i] = true;
                if (i % prime[j] == 0) break;
            }
        }
        System.out.println(prime[N - 1]);
    }
}
