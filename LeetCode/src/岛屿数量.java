/**
 * LeetCode 200 岛屿数量
 * 实际上是求有几个联通分量
 */
public class 岛屿数量 {
    private int R, C;
    private char[][] grid;
    private boolean[][] visited;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//四联通偏移量


    public int numIslands(char[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;
        if (R == 0) return 0;
        C = grid[0].length;
        if (C == 0) return 0;
        this.grid = grid;

        int res = 0;
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(i, j);
                    res++;
                }
            }
        }
        return res;

    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int res = 1;
        for (int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            if (inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == '1') {
                res += dfs(nextx, nexty);
            }
        }
        return res;
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
