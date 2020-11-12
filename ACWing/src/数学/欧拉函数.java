package 数学;

//https://blog.csdn.net/weixin_43237242/article/details/97388834
public class 欧拉函数 {
    public static void main(String[] args) {
        System.out.println(euler(6));
    }

    /**
     *  5 1234
     * φ(6)=1 5 与6互质,所以φ(6)=2
     * φ(n)= n*(1-1/p1 )(1-1/p2)... (1-1/pn)
     * φ(6)=6*(1-1/2)(1-1/3)=3*2/3=2
     * 1~N中和N互质的数的个数
     * O(根号n)
     *
     * @param n
     * @return 1~n中与n互质的数的个数
     */
    static long euler(long n) {
        long ans = n;
        for (int i = 2; i <= n / i; i++) {//质因数肯定不超过根号n
            if (n % i == 0) {//如果n能整除i,说明i是n的一个质因子
                ans -= ans / i;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) ans -= ans / n;//说明a还有一个质因子1
        return ans;
    }

    static boolean[] st = new boolean[(int) 1e5];
    static int[] primer = new int[(int) 1e5];
    static int[] euler = new int[(int) 1e5];
    static int cnt = 0;

    /**
     * 1~N中每个数都要求欧拉函数,
     * 借鉴筛法,O(N)求出1~N每个数的欧拉函数
     * 筛法求欧拉函数
     *
     * @param n
     */
    static void geteuler(int n) {

        euler[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                primer[cnt++] = i;
                euler[cnt] = i - 1;//对于素数而言，小于它的数都与之互质，所以i为质数时，phi(i) = i - 1
            }
            for (int j = 0; primer[j] * i <= n; j++) {
                int t = i * primer[j];
                st[t] = true;
                if (i % primer[j] == 0) { //说明primer[j]是i的质因子,
                    //φ(primer[j]*i)与φ(i)的关系
                    //φ(primer[j]*i)=primer[j]φ(i)
                    euler[t] = euler[i] * primer[j];
                    break;
                }
                //i mod pirmer[j] 不等于0
                euler[t] = euler[i] * (primer[j] - 1);
            }
        }

    }
}
