package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104202667
 * 在 n×n 的棋盘上放 k 个国王，国王可攻击相邻的 8个格子，求使它们无法互相攻击的方案总数。
 * 输入格式
 * 共一行，包含两个整数 n和 k。
 * 输出格式
 * 共一行，表示方案总数，若不能够放置则输出0。
 * 数据范围
 * 1≤n≤10,
 * 0≤k≤n^2
 * 输入样例：
 * 3 2
 * 输出样例：
 * 16
 * 本题考察状态压缩DP，而且是棋盘类状压。
 * 首先，很容易想到用一串二进制数表示一行放置国王的情况，1表示放置，0表示未放置。
 * 而且由每个国王可以攻击周围的八连通区域的格子推出，每一行的状态最多会影响下一行，
 * 而不会影响下下行，这为我们逐行进行状态转移提供了可能性。首先，分析从第一行开始，
 * 逐行放置国王，如何放置是合法的。第一个条件，第i行不能有相邻两列都放了国王，
 * 设第i列的状态为st，只需要st & （st >> 1）为0就可以说明不存在相邻两列都是1了。
 * 第二个条件，根据上一行的状态如何得出下一行哪些状态是合法的。
 * 首先，第i-1行第j列放置了国王，则第i行第j列就不能放置了，即s & t要为0，
 * 其中s为i-1行的状态，t为第i行的状态；另外，上下两行对角线也不能同时为1，
 * 只需要判断s | t是否存在相邻的1即可，不存在相邻的1则说明对角线不同时为1。
 * 状态表示：本来只需要第一维表示遍历到的行数，第二维表示第i行的状态即可，
 * 但是由于放置的国王数不能超过k，所以需要增加一维表示已经放置的国王数。
 * 故f[i][j][k]表示放完前i行的国王，一共放了j个国王且第i行状态为k的方案数。
 * 状态转移方程为f[i][j][k] = f[i][j][k] + f[i-1][j-cnt[i]][k']，
 * 其中cnt[i]表示第i行放的国王数量，k表示放置第i行前第i-1行的状态，不难理解这个状态的转移，
 * 就像01背包问题中选择第i个物品相当于f[i-1][j-v] + w一样，f[i][j][k]这个状态只要合法，
 * 方案数自然等于所有能从第i-1行的合法状态转移到f[i][j][k]状态的方案数之和。
 * 本题并不复杂，只是稍微麻烦的是为了提高效率还需要预处理所有合法的状态，
 * 将所有不存在相邻1的合法状态都存进向量a中，并统计出每个状态包含1的数量。
 * 另外，a中的合法状态可以转移到哪些合法的状态，也可以预处理出来存进向量b中。
 * 本题方案数可能很大，需要用long long存储。
 * <p>
 * f[i,j,k]  k为二进制数,表示在哪里放了国王
 * 状态定义:所有只从前i行摆放,已经摆了j个国王,并且第i行的摆放状态是k的所有方案的集合
 * 属性count
 */
public class 骑士 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < 1 << n; i++) {
            if (check(i)) {
                a.add(i);//去除相邻的放置方法
                cnt.add(count(i));//预处理i作为
            }
        }
        int s = 0, t = 0, u = 0;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                s = a.get(i);
                t = a.get(j);
                if ((s & t) == 0 && check(s | t)) b[s].add(t);
                //去除s&t(i-1行&i)两行之内有相同元素
                //check(s|t)把有1的全部置为1,如果有相邻1说明对角线为1
                //预处理所有方案
            }
        }
        f[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {//枚举个数
                for (int k = 0; k < a.size(); k++) {
                    t = a.get(k);//取出作为第i行
                    for (s = 0; s < b[t].size(); s++) {
                        u = b[t].get(s); //枚举与t不冲突的u
                        if (j >= cnt.get(k))//第i行的骑士个数要合法
                            f[i][j][t] += f[i - 1][j - cnt.get(k)][u];
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i < a.size(); i++) {
            res += f[n][m][a.get(i)];
        }
        System.out.println(res);
    }

    static int n, m;
    static long[][][] f = new long[12][105][1 << 12];
    static ArrayList<Integer>[] b = new ArrayList[1 << 12];

    static {
        for (int i = 0; i < b.length; i++) {
            b[i] = new ArrayList<Integer>();
        }
    }

    static ArrayList<Integer> a = new ArrayList<Integer>();
    static ArrayList<Integer> cnt = new ArrayList<Integer>();

    static boolean check(int st) {
        return (st & (st >> 1)) == 0;
        //判断相邻两列是否冲突,同时为1
    }

    //计算该行有多少个1
    static int count(int n) {
        int res = 0;
        while (n != 0) {
            if ((n & 1) == 1) res++;
            n >>= 1;
        }
        return res;
    }

}
