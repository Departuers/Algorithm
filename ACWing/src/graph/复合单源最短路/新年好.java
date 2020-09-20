package graph.复合单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_43256272/article/details/103392889
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
        for (int i = 0; i < 6; i++) {
            spfa(source[i], dis[i]);
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
            if (!vis[i]) {
                int next = source[i];
                vis[i] = true;
                res = Math.min(res, dfs(u + 1, i, dist + dis[start][next]));
                vis[i] = false;
            }
        }
        return res;
    }

    //起点是s,把单源最短路放进dis[i]
    private static void spfa(int s, int[] dis) {
        q.add(s);
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        Arrays.fill(vis, false);
        dis[s] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            vis[t] = false;
            for (int i = he[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    if (!vis[j]) {
                        vis[j] = true;
                        if (!q.isEmpty() && dis[j] < dis[q.peekFirst()]) q.addFirst(j);
                        else q.add(j);
                    }
                }
            }
        }
    }


    static int[] source = new int[6];

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
    static int[][] dis = new int[6][2560];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, cnt = 1;
}
