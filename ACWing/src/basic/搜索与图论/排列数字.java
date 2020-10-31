package basic.搜索与图论;

/**
 * dfs是一个执着的人,走不到底,不回头,边回头,边看还能不能往前走
 * 只有确认当前点所有的点都走不了时候,才会回退一步
 * <p>
 * bfs是一个稳重的人,他不想离家太远,
 * 会先拓展第一层,再拓展第二层
 * 数据结构    空间
 * dfs       stack    O(h)
 * bfs       queue      O(2^h)   拥有最短路性质
 * bfs第一次拓展到的点事最短路,所有边的权重都相同
 * <p>
 * dfs
 * _ _ _
 * 1__     2__        3__
 * 12_  13_  21_  23_   31_  32_
 * 123
 * 往回走叫回溯
 */
public class 排列数字 {
    public static void main(String[] args) {
        dfs(0);

    }

    static int N = 10, n=3;
    static int[] path = new int[N];
    static boolean[] st = new boolean[N];

    static void dfs(int u) {
        if (u == n) {//第i层看第i个位置,当第n层就看完了
            for (int i = 0; i < n; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!st[i]) {
                st[i] = true;
                path[u] = i;
                dfs(u + 1);
                //path[u] = 0; path[u]会被不断覆盖
                st[i] = false;
            }
        }
    }
}
