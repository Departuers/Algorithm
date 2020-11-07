package 基础练习;

import java.util.Scanner;

public class 回形取数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] tem = new int[209][290];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                tem[i][j] = sc.nextInt();
            }
        }
        int[][] st = new int[202][202];
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int k = 1, i = 1, j = 1, d = 0; k <= n * m; k++) {
            st[i][j] = k;
            System.out.print(tem[i][j] + " ");
            int a = i + dir[d][0], b = j + dir[d][1];
            if (a < 1 || a > n || b < 1 || b > m || st[a][b] != 0) {
                d = (d + 1) % 4;
                a = i + dir[d][0];
                b = j + dir[d][1];
            }
            i = a;
            j = b;
        }
    }
}
