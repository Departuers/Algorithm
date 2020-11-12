package night;

import java.util.Scanner;

public class n2皇后 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        dfsb(1);
        System.out.println(ans);
    }

    static int n, ans, m, N = 30;
    static int[][] a = new int[N][N];
    static int[] posb = new int[N], posw = new int[N];

    static void dfsb(int u) {
        if (u == n + 1) dfsw(1);
        for (int i = 1; i <= n; i++) {
            if (a[u][i] == 0) continue;
            if (checkb(u, i)) {
                dfsb(u + 1);
            }
        }
    }

    static boolean checkb(int cur, int col) {
        posb[cur] = col;
        for (int i = 1; i < cur; i++) {
            if (posb[i] == col || i + posb[i] == cur + col || i - posb[i] == cur - col) {
                //纵坐标相同,坐标之和,坐标之差,判断对角线
                return false;
            }
        }
        return true;
    }

    static boolean checkw(int cur, int col) {
        posw[cur] = col;
        for (int i = 1; i < cur; i++) {
            if (posw[i] == col || i + posw[i] == cur + col || i - posw[i] == cur - col) {
                //纵坐标相同,坐标之和,坐标之差,判断对角线
                return false;
            }
        }
        return true;
    }

    static void dfsw(int u) {
        if (u == n + 1) {
            ans++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (posb[u] == i) continue;
            if (a[u][i] == 0) continue;
            if (checkw(u, i)) {
                dfsw(u + 1);
            }
        }
    }
}
