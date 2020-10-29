package graph.单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class 信使spfa {
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
        System.out.println(spfa());

    }

    static int n, m, N = 240, M = 410, idx = 1;
    static int[] dis = new int[N], ne = new int[M],
            h = new int[N], e = new int[M], w = new int[M];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int spfa() {
        Arrays.fill(dis, 1 << 30);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        dis[1] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    if (!st[j]) {
                        st[j] = true;
                        q.add(j);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dis[i]);
            if (dis[i] == 1 << 30) {
                ans = -1;
                break;
            }
        }
        return ans;
    }
}
