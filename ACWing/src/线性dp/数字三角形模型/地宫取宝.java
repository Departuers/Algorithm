package 线性dp.数字三角形模型;

/**
 * X 国王有一个地宫宝库。是 n x m 个格子的矩阵。每个格子放一件宝贝。每个宝贝贴着价值标签。
 * 　　地宫的入口在左上角，出口在右下角。
 * 　　小明被带到地宫的入口，国王要求他只能向右或向下行走。
 * 　　走过某个格子时，如果那个格子中的宝贝价值比小明手中任意宝贝价值都大，小明就可以拿起它（当然，也可以不拿）。
 * 　　当小明走到出口时，如果他手中的宝贝恰好是k件，则这些宝贝就可以送给小明。
 * 　　请你帮小明算一算，在给定的局面下，他有多少种不同的行动方案能获得这k件宝贝。
 * 输入格式
 * 　　输入一行3个整数，用空格分开：n m k (1<=n,m<=50, 1<=k<=12)
 * 　　接下来有 n 行数据，每行有 m 个整数 Ci (0<=Ci<=12)代表这个格子上的宝物的价值
 * 输出格式
 * 　　要求输出一个整数，表示正好取k个宝贝的行动方案数。该数字可能很大，输出它对 1000000007 取模的结果。
 * 样例输入
 * 2 2 2
 * 1 2
 * 2 1
 * 样例输出
 * 2
 * 样例输入
 * 2 3 2
 * 1 2 3
 * 2 1 5
 * 样例输出
 * 14
 */
public class 地宫取宝 {
    public static void main(String[] args) {

    }

    static int dfs(int x, int y, int num, int max) {
        if (f[x][y][num][max + 1] != -1) {
            return f[x][y][num][max + 1];
        }
        if (x > n || y > m || num > k) return 0;

        if (x == n && y == m) {
            if (num == k || (num == k - 1 && max < g[x][y]))
                return f[x][y][num][max + 1] = 1;
            else return f[x][y][num][max + 1] = 0;
        }

        long s = 0;
        if (x + 1 <= n) {//判断合法性
            if (max < g[x][y]) {

            }
        }
        return f[x][y][num][max + 1];
    }

    static int n, m, k;
    static int[][] g = new int[51][51];
    static int[][][][] f = new int[51][51][14][14];
}
