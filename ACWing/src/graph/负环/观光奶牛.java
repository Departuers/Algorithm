package graph.负环;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107271543
 * https://blog.csdn.net/tomjobs/article/details/105252069
 * 给定一张L个点、P条边的有向图，每个点都有一个权值f[i]，每条边都有一个权值t[i]。
 * 求图中的一个环，使“环上各点的权值之和”除以“环上各边的权值之和”最大。
 * 输出这个最大值。
 * 注意：数据保证至少存在一个环。
 * 输入格式
 * 第一行包含两个整数L和P。
 * 接下来L行每行一个整数，表示f[i]。
 * 再接下来P行，每行三个整数a，b，t[i]，表示点a和b之间存在一条边，边的权值为t[i]。
 * 输出格式
 * 输出一个数表示结果，保留两位小数。
 * 数据范围
 * 2≤L≤1000,
 * 2≤P≤5000,
 * 1≤f[i],t[i]≤1000
 * 思路
 * 01分数规划。
 * 输入样例：
 * 5 7
 * 30
 * 10
 * 10
 * 5
 * 10
 * 1 2 3
 * 2 3 2
 * 3 4 5
 * 3 5 2
 * 4 5 5
 * 5 1 3
 * 5 2 2
 * 输出样例：
 * 6.00
 * 01分数规划问题,二分
 * 每个点都有权值,每条边都有权值,求一个环,
 * 使得每个点的权值和除以每条边的权值和最大
 * 01分数规划,求得一个环,使得∑fi/∑ti最大,可以二分找正环
 * 推导:
 * 二分范围:该题所有边权重最大为1000,使得最大,分子取1000分母取1
 * 二分范围则是(0,1000]
 * 取中点mid,每次判断是否∑fi/∑ti>mid
 * =>   ∑fi>mid*∑ti   =>   ∑fi-mid*∑ti>0
 * 如果存在那么最优解,在[mid,r]之间
 * 保留二位小数,二分到区间足够小即可
 * 有点权重,放在有向边的出边和入边等价
 * ∑fi-mid*∑ti>0等价于图中是否存在正环
 * 把点权放在出边上，
 * 等价于∑(fi-mid*ti)>0
 * 等价于 i->j 上有一条长度是fi-mid*ti的边
 *
 * 正环和负环相对,把所有的边取相反数,找到负环就相当于找到负环,但不这么实现
 * 直接把最短路改成最长路,统计最短路径包含的边数即可
 * 为什么求正环要用最长路呢,
 * 因为最长路,是经过尽可能多的边,使得dis[i]=正无穷
 * 如果最长路中存在正环,那么最长路就会达到正无穷
 *
 * 技巧：点权，可以放到入边上也可以放到出边上
 */
public class 观光奶牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            wf[i] = sc.nextInt();
        }
        int a, b, c;
        while (m-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
        }
        double l = 0, r = 1e6;
        while (r - l > 1e-4) {//保留两位小数,多加2位,
            double mid = (l + r) / 2;
            if (check(mid)) l = mid;
            else r = mid;
        }
        System.out.printf("%.2f", l);
    }

    private static boolean check(double mid) {
//        Arrays.fill(st, false);
        Arrays.fill(count, 0);
        Arrays.fill(dis, 0);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
            st[i] = true;
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] < dis[t] + wf[t] - mid * w[i]) {
                    dis[j] = dis[t] + wf[t] - mid * w[i];
                    count[j] = count[t] + 1;
                    if (count[j] >= n) return true;
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return false;
    }

    private static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
    }

    static int n, m, N = 1010, M = 10100, cnt = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] wf = new int[N];
    static int[] ne = new int[M];
    static int[] w = new int[M];
    static double[] dis = new double[N];
    static int[] count = new int[N];
    static boolean[] st = new boolean[N];
}
