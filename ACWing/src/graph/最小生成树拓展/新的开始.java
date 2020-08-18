package graph.最小生成树拓展;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/103211427
 * 本来不是最小生成树问题
 * 把在该节点新建一条边,看做向虚拟源点(0号点)连一条边
 * 虚拟源点,是个非常重要的技巧,这样就变成了最小生成树问题
 * 图论问题,一定要先想到虚拟源点技巧
 */
public class 新的开始 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            g[0][i] = g[i][0] = sc.nextInt();
        }//把0号点看做超级源点
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        System.out.println(prim());
    }

    static int[][] g = new int[310][310];

    static int n;
    static int[] dis = new int[310];
    static boolean[] vis = new boolean[310];

    static int prim() {
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        dis[0] = 0;
        int ans = 0;
        //n+1个点,找n条边
        for (int i = 0; i <= n; i++) {
            int s = -1;
            for (int j = 0; j <= n; j++) {
                if (!vis[j] && (s == -1 || dis[j] < dis[s]))
                    s = j;
            }
            vis[s] = true;
            ans += dis[s];
            for (int j = 0; j <= n; j++) {
                dis[j] = Math.min(dis[j], g[s][j]);
            }
        }
        return ans;
    }
}
