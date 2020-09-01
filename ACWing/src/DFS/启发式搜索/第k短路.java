package DFS.启发式搜索;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

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
 * 在上一题八数码问题中，我们详细分析了A*算法的原理和实现。本题同样是A*算法的应用，题目中求的第K短路，要求每条路径至少要包含一条边，所以当起点与终点重合时，要去掉本身这条长度为0的自环边，
 * 执行k++操作即可。另外第k短路这里的k可以是重复的排名，
 * 比如k = 3时，那么第二短的路径有4条时，求的就是第2短的路径长度了。
 * <p>
 * 在上一题中，我们证明了A*算法中终点第一次出队时的距离就是最短路径距离，
 * 那么是不是终点第k次出队时的距离就是第k短路的距离呢，答案是肯定的，
 * 设终点t第二次出队时到起点的距离是g，到终点的估计距离是0（因为要不大于实际距离），
 * 队列中任一个顶点离起点的距离是g1，到终点的估计距离是f，因为终点出队了，
 * 所以g < g1 + f，又f小于到终点的实际距离d，
 * 故g < g1 + d = 经过这个顶点从起点到终点路径的长度，
 * 这就证明了当终点t第二次出队时路径长度已经是所有能到达终点路径中最小的了
 * （因为终点已经出队过一次了），同理可以证明t第k次出队时的距离就是第k短路距离。
 * 因为要求的倍数最短路径长度，所以松弛操作时应该将每次的距离都更新下，不论是否变小，
 * 每次被更新时到起点的距离可以直接作为队列的一个属性存入优先级队列。
 * <p>
 * 下面要考虑如何涉及估价函数，使得每个点的估计距离要小于等于到终点的实际距离，
 * 只需要从终点t倒着求一遍dijkstra算法，就可以求出每个点到终点的最短距离了，
 * 后面A*算法不论求的是第几短距离到终点的实际距离都不会小于最短距离。题目中的图是有向图，
 * 所以不仅需要建立邻接表，还要建立逆邻接表方便倒着来一遍dijkstra。实现细节并不复杂，
 * https://www.cnblogs.com/buhuiflydepig/p/11383538.html
 */
public class 第k短路 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(h, a, b, c);
            add(rh, b, a, c);//添加反向边
        }
        S = sc.nextInt();
        T = sc.nextInt();
        K = sc.nextInt();
        if (S == T) K++;
        dijkstra();
        System.out.println(Astar());
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

    static void add(int[] he, int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = he[a];
        he[a] = idx++;
    }

    //处理估价函数,从终点往前搜
    static void dijkstra() {
        PriorityQueue<node> q = new PriorityQueue<node>();
        q.add(new node(0, T));
        Arrays.fill(dist, 1 << 30);
        dist[T] = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            int v = p.to;
            if (st[v]) continue;//dij
            st[v] = true;
            for (int i = rh[v]; i != 0; i = ne[i]) {//反边
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
            if (v == T) {//记录出队次数
                cnt++;
            }
            if (cnt == K) return dis;//第k短路
            for (int i = h[v]; i != 0; i = ne[i]) {
                int j = e[i];
                q.add(new tem(dis + w[i] + dist[j], new node(dis + w[i], j)));
            }
        }
        return -1;
    }

}
