package DFS.搜索;

import java.util.ArrayList;

/**
 * 枚举每个数要还是不要,
 */
public class 部分数之和 {

    static int[] a = {4, 12, 3, 1, 2, 31, 6, 9};

    /**
     * @param u   数组下标
     * @param sum 要找的和
     * @param tem 保存状态
     */
    static void dfs(int u, int sum, ArrayList<Integer> tem) {
        if (sum == 0) {
            System.out.println(tem);
            return;
        }
        if (u == a.length || sum < 0) return;
        dfs(u + 1, sum, tem);
        tem.add(a[u]);
        dfs(u + 1, sum - a[u], tem);
        tem.remove(tem.size() - 1);
    }

    /**
     * 如上
     *
     * @param u     数组下标
     * @param sum   要求的和
     * @param state 状态压缩版本
     */
    static void dfss(int u, int sum, int state) {
        if (sum == 0) {
            for (int i = 0; i < a.length; i++) {
                if ((state >> i & 1) == 1) System.out.print(a[i] + " ");
            }
            System.out.println();
            return;
        }
        if (u == a.length || sum < 0) return;
        dfss(u + 1, sum, state);
        dfss(u + 1, sum - a[u], state | (1 << u));
    }

    public static void main(String[] args) {
        dfs(0, 13, new ArrayList<Integer>());
        System.out.println();
        dfss(0, 13, 0);
    }
}
