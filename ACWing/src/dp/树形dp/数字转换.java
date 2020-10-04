package dp.树形dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104402279
 * 如果一个数 x 的约数之和 y（不包括他本身）比他本身小，那么 x 可以变成 y，y 也可以变成 x。
 * 例如，4 可以变为 3，1可以变为 7。
 * 限定所有数字变换在不超过 n 的正整数范围内进行，求不断进行数字变换且不出现重复数字的最多变换步数。
 * 输入格式
 * 输入一个正整数 n。
 * 输出格式
 * 输出不断进行数字变换且不出现重复数字的最多变换步数。
 * 数据范围
 * 1≤n≤50000
 * 输入样例：
 * 7
 * 输出样例：
 * 3
 * 样例解释
 * 一种方案为：4→3→1→7。
 * 分析：
 * 首先，一个数字可以变成另外一个数字，就算是这两个数字之间存在一条边，
 * 当x的约数之和y小于x时，x与y之间边存在边。一个数的约数之和是唯一的，
 * 但一个数可以是很多数的约数之和，所以一个数的父节点就是这个数的约数之和。
 * 首先考虑如何求出1-n中所有数的约数之和，由于要在不超过n的正整数范围内变换，
 * 所以不需要求1的约数之和。可以采用类似于筛法的形式去求约数之和，
 * 将i的若干倍都加到其倍数的约数之和上即可。
 * 然后仿照树的最长路径那题对树做DFS，求出最长路径就是本题的最多变换步数了，
 * 因为不能确定1到n中所有的数都是连通的，所以需要多次进行DFS来遍历这个森林。
 * 这里的边也可以设置为双向的，但是由于是从小到大进行DFS，相当于从根节点开始遍历，
 * 就不需要增加孩子节点指向父节点的边来降低DFS的速度了。
 * <p>
 * 如4的约数有1,2,不包括4, 所以4和3之间可以相互转换
 * 所有的数不能超过n,不重复最多变换多少次
 * 对于每个数的约数之和是固定的,利用图论建模
 * 看成树的约数之和向数连一条边
 * 转换成求树的直径,路径可以走转化为可以转换数字,当前不能往回走就是不能重复
 * 一是枚举每个数都有哪些约数O(n sqrt(n))
 * 二,可以枚举每个数是哪些数的约数,枚举每个数的倍数O(n logn)
 */
public class 数字转换 {
    static int N = 50010, n, idx = 1, ans;
    static int[] h = new int[N], e = new int[N], ne = new int[N], sum = new int[N];
    static boolean[] st = new boolean[N];

    static int dfs(int u) {
        int d1 = 0, d2 = 0;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            int d = dfs(j) + 1;
            if (d >= d1) {
                d2 = d1;
                d1 = d;
            } else if (d > d2) {
                d2 = d;
            }
        }
        ans = Math.max(ans, d1 + d2);
        return d1;
    }

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {//O(n log n)
            for (int j = 2; j <= n / i; j++) {
                sum[i * j] += i;
            }
        }
        //1的约数之和是1,非法,所以从2开始
        for (int i = 2; i <= n; i++) {
            if (i > sum[i]) {
                add(sum[i], i);
                st[i] = true;//说明不是树根
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!st[i]) {
                dfs(i);
            }
        }
        System.out.println(ans);
    }
}
