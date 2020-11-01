package graph.最小生成树;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/107899568
 * 城市C是一个非常繁忙的大都市，城市中的道路十分的拥挤，于是市长决定对其中的道路进行改造。
 *
 * 城市C的道路是这样分布的：
 *
 * 城市中有 n 个交叉路口，编号是 1∼n，有些交叉路口之间有道路相连，两个交叉路口之间最多有一条道路相连接。
 *
 * 这些道路是 双向 的，且把所有的交叉路口直接或间接的连接起来了。
 *
 * 每条道路都有一个分值，分值越小表示这个道路越繁忙，越需要进行改造。
 *
 * 但是市政府的资金有限，市长希望进行改造的道路越少越好，于是他提出下面的要求：
 *
 * 1．改造的那些道路能够把所有的交叉路口直接或间接的连通起来。
 *
 * 2．在满足要求1的情况下，改造的道路尽量少。
 *
 * 3．在满足要求1、2的情况下，改造的那些道路中分值最大值尽量小。
 *
 * 作为市规划局的你，应当作出最佳的决策，选择那些道路应当被修建。
 *
 * 输入格式
 *
 * 第一行有两个整数 n,m 表示城市有 n 个交叉路口，m 条道路。
 *
 * 接下来 m 行是对每条道路的描述，每行包含三个整数u,v,c 表示交叉路口 u 和 v 之间有道路相连，分值为 c。
 *
 * 输出格式
 *
 * 两个整数 s,max，表示你选出了几条道路，分值最大的那条道路的分值是多少。
 *
 * 数据范围
 *
 * 1≤n≤300,
 * 1≤m≤8000,
 * 1≤c≤10000
 *
 * 输入样例：
 *
 * 4 5
 * 1 2 3
 * 1 4 5
 * 2 4 7
 * 2 3 6
 * 3 4 8
 * 输出样例：
 * 3 6
 * 分析：
 * 本题求一个图的最小生成树中边权最大的边最小是多少，本质是考察对于kruskal算法的理解。
 * 要想构造一个最小生成树，就要选出n - 1条边，为了使得生成树中边权最大的边最小，
 * 首选的应该是边权最小的n-1个边，但是这n-1个边未必能构成一棵生成树，
 * 所以需要从大到小删掉一些不会影响连通性的边，然后再从第n大的边开始尝试加到生成树中，
 * 当构造出一棵完整的生成树时，此时用到的最大边权就最小，这个过程与kruskal算法的过程如出一辙，
 * 所以kruskal算法求出的最小生成树不仅树的权值之和最小，最大边权也是最小的
 * 求出无向图最小生成树中,最长边权的最小值
 * 最小生成树的权值最大的边,对应Kruskal就是最后取到的那条边
 */
public class 繁忙的都市 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        n = sc.nextInt();
        m = sc.nextInt();
        PriorityQueue<node> q = new PriorityQueue<node>();

        for (int i = 0; i < m; i++) {
            q.add(new node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        int ans = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            if (!is(p.x, p.y)) {
                union(p.x, p.y);
                ans = p.w;
            }
        }
        System.out.println(n - 1 + " " + ans);
    }

    static void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if (yroot != xroot) par[xroot] = yroot;
    }

    static boolean is(int x, int y) {
        return find(x) == find(y);
    }

    static int find(int x) {
        while (x != par[x]) {
            par[x] = par[par[x]];
            x = par[x];
        }
        return x;
    }

    static int[] par = new int[1010];

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

}
