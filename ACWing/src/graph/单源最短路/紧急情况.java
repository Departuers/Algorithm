package graph.单源最短路;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 紧急情况 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        t = sc.nextInt();
        for (int i = 0; i < n; i++) {
            g[i] = sc.nextInt();
        }
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        for (int i = he[0]; i != 0; i = ne[i]) {
            System.out.println(e[i]);
        }
        dijkstra(s);
    }

    static void dijkstra(int s) {
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        PriorityQueue<node> q = new PriorityQueue<node>();
        dis[s] = 0;
        q.add(new node(s, 0));
        while (!q.isEmpty()) {
            node p = q.poll();
            int x = p.to;
            if (vis[x]) continue;
            vis[x] = true;
            for (int i = he[x]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[x] + w[i]) {
                    dis[j] = dis[x] + w[i];
                    q.add(new node(j, dis[j]));
                    pre[j] = x;
                }
            }
        }
        int cur = t;
        int ans = 0;
        while (cur != 0) {
            ans += g[cur];
            cur = pre[cur];
        }
        System.out.println(Arrays.toString(dis));
        System.out.println(dis[t]);
        System.out.println(ans);
    }

    static class node implements Comparable<node> {
        int to, dis;

        public node(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }

        @Override
        public int compareTo(node node) {
            return dis - node.dis;
        }
    }

    static boolean[] vis = new boolean[510];
    static int[] dis = new int[510];
    static int[] pre = new int[510];
    static int[] g = new int[510];
    static int n, m, s, t, cnt = 1;
    static int[] he = new int[510];
    static int[] ne = new int[1210];
    static int[] e = new int[1210];
    static int[] w = new int[1210];

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }
}
