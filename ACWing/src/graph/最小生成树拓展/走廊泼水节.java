package graph.最小生成树拓展;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/102526856
 * 给定一个N个节点的树,
 * 把这棵树扩充为完全图,并满足图的唯一最小生成树仍然是这棵树
 * 连接两个集合的新边,
 * 把最小生成树,扩充,则新加的边必须严格大于最小生成树的边,
 * 都取边权w+1最小的那个满足要求
 */
public class 走廊泼水节 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        ArrayList<node> q = new ArrayList<node>();
        int[] size = new int[6100];
        while (t-- != 0) {
            q.clear();
            n = sc.nextInt();
            for (int i = 1; i < n; i++) {
                q.add(new node(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }
            Collections.sort(q);
            for (int i = 0; i < par.length; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int res = 0;
            for (int i = 0; i < n - 1; i++) {
                node c = q.get(i);
                int a = find(c.x), b = find(c.y);
                if (a != b) {
                    res += (size[a] * size[b] - 1) * (c.w + 1);//新边都取w+1
                    System.out.println(Arrays.toString(size));
                    size[b] += size[a];//合并两个集合
                    par[a] = b;//a指向b
                }
            }
            System.out.println(res);
        }
    }

    static int[] par = new int[6100];

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

    static int find(int x) {
        if (x == par[x]) return x;
        return par[x] = find(par[x]);
    }

    static int t, n;
}
