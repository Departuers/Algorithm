package graph.单源最短路拓展;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://blog.csdn.net/qq_30277239/article/details/106864304
 * 给出一个 N 个顶点 M 条边的无向无权图，顶点编号为 1 到 N。
 * 问从顶点 1 开始，到其他每个点的最短路有几条。
 * 输入格式
 * 第一行包含 2 个正整数 N,M，为图的顶点数与边数。
 * 接下来 M 行，每行两个正整数 x,y，表示有一条顶点 x 连向顶点 y 的边，请注意可能有自环与重边。
 * 输出格式
 * 输出 N 行，每行一个非负整数，第 i 行输出从顶点 1 到顶点 i 有多少条不同的最短路，由于答案有可能会很大，你只需要输出对 100003 取模后的结果即可。
 * 如果无法到达顶点 i 则输出 0。
 * 数据范围
 * 1≤N≤10^5,
 * 1≤M≤2×10^5
 * 输入样例：
 * 5 7
 * 1 2
 * 1 3
 * 2 4
 * 3 4
 * 2 3
 * 4 5
 * 4 5
 * 输出样例：
 * 1
 * 1
 * 1
 * 2
 * 4
 * 本题类似于背包问题的方案数，如果是求起点到终点所有的路径条数，直接遍历图的同时统计下方案数即可。
 * 现在要求的是最短路径的条数，因此应该在计算最短路径的过程中使用动态规划的思想去求解。
 * 本题中涉及的边都是无权边，或者说边权都是1，因此可以直接用bfs来求最短路径。
 * 回忆下基本的BFS过程：将起点加入队列，访问位置为true；当队列非空时不断取队头元素，
 * 将队头元素相邻的访问位为false的点都加入到队列中来，同时更新这些点的最短路径。
 * 这意味着如果从a扩展到b点时，将b入队了，然后即使从c点扩展到b点，
 * 并且这两种扩展的路径中b的最短路径都是相同的，但是由于b由a扩展了已经入队了，
 * 再次由c扩展到时不会再次入队了，而统计方案数则要统计所有的最短路径情况。
 * 因此，设d[i]为目前起点到i点的最短路径，cnt[i]是目前起点到i点最短路径的条数，
 * 如果从上一点u向i点扩展，d[i] < d[u] + 1时，需要更新最短路径长度，d[i] = d[u] + 1，
 * 并且此刻到i的最短路径长度应该继承到u的最短路径条数，即cnt[i] = cnt[u]；d[i] == d[u] + 1时，
 * 不需要更新最短路径长度，但是需要将到u的最短路径条数加到到i的最短路径条数中，即cnt[i] += cnt[u]。
 * 最短路径长度和最短路径条数更新的条件已经明确了，最后要明确的就是何时将节点加入到队列中？
 * 从u扩展到i，但是d[u] + 1 > d[i]，此时自然不用将i入队；d[u] + 1 < d[i]，
 * 这时候更新了i点的最短路径长度，因此要将i入队；那么d[u] + 1 == d[i]时呢？此刻由于d[i]与d[u] + 1相等，
 * 必然已经经由其他点扩展过加入队列了，而比d[i]更小的d[u]才刚刚出队，d[i]必然还在队列中，
 * 因此不用再次将i入队了。这样一来，标志数组st也就没有必要设置了。
 * 对于题目中的重边和自环，重边的存在有可能将最短路径条数翻倍，自环的存在可以忽略，
 * 一个点走一遍自环会使得到起点的距离加上1，必然不是最短距离。总的代码如下：
 * <p>
 * dp思想
 * 1.先求出全局最小值,
 * 2.分别求出每个子集中高等于全局最小值的元素个数
 * <p>
 * dist[i] 表示所有从1到i号点,所有路径长度最短的值是多少
 * cnt[i] 表示从1到i最短路径的条数
 * 根据最后一条到i的边的方向,分类
 * t->j dist[j]=dist[t]+w[i]
 * 记录前驱,
 * 如果有一个环权值和为0,那么最短路条数为正无穷
 * bfs 每个点只入队一次,只出队一次,
 * Dijkstra 每个点,只作为最小值出队一次,每个点第一次出队序列满足拓扑序
 * 前面可以顺便统计count
 * bellman-ford(spfa) 本身不具备拓扑序
 */
public class 最短路计数 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b;
        while (m-- != 0) {
            a = nextInt();
            b = nextInt();
            add(a, b);
            add(b, a);
        }
        bfs();
        for (int i = 1; i <= n; i++) {
            if (i == n) bw.write(cnt[i] + "");
            else bw.write(cnt[i] + "\n");
        }
        bw.flush();
    }

    static int inf = 0x3f3f3f3f;

    static void bfs() {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        Arrays.fill(dis, inf);

        dis[1] = 0;
        cnt[1] = 1;//1号点的最短路径条数是1
        q.add(1);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = h[u]; i != 0; i = ne[i]) {
                int j = e[i];

                //核心部分,看做dp的转移
                if (dis[j] > dis[u] + 1) {
                    cnt[j] = cnt[u];
                    dis[j] = dis[u] + 1;

                    q.add(j);
                } else if (dis[j] == dis[u] + 1) {
                    cnt[j] %= mod;
                    cnt[j] = (cnt[j] + cnt[u]) % mod;
                }


            }

        }
    }

    static int N = 100005, M = 400005, mod = 100003, n, m, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] dis = new int[N];
    static int[] cnt = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }

}
