package graph.复合单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_43256272/article/details/103392889
 * https://www.acwing.com/activity/content/code/content/141215/
 * 使用spfa+dfs
 * 复杂度 6*O(m)+5!
 * 预处理出单源最短路
 * 5!只有120不需要剪枝
 */
public class 新年好 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        source[0] = 1;//起点
        for (int i = 1; i <= 5; i++) {
            source[i] = sc.nextInt();//起点
        }
        int a, b, c;
        while (m-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        for (int i = 0; i < 6; i++) {//以source[i]作为起点,所有点的最短路
            spfa(source[i], dis[i]);//最短路记录在dis[i]中
        }
        System.out.println(dfs(1, 0, 0));
    }

    /**
     * 枚举每个坑里填什么
     *
     * @param u     当前枚举到第几个
     * @param start 当前起点
     * @param dist  当前距离
     * @return 花费
     */
    static int dfs(int u, int start, int dist) {
        if (u == 6) return dist;
        int res = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= 5; i++) {
            if (!st[i]) {
                int next = source[i];
                st[i] = true;
                res = Math.min(res, dfs(u + 1, i, dist + dis[start][next]));
                st[i] = false;
            }
        }
        return res;
    }

    //起点是s,把单源最短路放进dis[i],会被卡
    private static void spfa(int s, int[] dis) {
        q.add(s);
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        Arrays.fill(st, false);
        dis[s] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = he[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    if (!st[j]) {
                        st[j] = true;
                        if (!q.isEmpty() && dis[j] < dis[q.peekFirst()]) q.addFirst(j);
                        else q.add(j);
                    }
                }
            }
        }
    }


    static int[] source = new int[6];

    static void dijkstra(int s, int[] dis) {
        PriorityQueue<node> q = new PriorityQueue<node>();
        q.add(new node(0, s));
        dis[s] = 0;
        while (!q.isEmpty()) {
            int u = q.poll().y;
            if (st[u]) continue;
            st[u] = true;

        }
    }

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static class node implements Comparable<node> {
        int dis;
        int y;

        public node(int dis, int y) {
            this.dis = dis;
            this.y = y;
        }

        @Override
        public int compareTo(node o) {
            return dis - o.dis;
        }
    }

    static int[] he = new int[50005];
    static int[] ne = new int[16550 * 2];
    static int[] e = new int[16550 * 2];
    static int[] w = new int[16550 * 2];
    static boolean[] st = new boolean[12560];
    static int[][] dis = new int[6][52560];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, cnt = 1;
}
