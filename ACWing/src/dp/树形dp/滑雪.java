package dp.树形dp;

import java.util.Scanner;

/**
 * 给定一个R行C列的矩阵，表示一个矩形网格滑雪场。
 * 矩阵中第 i 行第 j 列的点表示滑雪场的第 i 行第 j 列区域的高度。
 * 一个人从滑雪场中的某个区域内出发，每次可以向上下左右任意一个方向滑动一个单位距离。
 * 当然，一个人能够滑动到某相邻区域的前提是该区域的高度低于自己目前所在区域的高度。
 * 下面给出一个矩阵作为例子：
 * 1  2  3  4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * 在给定矩阵中，一条可行的滑行轨迹为24-17-2-1。
 * 在给定矩阵中，最长的滑行轨迹为25-24-23-…-3-2-1，沿途共经过25个区域。
 * 现在给定你一个二维矩阵表示滑雪场各区域的高度，请你找出在该滑雪场中能够完成的最长滑雪轨迹，并输出其长度(可经过最大区域数)。
 * 输入格式
 * 第一行包含两个整数R和C。
 * 接下来R行，每行包含C个整数，表示完整的二维矩阵。
 * 输出格式
 * 输出一个整数，表示可完成的最长滑雪长度。
 * 数据范围
 * 1≤R,C≤300,
 * 0≤矩阵中整数≤10000
 * 输入样例：
 * 5 5
 * 1 2 3 4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * 输出样例：
 * 25
 * 本题是一个搜索问题，适合用记忆化搜索解决。
 * 状态表示:设f[i][j]表示从（i，j）出发能够完成的最长滑雪轨迹，
 * 属性max
 * 状态划分:从上下左右哪一个方向划过来的,取最大
 * 则f[i][j] = max(f[i-1][j],f[i+1][j],f[i][j-1],f[i][j+1]) + 1。
 * <p>
 * 则可以向上下左右中高度低于自身的区域滑动，如果四个方向高度都低于（i，j），
 * 且未到达边界，
 * 则f[i][j] = max(f[i-1][j],f[i+1][j],f[i][j-1],f[i][j+1]) + 1。
 * 我们沿着某个方向搜索，直至搜索到某个方格高度小于四周方格不能再滑动为止。
 * 如果对每个格子都做一次dfs冗余计算很多，比如23可以滑动到22，
 * 然后22可以一直滑动到不能再滑动为止；25也可以滑动到22，如果从23滑动到22后完成了搜索，
 * 那么，从25滑动到22后的搜索轨迹与上一次从23滑动到22后的搜索轨迹一模一样，
 * 所以某个状态搜索完毕后可以保存下来，下次搜索到该状态就可以直接使用了。
 */
public class 滑雪 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = sc.nextInt();
                f[i][j] = 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(dfs(i, j), ans);
            }
        }
        System.out.println(ans);
    }

    static int[][] g = new int[310][310];
    static int n, m;
    static int[][] f = new int[310][310];
    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    /**
     * 表示从i,j位置作为起始点,最多能滑多远
     * 初始化每个位置为 1
     * 起码都是1
     */
    static int dfs(int i, int j) {
        if (f[i][j] > 1) return f[i][j];
        for (int d = 0; d < 4; d++) {
            int x = i + dir[d][0], y = j + dir[d][1];
            if (inarea(x, y)) {
                if (g[i][j] > g[x][y]) {
                    f[i][j] = Math.max(f[i][j], dfs(x, y) + 1);//所以要+1
                }
            }
        }
        return f[i][j];
    }

    static boolean inarea(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
