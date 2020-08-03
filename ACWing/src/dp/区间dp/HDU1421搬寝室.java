package dp.区间dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/zhengde152/article/details/81428078
 * 有n个行李，每个行李有一个重量。
 * 现在你要搬走2k个行李，你一共去k次。每次左手右手各拿一个行李，
 * 假设这两个行李的重量分别为x和y。
 * 那么这一次搬运产生的疲惫度是(x-y)^2
 * 现在你希望最小化疲惫度。
 * 2≤2k≤n<2000
 * Sample Input
 * 2 1 1 3
 * Sample Output
 * 4
 * n个物品选择2k个,分成k组,不好选
 * 假设拿到了2k个物品,如何组合使得疲劳度最小
 * 如果有4个行李,从小到大师a,b,c,d
 * (a-b)^2+(c-d)^2  < (a-c)^2+(b-d)^2
 * 显然(a,b)(c,d)分组最优
 * 因此2k个行李从小到大排序一次取2个配对最好
 * 可以一开始就对n个物品排序
 * f[i,j]表示从前i个物品中选出了j对的最小疲惫度
 * 考虑last
 * 考虑第i个取不取,如果取上第i个,一个和第i-1个配对
 * 因此要求前i-2个物品选出j-1对
 * f[i,j]=min{f[i,j],f[i-2][j-1]+a[i]-a[i-1]*a[i]-a[i-1]}
 * 如果不取则f[i,j]=f[i-1,j]
 */
public class HDU1421搬寝室 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!sc.hasNext()) break;
            n = sc.nextInt();
            k = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a, 1, n + 1);
            int t = Integer.MAX_VALUE / 2;
            for (int i = 0; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    f[i][j] = t;
                }
            }
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j * 2 <= i; j++) {
                    f[i][j] = Math.min(f[i - 2][j - 1] + (a[i] - a[i - 1]) * (a[i] - a[i - 1]), f[i - 1][j]);
                }
            }
            System.out.println(f[n][k]);
        }

    }

    static int[] a = new int[2010];
    static int[][] f = new int[2010][2010];
    static int n, k;
}
