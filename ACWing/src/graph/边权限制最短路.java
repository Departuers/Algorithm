package graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你求出从1号点到n号点的最多经过k条边的最短距离，如果无法从1号点走到n号点，输出impossible。
 * 注意：图中可能 存在负权回路 。
 * 输入格式
 * 第一行包含三个整数n，m，k。
 * 接下来m行，每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
 * 输出格式
 * 输出一个整数，表示从1号点到n号点的最多经过k条边的最短距离。
 * 如果不存在满足条件的路径，则输出“impossible”。
 * 数据范围
 * 1≤n,k≤500,
 * 1≤m≤10000,
 * 任意边长的绝对值不超过10000。
 * 输入样例：
 * 3 3 1
 * 1 2 1
 * 2 3 1
 * 1 3 3
 * 输出样例：
 * 3
 */
public class 边权限制最短路 {
    static class node {
        int x, y, z;

        public node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static node[] node = new node[10005];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < m; i++) {
            node[i] = new node(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        if (blm() == -1) System.out.println("Imposttb");
        else System.out.println(dis[n]);
    }

    private static int blm() {
        Arrays.fill(dis, 0x3f3f3f3f);
        dis[1] = 0;
        for (int i = 0; i < k; i++) {
            tem = Arrays.copyOf(dis, dis.length);
            for (int j = 0; j < m; j++) {
                dis[node[j].y] = Math.min(dis[node[j].y], tem[node[j].x] + node[j].z);
            }
        }
        if (dis[n] > 0x3f3f3f3f / 4) return -1;
        return dis[n];
    }

    static int[] dis = new int[502];
    static int[] tem;
    static int n, m, k;

}
