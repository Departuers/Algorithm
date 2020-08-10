package graph.单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.cnblogs.com/zqzxwdm/p/5590204.html
 * 3 4 5
 * 2
 * 3
 * 4
 * 1 2 1
 * 1 3 5
 * 2 3 7
 * 2 4 3
 * 3 4 5
 * out:
 * 8
 * 选一个节点作为起点使得其他节点到该节点的所有步数最少,显然
 * 多源汇最短路,n为500  Floyd O(n^3)超时
 * 使用spfa
 * O(nm)最差O(n^2m)
 * 或者使用Dijkstra
 * O(nm log n)
 */
public class 香甜的黄油 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        p = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            id[i] = sc.nextInt();
        }
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        for (int i = 1; i <= p; i++) {
            ans = Math.min(ans, spfa(i));
        }
        System.out.println(ans);
    }

    static int spfa(int s) {
        int res = 0;
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        dis[s] = 0;
        q.add(s);
        vis[s] = true;
        while (!q.isEmpty()) {
            int p = q.poll();
            vis[p] = false;
            for (int i = he[p]; i != 0; i = ne[i]) {
                if (dis[e[i]] > dis[p] + w[i]) {
                    dis[e[i]] = dis[p] + w[i];
                    if (!vis[e[i]]) {
                        vis[e[i]] = true;
                        if (!q.isEmpty() && dis[e[i]] < dis[q.peekFirst()]) q.addFirst(e[i]);
                        else q.add(e[i]);
                    }
                }
            }
        }
        //计算从0~n的距离和
        for (int i = 0; i < n; i++) {
            int j = id[i];
            if (dis[j] == Integer.MAX_VALUE / 2) return Integer.MAX_VALUE / 2;
            //无法到达
            res += dis[j];
        }
        return res;
    }

    static int Dijkstra(int s) {
        int res = 0;
        PriorityQueue<node> q = new PriorityQueue<node>();
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        Arrays.fill(vis, false);
        dis[s] = 0;
        q.add(new node(0, s));
        while (!q.isEmpty()) {
            int p = q.poll().to;
            //pq每次取出的边,就是算出最短路径的边
            if (vis[p]) continue;
            vis[p] = true;
            for (int i = he[p]; i != 0; i = ne[i]) {
                int ed = e[i];
                if (dis[p] != Integer.MAX_VALUE && dis[ed] > dis[p] + w[i]) {
                    dis[ed] = dis[p] + w[i];
                    q.add(new node(ed, dis[ed]));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int j = id[i];
            if (dis[j] == Integer.MAX_VALUE / 2) return Integer.MAX_VALUE / 2;
            //无法到达
            res += dis[j];
        }
        return res;
    }

    static class node implements Comparable<node> {
        int dis, to;

        public node(int dis, int to) {
            this.dis = dis;
            this.to = to;
        }

        @Override
        public int compareTo(node node) {
            return dis - node.dis;
        }
    }

    static int ans = Integer.MAX_VALUE;

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int[] he = new int[2550];
    static int[] ne = new int[6550 * 2];
    static int[] e = new int[6550 * 2];
    static int[] w = new int[6550 * 2];
    static boolean[] vis = new boolean[2560];
    static int[] dis = new int[2560];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, p, cnt = 1;
    static int[] id = new int[2523];

}
