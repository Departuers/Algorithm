package graph;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * https://www.luogu.com.cn/blog/ONE-PIECE/solution-p2865
 * https://www.jianshu.com/p/616f1bf28b0d
 * spfa做次短路
 * 更新次小距离的条件有三种：
 * 1.更新了最小距离，要把上次的最小距离先拿给次小距离（刚开始没想到这个条件，
 * 调了好久），因为上次的最小距离就是比当前距离大且最小的距离（即为次短距离）。
 * 2.虽然可能当前路径无法更新最小距离，但可能更新次小距离，要加判断
 * 3.从上一个点的次短距离更新这一个点的次短距离
 * 注意：
 * 想转移次短距离，必须保证该距离小于最短距离。
 */
public class 次短路 {
    public static void main(String[] args) {

    }

    static int n, m, N = 10010, idx = 1, M = 200100, inf = 0x3f3f3f3f;
    static int[][] dis = new int[2][N];//dis[0,t] dis[1,t]存储最短路,和次短路
    static int[] h = new int[N], e = new int[M], ne = new int[M], w = new int[M];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void spfa(int s) {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        Arrays.fill(dis[0], inf);
        Arrays.fill(dis[1], inf);
        dis[0][s] = 0;
        dis[1][s] = 0;
        int u;
        q.add(s);
        while (!q.isEmpty()) {
            u = q.poll();
            st[u] = false;
            for (int i = h[u]; i != 0; i = ne[i]) {
                int d = e[i];
                if (dis[0][d] > dis[0][u] + w[i]) {
                    dis[1][d] = dis[0][d];//次短路
                    dis[0][d] = dis[0][u] + w[i];//最短路
                    if (!st[d]) {
                        st[d] = true;
                        q.add(d);
                    }
                    if ((dis[1][d] > dis[0][d] + w[i] && dis[0][u] + w[i] > dis[0][d]) || (dis[1][d] == dis[0][d])) {
                        dis[1][d] = dis[0][u] + w[i];
                        if (!st[d]) {
                            st[d] = true;
                            q.add(d);
                        }
                    }
                    if (dis[1][d] > dis[1][u] + w[i] && dis[1][u] + w[i] > dis[0][d]) {
                        dis[1][d] = dis[1][u] + w[i];
                        if (!st[d]) {
                            st[d] = true;
                            q.add(d);
                        }
                    }
                }
            }
        }
    }
}
