package DFS.floodfill;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104671983
 * 显然bfs过程中寻找,
 * 如果该联通块没有比当前位置数更小的就是山谷
 * 如果该联通块没有比当前位置数更大的就是山峰
 * 打上标记,如果都有,则是山坡不计算...
 * 遍历的是什么东西,考虑进入队列的是什么东西
 */
public class 山峰和山谷 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        int peak = 0, valley = 0;//山峰,山谷
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j]) {
                    High = false;
                    low = false;
                    bfs(i, j);
                    if (!High && !low) ;
                    else if (!High) peak++;
                    else if (!low) valley++;
                }
            }
        }
        System.out.println(peak);
        System.out.println(valley);
    }

    static void bfs(int x, int y) {
        q.add(x * n + y);
        int t = g[x][y];
        while (!q.isEmpty()) {
            int p = q.poll();
            x = p / n;
            y = p % n;
            vis[x][y] = true;
            for (int i = -1; i < 2; i++) {//八联通
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) continue;
                    int a = x + i, b = y + j;
                    if (a < 0 || a >= n || b < 0 || b >= n) continue;
                    if (g[x][y] != g[a][b]) {
                        if (g[a][b] > g[x][y]) High = true;
                        else if (g[a][b] < g[x][y]) low = true;
                    } else if (!vis[a][b]) {
                        //这一步最重要,进入队列的都是满足未访问过该节点且与拓展之前的节点值相同
                        //一遍bfs会把一个连通块权值相同的连通块都遍历到
                        q.add(a * n + b);
                    }
                }
            }
        }
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static boolean High = false, low = false;
    static int n;
    static int[][] g = new int[1010][1010];
    static boolean[][] vis = new boolean[1010][1010];
}
