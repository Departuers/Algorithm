/**
 * LeetCode 130 被围绕的区域
 * 思路:边界上开始的O一定不是被包围的,把与其相连的点遍历vis记录
 * 最后把剩余没被vis遍历的,并且是'O'改成'X'即可
 */
public class 被围绕的区域 {
    int R, C;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    boolean[][] visited;
    char[][] res;

    public void solve(char[][] board) {
        R = board.length;
        if (R == 0) return;
        C = board[0].length;
        visited = new boolean[R][C];
        if (C == 0) return;
        res = board;
        for (int i = 0; i < R; i++) {
            if (board[i][0] == 'O')
                dfs(i, 0);
            if (board[i][C - 1] == 'O')
                dfs(i, C - 1);
        }
        for (int j = 0; j < C; j++) {
            if (board[R - 1][j] == 'O')
                dfs(R - 1, j);
            if (board[0][j] == 'O')
                dfs(0, j);
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

    }

    void dfs(int x, int y) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0], nextY = y + dirs[d][1];
            if (inArea(nextX, nextY) && !visited[nextX][nextY] && res[nextX][nextY] == 'O')
                dfs(nextX, nextY);
        }
    }

    boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
