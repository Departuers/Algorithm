package graph.最小生成树;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/107898613
 * 题目描述：
 * 某个局域网内有 n 台计算机和 k 条 双向 网线，计算机的编号是 1∼n。由于搭建局域网时工作人员的疏忽，
 * 现在局域网内的连接形成了回路，我们知道如果局域网形成回路那么数据将不停的在回路内传输，造成网络卡的现象。
 * 对于某一个连接，虽然它是双向的，但我们不将其当做回路。本题中所描述的回路至少要包含两条不同的连接。
 * 两台计算机之间最多只会存在一条连接。
 * 不存在一条连接，它所连接的两端是同一台计算机。
 * 因为连接计算机的网线本身不同，所以有一些连线不是很畅通，
 * 我们用 f(i,j) 表示 i,j 之间连接的畅通程度，f(i,j)值越小表示 i,j 之间连接越通畅。
 * 现在我们需要解决回路问题，我们将除去一些连线，
 * 使得网络中没有回路且不影响连通性（即如果之前某两个点是连通的，去完之后也必须是连通的），并且被除去网线的 Σf(i,j) 最大，请求出这个最大值。
 * 输入格式
 * 第一行两个正整数 n,k。
 * 接下来的 k 行每行三个正整数 i,j,m 表示 i,j 两台计算机之间有网线联通，通畅程度为 m。
 * 输出格式
 * 一个正整数，表示被除去网线的 Σf(i,j) 的最大值。
 * 数据范围
 * 1≤n≤100
 * 0≤k≤200
 * 1≤f(i,j)≤1000
 * 输入样例：
 * 5 5
 * 1 2 8
 * 1 3 1
 * 1 5 3
 * 2 4 5
 * 3 4 2
 * 输出样例：
 * 8
 * 分析：
 * 本题要求被除去网线的通畅程度之和最大，则要求留下来的网线通畅程度最小，也就是求图的最小生成树，
 * 由于原图不一定是连通图，所以要求的实际上是原图的最小生成森林，即若干个生成树的集合。
 * 可以用Kruskal算法求解，时间复杂度为O（mlogm）。
 * 简单回忆下kruskal算法。并查集中用生成树的根节点作为这个集合的表示，如果两个节点所在集合的标识相同，
 * 就处在同一个集合中。用find(int x)函数求x所在集合的标识，初始情况下各个点互不联通，
 * 所以某个节点在生成树中的父节点fa[i] = i,也就是其本身，在find函数中，一旦x == fa[x]就说明x是树的根节点，
 * 直接返回自身的值即可，否则继续对x的父节点fa[x]调用find函数，知道找到生成树的根节点。kruskal算法的过程是，
 * 首先按照边权对所有边从小到大排序，然后自小到大遍历边的集合，如果某条边的两个顶点a和b还不在一个集合中，
 * 就将a的父节点设置为b，相当于合并了这两个集合。总的代码如下：
 * 求最小生成森林
 * 求每一个联通块的,最小生成树
 * 使用Kruskal算法最好写,即使算法没有进行完,那么算法也是正确的
 * 统计所有非最小生成树的边的权值
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
            else res += p.w;//走到了这里说明,这条边不是最小生成树的一条边,记录一下权值
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
