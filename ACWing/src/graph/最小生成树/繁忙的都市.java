package graph.最小生成树;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.acwing.com/activity/content/code/content/308366/
 * 求出无向图最小生成树中,最长边权的最小值
 * 最小生成树必然是n-1条边,
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
        System.out.println(n-1+" "+ans);
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
