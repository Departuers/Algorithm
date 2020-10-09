package dp.树形dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103994623
 * 他们的关系就像一棵以校长为根的树，父节点就是子节点的直接上司。
 * 每个职员有一个快乐指数，用整数 Hi给出，其中 1≤i≤N。
 * 现在要召开一场周年庆宴会，不过，没有职员愿意和直接上司一起参会。
 * 在满足这个条件的前提下，主办方希望邀请一部分职员参会，使得所有参会职员的快乐指数总和最大，求这个最大值。
 * 输入格式
 * 第一行一个整数N。
 * 接下来N行，第 i 行表示 i 号职员的快乐指数Hi。
 * 接下来N-1行，每行输入一对整数L, K,表示K是L的直接上司。
 * 输出格式
 * 输出最大的快乐指数。
 * 数据范围
 * 1≤N≤6000,
 * −128≤Hi≤127
 * 输入样例：
 * 7
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1 3
 * 2 3
 * 6 4
 * 7 4
 * 4 5
 * 3 5
 * 输出样例：
 * 5
 * 每条边上最多选择一个点,求最大权值
 * 本题等价于在一棵树中选择若干节点，使得这些节点的权值之和最大，
 * 要求父节点与孩子节点不能同时被选择。
 * 状态机模型,类似...
 * 状态表示：f[u][1]表示以u为根的子树选择了u顶点的最大快乐指数，
 * f[u][0]表示没有选择u的最大快乐指数。
 * 属性max
 * 选择了u时，u的孩子便不能选择了，
 * 设u有两个孩子v1，v2，故f[u][1] = f[v1][0] + f[v2][0] + happy[u]；
 * 而若没有选择u，则u的孩子可选可不选，
 * f[u][0] = max(f[v1][0],f[v1][1]) + max(f[v2][0],f[v2][1])
 * 我们用邻接表存储树形结构，然后从根节点对树做一次dfs，
 * 便可以计算出f[root][0]和f[root][1]了，
 * 二者中的较大者就是我们所要求的最大快乐指数。
 * 最大独立集
 */
public class 没有上司的舞会 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            f[i][1] = sc.nextInt();
        }
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(b, a);
            hasFather[a] = true;
        }
        int root = 1;
        while (hasFather[root]) root++;
        dfs(root);
        System.out.println(Math.max(f[root][1], f[root][0]));
    }

    private static void dfs(int u) {
        // f[u][1] = happy[u];//选择了u就加上u的幸福指数
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            dfs(j);
            f[u][1] += f[j][0];//选当前节点,就不能选子节点
            f[u][0] += Math.max(f[j][0], f[j][1]);
            //不选当前节点,是选还是不选子节点
        }
    }

    static int[][] f = new int[6010][6010];

    static void add(int u, int v) {
        e[cnt] = v;
        ne[cnt] = he[u];
        he[u] = cnt++;
    }

    static int n, cnt = 1;
    static int[] he = new int[6010];
    static int[] ne = new int[6010];
    static int[] e = new int[6010];
    static int[] happy = new int[6010];
    static boolean[] hasFather = new boolean[6010];

}
