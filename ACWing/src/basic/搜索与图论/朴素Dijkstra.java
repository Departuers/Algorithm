package basic.搜索与图论;

import java.util.Arrays;
import java.util.Scanner;

/**
 * O(n^2)
 * 适用于稠密图
 * m~n^2称为稠密图
 * m~n称为稀疏图
 * 朴素Dijkstra
 * 存在集合S,表示已经求出最近距离的点的集合,点集
 * <p>
 * dist[起点]=0  dist[其他]=正无穷
 * for i:1~n
 * t<-不在s中的距离最近的点,也就是还没有确定最短距离的点中,找出离起点最近的那个点
 * S<-t   把t加入S集合中,
 * 每次循环都可以确定一个点的最短距离
 * 用t来更新其他点的距离
 */
public class 朴素Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < 510; i++) {
            Arrays.fill(g[i], 0x3f3f3f3f);
            g[i][i] = 0;
        }
        int a, b, c;
        while (m-- > 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            g[a][b] = Math.min(g[a][b], c);//有向图
        }
        dijkstra();
        if (dis[n] != 0x3f3f3f3f)
            System.out.println(dis[n]);
        else System.out.println(-1);
    }

    static int n, m;
    static int[][] g = new int[1010][1100];//邻接矩阵存储图
    static int[] dis = new int[1010];
    static boolean[] st = new boolean[1010];

    static void dijkstra() {
        Arrays.fill(dis, 0x3f3f3f3f);
        dis[1] = 0;
        for (int i = 0; i < n; i++) {//循环n次,就可以找到n个点的最短路
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (!st[j] && (t == -1 || dis[t] > dis[j])) t = j;
            }
            st[t] = true;

            //从t扩展其他所有点,看能不能松弛,起点->t加上t到j<dis[j]就可以更新
            for (int j = 1; j <= n; j++) {
                dis[j] = Math.min(dis[j], dis[t] + g[t][j]);
            }
        }
    }
}
