package 数学;

//线性筛质数
public class 线性筛 {
    public static void main(String[] args) {
        oldN(1234567);
    }

    static int cnt = 0;
    static int[] prime = new int[100];
    static boolean[] vis = new boolean[10000];

    //让每个合数只被它的最小质因子筛选一次，以达到不重复的目的。
    //太过牛逼只能背下来
    static void ol(int n) {
        for (int i = 2; i <= n; i++) {
            if (!vis[i]) prime[cnt++] = i;
            for (int j = 0; prime[j] <= n / i; j++) {
                vis[prime[j] * i] = true;
                if (i % prime[j] == 0) break;
            }
        }
    }

    static void oldN(int N) {
        int n = N;
        int r = 0;
        while ((n / Math.log(n)) < N) {
            r++;
            n += 599;
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
