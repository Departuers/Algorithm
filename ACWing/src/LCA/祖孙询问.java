package LCA;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107291771
 * 已知一棵 n个节点的有根树。有 m 个询问，每个询问给出了一对节点的编号 x 和 y，询问 x 与 y 的祖孙关系。
 * 【输入】
 * 输入第一行包括一个整数 n 表示节点个数；
 * 接下来 n 行每行一对整数对 a和 b表示 a 和 b 之间有连边。如果 b 是 −1，那么a 就是树的根；
 * 第 n+2 行是一个整数 m 表示询问个数；
 * 接下来 m 行，每行两个正整数 x 和 y，表示一个询问。
 * 【输出】
 * 对于每一个询问，若 x 是 y 的祖先则输出1，若 y 是 x 的祖先则输出 2，否则输出 0。
 * 【输入样例】
 * 10
 * 234 -1
 * 12 234
 * 13 234
 * 14 234
 * 15 234
 * 16 234
 * 17 234
 * 18 234
 * 19 234
 * 233 19
 * 5
 * 234 233
 * 233 12
 * 233 13
 * 233 15
 * 233 19
 * 【输出样例】
 * 1
 * 0
 * 0
 * 0
 * 2
 */
public class 祖孙询问 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b, root = 0;
        for (int i = 0; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            if (b != -1)
                add(a, b);
            else {
                root = a;
            }
        }
        bfs(root);
        m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            int p = lca(a, b);
            if (p == a) System.out.println(1);
            else if (p == b) System.out.println(2);
            else System.out.println(0);
        }

    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) return lca(b, a);
        //a要在b的上面
        for (int k = 17; k >= 0; k--) {
            if (depth[up[a][k]] >= depth[b]) {
                a = up[a][k];
            }
        }//从高往低跳
        if (a == b) return a;
        for (int k = 17; k >= 0; k--) {
            if (up[a][k] != up[b][k]) {
                a = up[a][k];
                b = up[b][k];
            }
        }
        return up[a][0];
    }


    static int n, m, N = 40010, M = N * 2, cnt = 1, inf = Integer.MAX_VALUE / 2;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] depth = new int[N];
    static int[][] up = new int[N][19];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = h[a];
        h[a] = cnt++;
        e[cnt] = a;
        ne[cnt] = h[b];
        h[b] = cnt++;
    }

    /**
     * bfs求深度和lca
     *
     * @param root
     */
    static void bfs(int root) {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[root] = 1;//规定根节点深度为1
        q.add(root);//加入根节点
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (depth[j] > depth[t] + 1) {
                    depth[j] = depth[t] + 1;//bfs拓展
                    q.add(j);
                    up[j][0] = t;//记录第一个
                    for (int k = 1; k <= 17; k++) {//记录后面的up,虽然有无效计算,但胜在好写
                        up[j][k] = up[up[j][k - 1]][k - 1];
                    }
                }
            }
        }
    }
}
