import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 1091 二进制矩阵中的最短路径
 */
public class 二进制矩阵中的最短路径 {
    private int R, C;

    public int shortestPathBinaryMatrix(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];
        if (grid[0][0] == 1)//无解,初始节点被阻塞
            return -1;
        if (R == 1 && C == 1) return 1;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(0);
        visited[0][0] = true;
        dis[0][0] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int curx = cur / C, cury = cur % C;
            for (int i = -1; i <= 1; i++) {//八联通
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    int nextx = curx + i, nexty = cury + j;
                    if (inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 0) {
                        queue.add(nextx * C + nexty);
                        visited[nextx][nexty] = true;
                        dis[nextx][nexty] = dis[curx][cury] + 1;
                        if (nextx == R - 1 && nexty == C - 1) {
                            return dis[nextx][nexty];
                        }
                    }
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
