package basic.search;

public class 全排列 {
    public static void main(String[] args) {
        dfs(0);
    }

    static int[] p = new int[10];
    static boolean[] st = new boolean[10];
    static int n = 5;

    static void dfs(int u) {
        if (u == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(p[i]);
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!st[i]) {
                p[u] = i;
                st[i] = true;
                dfs(u + 1);
                st[i] = false;
            }
        }
    }
}
