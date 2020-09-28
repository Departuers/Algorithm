package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104202667
 * f[i,j,k]状态表示:所有只摆在前i行,已经摆了j个国王,并且第i行的摆放状态是k所有方案的集合
 * 属性count
 * 状态计算:
 * 当前行状态已经有了,就是k
 * 考虑当前层的上一层是什么状态
 * 划分依据,根据上一层的状态是什么,把集合分成若干类
 * 枚举上一层状态是什么,最多2^m种方案,但被预处理了没有这么高
 * 考虑第i-1行需要满足什么条件才能转移到第i行,也就是第i-1行不与第i行冲突
 * 首先第i-1行要满足:没有相邻的两个1
 * 还需要满足,第i-1行与第i行不能相互攻击到
 * 如何实现呢,i-1行的状态是a,第i行状态为b    a&b==0 表示没有一列有两个国王
 * 且a|b不能有相邻的两个1,
 * 如果第i-1行是合法的,直接加上这一类的元素数量即可
 * <p>
 * 已经摆完了前i排,并且第i行状态是a,第i-1行状态是b,已经摆了j个国王的所有方案
 * 已经摆完了前i-1排,并且i-1行状态是b,已经摆了j-count(a)个国王的所有方案,   f[ i-1 , j-count(a) , b ]
 */
public class 骑士y {
    public static void main(String[] args) {
        for (int i = 0; i < M; i++) {
            head[i] = new ArrayList<Integer>();
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < 1 << n; i++) {
            if (check(i)) {
                cnt.add(count(i));
                state.add(i);
            }
        }
        for (int i = 0; i < state.size(); i++) {
            for (int j = 0; j < state.size(); j++) {
                int a = state.get(i), b = state.get(j);
                if ((a & b) == 0 && check(a | b)) {
                    head[a].add(b);
                }
            }
        }
        f[0][0][0] = 1;
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k < state.size(); k++) {
                    int t = state.get(k);
                    for (int s = 0; s < head[t].size(); s++) {
                        int u = head[t].get(s);
                        int c = cnt.get(k);
                        if (j >= c) {
                            f[i][j][t] += f[i - 1][j - c][u];
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i < state.size(); i++) {
            res += f[n][m][state.get(i)];
        }
        System.out.println(res);
        System.out.println(f[n + 1][m][0]);
    }

    static int N = 12, n, m, M = 1 << 11, K = 110;
    static long[][][] f = new long[N][K][M];
    static ArrayList<Integer> cnt = new ArrayList<Integer>();
    static ArrayList<Integer> state = new ArrayList<Integer>();
    static ArrayList<Integer>[] head = new ArrayList[M];

    static boolean check(int state) {
        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 1 && (state >> i + 1 & 1) == 1)
                return false;
        }
        return true;
    }

    static int count(int state) {
        int res = 0;
        while (state != 0) {
            res++;
            state -= state & -state;
        }
        return res;
    }
}
