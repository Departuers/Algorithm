package graph.最小生成树拓展;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/108190012
 * 农夫约翰要把他的牛奶运输到各个销售点。
 * 运输过程中，可以先把牛奶运输到一些销售点，再由这些销售点分别运输到其他销售点。
 * 运输的总距离越小，运输的成本也就越低。
 * 低成本的运输是农夫约翰所希望的。
 * 不过，他并不想让他的竞争对手知道他具体的运输方案，所以他希望采用费用第二小的运输方案而不是最小的。
 * 现在请你帮忙找到该运输方案。
 * 如果两个方案至少有一条边不同，则我们认为是不同方案；
 * 费用第二小的方案在数值上一定要严格小于费用最小的方案；
 * 答案保证一定有解；
 * 输入格式
 * 第一行是两个整数 N,M，表示销售点数和交通线路数；
 * 接下来 M 行每行 3 个整数 x,y,z，表示销售点 x 和销售点 y 之间存在线路，长度为 z。
 * 输出格式
 * 输出费用第二小的运输方案的运输总距离。
 * 数据范围
 * 1≤N≤500,
 * 1≤M≤10^4,
 * 1≤z≤10^9,
 * 数据中可能包含重边。
 * 输入样例：
 * 输入样例：
 * 4 4
 * 1 2 100
 * 2 4 200
 * 2 3 250
 * 3 4 100
 * 输出样例：
 * 450
 */
public class 牛奶运输 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, w;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w = sc.nextInt();
            edge.add(new node(a, b, w));
        }
        Collections.sort(edge);
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        long sum = 0;
        for (int i = 0; i < m; i++) {
            a = find(edge.get(i).a);
            b = find(edge.get(i).b);
            w = edge.get(i).w;
            if (a != b) {
                p[a] = b;
                sum += w;
                add(a, b, w);
                add(b, a, w);
                edge.get(i).isShu = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            dfs(i, -1, 0, dis[i]);
        }
        long res = (long) 1e18;
        for (int i = 0; i < m; i++) {
            if (!edge.get(i).isShu) {//遍历非树边
                a = edge.get(i).a;
                b = edge.get(i).b;
                w = edge.get(i).w;
                if (w > dis[a][b]) {
                    res = Math.min(res, sum + w - dis[a][b]);
                }
            }
        }
        System.out.println(res);
    }

    /**
     * 枚举任意两点路径的最大边权的那条边,由dis[a,b]表示a,b两点路径的最大边权的那条边
     *
     * @param u    当前点
     * @param fa   父节点
     * @param maxd 最大边权
     * @param d    结果记录
     */
    static void dfs(int u, int fa, int maxd, int[] d) {
        d[u] = maxd;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                dfs(j, u, Math.max(maxd, w[i]), d);
            }
        }
    }

    /**
     * 求任意两点路径中的单条边权最大值,和次大值
     *
     * @param u  当前节点
     * @param fa 父节点,从哪来的
     * @param r1 最大值
     * @param r2 次大值
     * @param s1 记录最大值
     * @param s2 记录次大值
     */
    static void dubbo(int u, int fa, int r1, int r2, int[] s1, int[] s2) {
        s1[u] = r1;
        s2[u] = r2;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j != fa) {
                int t1 = r1, t2 = r2;//结合当前边的边权修改最大值,最小值
                if (w[i] > t1) {//当前边权比最大值还大
                    t2 = t1;
                    t1 = w[i];
                } else if (w[i] > t2 && w[i] != t1) {
                    //当前边权小于最大值,大于次大值,但不可以与最大值相等,求的是严格次大值
                    t2 = w[i];
                }
                dubbo(j, u, t1, t2, s1, s2);
            }
        }
    }


    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
    }

    static int find(int a) {
        if (p[a] != a) return p[a] = find(p[a]);
        return a;
    }

    static class node implements Comparable<node> {
        int a, b, w;
        boolean isShu;

        public node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(node node) {
            return w - node.w;
        }
    }

    static int n, m, N = 510, cnt = 1, M = 10010;
    static ArrayList<node> edge = new ArrayList<node>();
    static int[] p = new int[N];//并查集
    static int[][] dis = new int[N][N];//两点路径中单条边权最大值,参见dfs
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static int[][] m1 = new int[N][N];//两点路径中单条边权最大值,用dubbo方法求解
    static int[][] m2 = new int[N][N];//两点路径中单条边权次大值
}
