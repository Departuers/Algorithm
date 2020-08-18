package graph.差分约束;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107272314
 * 给定n个区间,[ai,bi]和n个整数Ci
 * 你需要构造一个整数集合 Z，
 * 使得∀i∈[1,n],Z 中满足ai≤x≤bi的整数 x 不少于 ci 个。
 * 求这样的整数集合 Z 最少包含多少个数。
 * 1≤n≤50000,
 * 0≤ai,bi≤50000,
 * 1≤ci≤bi−ai+1
 * 输入样例：
 * 5
 * 3 7 3
 * 8 10 3
 * 6 8 1
 * 1 3 1
 * 10 11 1
 * 输出样例：
 * 6
 * 该题必定有解,显然全放进去,一定满足要求
 * 思路:
 * 先将不等式写出来
 * 将所有区间向右移一位，这样如果1-2有一条边 3-4有一条边，
 * 我们应该让他可以处理为1-4区间，
 * 所以相当于给的区间为左闭右闭的区间改为左闭右开的区间。
 * 初始的时候建立向右走1权值为0的边，向左走1权值为-1的边。
 * 跑最长路即可。
 * 列不等式:
 * S0=0
 * Si表示1~i中被选出的数的个数
 * S5001的最小值,用最长路求解
 * Si>=S(i-1)  1<=i<=50001
 * Si-S(i-1)<=1  =>S(i-1)>=Si-1
 * [a,b]区间选c个,也就是Sb-S(a-1)>=c
 * 不等关系要找全
 */
public class 区间 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= 50001; i++) {
            add(i - 1, i, 0);
            add(i, i - 1, -1);
        }
        int a, b, c;
        while (n-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            a++;
            b++;
            add(a - 1, b, c);
        }
        spfa();
        System.out.println(dis[50001]);
    }

    private static void spfa() {
        q.add(0);
        Arrays.fill(dis, -Integer.MAX_VALUE / 2);
        st[0] = true;
        dis[0] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] < dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
    }


    static int n, m, N = 50010, M = 150010, count = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] w = new int[M];
    static int[] dis = new int[N];
    static boolean[] st = new boolean[N];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static void add(int a, int b, int c) {
        e[count] = b;
        w[count] = c;
        ne[count] = h[a];
        h[a] = count++;
    }
}
