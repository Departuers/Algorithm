package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 489199 894799999 15 3
 * 15 20 2 2
 */
public class 度的数量 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        r = sc.nextInt();
        K = sc.nextInt();
        B = sc.nextInt();
        System.out.println(dp(r) - dp(l - 1));

    }

    //求出从0~n中求出满足要求的数
    static int dp(int n) {
        if (n == 0) return 0;
        ArrayList<Integer> num = new ArrayList<Integer>();
        //把数字转换成B进制
        while (n != 0) {
            num.add(n % B);
            n /= B;
        }
        System.out.println(num);
        int res = 0, last = 0;
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            if (x != 0) {
                res += f[i][K - last];
                if (x > 1) {
                    if (K - last - 1 >= 0) res += f[i][K - last - 1];
                    break;
                }else {
                    last++;
                    if (last > K) break;
                }
            }
            if (i == 0 && last == K) res++;
        }
        return res;
    }

    /**
     * 求组合数
     * 状态定义:f[i,j]前i个数可选,选j个有多少种组合方案
     * 状态划分2个:j个数已经选完,那么f[i-1][j]
     * j个数选的是第i个元素,f[i-1][j-1]
     */
    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) f[i][j] = 1;
                else f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
            }
        }
    }

    static int l, r, K, B, N = 35;
    static int[][] f = new int[N][N];
}
