package graph.有向图的强联通分量;

import java.util.Scanner;

/**
 * tarjan求强联通分量
 * https://www.luogu.com.cn/blog/zyb2624936151/tarjan
 * O(n+m)
 * 每头奶牛都梦想成为牛棚里的明星。被所有奶牛喜欢的奶牛就是一头明星奶牛。
 * 所有奶牛都是自恋狂，每头奶牛总是喜欢自己的。奶牛之间的“喜欢”是可以传递的——如果 A喜欢 B，B 喜欢 C
 * 那么 A 也喜欢 C。牛栏里共有 N头奶牛，给定一些奶牛之间的爱慕关系，请你算出有多少头奶牛可以当明星。
 * 输入格式
 * 第一行：两个用空格分开的整数：N 和 M。
 * 接下来 M 行：每行两个用空格分开的整数：A 和 B，表示 A 喜欢 B。
 * 输出格式
 * 一行单独一个整数，表示明星奶牛的数量。
 * 输入输出样例
 * 输入
 * 3 3
 * 1 2
 * 2 1
 * 2 3
 * 输出
 * 1
 * 显然,对于每个点,在反图上遍历一遍,看能不能到达其他所有点,O(n+m)每次
 * 复杂负最终为O(n(n+m))
 * 假设这个图是拓扑图,缩点后,那么肯定会走到一个出度为0的点
 * 如果存在两个出度为0的点,那么无解
 * 结论:如果只有一个强连通分量的缩点后的出度0,那么答案就是该强连通分量的点的数量
 * 如果有大于一个强连通分量的出度为0,那么答案为0
 * tanjan+缩点
 * 拿Cpp写过了,JavaRe2个
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


        //缩点
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
                sum = size[i];//出度为0的强连通分量,有多少个点
                if (zeros > 1) {//拓扑图,缩点后,如果有超过一个出度为0的点,那么无解
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
        }
        if (dfn[u] == low[u]) {//找到一个强连通分量的最高点
            ++scc_cnt;
            int y = 0;
            do {
                y = stk[top--];
                in_stk[y] = false;//出栈

                id[y] = scc_cnt;//y属于哪一个强连通分量编号
                size[scc_cnt]++;//数量++
            } while (y != u);
        }
    }

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int N = 10010, n, M = 50010, time, top, scc_cnt = 0, m, idx = 1;
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
