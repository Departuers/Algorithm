package DFS.双端队列;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 超级复杂...
 * https://blog.csdn.net/qq_30277239/article/details/104719098
 * 当一个图的边权只可能是0或者1时，就可以使用双端队列BFS求解
 * 本题求从左上角到右下角要想连通的最小旋转次数，
 * 一个方格的对角线之间，如果有连线，就可以被视为这两点之间的边权长度是0，
 * 如果需要旋转才有连线，则视边权的长度为1，
 * 所以本题就转化为了一个边权只有01两种情况的最短路问题。
 * 为什么BFS可以解决边权不等的最短路问题，
 * 可以说是此时BFS的队列相当于dijkstra算法中的优先级队列，
 * 是单调的，也是由BFS队列中元素的两段性和单调性决定的。
 * dijkstra算法在非负权图都是正确的,但需要建图
 * 经典做法,双端队列
 * 使用方法:拓展的节点,边权为0插入队头,边权为1插入队尾,取只取队头
 * 转化为Dijkstra算法,把问题转化为Dijkstra算法能解决的问题
 * 队列前面的元素一定小于等于后面的元素到达源点的距离
 * bfs中队列具有两段性,单调性,
 * 以上双端队列的方式仍然具有两段性,和单调性
 * 则正确
 * 有边权为0/1的边,有可能每条边加入队列多次
 * 比如di=dj di+1>dj+0
 * 队列后面的边权可能更小
 * 由于只能走对角线,每个点的横纵坐标都会变1
 * 奇点就是横纵坐标是奇数的点,同理偶点
 * 则要求终点是偶点
 * 起点和终点奇偶性相同才可以到达
 * 起点(0,0)自然是偶点
 * <p>
 * 1
 * 3 5
 * \\/\\
 * \\///
 * /\\\\
 * out:1
 */
public class 电路维修 {
    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            for (int i = 0; i < n; i++) {
                g[i] = sc.next().toCharArray();
            }
            if (((n + m) & 1) == 1) System.out.println("NO SOLUTION");//首先，由于只能斜着走，所以横纵坐标要么同时加1，要么同时减1，
                // 要么一个加1一个减1。不论是那种，从起点（0,0）出发，能够到达的点的横纵坐标之和一定是偶数，所以当R + C是奇数时，就无解
            else {
                bfs();
                System.out.println(dist[n][m]);
            }
        }
    }


    private static void bfs() {
        for (int[] ints : dist) {
            Arrays.fill(ints, inf);
        }
        for (boolean[] w : st) {
            Arrays.fill(w, false);
        }
        q.add(new node(0, 0));
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            node e = q.poll();
            if (st[e.x][e.y]) continue;
            st[e.x][e.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = e.x + dir[i][0], ny = e.y + dir[i][1];
                if (nx < 0 || nx > n || ny < 0 || ny > m) continue;
                int ex = e.x + idx[i][0], ey = e.y + idx[i][1];//在格子里的下标,斜杠的位置
                //ex代表往下扩展的点,所经过的边在地图,也就是g数组中的位置,得到的是方向
                int w = (g[ex][ey] != op.charAt(i) ? 1 : 0);
                int d = dist[e.x][e.y] + w;//4个方向,判断这条边有没有,没有就是边权为1的边
                if (d < dist[nx][ny]) {//类似于Dijkstra的松弛,能够松弛才入队
                    dist[nx][ny] = d;
                    if (w == 0) q.addFirst(new node(nx, ny));
                    else q.addLast(new node(nx, ny));
                }
            }
        }
    }

    static String op = "\\/\\/";//4个方向
    static int[][] dir = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};//地图的位置

    static int[][] idx = {{-1, -1}, {-1, 0}, {0, 0}, {0, -1}};
    static int inf = 0x3f3f3f3f;
    static ArrayDeque<node> q = new ArrayDeque<node>();
    static int n, m, N = 510, M = N * N;
    static int[][] dist = new int[N][N];
    static char[][] g = new char[N][N];
    static boolean[][] st = new boolean[N][N];
}
