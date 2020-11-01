package graph.单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.cnblogs.com/zqzxwdm/p/5590204.html
 * 农夫John发现做出全威斯康辛州最甜的黄油的方法：糖。把糖放在一片牧场上，
 * 他知道N（1<=N<=500）只奶牛会过来舔它，这样就能做出能卖好价钱的超甜黄油。
 * 当然，他将付出额外的费用在奶牛上。
 * 农夫John很狡猾。他知道他可以训练这些奶牛，让它们在听到铃声时去一个特定的牧场。
 * 他打算将糖放在那里然后下午发出铃声，以至他可以在晚上挤奶。
 * 农夫John知道每只奶牛都在各自喜欢的牧场呆着（一个牧场不一定只有一头牛）。
 * 给出各头牛在的牧场和牧场间的路线，找出使所有牛到达的路程和最短的牧场（他将把糖放在那）。
 * 输入描述 Input Description
 * 第一行: 三个数：奶牛数N，牧场数P（2<=P<=800），牧场间道路数C(1<=C<=1450).
 * 第二行到第N+1行: 1到N头奶牛所在的牧场号.
 * 第N+2行到第N+C+1行： 每行有三个数：相连的牧场A、B，两牧场间距（1<=D<=255），当然,连接是双向的.
 * 输出描述 Output Description
 * 一行 输出奶牛必须行走的最小的距离和.
 * 3 4 5
 * 2
 * 3
 * 4
 * 1 2 1
 * 1 3 5
 * 2 3 7
 * 2 4 3
 * 3 4 5
 * out:
 * 8
 * 选一个节点作为起点使得其他节点到该节点的所有步数最少,显然
 * 多源汇最短路,n为500  Floyd O(n^3)超时
 * 使用spfa
 * O(nm)最差O(n^2m)
 * 或者使用Dijkstra
 * O(nm log n)
 */
public class 香甜的黄油 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        p = sc.nextInt();
        m = sc.nextInt();
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
        for (int i = 1; i <= p; i++) {
            ans = Math.min(ans, spfa(i));
        }
        System.out.println(ans);
    }

    static int spfa(int s) {
        int res = 0;
        Arrays.fill(dis, inf);
        Arrays.fill(vis, false);
        dis[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int p = q.poll();
            vis[p] = false;
            for (int i = he[p]; i != 0; i = ne[i]) {
                if (dis[e[i]] > dis[p] + w[i]) {
                    dis[e[i]] = dis[p] + w[i];
                    if (!vis[e[i]]) {
                        vis[e[i]] = true;
                        if (!q.isEmpty() && dis[e[i]] < dis[q.peekFirst()]) q.addFirst(e[i]);
                        else q.add(e[i]);
                    }
                }
            }
        }
        //计算有奶牛的牧场到起点的距离
        for (int i = 0; i < n; i++) {
            int j = g[i];
            if (dis[j] == inf) return inf;
            //无法到达
            res += dis[j];
        }
        return res;
    }

    static int Dijkstra(int s) {
        int res = 0;
        PriorityQueue<node> q = new PriorityQueue<node>();
        Arrays.fill(dis, inf);
        Arrays.fill(vis, false);
        dis[s] = 0;
        q.add(new node(0, s));
        while (!q.isEmpty()) {
            int p = q.poll().to;
            //pq每次取出的边,就是算出最短路径的边
            if (vis[p]) continue;
            vis[p] = true;
            for (int i = he[p]; i != 0; i = ne[i]) {
                int ed = e[i];
                if (dis[p] != inf && dis[ed] > dis[p] + w[i]) {
                    dis[ed] = dis[p] + w[i];
                    q.add(new node(ed, dis[ed]));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int j = g[i];
            if (dis[j] == inf) return inf;
            //无法到达
            res += dis[j];
        }
        return res;
    }

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
    static int[] dis = new int[2560];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static int n, m, p, cnt = 1, inf = 0x3f3f3f3f, ans = inf;
    static int[] g = new int[2523];

}
