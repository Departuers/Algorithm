package graph.shortestPath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 5 6
 * 1 2 7 5
 * 1 3 4 2
 * 2 4 -1 10
 * 2 5 2 4
 * 3 4 10 1
 * 4 5 8 5
 * 1 5 10
 * <p>
 * 5 6
 * 1 2 7 5
 * 1 3 4 2
 * 2 4 -1 10
 * 2 5 2 4
 * 3 4 10 1
 * 4 5 8 5
 * 1 5 4
 * <p>
 * Sample Output
 * Case 1:
 * maximum height = 7
 * length of shortest route = 20
 * <p>
 * Case 2:
 * maximum height = 4
 * length of shortest route = 8
 */
public class HDoj2962运货 {
    static class node {
        int v, w, h, next;

        public node(int v, int w, int h, int next) {
            this.v = v;
            this.w = w;
            this.h = h;
            this.next = next;
        }
    }

    static node[] edge = new node[1005];
    static int[] pre = new int[1005];
    static int[] dis = new int[1005];
    static boolean[] vis = new boolean[1005];
    static int v, e;

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        v = sc.nextInt();
        e = sc.nextInt();
        int s, t, w, h;
        for (int i = 0; i < e; i++) {
            s = sc.nextInt();
            t = sc.nextInt();
            h = sc.nextInt();
            if (h == -1) h = Integer.MAX_VALUE;
            w = sc.nextInt();
            add(s, t, w, h);
        }
        int u = sc.nextInt();//卡车出发城市编号
        int v = sc.nextInt();//卡车到达城市编号
        int zh = sc.nextInt();//卡车最大载物高度
        int left = 0, right = zh;
        int mid = 0, res = Integer.MAX_VALUE;
        while (left < right) {
            mid = (left + right + 1) / 2;
            spfa(u, mid);
            if (dis[v] != Integer.MAX_VALUE) {
                left = mid;
                res = dis[v];
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
        System.out.println(res);

    }

    static void spfa(int u, int limit) {
        int v, w, i;
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[u] = 0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.push(u);
        vis[u] = true;
        while (!q.isEmpty()) {
            u = q.poll();
            vis[u] = false;
            for (i = pre[u]; i != 0; i = edge[i].next) {
                if (edge[i].h < limit) continue;
                w = edge[i].w;
                v = edge[i].v;
                if (dis[u] != Integer.MAX_VALUE && dis[v] > w + dis[u]) {

                    dis[v] = w + dis[u];
                    if (!vis[v]) {
                        if (!q.isEmpty() && dis[w] < dis[q.peekFirst()]) q.addFirst(v);
                        else q.add(v);
                        vis[v] = true;
                    }
                }
            }
        }
    }

    static int cnt = 1;//只能从1开始!!!

    static void add(int u, int v, int w, int h) {
        edge[cnt] = new node(v, w, h, pre[u]);
        pre[u] = cnt++;
        edge[cnt] = new node(u, w, h, pre[v]);
        pre[v] = cnt++;
    }
}
