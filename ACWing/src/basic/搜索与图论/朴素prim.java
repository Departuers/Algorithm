package basic.搜索与图论;

import java.util.Arrays;
import java.util.Scanner;

/**
 * dis[所有点]初始化为正无穷
 * S当前已经在连通块中的所有点
 * for(i=0;i<n;i++){
 * 1. 找到集合外距离最近的点t
 * 2. 用t来更新其他点到集合的距离,点到集合的距离定义为,点到集合中任意一点长度最小的那条边的权值
 * 3. st[t]=true 把t加到集合中去
 * }
 */
public class 朴素prim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            Arrays.fill(g[i], 0x3f3f3f3f);
            g[i][i] = 0;
        }
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        while (m-- > 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            g[a][b] = g[b][a] = Math.min(g[a][b], c);
        }
        int t = prim();
        if (t == inf) System.out.println("impossible");
        else System.out.println(t);
    }

    static int N = 510, n, m, inf = 0x3f3f3f3f;
    static int[] dis = new int[N];
    static int[][] g = new int[N][N];
    static boolean[] st = new boolean[N];

    static int prim() {
        Arrays.fill(dis, inf);
        int res = 0, t;
        for (int i = 0; i < n; i++) {
            t = -1;
            for (int j = 1; j <= n; j++) {
                if (!st[j] && (t == -1 || dis[t] > dis[j])) t = j;
            }//找到点到集合距离最近的那个点

            if (i != 0 && dis[t] == inf) return inf;
            if (i != 0) res += dis[t];
            st[t] = true;//加入集合

            for (int j = 1; j <= n; j++) {//更新点到集合的距离
                dis[j] = Math.min(dis[j], g[t][j]);
            }


        }
        return res;
    }
}
