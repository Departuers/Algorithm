package graph.最小生成树;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.acwing.com/activity/content/code/content/308367/
 * 有一些必选边,一些可选边组成最小生成树
 * 也是用Kruskal算法,
 * 精髓在于Kruskal进行到一半,也是正确的
 */
public class 联络员 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i <= n; i++) {
            par[i] = i;
        }
        PriorityQueue<node> q = new PriorityQueue<node>();
        int res = 0;
        int t, a, b, c;
        for (int i = 0; i < m; i++) {
            t = sc.nextInt();
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            if (t == 1) {
                res += c;
                union(a, b);
            } else {
                q.add(new node(a, b, c));
            }
        }
        while (!q.isEmpty()) {
            node p = q.poll();
            if (!is(p.x, p.y)) {
                union(p.x, p.y);
                res += p.w;
            }
        }
        System.out.println(res);
    }

    static int[] par = new int[1100];
    static int n, m;

    static void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if (yroot != xroot) par[xroot] = yroot;
    }

    static int find(int x) {
        while (x != par[x]) {
            par[x] = par[par[x]];
            x = par[x];
        }
        return x;
    }

    static boolean is(int x, int y) {
        return find(x) == find(y);
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

}
