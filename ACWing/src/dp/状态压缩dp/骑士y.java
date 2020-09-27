package dp.状态压缩dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
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
        for (int i = 0; i < head.length; i++) {
            head[i] = new ArrayList<Integer>();
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < 1 << n; i++) {
            if (check(i)) {
                state.add(i);
                cnt[i] = count(i);
            }
        }

        //预处理两个状态之间的转移关系,哪个状态之间可以转移
        for (int i = 0; i < state.size(); i++) {
            for (int j = 0; j < state.size(); j++) {
                int a = state.get(i), b = state.get(j);
                //第i-1行与第i行不能相互攻击到,内部不冲突,前面已经判断
                if (((a & b) == 0) && check(a | b)) {
                    head[a].add(b);
                }
            }
        }

        f[0][0][0] = 1;
//        for (int i = 1; i <= n + 1; i++) {//枚举每一行
//            for (int j = 0; j <= m; j++) {//国王数量
//                for (int a = 0; a < state.size(); a++) {
//                    s = state.get(a);
//                    for (Integer b : head[a]) {
//                        int c = cnt[state.get(a)];
//                        if (j >= c) {
//                            f[i][j][s] +=f[i - 1][j - c][b];
//                        }
//                    }
//                }
//            }
//        }
        int s, t, u;
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 0; j <= m; j++) {//枚举个数
                for (int k = 0; k < state.size(); k++) {
                    t = state.get(k);//取出作为第i行
                    for (s = 0; s < head[t].size(); s++) {
                        u = head[t].get(s); //枚举与t不冲突的u
                        if (j >= cnt[k])//第i行的骑士个数要合法
                            f[i][j][t] += f[i - 1][j - cnt[k]][u];
                    }
                }
            }
        }

        System.out.println(f[n + 1][m][0]);

        long res = 0;
        for (int i = 0; i < state.size(); i++) {
            res += f[n][m][state.get(i)];
        }
        System.out.println(res);
    }

    static int count(int state) {
        int res = 0;
        while (state != 0) {
            res++;
            state -= (state & -state);
        }
        return res;
    }

    /**
     * 检查状态是否存在连续的两个1
     *
     * @param state 状态
     */
    static boolean check(int state) {
        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 1 && (state >> i + 1 & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    static int n, m, N = 12, M = 1 << 10, K = 110;//K是国王数量
    static ArrayList<Integer> state = new ArrayList<Integer>();
    static ArrayList<Integer>[] head = new ArrayList[M];
    static int[] cnt = new int[M];//每个状态1的个数
    static long[][][] f = new long[N][K][M];

}
