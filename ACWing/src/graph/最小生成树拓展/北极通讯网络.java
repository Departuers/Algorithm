package graph.最小生成树拓展;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/103211715
 * 做最小生成树,把最大的几条边用卫星连接
 * 抽象题目为:找一个最小的d值,是得所有权值大于d的边删去过后,整个图的连通块不超过k
 * 具有单调性,随着d的增加,k单调减少
 * 最终Kruskal本质上在求连通性
 * 当前循环完第i条边,求出由前i条边所构成的所有连通块
 */
public class 北极通讯网络 {
    public static void main(String[] args) {
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(new node(sc.nextInt(), sc.nextInt()));
        }
        PriorityQueue<node> q = new PriorityQueue<node>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                q.add(new node(i, j, getdist(list.get(i), list.get(j))));
            }
        }
        int cnt = n;
        double res = 0;
        while (!q.isEmpty()) {
            if (cnt <= k) break;
            node p = q.poll();
            int a = fin(p.x), b = fin(p.y);
            double w = p.w;
            if (a != b) {
                par[a] = b;
                cnt--;
                res = w;
            }
        }
        System.out.printf("%.2f", res);
    }

    private static double getdist(node a, node b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static ArrayList<node> list = new ArrayList<node>();

    static class node implements Comparable<node> {
        int x, y;
        double w;

        public node(int x, int y, double w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return (int) (w * 1000 - node.w * 1000);
        }
    }

    static int[] par = new int[250000];
    static int n, m;

    static int find(int x) {
        while (x != par[x]) {
            par[x] = par[par[x]];
            x = par[x];
        }
        return x;
    }

    static int fin(int x) {
        if (par[x] == x) return x;
        return par[x] = fin(par[x]);
    }
}
