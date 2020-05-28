package graph.最小生成树;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/weixin_43872728/article/details/105852223
 * 求最小生成森林
 * 求每一个联通块的,最小生成树
 * 使用Kruskal算法最好写,即使算法没有进行完,那么算法也是正确的
 */
public class 局域网 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        PriorityQueue<node> q = new PriorityQueue<node>();
        for (int i = 0; i < m; i++) {
            q.add(new node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        int res = 0;
        while (!q.isEmpty()) {
            node p = q.poll();
            if (!is(p.x, p.y)) union(p.x, p.y);
            else res += p.w;
        }
        System.out.println(res);
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

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot != yRoot) par[xRoot] = yRoot;
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

    static int[] par = new int[110];

    static int n, m;
}
