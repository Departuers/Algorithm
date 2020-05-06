package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出impossible。
 * 数据保证不存在负权回路。
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
 * 输出格式
 * 输出一个整数，表示1号点到n号点的最短距离。
 * 如果路径不存在，则输出”impossible”。
 * 数据范围
 * 1≤n,m≤10^5,
 * 图中涉及边长绝对值均不超过10000。
 * 输入样例：
 * 3 3
 * 1 2 5
 * 2 3 -3
 * 1 3 4
 * 输出样例：
 * 2
 * Bellman-Ford算法的时间复杂度为O（mn），处理本题显然会超时，
 * 而spfa算法则是采用了队列优化后的Bellman-Ford算法，
 * spfa算法一般情况下的时间复杂度为O(m)，最坏情况下的时间复杂度为O(mn)。
 * 要理解spfa算法的思路很简单，只需要理解Bellman-Ford算法的缺点即可。
 * 回忆下Bellman-Ford算法，每轮松弛都松弛所有的边，
 * 在执行过程中可以发现每轮只有很少的边会被松弛成功，因为假设第i轮仅松弛了a节点的距离，
 * 那么在下一轮的松弛中，除了a可以到达的点有被松弛的可能性，其它的点不用判断也能知道不会被松弛。
 * 既然事先已经知道有些边不会被松弛，那么为什么还要继续去遍历所有边呢？spfa算法的核心思想就是：
 * 每一轮只遍历上一轮被松弛的点连接的边，只松弛也可能被松弛的边。
 */
public class Spfa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int x, y, z;
        for (int i = 0; i < m; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            add(x, y, z);
        }
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        vis[1] = true;
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(1);
        int t = 0;
        while (!q.isEmpty()) {
            x = q.poll();
            vis[x] = false;
            for (int i = he[x]; i != 0; i = ne[i]) {
                t = e[i];
                if (dis[x] != Integer.MAX_VALUE && dis[t] > dis[x] + w[i]) {
                    dis[t] = dis[x] + w[i];
                    if (!vis[t]) {
                        if (!q.isEmpty()&&dis[t]<dis[q.peekFirst()]){
                            q.addFirst(t);
                        }else q.add(t);
                        vis[t]=true;
                    }
                }
            }
        }
        System.out.println(dis[n]);
    }

    static int[] dis = new int[100005];
    static boolean[] vis = new boolean[100005];
    static int[] he = new int[100005];
    static int[] w = new int[200005];
    static int[] ne = new int[200005];
    static int[] e = new int[200005];

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, m, cnt = 1;
}
