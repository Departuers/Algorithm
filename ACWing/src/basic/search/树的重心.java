package basic.search;

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
        for (int i = 1; i < n; i++) {
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

    static int N = (int) (1e5 + 10), inf = 0x3f3f3f3f;
    static int n, m, idx = 1, ans = inf;
    static int[] h = new int[N];
    static int[] ne = new int[N];
    static int[] e = new int[N];
    static boolean[] st = new boolean[N];
    static int[] count = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int dfs(int u) {
        st[u] = true;
        int size = 0, sum = 1;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (st[j])continue;
            int s = dfs(j);
            size = Math.max(s, size);
            sum += s;
        }
        size = Math.max(size, n - sum);
        count[u] = size;
        ans = Math.min(ans, size);
        return sum;
    }
}
