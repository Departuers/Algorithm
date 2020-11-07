package graph.二分图;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 有两台机器 A，B 以及 K 个任务。
 * 机器 A 有 N 种不同的模式（模式0~N-1），机器 B 有 M 种不同的模式（模式0~M-1）。
 * 两台机器最开始都处于模式0。
 * 每个任务既可以在A上执行，也可以在B上执行。
 * 对于每个任务 i，给定两个整数 a[i] 和 b[i]，表示如果该任务在 A 上执行，需要设置模式为 a[i]，如果在 B 上执行，需要模式为 b[i]。
 * 任务可以以任意顺序被执行，但每台机器转换一次模式就要重启一次。
 * 求怎样分配任务并合理安排顺序，能使机器重启次数最少。
 * 输入格式
 * 输入包含多组测试数据。
 * 每组数据第一行包含三个整数 N, M, K。
 * 接下来k行，每行三个整数 i, a[i]和b[i]，i 为任务编号，从0开始。
 * 当输入一行为0时，表示输入终止。
 * 输出格式
 * 每组数据输出一个整数，表示所需的机器最少重启次数，每个结果占一行。
 * 数据范围
 * N,M<100,K<1000
 * 0≤a[i]<N
 * 0≤b[i]<M
 * 输入样例：
 * 5 5 10
 * 0 1 1
 * 1 1 2
 * 2 1 3
 * 3 1 4
 * 4 2 1
 * 5 2 2
 * 6 2 3
 * 7 2 4
 * 8 3 3
 * 9 4 3
 * 0
 * 输出样例：
 * 3
 * <p>
 * A  B
 * 0  0
 * 会先把在A上模式为0的,或者在B上模式为0的任务做掉
 * 那么Ai和Bi的任务全都是大于0的
 * Ai和Bi连一条无向边.
 * 最少选一个点,也就是选A机器或者B机器执行该任务
 * 由于初始化都为0,所以一共有N+M-2个点
 */
public class 机器任务 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, t;
        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
            if (n == 0) break;
            Arrays.fill(h, 0);
            Arrays.fill(match, 0);
            Arrays.fill(st, 0);
            idx = 1;
            while (k-- != 0) {
                t = sc.nextInt();
                a = sc.nextInt();
                b = sc.nextInt();
                if (a == 0 || b == 0) continue;
                add(a, b);
            }
            int res = 0;
            for (int i = 1; i < n; i++) {
                if (find(i, i * 10)) res++;
            }
            System.out.println(res);
        }
    }

    static int N = 110, idx = 1, n, m, k;
    static int[] match = new int[N], st = new int[N];
    static int[] h = new int[N], e = new int[N * N / 2], ne = new int[N * N / 2];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static boolean find(int u, int tag) {
        if (st[u] == tag) return false;
        st[u] = tag;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (match[j] == 0 || find(j, tag)) {
                match[j] = u;
                return true;
            }
        }
        return false;
    }
}
