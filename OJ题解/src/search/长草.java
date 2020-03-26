package search;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 一个地方可能长多次草
 */
public class 长草 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new char[n][m];
        String s;
        ArrayList<Integer> l = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            s = sc.next();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'g')
                    l.add(i * m + j);
            }
        }
        k = sc.nextInt();
        int nnx, nny;
        for (Integer w : l) {
            nnx = w / m;
            nny = w % m;
            dfs(nnx, nny, k);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int n, m, k;
    static char[][] arr;
    static int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int nx, ny;

    static void dfs(int x, int y, int t) {
        if (t < 0) return;
        arr[x][y] = 'g';//长草
        for (int d = 0; d < 4; d++) {
            nx = x + dirs[d][0];
            ny = y + dirs[d][1];
            if (inArea(nx, ny)) {
                dfs(nx, ny, t - 1);
            }
        }
    }

    static boolean inArea(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }


}
