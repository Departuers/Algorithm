package dp.线性dp;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/content/20557/
 * X 国王有一个地宫宝库。是 n x m 个格子的矩阵。每个格子放一件宝贝。
 * 每个宝贝贴着价值标签。
 * 地宫的入口在左上角，出口在右下角。
 * 小明被带到地宫的入口，国王要求他只能向右或向下行走。
 * 走过某个格子时，如果那个格子中的宝贝价值比小明手中任意宝贝价值都大，
 * 小明就可以拿起它（当然，也可以不拿）。
 * 当小明走到出口时，如果他手中的宝贝恰好是k件，则这些宝贝就可以送给小明。
 * 请你帮小明算一算，在给定的局面下，他有多少种不同的行动方案能获得这k件宝贝。
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
 * f[i,j,k,c]状态表示:所有起点走到(i,j)取了k件物品,且最后一件物品价值为c的方案数集合
 * 属性:方案数
 * 状态划分:最后一步是从上面还是左边转移过来的,还有取不取
 * 最后一步从上面过来不取f[i-1,j,k,c]
 * 最后一步从左面过来不取f[i,j-1,k,c]
 * 最后一步从上面过来取 f[i-1,j,k-1,c` ]  c`< c
 * 最后一步从左边过来取 f[i,j-1,k-1,c``]  c``< c
 * 条件w[i,j]=c取得最后一件物品为c
 * 边界f[1,1,1,w[1,1] ]=1 第一个物品选
 * f[1,1,0,-1]=1 第一个物品不选
 * -1都加上1 c∈[-1,13] 转化为c∈[0,14]
 */
public class 地宫取宝 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                w[i][j] = sc.nextInt();
                w[i][j]++;
            }
        }
        f[1][1][1][w[1][1]] = 1;
        f[1][1][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;
                for (int u = 0; u <= k; u++) {
                    for (int v = 0; v <= 13; v++) {
                        f[i][j][u][v] = (f[i][j][u][v] + f[i - 1][j][u][v]) % mod;
                        f[i][j][u][v] = (f[i][j][u][v] + f[i][j - 1][u][v]) % mod;
                        if (u > 0 && v == w[i][j]) {
                            for (int c = 0; c < v; c++) {
                                f[i][j][u][v] = (f[i][j][u][v] + f[i - 1][j][u - 1][c]) % mod;
                                f[i][j][u][v] = (f[i][j][u][v] + f[i][j - 1][u - 1][c]) % mod;
                            }
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int i = 0; i <= 13; i++) {
            res = (res + f[n][m][k][i]) % mod;
        }
        System.out.println(res);
    }

    static int n, m, k, mod = (int) (1e9 + 7);
    static int[][] w = new int[51][51];
    static int[][][][] f = new int[51][51][14][14];
}
