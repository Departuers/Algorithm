package DFS.连通性;

import java.util.Scanner;

/**
 * 有一间长方形的房子，地上铺了红色、黑色两种颜色的正方形瓷砖。
 * 你站在其中一块黑色的瓷砖上，只能向相邻（上下左右四个方向）的黑色瓷砖移动。
 * 请写一个程序，计算你总共能够到达多少块黑色的瓷砖。
 * 输入格式
 * 输入包括多个数据集合。
 * 每个数据集合的第一行是两个整数 W 和 H，分别表示 x 方向和 y 方向瓷砖的数量。
 * 在接下来的 H 行中，每行包括 W 个字符。每个字符表示一块瓷砖的颜色，规则如下
 * 1）‘.’：黑色的瓷砖；
 * 2）‘#’：白色的瓷砖；
 * 3）‘@’：黑色的瓷砖，并且你站在这块瓷砖上。该字符在每个数据集合中唯一出现一次。
 * 当在一行中读入的是两个零时，表示输入结束。
 * 输出格式
 * 对每个数据集合，分别输出一行，显示你从初始位置出发能到达的瓷砖数(记数时包括初始位置的瓷砖)。
 * 数据范围
 * 1≤W,H≤20
 * 输入样例：
 * 6 9
 * ....#.
 * .....#
 * ......
 * ......
 * ......
 * ......
 * ......
 * #@...#
 * .#..#.
 * 0 0
 * 输出样例：
 * 45
 * 分析：
 * 本题是求连通块的大小，数据量不大，可以用dfs实现，由于比较简单，
 */
public class 红与黑 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        for (int i = 0; i < m; i++) {
            g[i] = s.next().toCharArray();
        }
        sx = s.nextInt();
        sy = s.nextInt();
        System.out.println(dfs(sx, sy));
    }

    static int dfs(int sx, int sy) {
        if (g[sx][sy] == '#') return 0;
        int cnt = 1;        //dfs在开头判掉,bfs在内部判掉
        st[sx][sy] = true;
        for (int i = 0; i < 4; i++) {
            int nx = sx + dir[i][0], ny = sy + dir[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !st[nx][ny]) {
                cnt += dfs(nx, ny);
            }
        }
        return cnt;
    }

    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static boolean[][] st = new boolean[22][22];
    static int n, m, sx, sy;
    static char[][] g = new char[22][22];
}
