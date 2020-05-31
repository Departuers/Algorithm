package graph.负环;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.cnblogs.com/ctyakwf/p/12840842.html
 * 2
 * 3 3 1
 * 1 2 2
 * 1 3 4
 * 2 3 1
 * 3 1 3
 * 3 2 1
 * 1 2 3
 * 2 3 4
 * 3 1 8
 */
public class 虫洞 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        int a, b, c;
        while (t-- != 0) {
            n = sc.nextInt();
            m1 = sc.nextInt();
            m2 = sc.nextInt();
            idx = 1;
            Arrays.fill(he, 0);
            while (m1-- != 0) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                add(a, b, c);
                add(b, a, c);
            }
            while (m2-- != 0) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                add(a, b, -c);
            }
            if (spfa()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    private static boolean spfa() {
        Arrays.fill(dis, 0);
        Arrays.fill(vis, false);
        Arrays.fill(cnt, 0);
        for (int i = 1; i <= n; i++) {
            q.add(i);
            vis[i] = true;
        }
        int p;
        while (!q.isEmpty()) {
            p = q.poll();
            vis[p] = false;
            for (int i = he[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[p] + w[i]) {
                    dis[j] = dis[p] + w[i];
                    cnt[j] = cnt[p] + 1;
                    if (cnt[j] >= n) return true;
                    if (!vis[j]) {
                        q.add(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return false;
    }

    static int t, n, m1, m2, idx = 1;
    static int[] he = new int[510];
    static int[] e = new int[5210];
    static int[] ne = new int[5210];
    static int[] w = new int[5210];
    static int[] cnt = new int[510];
    static int[] dis = new int[510];
    static boolean[] vis = new boolean[510];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = he[a];
        he[a] = idx++;
    }

}
