package basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * https://www.acwing.com/activity/content/code/content/245992/
 */
public class 蛇形矩阵 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        //右下左上
        for (int x = 0, y = 0, k = 1, d = 0; k <= n * m; k++) {
            res[x][y] = k;
            int a = dx[d] + x, b = dy[d] + y;

            if (a < 0 || a >= n || b < 0 || b >= m || res[a][b] != 0) {//出界或者有值,咱们就换方向
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(res[i][j] + "");
            }
            bw.write("\n");
        }
        bw.flush();
        for (int k = 0, x = 0, y = 0, d = 0; k <= n * m; k++) {
            res[x][y] = k;
            int a = dx[d] + x, b = y + dy[d];
            if (a < 0 || a >= n || b < 0 || b >= m || res[a][b] != 0) {
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] res = new int[100][100];
}
