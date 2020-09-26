package graph.欧拉路径;

import java.util.Scanner;

/**
 * 判断有向图是否存在欧拉路径,
 * 1.除了起点和终点其余点都入度等于出度
 * 2.是否连通(并查集)
 * 判断是否有孤立点
 */
public class 单词游戏 {
    public static void main(String[] args) {
        for (int i = 1; i < N; i++) {
            p[i] = i;
        }
        Scanner sc = new Scanner(System.in);
        while ((n = sc.nextInt()) != 0) {

        }
    }

    static int n, m, N = 30;
    static int[] p = new int[N], din = new int[N], dout = new int[N];
    static boolean[] st = new boolean[N];

    static int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
