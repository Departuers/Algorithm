package graph.单源最短路;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 信使Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        System.out.println(dij());
    }

    static class node implements Comparable<node> {
        int dis, to;

        public node(int dis, int to) {
            this.dis = dis;
            this.to = to;
        }

        @Override
        public int compareTo(node o) {
            return dis - o.dis;
        }
    }

    static int n, m, s, t, N = 10010, idx = 1;
    static int[] dis = new int[N], h = new int[N], ne = new int[N], w = new int[N], e = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int dij() {
        int ans = 0;
        Arrays.fill(dis, (int) 1e9);
        PriorityQueue<node> q = new PriorityQueue<>();
        q.add(new node(0, 1));
        dis[1] = 0;
        while (!q.isEmpty()) {
            int v = q.poll().to;
            if (st[v]) continue;
            st[v] = true;
            ans = Math.max(ans, dis[v]);
            for (int i = h[v]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[v] + w[i]) {
                    dis[j] = dis[v] + w[i];
                    q.add(new node(dis[j], j));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (dis[i] == 1e9) {
                ans = -1;
                break;
            }
        }
        return ans;
    }
}
