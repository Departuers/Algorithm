package DFS.启发式搜索;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://www.cnblogs.com/liuwenyao/p/10162462.html
 * https://www.acwing.com/problem/content/180/
 * 使用A*求第K短路
 * 所有从起点到终点的路径中的第k短路,路径允许进过
 * 给定一张N个点（编号1,2…N），M条边的有向图，求从起点S到终点T的第K短路的长度，路径允许重复经过点或边。
 * 注意： 每条最短路中至少要包含一条边。
 * 输入格式
 * 第一行包含两个整数N和M。
 * 接下来M行，每行包含三个整数A,B和L，表示点A与点B之间存在有向边，且边长为L。
 * 最后一行包含三个整数S,T和K，分别表示起点S，终点T和第K短路。
 * 输出格式
 * 输出占一行，包含一个整数，表示第K短路的长度，如果第K短路不存在，则输出“-1”。
 * 数据范围
 * 1≤S,T≤N≤1000,
 * 0≤M≤105,
 * 1≤K≤1000,
 * 1≤L≤100
 * 输入样例：
 * 2 2
 * 1 2 5
 * 2 1 4
 * 1 2 2
 * 输出样例：
 * 14
 * 当终点从pq中第一次取出,那就是最小的,第二次就是,第二小距离,...第n次就是第n小距离
 * 证明难证...
 */
public class 第k短路 {

    public static void main(String[] args) {

    }

    static int N = 1010, M = (int) (2e5 + 10), n, m, idx = 1, S, T, K;
    static int[] h = new int[N];
    static int[] w = new int[M];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];
    static int[] rh = new int[N];

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

    static class tem implements Comparable<tem> {
        int dis;
        node t;

        public tem(int dis, node t) {
            this.dis = dis;
            this.t = t;
        }

        @Override
        public int compareTo(tem tem) {
            return dis - tem.dis;
        }
    }

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void dijkstra() {
        PriorityQueue<node> q = new PriorityQueue<node>();
        q.add(new node(0, T));
        Arrays.fill(dist, 1 << 30);
        dist[T] = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            int v = p.to;
            if (st[v]) continue;
            st[v] = true;
            for (int i = rh[v]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[v] + w[i]) {
                    dist[j] = dist[v] + w[i];
                    q.add(new node(dist[j], j));
                }
            }
        }
    }

    static int Astar() {
        PriorityQueue<tem> q = new PriorityQueue<tem>();
        q.add(new tem(dist[S], new node(0, S)));
        int cnt = 0;
        while (!q.isEmpty()) {
            tem p = q.poll();
            int v = p.t.to, dis = p.t.dis;
            if (v == T) {
                cnt++;
            }
            if (cnt == K) return dis;
            for (int i = h[v]; i != 0; i = ne[i]) {
                int j = e[i];
                q.add(new tem(dis + w[i] + dist[j], new node(1, 1)));
            }
        }
        return -1;
    }

}
