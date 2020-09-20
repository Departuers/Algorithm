package graph.单源最短路;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 作为城市的紧急救援团队负责人，你将获得一张你所在国家的特殊地图。
 * 该地图显示了一些通过道路连接的分散城市，道路是双向的。
 * 地图上标出了每个城市的救援队数量以及每对城市之间的每条道路的长度。
 * 当其他城市发出紧急求援信息时，你的工作是尽快带领你的士兵前往该地点，同时，在途中尽可能多地调动救援帮手。
 * 输入格式
 * 第一行包含四个整数 N，表示城市数量（城市编号从 0 到 N−1），M 表示道路数量，C1 表示你当前所在的城市编号，C2 表示发出紧急求援信息的城市编号。
 * 第二行包含 N 个整数，其中第 i 个整数表示城市 i 的救援队数量。
 * 接下来 M 行，每行包含三个整数 c1,c2,Li，表示城市 c1 和城市 c2 之间存在一条道路相连，道路长度为 Li。
 * 数据保证 C1 和 C2 之间至少存在一条路径相连。
 * 输出格式
 * 共一行，两个整数，第一个整数表示 C1 和 C2 之间最短路的数量，第二个整数表示走最短路的情况下，能聚集到的救援队最大数量。
 * 数据范围
 * 2≤N≤500,
 * 1≤M≤600,
 * 1≤Li≤200,
 * 每个城市包含的救援人员数量不超过 200。
 * 输入样例：
 * 5 6 0 2
 * 1 2 1 5 3
 * 0 1 1
 * 0 2 2
 * 0 3 1
 * 1 2 1
 * 2 4 1
 * 3 4 1
 * 输出样例：
 * 2 4
 */
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
