package graph.有向图的强联通分量;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107348863
 * 和糖果一模一样的题...
 * 银河中的恒星浩如烟海，但是我们只关注那些最亮的恒星。
 * 我们用一个正整数来表示恒星的亮度，数值越大则恒星就越亮，恒星的亮度最暗是 1。
 * 现在对于 N 颗我们关注的恒星，有 M 对亮度之间的相对关系已经判明。
 * 你的任务就是求出这 N 颗恒星的亮度值总和至少有多大。
 * 输入格式
 * 第一行给出两个整数 N 和 M。
 * 之后 M 行，每行三个整数 T, A, B，表示一对恒星(A, B)之间的亮度关系。恒星的编号从 1 开始。
 * 如果 T = 1，说明 A 和 B 亮度相等。
 * 如果 T = 2，说明 A 的亮度小于 B 的亮度。
 * 如果 T = 3，说明 A 的亮度不小于 B 的亮度。
 * 如果 T = 4，说明 A 的亮度大于 B 的亮度。
 * 如果 T = 5，说明 A 的亮度不大于 B 的亮度。
 * 输出格式
 * 输出一个整数表示结果。
 * 若无解，则输出 -1。
 * 数据范围
 * N≤100000,M≤100000
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
 * 首先这道题可以用差分约束系统，用栈优化spfa求正环来做；最小值,用最长路
 * 最长路三角不等式  dis[j]>=dis[i]+w
 * 或者tarjan+缩点来做
 * 差分约束有可能超时
 * 找约束
 * A=B  =>   A>=B   B>=A
 * A<B  =>    B>=A+1
 * A>=B =>  A>=B
 * A>B  =>  A>=B+1
 * A<=B =>  B>=A
 * 最长路看有没有正环,有正环无解
 * 必须有一个绝对值,超级源点,可以到所有边
 * 技巧设置0号点, Xi>=X0+1   0号点向其他所有点连一条长度为1的边
 * <p>
 * 由于所有点的权值都大于等于0,我们用tarjan+缩点做这个题,O(n+m)的tarjan O(n)的缩点,递推也是线性
 * 有向图的强连通分量做法：
 * 用tarjan求强连通分量跑图，同时记录每个连通分量的大小；
 * 遍历每一个点，然后对于不是一个强连通块内的点进行缩点，建立新边；
 * 由于处理完的图是有向无环图，满足拓扑序，那么可以直接用递推求最长路
 * （用拓扑排序递推最长路需要倒着递推）；
 * 每个强连通块内部两两之间值边权一定为0，否则会出现正环，也就是无解的情况；
 * 1. 判断正环
 * tarjan+缩点后,对于每个强连通分量,如果存在一条边权值大于0 u->v w>0
 * 那么v也可以到u,必然存在正环,无解
 * 如果不存在环,必然所有点的权值都相同为0,
 * tarjan后,按照节点编号从大到小就是拓扑序
 * 递推,起点最长路
 */
public class 银河 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int t, a, b;
        for (int i = 1; i <= n; i++) {
            add(h, 0, i, 1);
        }//从0往其他所有点连一条权值为0的办

        while (m-- != 0) {
            t = sc.nextInt();
            a = sc.nextInt();
            b = sc.nextInt();
            if (t == 1) {
                add(h, b, a, 0);
                add(h, a, b, 0);
            } else if (t == 2) add(h, a, b, 1);
            else if (t == 3) add(h, b, a, 0);
            else if (t == 4) add(h, b, a, 1);
            else add(h, a, b, 0);
        }
        tarjan(0);
        boolean success = true;
        for (int i = 0; i <= n; i++) {
            for (int j = h[i]; j != 0; j = ne[j]) {
                int k = e[j];
                a = id[i];
                b = id[k];
                if (a == b) {
                    if (w[j] > 0) {
                        success = false;
                        break;
                    }
                } else add(hs, a, b, w[j]);
            }
            if (!success) break;
        }
        if (!success) System.out.println(-1);
        else {
            for (int i = sccCnt; i != 0; i--) {
                for (int j = hs[i]; j != 0; j = ne[j]) {
                    int k = e[j];
                    dis[k] = Math.max(dis[k], dis[i] + w[j]);
                }
            }
            long res = 0;
            for (int i = 1; i <= sccCnt; i++) {
                res += (long) dis[i] * size[i];
            }
            System.out.println(res);
        }
    }

    static int N = 100010, M = 600010, n, m, idx = 1, top, time, sccCnt;
    static int[] h = new int[N];
    static int[] ne = new int[M];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] hs = new int[N];
    static int[] id = new int[N];
    static int[] stk = new int[N], dfn = new int[N], low = new int[N], size = new int[N];
    static boolean[] instk = new boolean[N];
    static int[] dis = new int[N];//最长路

    static void add(int[] h, int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void tarjan(int u) {
        dfn[u] = low[u] = ++time;
        stk[++top] = u;
        instk[u] = true;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (dfn[j] == 0) {
                tarjan(j);
                low[u] = Math.min(low[u], low[j]);
            } else if (instk[j]) {
                low[u] = Math.min(low[u], dfn[j]);
            }
        }
        if (dfn[u] == low[u]) {
            ++sccCnt;
            int y;
            do {
                y = stk[top--];
                instk[y] = false;
                id[y] = sccCnt;
                size[sccCnt]++;
            } while (y != u);
        }
    }
}
