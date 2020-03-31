package 递归;

public class m任取n满足 {
    public static void main(String[] args) {
        f(0, 0, 7);
        System.out.println(ans);
    }

    static int ans, n = 6, m = 2;
    static int[] arr = {2, 3, 5, 6, 1, 7};

    static void f(int u, int sum, int s) {
        if (sum + n - u < m) return;//n-u是剩余可选的数,
        if (u == 6) {
            if (sum == 2 && s == 0)
                ans++;
            return;
        }
        if (u > n) return;
        f(u + 1, sum, s);
        f(u + 1, sum + 1, s - arr[u]);
    }

    static boolean x[] = new boolean[6];

    //搜索策略
    static void dfs(int u, int cnt) {


        for (int i = 0; i < n; i++) {
            if (!x[u]) {
                x[u] = true;
                dfs(u + 1, cnt);
            }
        }
    }
}