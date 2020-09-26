package graph.无向图双连通分量;

import java.util.Arrays;
import java.util.Scanner;

/**
 * * https://blog.csdn.net/weixin_45080867/article/details/108754265
 * 给定一个n个点,m条边构成的无向图,请你求出该图删除一个点后,连通块最多有多少个
 * 数据范围
 * 1≤n≤10000,
 * 0≤m≤15000,
 * 0≤a,b<n
 * 输入样例：
 * 3 3
 * 0 1
 * 0 2
 * 2 1
 * 4 2
 * 0 1
 * 2 3
 * 3 1
 * 1 0
 * 0 0
 * 输出样例：
 * 1
 * 2
 * 2
 * 点的双连通分量算法tarjan
 * 也是基于时间戳
 * dfn[x]
 * low[x]
 * 如果判断割点:
 * low[y]>=dfn[x]
 * 1.如果x不是根节点,那么x是割点
 * 2.x是根节点,至少有两个子树满足low[y]>=dfn[x]
 * 各种特判
 * <p>
 * 题目要求删除一个点以后，最后有多少个连通块，那么我们用割点的方法，
 * 在用tarjan算法可以把每一个连通块求出来，对于每一个连通块，
 * 如果dfs[n]<=low[j]的话，删除n点，
 * 肯定会使得 j 所在的连通块部分成为一个新的连通块，所以记录一下有多少个j，
 * 特判一下如果是n不是该连通块的根节点，就会多一个连通块。
 * <p>
 * 1.统计连通块个数,cnt
 * 2.依次枚举从哪个块中删,再枚举删除每个点,假设那个点删除可以分成s个连通块
 * 那么结果就是s+cnt-1  使得s+cnt-1最大
 */
public class 电力 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b;
        while (sc.hasNext()) {
            n = sc.nextInt();
            m = sc.nextInt();
            Arrays.fill(h, -1);
            Arrays.fill(dfn, 0);
            Arrays.fill(low, 0);
            idx = time = 0;
            while (m-- != 0) {
                a = sc.nextInt();
                b = sc.nextInt();
                add(a, b);
                add(b, a);
            }
            cnt = 0;//连通块数量
            for (root = 0; root < n; root++) {
                if (dfn[root] == 0) {
                    cnt++;
                    tarjan(root);
                }
            }
            System.out.println(cnt + ans - 1);
        }
    }

    static int n, m, N = 10010, M = 30010, idx = 2, cnt, time, root, ans;
    static int[] h = new int[N], e = new int[M], ne = new int[M],
            dfn = new int[N], low = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void tarjan(int u) {
        dfn[u] = low[u] = ++time;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (dfn[j] == 0) {
                tarjan(j);
                low[u] = Math.min(low[u], low[j]);
                if (low[j] >= dfn[u]) {
                    cnt++;
                }
            } else low[u] = Math.min(low[u], dfn[j]);
        }
        if (u != root && cnt > 0) {
            cnt++;
        }
        ans = Math.max(ans, cnt);
    }
}
