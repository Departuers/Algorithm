/**
 * LeetCode 1020: 飞地的数量
 *
 * 给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。
 * 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
 * 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。
 * 示例 1：
 * 输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：
 * 有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 * 输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：
 * 所有 1 都在边界上或可以到达边界。
 * 思路:把图中边界相连的1都变成0,最后遍历查询整张图有多少个1即可
 */
public class 飞地的数量 {
    int[][] graph;
    int R, C;
    int ans = 0;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int numEnclaves(int[][] A) {
        graph = A;
        R = A.length;
        C = A[0].length;
        for (int i = 0; i < R; i++) {
            if (graph[i][0] == 1)
                dfsC(i, 0);
            if (graph[i][C - 1] == 1)
                dfsC(i, C - 1);
        }
        for (int j = 0; j < C; j++) {
            if (graph[R - 1][j] == 1)
                dfsC(R - 1, j);
            if (graph[0][j] == 1)
                dfsC(0, j);
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (graph[i][j] == 1)
                    ans++;
            }
        }
        return ans;
    }

    private void dfsC(int x, int y) {
        if (!inArea(x, y) || graph[x][y] == 0) return;
        graph[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            if (inArea(nextx, nexty) && graph[nextx][nexty] == 1)
                dfsC(nextx, nexty);
        }
    }

    boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
