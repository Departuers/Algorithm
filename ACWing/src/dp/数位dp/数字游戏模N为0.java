package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104501801
 * 由于科协里最近真的很流行数字游戏。
 * 某人又命名了一种取模数，这种数字必须满足各位数字之和 mod N为 0。
 * 现在大家又要玩游戏了，指定一个整数闭区间 [a.b]，问这个区间内有多少个取模数。
 * 输入格式
 * 输入包含多组测试数据，每组数据占一行。
 * 每组数据包含三个整数 a,b,N。
 * 输出格式
 * 对于每个测试数据输出一行结果，表示区间内各位数字和 mod N为 0 的数的个数。
 * 数据范围
 * 1≤a,b≤2^31−1,
 * 1≤N<100
 * 输入样例：
 * 1 19 9
 * 输出样例：
 * 2
 */
public class 数字游戏模N为0 {
    static int l, r, N = 11, M = 110, P = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        r = sc.nextInt();
        P = sc.nextInt();
        init();
        System.out.println(dp(r) - dp(l - 1));
    }

    /**
     * 假如第一位已经确定,第二位枚举x,后面的数随便填
     * {A(n-2)+x+(...)}mod N=0
     * 剩下的n-2位所有位数之和=(-A(n-2)-x)mod N
     * f[i,j,k]表示,i位数字,且最高位为j,余数为k的所有集合
     * 考虑last,x____假如第二位填k 0<=x<=9
     * 各位数字之和
     * f[i,j,k]=f[i-1,x,(k-j)mod N]
     */
    private static void init() {
        for (int i = 0; i <= 9; i++) {
            f[1][i][i % P]++;
        }
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < P; k++) {
                    for (int x = 0; x <= 9; x++) {
                        f[i][j][k] += f[i - 1][x][mod(k - j, P)];
                    }
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
                res += f[i + 1][j][mod(-last, P)];
            }
            last += x;
            if (i == 0 && last % P == 0) res++;
        }
        return res;
    }

    static int[][][] f = new int[N][10][M];

    static int mod(int x, int y) {
        return (x % y + y) % y;
    }
}
