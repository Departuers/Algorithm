package DFS.搜索;

import java.util.Scanner;

/**
 * 蒜头君是一个玩迷宫的高手，天下还没有能难住他的迷宫。但是总有人喜欢刁难蒜头君，
 * 不停的给蒜头君出难题。这个出题的人很聪明，他知道天下还没有能难住蒜头君的迷宫。
 * 所以他便转换思维问蒜头君，在不走重复路径的情况下，总共有多少不同可以到达终点的路径呢？
 * 蒜头君稍加思索便给出了答案，你要不要也来挑战一下？
 * 输入格式
 * 第一行输入两个整数 n(1 \le n \le 11),n(1≤n≤11), m(1 \le m \le 11)m(1≤m≤11)，表示迷宫的行和列。
 * 然后有一个 n \times mn×m 的地图，地图由’.’、’#’、‘s’、‘e’这四个部分组成。
 * ’.‘表示可以通行的路，’#'表示迷宫的墙，'s’表示起始点，'e’表示终点。
 * 输出格式
 * 输出一个整数，表示从’s’到达’e’的所有方案数。
 * 样例输入 复制
 * 5 5
 * s####
 * .####
 * .####
 * .####
 * …e
 * 样例输出 复制
 * 1
 */
public class 迷宫解的方案数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            g[i] = s.toCharArray();
        }
        dfs(0, 0);
        System.out.println(ans);
    }

    static int n, m, ans;
    static boolean[][] st = new boolean[12][12];
    static char[][] g = new char[20][];
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void dfs(int x, int y) {
        if (x >= n || x < 0 || y < 0 || y >= m || g[x][y] == '#' || st[x][y]) return;
        if (g[x][y] == 'e') {
            ans++;
            return;
        }
        st[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + x, ny = dir[i][1] + y;
            dfs(nx, ny);
        }
        st[x][y] = false;
    }
}
