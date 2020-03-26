package search;

import java.util.*;

import static java.lang.System.in;

public class 节目 {
    static class node {
        int sum, index;

        public node(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }

    static node[] node;

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        m = sc.nextInt();
        node = new node[n];
        for (int i = 0; i < n; i++) {
            node[i] = new node(sc.nextInt(), i);
        }
        Arrays.sort(node, 0, node.length, new Comparator<节目.node>() {
            @Override
            public int compare(节目.node node, 节目.node t1) {
                return t1.sum - node.sum;
            }
        });
        Arrays.sort(node, 0, m , new Comparator<node>() {
            @Override
            public int compare(节目.node node, 节目.node t1) {
                return node.index - t1.index;
            }
        });
        for (int i = 0; i < m; i++) {
            System.out.println(node[i].sum);
        }
    }

    static int n, m;
}
