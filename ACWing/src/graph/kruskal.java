package graph;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 给定一个n个点m条边的无向图，图中可能存在重边和自环，边权可能为负数。
 * 求最小生成树的树边权重之和，如果最小生成树不存在则输出impossible。
 * 给定一张边带权的无向图G=(V, E)，其中V表示图中点的集合，E表示图中边的集合，n=|V|，m=|E|。
 * 由V中的全部n个顶点和E中n-1条边构成的无向连通子图被称为G的一棵生成树，
 * 其中边的权值之和最小的生成树被称为无向图G的最小生成树。
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来m行，每行包含三个整数u，v，w，表示点u和点v之间存在一条权值为w的边。
 * 输出格式
 * 共一行，若存在最小生成树，则输出一个整数，表示最小生成树的树边权重之和，
 * 如果最小生成树不存在则输出impossible。
 * 数据范围
 * 1≤n≤10^5,
 * 1≤m≤2∗10^5,
 * 图中涉及边的边权的绝对值均不超过1000。
 * 输入样例：
 * 4 5
 * 1 2 1
 * 1 3 2
 * 1 4 3
 * 2 3 2
 * 3 4 4
 * 输出样例：
 * 6
 * 分析：
 * kruskal算法的时间复杂度为O(mlogm)，用于求解稀疏图的最小生成树问题，
 * 而prim算法用于解决稠密图的最小生成树问题。kruskal算法是基于贪心思想的，
 * 每次选取权重最小的边加入最小生成树，并且需要保证新加入的边不会构成环。
 * kruskal算法一般使用并查集实现，具体算法的流程就是先对各个边按边权从小到大排序，
 * 按照边权从小到大选取边加入最小生成树的集合，判断新加入的边是否会构成环，
 * 只需要判断下边的两个顶点是否在同一集合中即可，
 * 在则说明边的两个顶点均已加入并查集中，不可再添加边了。
 */
public class kruskal {
    public static void main(String[] args) throws IOException {
        PriorityQueue<node> q = new PriorityQueue<node>();
        //Scanner sc = new Scanner(System.in);
        n = nextInt();
        m = nextInt();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            q.add(new node(a, b, c));
        }
        int res = 0;
        int cnt = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            if (!is(p.x, p.y)) {
                res += p.w;
                union(p.x, p.y);
                cnt++;
            }
        }
        if (cnt==n-1)
        System.out.println(res);
        else System.out.println("orz");
    }

    static boolean is(int p, int q) {
        return find(p) == find(q);
    }

    static class node implements Comparable<node> {
        int x, y, w;

        public node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(node node) {
            return w - node.w;
        }
    }

    static int n, m;

    static int[] par = new int[200005];

    static {
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
    }

    static void union(int a, int b) {
        int aroot = find(a);
        int broot = find(b);
        if (aroot != broot)
            par[aroot] = broot;
    }

    static int find(int x) {
        while (x != par[x]) {
            par[x] = par[par[x]];
            x = par[x];
        }
        return x;
    }
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}
