package graph.负环;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107271849
 * 我们有 n 个字符串，每个字符串都是由 a∼z 的小写英文字母组成的。
 * 如果字符串 A 的结尾两个字符刚好与字符串 B 的开头两个字符相匹配，
 * 那么我们称 A 与 B 能够相连（注意：A 能与 B 相连不代表 B 能与 A 相连）。
 * 我们希望从给定的字符串中找出一些，
 * 使得它们首尾相连形成一个环串（一个串首尾相连也算），
 * 我们想要使这个环串的平均长度最大。
 * 如下例：
 * 第一个串能与第二个串相连，第二个串能与第三个串相连，第三个串能与第一个串相连，我们按照此顺序相连，便形成了一个环串，长度为 5+7+10=22（重复部分算两次），总共使用了 3 个串，所以平均长度是 22/3≈7.33。
 * 输入格式
 * 本题有多组数据。
 * 每组数据的第一行，一个整数 n，表示字符串数量；
 * 接下来 n 行，每行一个长度小于等于 1000 的字符串。
 * 读入以 n=0 结束。
 * 输出格式
 * 若不存在环串，输出”No solution”，否则输出最长的环串的平均长度。
 * 只要答案与标准答案的差不超过 0.01，就视为答案正确。
 * 数据范围
 * 1≤n≤10^5
 * 思路
 * 我们把输入的每个字符串当做一条边，点及只有26*26个，
 * 然后再二分答案跑spfa即可。spfa中有个玄学优化，
 * 访问点的次数为总点数的几倍的时候，即可认为它含环。
 * 后两位有机会连接,那么有26^2种选择,考虑建图
 * 做一个对偶,把点看成边,把边看成点
 * 如ababc  相当于把ab连到bc长度是5
 * 如bckjaca  相当于bc连向ca长度是7
 * 如caahoynaab 相当于ca连向ab 长度是10
 * 最多有10^5条边,最多676个点,不会爆空间时间
 * 非常巧妙的想法,
 * 在这样的图中,是不是是原图中的环是等价的呢.显然是的
 * 考虑二分范围[0,1000]
 * 点权在边上
 * ∑wi/∑1>M
 * ∑wi>M∑1
 * ∑wi-M∑1>0
 * 边权越大越说明有正环
 * M取0还没有环,
 */
public class 单词环 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str;
        while (sc.hasNext()) {
            n = sc.nextInt();
            if (n == 0) break;
            Arrays.fill(h, 0);
            idx = 1;
            for (int i = 0; i < n; i++) {
                str = sc.next().toCharArray();
                int len = str.length;
                if (len >= 2) {
                    int left = (str[0] - 'a') * 26 + str[1] - 'a';//看做一个26进制数
                    int right = (str[len - 2] - 'a') * 26 + str[len - 1] - 'a';
                    add(left, right, len);

                }
            }
            if (!check(0)) System.out.println("No solution");
            else {
                double l = 0, r = 1000;
                while (r - l > 1e-5) {
                    double mid = (l + r) / 2;
                    if (check(mid)) l = mid;
                    else r = mid;
                }
                System.out.printf("%.3f", r);
            }
        }
    }

    static int inf = 0x3f3f3f3f;

    private static boolean check(double mid) {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < 676; i++) {
            dis[i] = count[i] = 0;
            q.add(i);
            st[i] = true;
        }
        int cn = 0;
        while (!q.isEmpty()) {
            int t = q.poll();//换成栈
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] < dis[t] + w[i] - mid) {
                    dis[j] = dis[t] + w[i] - mid;
                    count[j] = count[t] + 1;
//                    if (count[j] >= N) return true;
                    if (++cn > 10000) return true;//经验trick,或者换成把spfa的队列换成栈
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return false;
    }

    static int n, idx = 1, N = 700, M = 100100;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static double[] dis = new double[N];
    static int[] count = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

}
