package 前缀和差分;

import java.util.Scanner;

/**
 * 给定一个n*m的大矩阵,在里面求得一个c*c的矩阵
 */
public class 激光炸弹 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        int x, y, w;
        while (n-- != 0) {
            x = sc.nextInt();
            y = sc.nextInt();
            w = sc.nextInt();
            a[++x][++y] += w;
        }
        for (int i = 1; i <= 5000; i++) {
            for (int j = 1; j <= 5000; j++) {
                qzh[i][j] += qzh[i - 1][j] + qzh[i][j - 1] - qzh[i - 1][j - 1] + a[i][j];
            }
        }
        long res = 0;
        for (int i = 1; i <= 5000 - r + 1; i++) {
            for (int j = 1; j <= 5000 - r + 1; j++) {
                res = Math.max(get(i, j, i + r - 1, j + r - 1), res);
            }
        }
        System.out.println(res);
    }

    static int n, r;
    static int[][] qzh = new int[5005][5005];
    static int[][] a = new int[5005][5005];

    static int get(int x1, int y1, int x2, int y2) {
        return qzh[x2][y2] - qzh[x1 - 1][y2] - qzh[x2][y1 - 1] + qzh[x1 - 1][y1 - 1];
    }
}
