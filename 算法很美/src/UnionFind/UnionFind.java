package UnionFind;

//最优实现并查集
public class UnionFind {
    public int[] parent;
    private int[] rank;

    UnionFind(int n) {
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
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

}
