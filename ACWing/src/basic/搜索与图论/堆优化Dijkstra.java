package basic.搜索与图论;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 堆优化Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        while (m-- > 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
        }
        Dijkstra();
        if (dis[n] == 1e9) {
            System.out.println(-1);
        } else System.out.println(dis[n]);
    }

    static int n, m, N = (int) 1e5 + 190, idx = 1;
    static int[] h = new int[N], e = new int[N], ne = new int[N], w = new int[N];
    static int[] dis = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
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

    static void Dijkstra() {
        PriorityQueue<node> q = new PriorityQueue<node>();
        q.add(new node(0, 1));
        Arrays.fill(dis, (int) 1e9);
        dis[1] = 0;
        while (!q.isEmpty()) {
            int t = q.poll().to;
            if (st[t]) continue;
            st[t] = true;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    q.add(new node(dis[j], j));
                }
            }
        }
    }
}
