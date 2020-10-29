package graph.复合单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acwing.com/file_system/file/content/whole/index/content/533831/
 * https://www.cnblogs.com/wulichenai/p/12694599.html
 * https://www.cnblogs.com/gzh-red/p/10999939.html
 * 从a到b,路径的长度定义为第k+1大值
 * 二分:定义在[0,1000001]区间性质为
 * 对于区间中的某一个点x
 * 求出1~N中最少经过几条长度大于x的边,假设最少经过y条,
 * (最少经过多少条大于x的边的数量是否小于等于k)
 * 把边长大于x的看做1,否则看做0,
 * 用双端队列bfs
 */
public class 通信线路 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        int a, b, c;
        while (m-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        int l = 0, r = (int) (1e6 + 1);
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        if (r == 1e6 + 1) r = -1;
        System.out.println(r);
    }


    private static boolean check(int bound) {
        Arrays.fill(st, false);
        Arrays.fill(dis, inf);
        dis[1] = 0;
        q.add(1);
        while (!q.isEmpty()) {
            int t = q.poll();
            if (st[t]) continue;//双端队列类比Dijkstra,每次出队可以确定最小值,
            st[t] = true;//每个点不止入队一次
            for (int i = he[t]; i != 0; i = ne[i]) {
                int j = e[i], v = w[i] > bound ? 1 : 0;
                if (dis[j] > dis[t] + v) {
                    dis[j] = dis[t] + v;
                    if (v == 0) q.addFirst(j);
                    else q.addLast(j);
                }
            }
        }
        return dis[n] <= k;
    }

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int inf = 0x3f3f3f3f;
    static int n, m, N = 1010, M = 20010, cnt = 1, k;
    static int[] he = new int[N];
    static int[] ne = new int[M];
    static int[] w = new int[M];
    static int[] e = new int[M];
    static int[] dis = new int[N];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static boolean[] st = new boolean[N];

}
