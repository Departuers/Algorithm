package graph.差分约束;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/105072757
 * 幼儿园里有 N 个小朋友，老师现在想要给这些小朋友们分配糖果，
 * 要求每个小朋友都要分到糖果.
 * 但是小朋友们也有嫉妒心，总是会提出一些要求，
 * 比如小明不希望小红分到的糖果比他的多，于是在分配糖果的时候，
 * 老师需要满足小朋友们的 K 个要求。
 * 幼儿园的糖果总是有限的，老师想知道他至少需要准备多少个糖果，
 * 才能使得每个小朋友都能够分到糖果，并且满足小朋友们所有的要求。
 * 输入格式
 * 输入的第一行是两个整数 N,K。
 * 接下来 K 行，表示分配糖果时需要满足的关系，每行 3 个数字 X,A,B。
 * 如果 X=1．表示第 A 个小朋友分到的糖果必须和第 B 个小朋友分到的糖果一样多。
 * 如果 X=2，表示第 A 个小朋友分到的糖果必须少于第 B 个小朋友分到的糖果。
 * 如果 X=3，表示第 A 个小朋友分到的糖果必须不少于第 B 个小朋友分到的糖果。
 * 如果 X=4，表示第 A 个小朋友分到的糖果必须多于第 B 个小朋友分到的糖果。
 * 如果 X=5，表示第 A 个小朋友分到的糖果必须不多于第 B 个小朋友分到的糖果。
 * 小朋友编号从 1 到 N。
 * 输出格式
 * 输出一行，表示老师至少需要准备的糖果数，如果不能满足小朋友们的所有要求，就输出 −1。
 * 数据范围
 * 1≤N<105,1≤X≤5,1≤A,B≤N1≤N<10^5,1≤X≤5,1≤A,B≤N
 * 输入样例：
 * 5 7
 * 1 1 2
 * 2 3 2
 * 4 4 1
 * 3 4 5
 * 5 4 5
 * 2 3 5
 * 4 5 1
 * 输出样例：
 * 11
 * 思路：
 * 因为我们要求的是最小值且约束条件可转换为若干不等式，所以我们可以用差分约束来做，求最长路，不等式为大于号。
 * 将题目转换成一堆不等式
 * x=1=》A>=B&&B>=A
 * x=2=》B>=A+1
 * x=3=》A>=B
 * x=4=》A>=B+1
 * x=5=》B>=A
 * 设一个超级原点x0，x0=0；
 * 因为每个人至少有一个糖果，所以xi>=x0+1。
 * 本题可能出现无解的情况，即A>B+1&&B>A+1，所以需要判断是否存在环路，此时使用SPFA时应将queue改为stack，能提高速度。
 * 因为N较大，所以建图时不要使用vector(会超时)，用前向星就行。
 */
public class 糖果 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int x, a, b;
        while (m-- != 0) {
            x = sc.nextInt();
            a = sc.nextInt();
            b = sc.nextInt();
            if (x == 1) {
                add(b, a, 0);
                add(a, b, 0);
            } else if (x == 2) {
                add(a, b, 1);
            } else if (x == 3) {
                add(b, a, 0);
            } else if (x == 4) {
                add(b, a, 1);
            } else add(a, b, 0);
        }
        for (int i = 1; i <= n; i++) {
            add(0, i, 1);
        }
        if (!spfa()) System.out.println("-1");
        else {
            long res = 0;
            for (int i = 1; i <= n; i++) {
                res += dis[i];
            }
            System.out.println(res);
        }
    }

    private static boolean spfa() {//求负环改成栈!!!
        Arrays.fill(dis, finf);
        dis[0] = 0;
        q.clear();
        q.add(0);
        st[0] = true;
        while (!q.isEmpty()) {
            int t = q.pollLast();
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] < dis[t] + w[i]) {//最长路!
                    dis[j] = dis[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if (cnt[j] >= n + 1) return false;//有负环
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return true;
    }

    static int finf = Integer.MIN_VALUE / 2;

    static int n, m, N = 100010, M = 300010, count = 1;
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
