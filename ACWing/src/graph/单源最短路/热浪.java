package graph.单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * http://blog.sina.com.cn/s/blog_83ac6af80102v0zj.html
 * 裸最短路
 */
public class 热浪 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        t = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }

        dijkstra();
        System.out.println(dis[t]);
    }

    static void spfa() {
        Arrays.fill(dis, Integer.MAX_VALUE - 124124);
        q.add(s);
        dis[s] = 0;
        vis[s] = true;
        while (!q.isEmpty()) {
            int p = q.pop();
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
    }

    static class node implements Comparable<node> {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return x - node.x;
        }
    }

    static void dijkstra() {
        PriorityQueue<node> q = new PriorityQueue<node>();
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        dis[s] = 0;
        q.add(new node(0, s));
        while (!q.isEmpty()) {
            node ed = q.poll();
            int y = ed.y;
            if (vis[y]) continue;
            vis[y] = true;
            for (int i = he[y]; i != 0; i = ne[i]) {
                if (dis[e[i]] > dis[y] + w[i]) {
                    dis[e[i]] = dis[y] + w[i];
                    q.add(new node(w[i], e[i]));
                }
            }
        }
    }

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
    static int n, m, s, t, cnt = 1;
}
