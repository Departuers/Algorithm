package DFS.搜索顺序;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 分组搜索
 */
public class 分成互质组另一种写法 {
    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            group[i] = new ArrayList<Integer>();
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        dfs(0);
        System.out.println(ans);
    }

    /**
     * 设已有len个不同分组，遍历到第u个数时，
     * 先尝试去加入每个分组，如果与每个分组里所有的数都是互质的，
     * 就可以加入。所有分组都尝试过后，再考虑新建一个分组的情况，
     * 全部搜完求出len的最小值就是题目所求的最优解。
     * 每个数有两个抉择:
     * 1:如果不冲突把当前数,放进前面几个组中去
     * 2:第一种不行就新开组,
     *
     * @param u
     */
    static void dfs(int u) {
        if (len >= ans) return;
        if (u == n) {
            ans = len;
            return;
        }
        for (int i = 0; i < len; i++) {
            if (check(a[u], i)) {//枚举把第i个数,放进第i组
                group[i].add(a[u]);
                dfs(u + 1);
                group[i].remove(group[i].size() - 1);//回溯删掉最后一个
            }
        }
        group[len++].add(a[u]);//开新的组
        dfs(u + 1);
        group[--len].remove(group[len].size() - 1);//回溯删掉最后一个
    }

    static boolean check(int x, int t) {
        for (int i = 0; i < group[t].size(); i++) {
            if (gcd(x, group[t].get(i)) > 1) return false;
        }
        return true;
    }

    static ArrayList<Integer>[] group = new ArrayList[12];

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int n, ans = 12, len;
    static int[] a = new int[12];
}
