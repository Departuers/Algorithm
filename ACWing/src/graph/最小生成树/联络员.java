package graph.最小生成树;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.acwing.com/activity/content/code/content/308367/
 * Tyvj已经一岁了，网站也由最初的几个用户增加到了上万个用户，
 * 随着Tyvj网站的逐步壮大，管理员的数目也越来越多，现在你身为Tyvj管理层的联络员，
 * 希望你找到一些通信渠道，使得管理员两两都可以联络（直接或者是间接都可以）。
 * 本题中所涉及的通信渠道都是 双向 的。
 * Tyvj是一个公益性的网站，没有过多的利润，所以你要尽可能的使费用少才可以。
 * 目前你已经知道，Tyvj的通信渠道分为两大类，一类是必选通信渠道，无论价格多少，
 * 你都需要把所有的都选择上；还有一类是选择性的通信渠道，你可以从中挑选一些作为最终管理员联络的通信渠道。
 * 数据保证给出的通信渠道可以让所有的管理员联通。
 * 注意： 对于某两个管理员 u,v
 * ，他们之间可能存在多条通信渠道，你的程序应该累加所有 u,v
 * 之间的必选通行渠道。
 * 输入格式
 * 第一行两个整数 n，m
 * 表示Tyvj一共有 n 个管理员，有 m个通信渠道;
 * 第二行到 m+1
 * 行，每行四个非负整数，p,u,v,w 当 p=1 时，表示这个通信渠道为必选通信渠道；当 p=2 时，表示这个通信渠道为选择性通信渠道；u,v,w 表示本条信息描述的是 u，v 管理员之间的通信渠道，u 可以收到 v 的信息，v 也可以收到 u 的信息，w
 * 表示费用。
 * 输出格式
 * 一个整数，表示最小的通信费用。
 * 数据范围
 * 1≤n≤2000
 * 1≤m≤10000
 * 输入样例：
 * 5 6
 * 1 1 2 1
 * 1 2 3 1
 * 1 3 4 1
 * 1 4 1 1
 * 2 2 5 10
 * 2 2 5 5
 * 输出样例：
 * 9
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
