package UnionFind;

import java.util.Arrays;

public class UnionFindNR {
    private int[] parent;
    private int[] rank;

    UnionFindNR(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void Union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else
            parent[qRoot] = pRoot;
        rank[pRoot] += 1;
    }

    public boolean isCon(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = find(parent[p]);
        }
        return parent[p];
    }//路径压缩,压缩成多个节点指向一个父亲节点,但还是不能去掉rank数组,千万级别效率不如最优实现

    public void print() {
        System.out.println(Arrays.toString(parent));
    }

    public static void main(String[] args) {
        UnionFindNR s = new UnionFindNR(12);
        s.Union(3, 4);
        s.Union(4, 5);
        s.Union(2, 5);
        s.print();
        s.Union(6, 7);
        s.print();
        s.Union(2, 6);
        s.find(7);
        s.print();
        System.out.println(s.isCon(7,3));
    }

}
