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
        N = r;
        M = r;
        int x, y, v;
        while (n-- != 0) {
            x = sc.nextInt();
            y = sc.nextInt();
            v = sc.nextInt();
            x++;
            y++;
            N = Math.max(N, x);//求出矩形最大长宽
            M = Math.max(M, y);
            qzh[x][y] += v;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                qzh[i][j] += qzh[i - 1][j] + qzh[i][j - 1] - qzh[i - 1][j - 1];
            }
        }//前缀和
        long res = 0;
        for (int i = r; i <= N; i++) {
            for (int j = r; j <= M; j++) {
                res = Math.max(get(i - r + 1, j - r + 1, i, j), res);
            }
        }
        //求出最大r-1的矩形
        System.out.println(res);
    }

    static int[][] qzh = new int[5010][5010];
    static int n, r, N, M;

    static int get(int x1, int y1, int x2, int y2) {
        return qzh[x2][y2] - qzh[x1 - 1][y2] - qzh[x2][y1 - 1] + qzh[x1 - 1][y1 - 1];
    }
}
