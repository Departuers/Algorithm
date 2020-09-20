package graph.单源最短路;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/chrisblogtk/article/details/51099957?utm_source=blogxgwz7
 * 战争时期，前线有n 个哨所，每个哨所可能会与其他若干个哨所之间有通信联系。信使负责 在哨所之间传递信息，
 * 当然，这是要花费一定时间的（以天为单位）。指挥部设在第一个哨所。当指挥部下达一个命令后，
 * 指挥部就派出若干个信使向与指挥部相连的哨所送信。当一个哨所接到信后，
 * 这个哨所内的信使们也以同样的方式向其他哨所送信。直至所有n个哨所全部接到命令后，
 * 送信才算成功。因为准备充足，每个哨所内都安排了足够的信使（如果一个哨所与其他k个哨所有通信联系的话，这个哨所内至少会配备k个信使）。
 * 现在总指挥请你编一个程序，计算出完成整个送信过程最短需要多少时间。
 * 第 1 行有两个整数 n 和 m，中间用 1 个空格隔开，分别表示有 n 个 哨所和m条通信线路。1<=n<=100。
 * 第 2 至 m+1 行：每行三个整数 i、j、k，中间用 1 个空格隔开，表示第 i 个和第 j 个哨所 之间存在通信线路，且这条线路要花费k 天。
 * 仅一个整数，表示完成整个送信过程的最短时间。如果不是所有的 哨所都能收到信，就输出-1。
 * 对于每个点的时间等于他到指挥部的最短距离
 * 1号点是指挥部
 * 如果有点无法到达输出-1
 * 然后最短距离的最大值为最大
 * 4 4
 * 1 2 4
 * 2 3 7
 * 2 4 1
 * 3 4 6
 * out:
 * 11
 */
public class 信使 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < 110; i++) {
            Arrays.fill(g[i], Integer.MAX_VALUE / 3);
        }
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            g[a][b] = g[b][a] = Math.min(g[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (g[1][i] == Integer.MAX_VALUE / 2) {
                res = -1;
                break;
            } else res = Math.max(res, g[1][i]);
        }
        System.out.println(res);
    }

    static int[][] g = new int[110][110];
    static int n, m;
}
