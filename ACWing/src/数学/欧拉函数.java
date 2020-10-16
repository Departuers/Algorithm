package 数学;

//https://blog.csdn.net/weixin_43237242/article/details/97388834
public class 欧拉函数 {
    public static void main(String[] args) {
        System.out.println(euler(12));
    }

    static long euler(long n) {
        long ans = n;
        for (int i = 2; i <= n / i; i++) {//质因数肯定不超过根号n
            if (n % i == 0) {
                ans -= ans / i;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) ans -= ans / n;
        return ans;
    }

    static boolean[] st = new boolean[(int) 1e5];
    static int[] primer = new int[(int) 1e5];
    static int[] euler = new int[(int) 1e5];
    static int cnt = 0;

    /**
     * 筛法求欧拉函数
     * @param n
     */
    static void geteuler(int n) {

        euler[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                primer[cnt++] = i;
                euler[cnt] = i - 1;//对于素数而言，小于它的数都与之互质，所以i为质数时，phi(i) = i - 1
            }
            for (int j = 0; primer[j] <= n / i; j++) {
                int t = i * primer[j];
                st[t] = true;
                if (i % primer[j] == 0) {
                    euler[t] = euler[i] * primer[j];
                    break;
                }
                euler[t] = euler[i] * (primer[j] - 1);
            }
        }
    }
}
