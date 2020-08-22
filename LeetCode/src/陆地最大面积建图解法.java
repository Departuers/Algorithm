import java.util.HashSet;

/**
 * LeetCode 695
 */
public class 陆地最大面积建图解法 {
    private int R, C;
    private int[][] grid;
    private HashSet<Integer>[] G;
    private boolean[] visited;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//四联通偏移量

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;
        if (R == 0) return 0;
        C = grid[0].length;
        if (C == 0) return 0;
        this.grid = grid;
        G = constructGraph();

        int res = 0;
        visited = new boolean[G.length];
        for (int v = 0; v < G.length; v++) {
            int x = v / C, y = v % C;
            if (!visited[v] && grid[x][y] == 1) {
                res = Math.max(res, dfs(v));
            }
        }
        return res;

    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (Integer w : G[v]) {
            if (!visited[w])
                res += dfs(w);
        }
        return res;
    }

    /**
     * 建图
     *
     * @return 图
     */
    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[R * C];//地图上的每一个点都是顶点
        for (int i = 0; i < R * C; i++) {
            g[i] = new HashSet<Integer>();
        }
        for (int v = 0; v < g.length; v++) {
            int x = v / C, y = v % C;//将一维数组转换为二维坐标
            if (grid[x][y] == 1) {//如果这个点是陆地,就进行四联通检测
                for (int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];
                    //计算出偏移后的新坐标,不一定合法
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
        }
        return g;

    }

    /**
     * 判断生成的新坐标是否合法
     *
     * @param x 生成的新x坐标
     * @param y 生成的新x坐标
     * @return 合法与否
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
