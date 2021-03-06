package graph.欧拉路径;

import java.util.Scanner;

/**
 * https://blog.csdn.net/njuptACMcxk/article/details/107290559
 * 先给个T，T=1就代表数据是无向图，T=2就代表数据是有向图
 * 然后给n, m，表示有n个结点，m条边。
 * 之后判断有没有欧拉回路，没有就输出"NO"，
 * 有就输出"YES"并输出一条路径
 * 注意：有重边跟自环！
 * 输入样例1：
 * 1
 * 3 3
 * 1 2
 * 2 3
 * 1 3
 * 1
 * 2
 * 3
 * 4
 * 5
 * 输出样例1：
 * YES
 * 1 2 -3
 * 遍历欧拉回路：
 * <p>
 * 从出度不为0的点开始进入搜索。
 * 注意，先深搜到底，再将点加入队列。
 * 最后倒序输出队列，得到的欧拉路径。
 * 优化：
 * 由于每条边仅需遍历一次，为了避免很多自环出现在同一个点上的情况，由于每条边仅需遍历一次，
 * 为了避免很多自环出现在同一个点上的情况，
 * 我们每遍历一条边就跳过一条边(删除)，体现在将邻接表表头后移，
 * 另外，无向图建图时，每组边的编号依次为(0,1)、(2,3)、...(2×m−2,2×m−1)
 * 为了删除某条边的反向边，可以对该边的编号异或1，如0^1=1，2^1=3。
 */
public class 欧拉回路 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.nextInt();
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            if (type == 1) add(b, a);
            din[b]++;
            dout[a]++;
        }
        if (type == 1) {
            for (int i = 1; i <= n; i++) {
                if ((din[i] + din[i] & 1) == 1) {//对于无向图,如果入度+出度是奇数就输出无解
                    System.out.println("No");
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {//如果是有向图,判断入度是否等于出度
                if (din[i] != dout[i]) {
                    System.out.println("NO");
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (h[i] != 0) {
                dfs(i);
                break;
            }
        }
        if (cnt < m) System.out.println("NO");
        for (int i = cnt; i != 0; i--) {
            System.out.println(ans[i]);
        }
    }

    static void dfs(int u) {
        for (int i = h[u]; i != 0; i = ne[i]) {
            if (used[i]) {
                h[u] = ne[i];
                continue;
            }
            used[i] = true;//必须加
            h[u] = ne[i];
            if (type == 1) used[i ^ 1] = true;//对于无向图,标记反向边
            dfs(e[i]);
            if (type == 1) {

            }
        }
    }

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int n, m, N = 100010, M = 400010, idx = 1, type, cnt;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] ans = new int[M / 2];
    static int[] din = new int[N];
    static int[] dout = new int[N];
    static boolean[] used = new boolean[N];
}
