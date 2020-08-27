package search;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * 在一张最大为4X4的地图中，放置碉堡，所有的碉堡不能同行和同列，除非中间有墙阻隔！
 * 求最多能放置多少个碉堡！
 * '.'表示能放置碉堡的位置，‘X’表示墙！
 */
public class ZJU1002放碉堡 {

    private static int n;
    private static char[][] arr = new char[4][4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);

        while (true) {
            n = sc.nextInt();
            if (n == 0) break;
            ibest = 0;
            for (int i = 0; i < n; i++) {
                String z = sc.next();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = z.charAt(j);
                }
            }
            dfs(0, 0);
            System.out.println(ibest);
        }

    }

    static int ibest = 0;

    static void dfs(int k, int cur) {
        int x, y;
        if (k == n * n) {
            if (cur > ibest) {
                ibest = cur;
            }
            return;
        } else {
            x = k / n;
            y = k % n;
            if (arr[x][y] == '.' && canPut(x, y)) {
                arr[x][y] = 'O';
                dfs(k + 1, cur + 1);
                arr[x][y] = '.';
            }
            dfs(k + 1, cur);
        }

    }

    /**
     * @param row 行
     * @param col 列
     * @return
     */
    static boolean canPut(int row, int col) {
        int i = 0;
        //判断列合法性
        for (i = row - 1; i >= 0; i--) {
            if (arr[i][col] == 'O') return false;//O代表有碉堡
            if (arr[i][col] == 'X') break;
        }
        //判断行合法性
        for (i = col - 1; i >= 0; i--) {
            if (arr[row][i] == 'O') return false;
            if (arr[row][i] == 'X') break;
        }
        return true;
    }
}
