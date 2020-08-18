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
 */
public class 单词环 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str;
        while (sc.hasNext()) {
            n = sc.nextInt();
            Arrays.fill(h, 0);
            cnt = 1;
            for (int i = 0; i < n; i++) {
                str = sc.next().toCharArray();
                int len = str.length;
                if (len >= 2) {
                    int left = (str[0] - 'a') * 26 + str[1] - 'a';//看做一个26进制数
                    int right = (str[len - 2] - 'a') * 26 + str[len - 1] - 'a';
                    add(left, right, len);
                }
            }
            if (!check(0)) System.out.println("无解");
            else {
                double l = 0, r = 1000;
                while (r - l > 1e-5) {
                    double mid = (l + r) / 2;
                    if (check(mid)) l = mid;
                    else r = mid;
                }
                System.out.println(r);
            }
        }
    }

    private static boolean check(double mid) {
        Arrays.fill(st, false);
        Arrays.fill(count, 0);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < 676; i++) {
            q.add(i);
            st[i] = true;
        }
        int cn = 0;
        while (!q.isEmpty()) {
            int t = q.pollLast();//换成栈
            st[t] = true;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] < dis[t] + w[i] - mid) {
                    dis[j] = dis[t] + w[i] - mid;
                    count[j] = count[t] + 1;
//                    if (count[j] >= N) return true;
                    if (++cn > 30000) return true;//经验trick,或者换成把spfa的队列换成栈
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return false;
    }

    static int n, cnt = 1, N = 700, M = 100100;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static double[] dis = new double[N];
    static int[] count = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
    }

}
