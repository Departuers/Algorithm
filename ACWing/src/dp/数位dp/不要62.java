package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104502932
 */
public class 不要62 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        r = sc.nextInt();
        System.out.println(dp(r) - dp(l - 1));

    }

    static int l, r, N = 9;
    static int[][] f = new int[N][10];


    /**
     * 树的左侧分支用dp预处理
     * f[i,j]表示最高位是j,只有一共有i位的不降数的集合
     * 属性;count,计数
     * 状态计算划分,f[i,j]
     * 考虑last,找到一个分界线,
     * j _ _ _ _... 第一位填j,第二位可以选填j,j+1,j+2...9
     * 假设第2位填k,组成不降数,那么变成最高位为k,且只用填i-1位,为f[i-1,k]
     * f[i,j]=f[i-1,k] k的取为j<=k<=9求和,值
     */
    static void init() {
        for (int i = 0; i <= 9; i++) {
            if (i != 4)
                f[1][i] = 1;
        }
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j != 4)
                    for (int k = 0; k <= 9; k++) {
                        if (k == 4 || j == 6 && k == 2) continue;
                        f[i][j] += f[i - 1][k];
                    }
            }
        }
    }

    static int dp(int n) {
        if (n == 0) return 1;
        ArrayList<Integer> num = new ArrayList<Integer>();
        while (n != 0) {
            num.add(n % 10);
            n /= 10;
        }
        int res = 0, last = 0;
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            for (int j = 0; j < x; j++) {
                if (j == 4 || last == 6 && j == 2) continue;
                res += f[i + 1][j];
            }
            if (x == 4 || last == 6 && x == 2) break;
            last = x;
            if (i == 0) res++;
        }
        return res;
    }
}
