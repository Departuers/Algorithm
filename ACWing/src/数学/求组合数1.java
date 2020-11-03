package 数学;

import java.util.Scanner;

/**
 * 给定n组询问，每组询问给定两个整数a，b，请你输出C(a,b) mod (10^9+7)的值。
 * 输入格式
 * 第一行包含整数n。接下来n行，每行包含一组a和b。
 * 输出格式
 * 共n行，每行输出一个询问的解。
 * 数据范围
 * 1≤n≤10000,1≤b≤a≤2000
 * 输入样例：
 * 3
 * 3 1
 * 5 3
 * 2 2
 * 输出样例：
 * 3
 * 10
 * 1
 * 求组合数,预处理!!!
 * 本题b和a的范围均不超过2000，故我们可以预处理出所有组合数的值，
 * 一个1 + 2 +...+2000 = 1000 * 2001约等于两百万，
 * 故可以在一秒内计算完成。具体的求法利用了组合数的递推式C(n,m) = C(n - 1,m) + C(n - 1,m - 1)。
 * 该递推式的推导使用的是类似于dp的思想，C(n,m)
 * f[n,m]表示在n个物品中选出m个物品的选法，可以划分为两种情况，
 * 第一种情况是m个物品均来自前n-1个物品，一共C(n-1,m)中选法；
 * 第二种情况是m - 1个物品来自前n - 1个物品，最后一个物品来自第n个物品，
 * 一共C(n-1,m-1)种选法，故可以得到C(n,m) = C(n - 1,m) + C(n - 1,m - 1)。
 * 边界情况为一个都不选的情况，方案数为1。
 * https://blog.csdn.net/qq_36293509/article/details/103858604
 */
public class 求组合数1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int x, y;
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) f[i][j] = 1;
                else f[i][j] = (f[i - 1][j] + f[i - 1][j - 1]) % mod;
            }
        }

        while (n-- != 0) {
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.println(f[x][y]);
        }
    }

    static void sun() {
        for (int i = 0; i < 2000; i++) {
            f[i][0] = f[i][i] = 1;
        }
        for (int i = 0; i < 2000; i++) {
            for (int j = 1; j < i; j++) {
                f[i][j] = (f[i - 1][j] + f[i - 1][j - 1]) % mod;
            }
        }
    }

    static int[][] f = new int[2005][2005];
    static int n, mod = (int) (1e9 + 7);
}
