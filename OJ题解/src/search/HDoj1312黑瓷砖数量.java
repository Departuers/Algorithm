package search;

import java.util.Scanner;

import static java.lang.System.in;

public class HDoj1312黑瓷砖数量 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        while (true){
            n = sc.nextInt();
            m = sc.nextInt();
            if (n==0&&m==0)break;
            arr = new char[m][n];
            for (int i = 0; i < m; i++) {
                String next = sc.next();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = next.charAt(j);
                    if (arr[i][j] == '@') {
                        sx = i;
                        sy = j;
                    }
                }
            }
            dfs(sx,sy);
            System.out.println(ans);
            ans=0;
        }
    }

    static char[][] arr;
    static int ans, n, m, sx, sy;
    static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    static void dfs(int x, int y) {
        ans++;
        arr[x][y] = '#';
        for (int i = 0; i < 4; i++) {
            int nx = dirs[i][0] + x, ny = y + dirs[i][1];
            if (inArea(nx,ny)&&arr[nx][ny]!='#'){
                dfs(nx,ny);
            }
        }
    }
    static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
