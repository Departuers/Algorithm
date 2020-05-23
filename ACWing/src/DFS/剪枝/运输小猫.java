package DFS.剪枝;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104813141
 */
public class 运输小猫 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        Arrays.sort(w, 0, n, new Comparator<Integer>() {
            @Override
            public int compare(Integer t2, Integer t1) {
                return t1 - t2;
            }
        });
        System.out.println(Arrays.toString(w));
        dfs(0, 0);
        System.out.println(ans);
    }

    /**
     * @param u 走到第几个小猫,下标
     * @param k 当前是第几辆车
     */
    static void dfs(int u, int k) {
        if (k >= ans) return;
        if (u == n) {
            ans = k;
            return;
        }
        for (int i = 0; i < k; i++) {
            if (sum[i] + w[u] <= m) {
                sum[i] += w[u];
                dfs(u + 1, k);
                sum[i] -= w[u];
            }
        }
        sum[k] = w[u];
        dfs(u + 1, k + 1);
        sum[k] = 0;
    }

    static Integer[] w = new Integer[20];//记录每只小猫的重量
    static int[] sum = new int[20];//每辆车的重量
    static int n, m, ans = 20;
}