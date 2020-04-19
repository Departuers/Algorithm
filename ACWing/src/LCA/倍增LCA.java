package LCA;

public class 倍增LCA {
    public static void main(String[] args) {

    }

    static int[] e = new int[100005];
    static int[] he = new int[100005];
    static int[] ne = new int[100005];
    static int n, m, cnt = 1;
    static int[][] up = new int[100005][20];
    static int[] depth = new int[100006];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static void dfs(int u, int fa, int d) {
        depth[u] = d;
        up[u][0] = fa;
        for (int i = he[u]; i != 0; i = ne[i]) {
            if (e[i] != fa)
                dfs(e[i], u, d + 1);
        }
    }
}
