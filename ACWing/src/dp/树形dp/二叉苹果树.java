package dp.树形dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104407327
 * 有一棵二叉苹果树，如果树枝有分叉，一定是分两叉，即没有只有一个儿子的节点。
 * 这棵树共 N 个节点，编号为 1 至 N，树根编号一定为 1。
 * 我们用一根树枝两端连接的节点编号描述一根树枝的位置。
 * 一棵苹果树的树枝太多了，需要剪枝。但是一些树枝上长有苹果，给定需要保留的树枝数量，
 * 求最多能留住多少苹果。
 * 这里的保留是指最终与1号点连通。
 * 输入格式
 * 第一行包含两个整数 N 和 Q，分别表示树的节点数以及要保留的树枝数量。
 * 接下来 N−1 行描述树枝信息，每行三个整数，前两个是它连接的节点的编号，第三个数是这根树枝上苹果数量。
 * 输出格式
 * 输出仅一行，表示最多能留住的苹果的数量。
 * 数据范围
 * 1≤Q<N≤100.
 * N≠1,
 * 每根树枝上苹果不超过 30000 个。
 * 输入样例：
 * 5 2
 * 1 3 1
 * 1 4 10
 * 2 3 20
 * 3 5 20
 * 输出样例：
 * 21
 * 其实是有依赖的背包问题,
 * f(i,j)以i为根的子树中选择j条边的最大价值
 * 比如i点可以选j条边
 *        i
 *     w/   \
 *    s1     s2
 * 物品组1    物品组2
 * 选择一个物品组,就必须保留这条边,
 * 在第一个物品组选择0条边 对应f(s1,0) 价值还要加上必选的那条边,w
 * 第一个个物品组选择1条边,对应f(s1,1) 如上
 * 显然按照选多少条边来划分枚举
 * 再利用分组背包的思想解决
 * 把选0个物品,选1个物品,选2个物品...选m个物品,都看作体积是1的物品
 *
 */
public class 二叉苹果树 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b, c;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        dfs(1, -1);
        System.out.println(f[1][m]);
    }

    static int n, m, idx = 1, N = 110;
    static int[] h = new int[N], w = new int[N << 1], ne = new int[N << 1], e = new int[N << 1];
    static int[][] f = new int[N][N];

    static void dfs(int u, int fa) {
        for (int i = h[u]; i != 0; i = ne[i]) {//先枚举物品组
            if (e[i] == fa) continue;
            dfs(e[i], u);
            /**
             * 基于分组背包
             */
            for (int j = m; j >= 0; j--) {//体积,只能从大到小,因为是优化了dp数组体积的
                for (int k = 0; k < j; k++) {//决策,选0-j-1选多少条边,因为
                    f[u][j] = Math.max(f[u][j], f[u][j - k - 1] + f[e[i]][k] + w[i]);
                    //选择这个物品组需要k+1的容量,加上子树的价值,加上必选的那条边
                }
            }

        }
    }

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
