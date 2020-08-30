package DFS.搜索顺序;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104799213
 * 给定 n 个正整数，将它们分组，使得每组中任意两个数互质。
 * 至少要分成多少个组？
 * 输入格式
 * 第一行是一个正整数 n。
 * 第二行是 n 个不大于10000的正整数。
 * 输出格式
 * 一个正整数，即最少需要的组数。
 * 数据范围
 * 1≤n≤10
 * 输入样例：
 * 6
 * 14 20 33 117 143 175
 * 输出样例：
 * 3
 * 有些数,不能放在一起,求最小组数量,
 * 直接暴搜即可。两个数互质，
 * 当且仅当这两个数的最大公约数是1，求最大公约数可以用欧几里得算法。
 * 至于如何分组，可以考虑以下思路：设已有len个不同分组，遍历到第u个数时，
 * 先尝试去加入每个分组，如果与每个分组里所有的数都是互质的，
 * 就可以加入。所有分组都尝试过后，再考虑新建一个分组的情况，全部搜完求出len的最小值就是题目所求的最优解。
 * 这种见另一种写法
 */
public class 分成互质组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        dfs(1, 0, 0, 0);
        System.out.println(ans);
    }

    /**
     * @param g  某一组的所有元素,数组中存的是质数的下标
     * @param gc 某一组的最多元素
     * @param i  应当放置数组的下标
     * @return 把第i个数能否放进第g这个组
     */
    static boolean check(int[] g, int gc, int i) {
        for (int j = 0; j < gc; j++) {
            if (gcd(a[g[j]], a[i]) > 1) return false;
        }
        return true;
    }

    //g代表当前哪一组,gc代表枚举到组内第几个元素tc表示当前一共处理多少数,start代表组内从哪个数开始枚举

    /**
     * 只有2个动作
     * 1:把当前数加到最后一组
     * 2:当前数加到新开的组
     * 假设:当前数与之前组的数字互质,不考虑
     * 若能将当前数,放到最后一组的末尾,而不冲突,但我们开新的组是最优解,
     * 则显然,放末尾不影响最优解,
     * 所以优先度最高放最后一个组的末尾
     *
     * @param g     g代表当前哪一组,组编号
     * @param gc    gc代表枚举到组内第几个元素
     * @param tc    tc表示当前一共处理多少数
     * @param start start代表组内从哪个数开始枚举,组合数搜索!
     */
    static void dfs(int g, int gc, int tc, int start) {
        if (g >= ans) return;//最优性剪枝
        if (tc == n) {//这个时候g<ans,因为g>=ans被判掉了
            ans = g;
        }
        boolean f = true;
        for (int i = start; i < n; i++) {
            if (!vis[i] && check(group[g], gc, i)) {
                vis[i] = true;
                group[g][gc] = i;
                dfs(g, gc + 1, tc + 1, i + 1);
                //放在当前组的末尾
                vis[i] = false;
                f = false;
            }
        }
        if (f) dfs(g + 1, 0, tc, 0);//开新的组,
    }

    static int n, ans = 100;
    static int[] a = new int[100];
    static int[][] group = new int[100][100];
    static boolean[] vis = new boolean[100];

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
