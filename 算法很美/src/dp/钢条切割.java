package dp;

import java.util.Arrays;

/**
 * 完全背包问题
 */
public class 钢条切割 {
    public static void main(String[] args) {
        Arrays.fill(vs, -1);
        System.out.println(GangtiaobyMem(10));
    }

    public static int[] V = {1, 5, 8, 16, 10, 17, 17, 20, 24, 30};
    public static int n_Changdu = 10;

    /**
     * | 长度i | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
     * | - | - | - | - | - | - | - | - | - | - |
     * 价格pi | 1 | 5 | 8 | 16 | 10 | 17 | 17 | 20 | 24 | 30 |
     *
     * @param n 钢条的长度
     * @return
     */
    public static int Gangtiao(int n) {
        if (n == 0)
            return 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int value = V[i - 1] + Gangtiao(n - i);
            ans = Math.max(value, ans);
        }
        return ans;
    }

    public static int[] vs = new int[n_Changdu + 1];

    /**
     * 记忆型递归,定义一个数组保存上次的结果
     *
     * @param n
     * @return
     */
    public static int GangtiaobyMem(int n) {
        if (n == 0)
            return 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (vs[n - i] == -1)
                vs[n - i] = GangtiaobyMem(n - i);
            int value = V[i - 1] + GangtiaobyMem(n - i);
            ans = Math.max(value, ans);
        }
        return ans;
    }

    /**
     * 小范围推导到大范围
     * 长度       0   1   2   3   4   5
     * 最大价值    0   1   5   8  16  17
     * 重复子问题,
     * 需要求4可以分解成
     * f(4)+f(0) f(3)+f(1) f(2)+f(2) f(1)+f(3)
     *    16        8+1       5+5       1+8
     * @param n    总钢条的长度
     * @return     最大价值
     */
    public static int GangTiaoByDp(int n) {
        vs[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                vs[i] = Math.max(V[j - 1] + vs[i - j], vs[i]);
            }
        }
        return vs[n];
    }
}