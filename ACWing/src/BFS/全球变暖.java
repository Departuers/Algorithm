package BFS;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 你有一张某海域 N×N 像素的照片，”.”表示海洋、”#”表示陆地，如下所示：
 * .......
 * .##....
 * .##....
 * ....##.
 * ..####.
 * ...###.
 * .......
 * 其中”上下左右”四个方向上连在一起的一片陆地组成一座岛屿，例如上图就有 2 座岛屿。
 * 由于全球变暖导致了海面上升，科学家预测未来几十年，岛屿边缘一个像素的范围会被海水淹没。
 * 具体来说如果一块陆地像素与海洋相邻(上下左右四个相邻像素中有海洋)，它就会被淹没。
 * 例如上图中的海域未来会变成如下样子：
 * .......
 * .......
 * .......
 * .......
 * ....#..
 * .......
 * .......
 * 请你计算：依照科学家的预测，照片中有多少岛屿会被完全淹没。
 * 输入格式
 * 第一行包含一个整数N。
 * 以下 N 行 N 列，包含一个由字符”#”和”.”构成的 N×N 字符矩阵，代表一张海域照片，”#”表示陆地，”.”表示海洋。
 * 照片保证第 1 行、第 1 列、第 N 行、第 N 列的像素都是海洋。
 */
public class 全球变暖 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!st[i][j] && map[i][j] == '#') {
                    total = 0;
                    bound = 0;
                    if (bfs(i, j)) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static boolean[][] st = new boolean[1010][1010];
    static ArrayDeque<node> q = new ArrayDeque<node>();

    static boolean bfs(int x, int y) {
        q.clear();
        st[x][y] = true;
        q.add(new node(x, y));
        while (!q.isEmpty()) {
            node t = q.poll();
            total++;//关于#的四连通块一共多少块地
            boolean isbou = false;//判断该地是否四面都是水
            for (int i = 0; i < 4; i++) {
                int tx = t.x + dr[i][0], ty = t.y + dr[i][1];
                if (tx < 0 || tx >= n || ty < 0 || ty >= n || st[tx][ty]) continue;
                if (map[tx][ty] == '.') {
                    isbou = true;//这块地起码有一面有水,会沉没
                    continue;
                }
                q.add(new node(tx, ty));
                st[tx][ty] = true;
            }
            if (isbou) bound++;//有水,会被沉没
        }
        return total == bound;
    }

    static int total = 0, bound = 0;

    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map = new char[1010][];
    static int n;
    static int[][] dr = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
}
