package Math;

/**
 * https://blog.csdn.net/qq_39763472/article/details/82428602
 */
public class 埃氏筛法 {
    public static void main(String[] args) {
        long l = System.nanoTime();
        System.out.println(AIshi(1234341));
        long t = System.nanoTime();
        System.out.println(t - l);
        l = System.nanoTime();
        eluer(1234341);
        t = System.nanoTime();
        System.out.println(t - l);
        l = System.nanoTime();
        System.out.println(Bei(1234341));
        t = System.nanoTime();
        System.out.println(t - l);
    }

    /**
     * 埃氏筛法,判断一群数是不是质数,或者求第多少个是素数
     * 素数定理
     * 埃氏筛法的缺陷 ：对于一个合数，有可能被筛多次。例如 30 = 2 * 15 = 3 * 10 = 5*6……那么如何确保每个合数只被筛选一次呢？我们只要用它的最小质因子来筛选即可，这便是欧拉筛法。
     */
    public static int AIshi(int N) {
        int n = 1000;
        while ((n / Math.log(n)) < N) {
            n++;
        }//在整数X范围内有X/log(X)个素数,
        //逆推第N个素数的范围
        int[] arr = new int[n];
        int x = 2;
        while (x < n) {
            if (arr[x] != 0) {
                x++;
                continue;
            }
            int k = 2;
            while (x * k < n) {
                arr[x * k] = -1;
                k++;
            }
            x++;
        }
        int res = 0;
        for (int i = 2; i < arr.length; i++) {

            if (arr[i] != -1) {
                res++;
            }
            if (res == N) {
                return i;
            }
        }
        return -1;
    }

    static int[] prime;
    static int[] visited;

    /**
     * 欧拉线性筛法求第N个质数(最快的!)
     *
     * @param N
     */
    static void eluer(int N) {
        int n = 1000;
        while ((n / Math.log(n)) < N) {
            n++;
        }
        prime = new int[n + 1];
        visited = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            if (visited[i] == 0) {
                prime[++prime[0]] = i;//这个prime[0] 相当于 cnt，用来计数
            }
            for (int j = 1; j <= prime[0] && i * prime[j] <= n; j++) {
                visited[i * prime[j]] = 1;
                if (i % prime[j] == 0) break;
            }
        }
        System.out.println(prime[N]);
    }

    static int Bei(int n) {
        int c = 1000;
        while ((c / Math.log(c)) < n) {
            c++;
        }//判断第n个数的范围
        boolean[] visited = new boolean[c];
        visited[1] = true;
        for (int i = 2; i < c; i++) {
            if (!visited[2]) {
                for (int j = 2 * i; j < c; j += i) {
                    visited[j] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 2; i < c; i++) {
            if (!visited[i]) ans++;
            if (ans == n) return i;
        }
        return -1;
    }
}
