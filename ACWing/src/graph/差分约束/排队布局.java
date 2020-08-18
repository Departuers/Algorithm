package graph.差分约束;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/weixin_43872728/article/details/105941583
 * 当排队等候喂食时，奶牛喜欢和它们的朋友站得靠近些。
 * 农夫约翰有 N 头奶牛，编号从 1 到 N，沿一条直线站着等候喂食。
 * 奶牛排在队伍中的顺序和它们的编号是相同的。
 * 因为奶牛相当苗条，所以可能有两头或者更多奶牛站在同一位置上。
 * 如果我们想象奶牛是站在一条数轴上的话，允许有两头或更多奶牛拥有相同的横坐标。
 * 一些奶牛相互间存有好感，它们希望两者之间的距离不超过一个给定的数 L。
 * 另一方面，一些奶牛相互间非常反感，它们希望两者间的距离不小于一个给定的数 D。
 * 给出 ML 条关于两头奶牛间有好感的描述，再给出 MD 条关于两头奶牛间存有反感的描述。
 * 你的工作是：如果不存在满足要求的方案，输出-1；如果 1 号奶牛和 N 号奶牛间的距离可以任意大，输出-2；否则，计算出在满足所有要求的情况下，1 号奶牛和 N 号奶牛间可能的最大距离。
 * 输入格式
 * 第一行包含三个整数 N,ML,MD。
 * 接下来 ML 行，每行包含三个正整数 A,B,L，表示奶牛 A 和奶牛 B 至多相隔 L 的距离。
 * 再接下来 MD 行，每行包含三个正整数 A,B,D，表示奶牛 A 和奶牛 B 至少相隔 D 的距离。
 * 输出格式
 * 输出一个整数，如果不存在满足要求的方案，输出-1；如果 1 号奶牛和 N 号奶牛间的距离可以任意大，输出-2；否则，输出在满足所有要求的情况下，1 号奶牛和 N 号奶牛间可能的最大距离。
 * 数据范围
 * 2≤N≤1000,
 * 1≤ML,MD≤104,
 * 1≤L,D≤106
 * 思路
 * 不等式，并且使所有点联通，i<=i+1+0
 * 加入i+1向i的边
 */
public class 排队布局 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        for (int i = 1; i < n; i++) {
            add(i + 1, i, 0);
        }
        int a, b, c, t;
        while (m1-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            if (b < a) {
                t = b;
                b = a;
                a = t;
            }
            add(a, b, c);
        }
        while (m2-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            if (b < a) {
                t = b;
                b = a;
                a = t;
            }
            add(b, a, -c);
        }
        if (!spfa(n)) System.out.println(-1);
        else {
            spfa(1);
            if (dis[n] == Integer.MAX_VALUE / 2) System.out.println(-2);
            else System.out.println(dis[n]);
        }
    }

    static boolean spfa(int size) {
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        Arrays.fill(st, false);
        Arrays.fill(cnt, 0);
        q.clear();
        for (int i = 1; i <= size; i++) {
            dis[i] = 0;
            q.add(i);
            st[i] = true;
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if (cnt[j] >= n) return false;
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return true;
    }

    static int n, m, N = 1010, M = 21010, count = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] w = new int[M];
    static int[] dis = new int[N];
    static int[] cnt = new int[N];
    static boolean[] st = new boolean[N];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static void add(int a, int b, int c) {
        e[count] = b;
        w[count] = c;
        ne[count] = h[a];
        h[a] = count++;
    }
}
