package DFS;

public class 重复性剪枝 {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            a[i] = i + 1;
        }
        long s = System.nanoTime();
        dfs(0, 0, 0);
        System.out.println(ans);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e9);
        s = System.nanoTime();
        ddf(0, 0, 0);
        t = System.nanoTime();
        System.out.println((t - s) / 1e9);
        System.out.println(te);

    }

    static int n = 30, k = 11, ans, te, sum = 200;
    static int[] a = new int[30];
    static boolean[] vis = new boolean[30];

    //当前位置,当前和 w选了多少个
    static void dfs(int u, int s, int w) {
        if (k - w > n - u + 1) return;
        if (s > sum || w > k) return;
        if (u == n) {
            if (s == sum && w == k) {
                ans++;
            }
            return;
        }
        dfs(u + 1, s + a[u], w + 1);
        dfs(u + 1, s, w);
    }

    static void ddf(int u, int cnt, int pos) {
        if (u > sum || cnt > k) return;
        if (u == sum && cnt == k) {
            te++;
            return;
        }
        //pos位置
        for (int i = pos; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                ddf(u + a[i], cnt + 1, i + 1);
                vis[i] = false;
            }
        }
    }
}