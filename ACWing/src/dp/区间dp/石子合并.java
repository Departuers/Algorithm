package dp.区间dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 设有N堆石子排成一排，其编号为1，2，3，…，N。
 * 每堆石子有一定的质量，可以用一个整数来描述，现在要将这N堆石子合并成为一堆。
 * 每次只能合并相邻的两堆，合并的代价为这两堆石子的质量之和，合并后与这两堆石子相邻的石子将和新堆相邻，合并时由于选择的顺序不同，合并的总代价也不相同。
 * 例如有4堆石子分别为 1 3 5 2， 我们可以先合并1、2堆，代价为4，得到4 5 2， 又合并 1，2堆，代价为9，得到9 2 ，再合并得到11，总代价为4+9+11=24；
 * 如果第二步是先合并2，3堆，则代价为7，得到4 7，最后一次合并代价为11，总代价为4+7+11=22。
 * 问题是：找出一种合理的方法，使总的代价最小，输出最小代价。
 * 输入格式
 * 第一行一个数N表示石子的堆数N。
 * 第二行N个数，表示每堆石子的质量(均不超过1000)。
 * 输出格式
 * 输出一个整数，表示最小代价。
 * 数据范围
 * 1≤N≤300
 * 输入样例：
 * 4
 * 1 3 5 2
 * 输出样例：
 * 22
 * 状态定义:f[i,j]是合并i~j位所需的最小代价的集合
 * 属性:min 最小代价
 * 状态划分考虑last
 * ___ _____
 * __ ______
 * _ _______
 * ____ ____
 * ...
 * 可以由以上状态转移到f[i,j],分界点不同,
 * 状态划分:f[i,j]划分,i  i+1  ... k  ... j-1
 * 可以由上面状态转移过来,左边,右边
 * 起码有两堆,不失一般性,考虑k,i~k  k+1~j
 * i~k合并的最小代价结合状态定义恰好是f[i,k]
 * k+1,j合并的最小代价结合状态定义恰好是f[k+1,j]
 * 最后加上他们的和 即可:s[j]-s[i-1]
 * <p>
 * 顾名思义,区间dp就是区间上的dp,
 * 通过先算出小区间的dp的到最优解,再去得到大区间的最优解
 * 一般设f[i,j]为区间[i,j]的最优解,一般f[i,j]都可以由[i,j]的子区间更新得到
 * 该题合并之前是两堆,合并之后是一堆,可以枚举分界线
 * f[i,j]=min{ f[i,k]+f[k+1,j]+a[j]-a[i-1] | i<=k<j }可以使用前缀和,
 * k为什么不能等于j?因为k是分界线,不能是j
 */
public class 石子合并 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt();
            s[i] += s[i - 1];//前缀和
        }
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(1, n));
        System.out.println(dp[1][n]);

        //要先更新小区间的答案,按照拓扑序
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                f[l][r] = (int) 1e9;
                for (int k = l; k < r; k++) {
                    f[l][r] = Math.min(f[l][r], f[l][k] + f[k + 1][r] + s[r] - s[l - 1]);
                }
            }
        }
        System.out.println(f[1][n]);

    }

    static int dfs(int i, int j) {
        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int ans = Integer.MAX_VALUE / 2;
        for (int k = i; k < j; k++) {
            ans = Math.min(ans, dfs(i, k) + dfs(k + 1, j) + s[j] - s[i - 1]);
        }
        return dp[i][j] = ans;
    }

    static int[][] dp = new int[310][310];
    static int[] s = new int[310];
    static int[][] f = new int[310][310];
    static int n = 0;
}
