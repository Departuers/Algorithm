package graph.负环;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
 * 请你判断图中是否存在负权回路。
 * 输入格式
 * 第一行包含整数n和m。
 * 接下来m行每行包含三个整数x，y，z，表示点x和点y之间存在一条有向边，边长为z。
 * 输出格式
 * 如果图中存在负权回路，则输出“Yes”，否则输出“No”。
 * 数据范围
 * 1≤n≤2000,
 * 1≤m≤10000,
 * 图中涉及边长绝对值均不超过10000。
 * 输入样例：
 * 3 3
 * 1 2 -1
 * 2 3 4
 * 3 1 -4
 * 输出样例：
 * Yes
 * 分析：
 * 在Bellman-Ford算法中，我们知道，经过最大n-1轮松弛操作，
 * 从起点到所有点的最短距离就被确定下了。而图中若存在负权回路，
 * 则每次通过该负权回路来松弛距离时，最短距离都会减小，
 * 是不存在最短路径的。所以可以在spfa算法中用cnt数组存储当前点最短路径经过的边数，
 * 一旦经过了n条边，则说明经过了n + 1个点，
 * 而图中仅有n个点，该最短路径经过了一个点两次，所以必然存在负权回路。
 */
public class spfa判断负环 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int x, y, z;
        for (int i = 0; i < m; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            add(x, y, z);
        }
        if (spfa()) System.out.println("YES");
        else System.out.println("NO");
        System.out.println(Arrays.toString(dis));
    }

    static boolean spfa() {
        // 不需要初始化dist数组
        // 原理：如果某条最短路径上有n个点（除了自己），那么加上自己之后一共有n+1个点，由抽屉原理一定有两个点相同，所以存在环。
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++) {
            q.add(i);//假设超级源点连接每个点权值都为0
            st[i] = true;
        }
        int t = 0, x;
        while (!q.isEmpty()) {
            x = q.poll();
            st[x] = false;
            for (int i = he[x]; i != 0; i = ne[i]) {
                t = e[i];
                if (dis[t] > dis[x] + w[i]) {
                    count[t] = count[x] + 1;
                    dis[t] = dis[x] + w[i];
                    if (count[t] >= n) return true;
                    if (!st[t]) {
                        q.add(t);
                        st[t] = true;
                    }
                }
            }
        }
        return false;
    }

    static int[] count = new int[100005];
    static int[] dis = new int[100005];
    static boolean[] st = new boolean[100005];
    static int[] he = new int[100005];
    static int[] w = new int[200005];
    static int[] ne = new int[200005];
    static int[] e = new int[200005];

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, m, cnt = 1;
}
