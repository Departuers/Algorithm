package DFS.递归;

import java.util.Arrays;

/**
 * 给定n个数,取出k个数使得和为sum,求方案数
 * 枚举选不选,不考虑顺序,枚举排列需要顺序
 */
public class m任取n满足 {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            arr[i] = i;
        }
        System.out.println(Arrays.toString(arr));
        //  dfs(0, 0, 7);
        long a = System.nanoTime();
        f(0, 0, 0);
        System.out.println(ans);
        long e = System.nanoTime();
        System.out.println((e - a) / 1e8);
        ans = 0;
        a = System.nanoTime();

        dfs(0, 0, 0);
        System.out.println(ans);
        e = System.nanoTime();

        System.out.println((e - a) / 1e8);
    }

    static int ans, n = 10, k = 2, suma = 10;
    static int[] arr = new int[30];

    /**
     * 枚举每一位选或者不选
     * 枚举每个数选还是不选,加判断
     *
     * @param u   是当前第几位
     * @param sum 是当前选了几个
     * @param s   当前和
     */
    static void f(int u, int sum, int s) {
//        if (sum + n - u < k) return;//n-u是剩余可选的数,
        if (sum > k) return;
        if (u == n) {//枚举到最后一个元素
            if (sum == k && s == suma)
                ans++;
            return;
        }
        if (u > n) return;
        f(u + 1, sum, s);
        f(u + 1, sum + 1, s + arr[u]);
    }


    static boolean[] x = new boolean[30];

    /**
     * 枚举每个数选还是不选,加判断
     * 加上pos之后,有k个坑,第一个坑里有n种平行选择,第二个坑里有n-1种,....类推
     * 显然不加pos,k个坑中,每个坑都有n种平行选择,然而这道题中是顺序无关的,会有重复搜索
     *
     * @param u   当前选了多少个
     * @param sum 当前和
     * @param pos 下一次从哪一个索引开始
     */
    static void dfs(int u, int sum, int pos) {
        if (sum > suma || u > k) return;
        if (sum == suma && u == k) {
            ans++;
            for (int i = 0; i < n; i++) {
                if (x[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        for (int i = pos; i < n; i++) {
            if (!x[i]) {
                x[i] = true;
                dfs(u + 1, sum + arr[i], i + 1);
                x[i] = false;
            }
        }
    }
}