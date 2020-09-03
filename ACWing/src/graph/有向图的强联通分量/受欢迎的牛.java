package graph.有向图的强联通分量;

import java.util.Scanner;

/**
 * tarjan求强联通分量
 * https://www.luogu.com.cn/blog/zyb2624936151/tarjan
 *
 */
@SuppressWarnings("all")
public class 受欢迎的牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b;
        while (m-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
        }
        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) {
                tarjan(i);
            }
        }
        for (int i = 1; i <= n; i++) {//遍历所有点
            for (int j = h[i]; j != 0; j = ne[j]) {//遍历i所有的邻边
                int k = e[j];
                a = id[i];
                b = id[k];
                if (a != b) {
                    dout[a]++;//出度
                }
            }
        }
        int zeros = 0, sum = 0;
        for (int i = 1; i <= scc_cnt; i++) {
            if (dout[i] == 0) {
                zeros++;
                sum += size[i];
                if (zeros > 1) {
                    sum = 0;
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    static void tarjan(int u) {
        dfn[u] = low[u] = ++time;//时间戳
        stk[++top] = u;
        in_stk[u] = true;//把u点放进栈中
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (dfn[j] == 0) {
                tarjan(j);
                low[u] = Math.min(low[u], low[j]);
            } else if (in_stk[j]) {
                low[u] = Math.min(low[u], dfn[j]);
            }
            if (dfn[u] == low[u]) {
                ++scc_cnt;
                int y = 0;
                do {
                    y = stk[top--];
                    in_stk[y] = false;
                    id[y] = scc_cnt;
                    size[scc_cnt]++;
                } while (y != u);
            }
        }
    }

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int N = 10010, n, M = 50010, time, top, scc_cnt, m, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] dfn = new int[N];
    static int[] low = new int[N];
    static int[] stk = new int[N];
    static boolean[] in_stk = new boolean[N];
    static int[] id = new int[N];
    static int[] size = new int[N];
    static int[] dout = new int[N];//出度
}
//    static void tarjar(int u) {
//        dfu[u] = low[u] = ++time;
//        stk[++top] = u;
//        in_stk[u] = true;
//        for (int i = he[u]; i != 0; i = ne[i]) {
//            int j=e[i];
//            if (!dfn[j]){
//                tarjar(j);
//                low[u]=Math.min(low[u],low[j]);
//            }else if (in_stk[j])
//                low[u]=Math.min(low[u],dfn[j]);
//        }
//        if (dfn[u]==low[u]){
//            int y;
//            ++scc_cnt;
//            do {
//                y=stk[top--];
//                in_stk[y]=false;
//                id[y]=scc_cnt;
//            }while (y!=u);
//        }
//    }
// 缩点:     for (int i = 1; i <= n; i++) {//遍历所有点
//            for (int j = h[i]; j != 0; j = ne[j]) {//遍历i所有的邻边
//                int k = e[j];
//                a = id[i];
//                b = id[k];
//                if (a != b) {
//                   加一条新边id[i]->id[j]
//                }
//            }
//        }
//
