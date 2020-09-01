package LCA;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107305481
 * 给出 n 个点的一棵树，多次询问两点之间的最短距离。
 * 注意：
 * 边是无向的。
 * 所有节点的编号是 1,2,…,n。
 * 输入格式
 * 第一行为两个整数 n 和 m。n 表示点数，m 表示询问次数；
 * 下来 n−1 行，每行三个整数 x,y,k，表示点 x 和点 y 之间存在一条边长度为 k；
 * 再接下来 m 行，每行两个整数 x,y，表示询问点 x 到点 y 的最短距离。
 * 树中结点编号从 1 到 n。
 * 输出格式
 * 共 m 行，对于每次询问，输出一行询问结果。
 * 数据范围
 * 2≤n≤104,
 * 1≤m≤2×104,
 * 0<k≤100,
 * 1≤x,y≤n
 * 输入样例1：
 * 2 2
 * 1 2 100
 * 1 2
 * 2 1
 * 输出样例1：
 * 100
 * 100
 * 输入样例2：
 * 3 2
 * 1 2 10
 * 3 1 15
 * 1 2
 * 3 2
 * 输出样例2：
 * 10
 * 25
 * 思路
 * tarjan离线lca，先将询问保存（查询a，b要把（a->b）和（b->a）都保存），
 * 然后每次查询的时候，将访问过的点标记一下，用并查集找到最近的根节点。
 */
public class 距离Tarjan {
    public static void main(String[] args) {
        for (int i = 1; i < p.length; i++) {
            p[i] = i;
        }
        for (int i = 0; i < query.length; i++) {
            query[i] = new ArrayList<node>();
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            if (a != b) {//如果a==b那么就是0,res初始化是0
                //a和b的距离,查询编号为i
                query[a].add(new node(b, i));
                query[b].add(new node(a, i));
            }
        }
        dfs(1, -1);
        tarjan(1);
        for (int i = 0; i < m; i++) {
            System.out.println(result[i]);
        }

    }

    static int n, m, N = 20010, M = N * 2, cnt = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[N];
    static int[] st = new int[N];
    static int[] result = new int[N];//预处理查询
    static int[] p = new int[N];
    static int[] dist = new int[N];//记录每个点到根节点的距离

    //求每个点到根节点的距离,前序遍历
    static void dfs(int u, int fa) {
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            dist[j] = dist[u] + w[i];
            dfs(j, u);
        }
    }

    static void tarjan(int u) {
        st[u] = 1;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (st[j] == 0) {
                tarjan(j);
                p[j] = u;
            }
        }
        for (node it : query[u]) {//u->y这条查询,查询编号为2
            int y = it.a, id = it.b;
            if (st[y] == 2) {
                int anc = find(y);//最近公共祖先
                result[id] = dist[u] + dist[y] - dist[anc] * 2;
                //u到根节点的距离加上y到根节点的距离减去两个最近共祖先到根节点的距离,就是两点之间的距离
            }
        }
        st[u] = 2;
    }

    static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static class node {
        int a, b;

        public node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static ArrayList<node>[] query = new ArrayList[N];
}
