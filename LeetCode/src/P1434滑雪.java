import java.util.Arrays;
import java.util.Scanner;

public class P1434滑雪 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = sc.nextInt();
            }
            Arrays.fill(mem[i], 1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(dfs(i, j), ans);
            }
        }
        System.out.println(ans);
    }

    static int dfs(int i, int j) {
        if (mem[i][j] > 1) return mem[i][j];
        for (int k = 0; k < 4; k++) {
            int nx = dir[k][0] + i, ny = dir[k][1] + j;
            if (nx >= 0 && nx < n && ny >= 0 && ny < m)
                if (g[nx][ny] < g[i][j])
                    mem[i][j] = Math.max(dfs(nx, ny) + 1, mem[i][j]);
        }
        return mem[i][j];
    }

    static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    static int[][] g = new int[110][110];
    static int[][] mem = new int[110][110];

    static int n, m;
}
