package graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的无向图，图中可能存在重边和自环，边权可能为负数。
 * 求最小生成树的树边权重之和，如果最小生成树不存在则输出impossible。
 * 给定一张边带权的无向图G=(V, E)，其中V表示图中点的集合，E表示图中边的集合，n=|V|，m=|E|。
 * 由V中的全部n个顶点和E中n-1条边构成的无向连通子图被称为G的一棵生成树，
 * 其中边的权值之和最小的生成树被称为无向图G的最小生成树。
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含三个整数u，v，w，表示点u和点v之间存在一条权值为w的边。
 * 输出格式
 * 共一行，若存在最小生成树，则输出一个整数，表示最小生成树的树边权重之和，如果最小生成树不存在则输出impossible。
 * 数据范围
 * 1≤n≤500,
 * 1≤m≤10^5,
 * 图中涉及边的边权的绝对值均不超过10000。
 * 输入样例：
 * 4 5
 * 1 2 1
 * 1 3 2
 * 1 4 3
 * 2 3 2
 * 3 4 4
 * 输出样例：
 * 6
 * 分析：
 * prim算法与dijkstra算法十分类似。都是基于点集的，
 * 每次找离点集最近的点，连上点集到该点的边。
 * 不同的是dijkstra算法用松弛操作来更新最短路径，
 * 而prim算法只需要每次选离点集最近的点，将边长加入最小生成树的权重中即可。
 * 如何寻找离点集最近的点，只需修改下dijkstra算法d数组的含义为某点向点集中连接的边的最小边的长度即可。
 * 每次加入某条边进生成树，即用选中的边的顶点更新其他连接边的最短边的长度
 */
public class prim {
    static class node implements Comparable<node> {
        int x, y, z;

        public node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.z = w;
        }

        @Override
        public int compareTo(node node) {
            return z - node.z;
        }

        @Override
        public String toString() {
            return "node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < g.length; i++) {
            Arrays.fill(g[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            g[a][b] = g[b][a] = Math.min(g[a][n], c);//处理自环边
        }
        int res = prim();
        if (res == -1) System.out.println("NO");
        else System.out.println(res);
    }

    private static int prim() {
        vis[1] = true;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (g[1][i] != Integer.MAX_VALUE) q.add(new node(1, i, g[1][i]));
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            node min = q.poll();
            if (vis[min.x] && vis[min.y]) continue;
            res += min.z;
            cnt++;
            int newV = vis[min.x] ? min.y : min.x;
            vis[newV] = true;
            for (int i = 1; i <= n; i++) {
                if (g[newV][i] != Integer.MAX_VALUE && !vis[i]) {
                    q.add(new node(newV, i, g[newV][i]));
                }
            }
        }
        if (cnt != n - 1) return -1;
        return res;
    }

    static PriorityQueue<node> q = new PriorityQueue<node>();

    static boolean[] vis = new boolean[510];
    static int n, m;
    static int[][] g = new int[510][510];

}
