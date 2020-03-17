package Math;

public class 欧拉线性筛最快的 {
    public static void main(String[] args) {
        eluer(12345);
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
}
