package 递归;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_38735931/article/details/89395313#A%20%E5%AE%B6%E8%B0%B1%C2%A0
 * 这一天蒜头君拿到了自己家的家谱，蒜头君便想知道，在自己家的家谱中，每位祖先有多少直系后代（直系后代包括他的孩子和他孩子的直系后代）。但是家族历史源远流长，家谱实在太庞大了，自己一个人完全数不过来。热心的你便自告奋勇帮蒜头君写一个程序，来统计每位祖先有多少直系后代。
 * 输入格式
 * 输入的第一行有一个整数 n(1≤n≤100000)，表示家谱中的总人数。
 * 接下来读入 n−1行，每行有两个整数 x(1≤x≤n), y(1≤y≤n)，表示 x 是 y 的父母。
 * 输出格式
 * 输出 n 行，每行有一个整数，表示第 i个人有多少个直系后代。
 * 样例输入
 * 4
 * 1 2
 * 1 3
 * 2 4
 * 样例输出
 * 3
 * 1
 * 0
 * 0
 */
public class 蒜头君家谱 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int x, y;
        for (int i = 0; i < n - 1; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            add(x, y);
            vis[y] = true;
        }
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                root = i;
                break;
            }
        }
        dfs(root);
        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);
        }
    }

    static int dfs(int x) {
        int cnt = 0;//代表x点有多少子节点
        for (int i = he[x]; i != 0; i = ne[i]) {
            int j = e[i];
            cnt += dfs(j);
        }
        ans[x] = cnt;
        return cnt + 1;
    }

    static boolean[] vis = new boolean[100005];
    static int[] ans = new int[100005];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, cnt = 1;
    static int[] e = new int[200005];
    static int[] he = new int[100005];
    static int[] ne = new int[200005];
}
