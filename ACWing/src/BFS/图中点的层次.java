package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环。
 * 所有边的长度都是1，点的编号为1~n。
 * 请你求出1号点到n号点的最短距离，如果从1号点无法走到n号点，输出-1。
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含两个整数a和b，表示存在一条从a走到b的长度为1的边。
 * 输出格式
 * 输出一个整数，表示1号点到n号点的最短距离。
 * 数据范围
 * 1≤n,m≤10^5
 * 输入样例：
 * 4 5
 * 1 2
 * 2 3
 * 3 4
 * 1 3
 * 1 4
 * 输出样例：
 * 1
 */
public class 图中点的层次 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            add(sc.nextInt(), sc.nextInt());
        }
        dis[n] = -1;
        bfs(1);
        System.out.println(dis[n]);
    }

    static int[] head = new int[(int) (1e5 + 10)];
    static int[] ne = new int[(int) (1e5 + 10)];
    static int[] e = new int[(int) (1e5 + 10)];
    static int[] dis = new int[(int) (1e5 + 10)];
    static boolean[] vis = new boolean[(int) (1e5 + 10)];
    static int cnt = 1, n, m;
    static Queue<Integer> q = new LinkedList<Integer>();

    static void add(int u, int v) {
        e[cnt] = v;
        ne[cnt] = head[u];
        head[u] = cnt++;
    }

    static void bfs(int u) {
        vis[u] = true;
        dis[u] = 0;
        q.add(u);
        int v, w;
        while (!q.isEmpty()) {
            v = q.poll();
            for (int i = head[v]; i != 0; i = ne[i]) {
                w = e[i];
                if (!vis[w]) {
                    vis[w] = true;
                    q.add(w);
                    dis[w] = dis[v] + 1;
                    if (w == n) return;
                    //如果找到了就直接返回
                }
            }
        }
    }

}
