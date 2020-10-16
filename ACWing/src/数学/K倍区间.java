package 数学;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/content/7098/
 */
public class K倍区间 {
    static int[] s = new int[100010];//记录前缀和
    static int[] cnt = new int[100010];//记录余数是i的数有多少个

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        cnt[0] = 1;
        for (int i = 1; i <= n; i++) {
            s[i] = (sc.nextInt() + s[i - 1]) % k;
            cnt[s[i]]++;
        }
        long res = 0;
        /**
         * 可以最后单独对res中的数据进行计算，即C2n，那么它的计算公式就是n(n−1)/2
         * 我们可以知道sum[i]在取模后中的最大值就是k−1，因此我们可以枚举到k−1，然后进行累加即可。
         */
        for (int i = 0; i < k; i++) {//余数必然在0~k-1之间
            res += (cnt[i] * (cnt[i] - 1)) / 2;
        }
        System.out.println(res);
    }

    static void k() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = (s[i - 1] + sc.nextInt()) % k;
        }//前缀和%k就考研

        long res = 0;
        cnt[0] = 1;//余数为0时，已经占一个
        for (int i = 1; i <= n; i++) {
            res += cnt[s[i]];
            cnt[s[i]]++;
        }
        System.out.println(res);
    }
}
