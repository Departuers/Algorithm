package graph.单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/SWEENEY_HE/article/details/81413683
 * 2
 * 5
 * 1 4
 * 2 3 4 5
 * out
 * 1
 * 样例输出
 * 3 7
 * 6 7
 * 4 7 3 6
 * 2 1 3 5
 * out
 * 2
 * 建图,问题,
 * 2 3 4 5可以看做2-3  2-4  2-5  3-4  3-5 4-5
 * 显然:2可以直接到3,2也可以直接到4,2也可以直接到5
 * 边权都为1可以使用bfs做
 * 则显然求最小换乘次数,转换成求最短路径问题
 */
public class 最优乘车 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine();//换行
        int[] stop = new int[510];
        for (int t = 0; t < m; t++) {
            String[] s2 = sc.nextLine().split(" ");
            for (int i = 0; i < s2.length; i++) {
                stop[i] = Integer.parseInt(s2[i]);
            }
            for (int i = 0; i < s2.length - 1; i++)
                for (int j = i + 1; j < s2.length; j++)
                    g[stop[i]][stop[j]] = 1;
        }
        bfs();
        if (dist[n] == Integer.MAX_VALUE / 2) System.out.println("NO");
        else
            System.out.println(Math.max(dist[n] - 1, 0));
    }

    static int[][] g = new int[510][510];
    static int[] dist = new int[510];

    static int n, m;

    static void bfs() {
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(1);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = 1; i <= n; i++) {
                if (g[t][i] == 1 && dist[i] > dist[t] + 1) {
                    dist[i] = dist[t] + 1;
                    q.add(i);
                }
            }
        }
    }
}
