package DFS;

import java.util.Scanner;

/**
 * 给定一颗树，树中包含n个结点（编号1~n）和n-1条无向边。
 * 请你找到树的重心，并输出将重心删除后，剩余各个连通块中点数的最大值。
 * 重心定义：重心是指树中的一个结点，如果将这个点删除后，剩余各个连通块中点数的最大值最小，那么这个节点被称为树的重心。
 * 输入格式
 * 第一行包含整数n，表示树的结点数。
 * 接下来n-1行，每行包含两个整数a和b，表示点a和点b之前存在一条边。
 * 输出格式
 * 输出一个整数m，表示重心的所有的子树中最大的子树的结点数目。
 * 数据范围
 * 1≤n≤10^5
 * 输入样例
 * 9
 * 1 2
 * 1 7
 * 1 4
 * 2 8
 * 2 5
 * 4 3
 * 3 9
 * 4 6
 * 输出样例：
 * 4
 */
public class 树的重心 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            add(b, a);
        }
        dfs(1);

        System.out.println(ans);
        for (int i = 1; i < n; i++) {
            System.out.println(count[i]);
        }
    }

    private static int dfs(int u) {
        vis[u] = 1;
        int size = 0, sum = 0;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (vis[j] == 1) continue;
            int s = dfs(j);
            vis[j] = 0;
            size = Math.max(size, s);
            //更新连通块的最值
            sum += s;//加上孩子节点的个数
        }
        size = Math.max(size, n - sum - 1);
        count[u] = size;
        ans = Math.min(ans, size);
        return sum + 1;
    }

    static int n, idx = 1, ans = Integer.MAX_VALUE;
    static int[] he = new int[100100];
    static int[] ne = new int[200100];
    static int[] e = new int[200100];
    static int[] vis = new int[100100];
    static int[] count = new int[100100];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }
}