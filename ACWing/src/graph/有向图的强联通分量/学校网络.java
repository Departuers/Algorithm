package graph.有向图的强联通分量;

import java.util.Scanner;

/**
 * 一些学校连接在一个计算机网络上，学校之间存在软件支援协议，
 * 每个学校都有它应支援的学校名单（学校A支援学校B，并不表示学校B一定要支援学校A）。
 * 当某校获得一个新软件时，无论是直接获得还是通过网络获得，
 * 该校都应立即将这个软件通过网络传送给它应支援的学校。
 * 因此，一个新软件若想让所有学校都能使用，只需将其提供给一些学校即可。
 * 现在请问最少需要将一个新软件直接提供给多少个学校，
 * 才能使软件能够通过网络被传送到所有学校？
 * 最少需要添加几条新的支援关系，使得将一个新软件提供给任何一个学校，
 * 其他所有学校就都可以通过网络获得该软件？
 * 输入格式
 * 第1行包含整数N，表示学校数量。
 * 第2..N+1行，每行包含一个或多个整数，第i+1行表示学校 i 应该支援的学校名单，每行最后都有一个0表示名单结束（只有一个0即表示该学校没有需要支援的学校）。
 * 输出格式
 * 输出两个问题的结果，每个结果占一行。
 * 数据范围
 * 2≤N≤100
 * 输入样例：
 * 5
 * 2 4 3 0
 * 4 5 0
 * 0
 * 0
 * 1 0
 * 输出样例：
 * 1
 * 2
 * 考虑假如通过缩点变成DAG,会不会变得简单
 * 图中有P个起点(入度为0的点),Q个终点,(出度为0的点)
 * 给每个起点发信息,网络一定可以得到结果
 * 第一问答案是P
 * 第二问,对于一张有向图,至少加几条边会变成强连通图,
 * 证明:设P<=Q,这个时候证明答案是Q
 * 如果P==1 起点必然可以走到每个终点,
 * 只需要从每个终点连向起点一条边,共加Q条边,就可以变成DAG
 * 如果 P>1 则Q>P>1 必然可以找到两个起点,
 * 反证可以退出矛盾...很难...
 * P1 -> Q1  P2->Q2   使Q1->P2 显然:使得PQ都减一,则答案为Q
 * 只需要加max(P,Q)条边,就可以变成强连通图
 * <p>
 * 先缩点,找出P,Q
 */
public class 学校网络 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int t = 1;
            while (true) {
                t = sc.nextInt();
                if (t == 0) break;
                add(i, t);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) tarjan(i);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = h[i]; j != 0; j = ne[j]) {
                int k = e[j];
                int a = id[i], b = id[k];
                if (a != b) {
                    dout[a]++;
                    din[b]++;
                }
            }
        }
        int a = 0, b = 0;
        for (int i = 1; i <= scc_cnt; i++) {
            if (din[i] == 0) a++;
            if (dout[i] == 0) b++;
        }
        System.out.println(a);
        if (scc_cnt == 1) System.out.println(0);
        else System.out.println(Math.max(a, b));
    }

    static void tarjan(int u) {
        low[u] = dfn[u] = ++time;
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
            ++scc_cnt;
            int y;
            do {
                y = stk[top--];
                instk[y] = false;
                id[y] = scc_cnt;
            } while (y != u);
        }
    }

    static int n, N = 110, M = 10010, idx = 1, time, top, scc_cnt;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] dfn = new int[N];
    static int[] low = new int[N];
    static int[] stk = new int[N];
    static boolean[] instk = new boolean[N];
    static int[] id = new int[N];
    static int[] din = new int[N];
    static int[] dout = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
