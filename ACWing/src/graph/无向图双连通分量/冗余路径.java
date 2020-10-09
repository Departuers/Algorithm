package graph.无向图双连通分量;

import java.util.Scanner;

/**
 * https://www.cnblogs.com/gzh-red/p/11253230.html
 * https://www.acwing.com/problem/content/397/
 * 为了从F个草场中的一个走到另一个，奶牛们有时不得不路过一些她们讨厌的可怕的树。
 * 奶牛们已经厌倦了被迫走某一条路，所以她们想建一些新路，
 * 使每一对草场之间都会至少有两条相互分离的路径，这样她们就有多一些选择。
 * 每对草场之间已经有至少一条路径。
 * 给出所有R条双向路的描述，每条路连接了两个不同的草场，
 * 请计算最少的新建道路的数量,路径由若干道路首尾相连而成。
 * 两条路径相互分离，是指两条路径没有一条重合的道路。
 * 但是，两条分离的路径上可以有一些相同的草场。
 * 对于同一对草场之间，可能已经有两条不同的道路，
 * 你也可以在它们之间再建一条道路，作为另一条不同的道路。
 * 输入格式
 * 第1行输入F和R。
 * 接下来R行，每行输入两个整数，表示两个草场，它们之间有一条道路。
 * 输出格式
 * 输出一个整数，表示最少的需要新建的道路数。
 * 数据范围
 * 1≤F≤5000,
 * F−1≤R≤10000
 * 输入样例：
 * 7 7
 * 1 2
 * 2 3
 * 3 4
 * 2 5
 * 4 5
 * 5 6
 * 5 7
 * 输出样例：
 * 2
 * 给定一个无向连通图,问最少加多少条边,可以将其变成一个边的双连通分量
 * 结论:对于有向图,最少加max(p,q),对于无向图(cnt+1)/2
 * <p>
 * 任意两点都至少有相互分离的两条路径,等价于边的双连通分量
 * 证明:如果至少存在一个桥,那么两个连通分量,所有的路径都必然会经过这个桥
 * 边的双连通分量:不包含桥=>x到y存在路径,x到y中的路径上的任何一条边都不是桥,删完边是可以连通的
 * 缩点过后,(叶节点+1)/2
 * tarjan+缩点
 * 缩点过后,变成一颗树,所有的边都是桥
 * 对于树当中所有所有度数为1的点至少加上一条边
 * 两个叶节点相连,如果有富余,随便连,所以如下
 * 假设度数为1的点有cnt个
 * 那么cnt/2上取整,=(cnt+1)/2下取整
 */
public class 冗余路径 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            add(b, a);
        }
        tarjan(1, -1);
        for (int i = 2; i < idx; i++) {
            if (isbirdge[i])
                d[id[e[i]]]++;
        }
        int cnt = 0;
        for (int i = 1; i <= dccCnt; i++) {
            if (d[i] == 1) {
                cnt++;
            }
        }
        System.out.println((cnt + 1) / 2);
    }

    static int n, m, N = 5010, M = 20010, idx = 2, time, dccCnt, top;//需要反向边所以从2开始
    static int[] h = new int[N], e = new int[M], ne = new int[M], dfn = new int[N], low = new int[N];
    static int[] stk = new int[N], id = new int[N], d = new int[N];
    static boolean[] isbirdge = new boolean[M];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void tarjan(int u, int fa) {
        dfn[u] = low[u] = ++time;
        stk[++top] = u;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (dfn[j] == 0) {
                tarjan(j, i);
                low[u] = Math.min(low[u], low[j]);
                if (dfn[u] < low[j]) {//是桥
                    isbirdge[i] = isbirdge[i ^ 1] = true;
                }
            } else if (i != (fa ^ 1)) {//不是父节点的
                low[u] = Math.min(low[u], dfn[j]);
            }
        }
        if (dfn[u] == low[u]) {
            ++dccCnt;
            int y;
            do {
                y = stk[top--];
                id[y] = dccCnt;
            } while (y != u);
        }
    }

}
