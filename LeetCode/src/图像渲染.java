import java.util.Arrays;

/**
 * LeetCode 733图像渲染
 * 思路:找到该点渲染进行floodFill,要有visited数组!!!
 */
public class 图像渲染 {
    int R, C;
    int[][] res;
    int color;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int d;
    boolean[][] visited;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        R = image.length;
        C = image[0].length;
        visited = new boolean[R][C];
        res = image;
        color = newColor;
        d = image[sr][sc];
        dfs(sr, sc);
        return res;
    }

    void dfs(int i, int j) {
        visited[i][j] = true;
        res[i][j] = color;
        for (int k = 0; k < 4; k++) {
            int nextX = i + dirs[k][0], nextY = j + dirs[k][1];
            if (inArea(nextX, nextY) && !visited[nextX][nextY] && res[nextX][nextY] == d)
                dfs(nextX, nextY);
        }
    }

    boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        图像渲染 a = new 图像渲染();
        int[][] ints = a.floodFill(arr, 1, 1, 2);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }
    }
}
