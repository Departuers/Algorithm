package dp.线性dp.数字三角形模型;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104025039
 * 小渊和小轩是好朋友也是同班同学，他们在一起总有谈不完的话题。
 * 一次素质拓展活动中，班上同学安排坐成一个 m行 n列的矩阵，而小渊和小轩被安排在矩阵对角线的两端，因此，他们就无法直接交谈了。幸运的是，他们可以通过传纸条来进行交流。
 * 纸条要经由许多同学传到对方手里，小渊坐在矩阵的左上角，坐标(1,1)，小轩坐在矩阵的右下角，坐标(m,n)。
 * 从小渊传到小轩的纸条只可以向下或者向右传递，从小轩传给小渊的纸条只可以向上或者向左传递。
 * 在活动进行中，小渊希望给小轩传递一张纸条，同时希望小轩给他回复。
 * 班里每个同学都可以帮他们传递，但只会帮他们一次，也就是说如果此人在小渊递给小轩纸条的时候帮忙，那么在小轩递给小渊的时候就不会再帮忙，反之亦然。
 * 还有一件事情需要注意，全班每个同学愿意帮忙的好感度有高有低（注意：小渊和小轩的好心程度没有定义，输入时用0表示），可以用一个0-100的自然数来表示，数越大表示越好心。
 * 小渊和小轩希望尽可能找好心程度高的同学来帮忙传纸条，即找到来回两条传递路径，使得这两条路径上同学的好心程度之和最大。
 * 现在，请你帮助小渊和小轩找到这样的两条路径。
 * 输入格式
 * 第一行有2个用空格隔开的整数 m和 n，表示学生矩阵有 m 行 n列。
 * 接下来的 m行是一个 m∗n 的矩阵，矩阵中第 i 行 j 列的整数表示坐在第 i 行 j 列的学生的好心程度，每行的 n个整数之间用空格隔开。
 * 输出格式
 * 输出一个整数，表示来回两条路上参与传递纸条的学生的好心程度之和的最大值。
 * 数据范围
 * 1≤n,m≤50
 * 输入样例：
 * 3 3
 * 0 3 9
 * 2 8 5
 * 5 7 0
 * 输出样例：
 * 34
 * 本题与AcWing 1027 方格取数十分相似。方格取数是从左上角走到右下角走两次，
 * 走过的数只能被取一次，而本题是一个人从左上角往右下角走，另一个从右下角向左上角走，
 * 一个人只能传一次纸条。在方格取数中，f[k][i1][i2]表示两个人从起点分别走到（i1，k-i1）和（i2，k-i2）取得的数字之和的最大值。
 * 或许一开始会想到类比上个问题，用f[k][i1][i2]表示一个人从（1，1）走，另一个人从（m，n）走到相应坐标的好心程度之和的最大值，
 * 事实上，从（1,1）走到（m，n）与从（m，n）走到（1,1）并没有区别，所走路径一样，最后的结果也一样，两个人起点不同，
 * 横纵坐标之和便不再都是k了，不好表示，不如看作依旧是求两个人都从（1,1）走到（m，n）的好心程度之和最大值。
 * 即f[k][i1][i2]表示f[k][i1][i2]表示两个人从起点分别走到（i1，k-i1）和（i2，k-i2）取得的好心程度之和的最大值，
 * 当然并不意味着本题和方格取数问题就完全没有区别了。
 * <p>
 * <p>
 * 方格取数问题两个人可以走到同一个格子，只不过第二个人走到那取不到数字而已，
 * 而本题格子上的人只能帮其中一个人传递纸条，如果帮其中一个人传递了，另一个人的纸条便不能经过该格子了，
 * 这是问题的关键。所以我们可以不计算走到同一个格子的状态，即i1 == i2时直接跳过即可，这意味着，
 * 不存在同一个人传递两次纸条的情况，需要注意的是，终点（m，n）是我们状态转移的终点，其好心程度为0，
 * 所以需要转移到终点这种状态。即i1 != i2或者k = m + n时，进行状态转移，
 * 状态转移方程为f[k][i1][i2] = max(f[k-1][i1-1][i2-1],f[k-1][i1][i2],f[k-1][i1-1][i2],f[k-1][i1][i2-1]) + w[i1][j1] + w[i2][j2]
 */
public class 传纸条 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                w[i][j] = sc.nextInt();
            }
        }
        for (int k = 2; k <= n + m; k++) {
            for (int i1 = 1; i1 <= m; i1++) {
                for (int i2 = 1; i2 <= m; i2++) {
                    if (i1 == i2 && k != m + n) continue;//不会走到同一个位置
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 >= 1 && j1 <= n && j2 >= 1 && j2 <= n) {
                        int t = w[i1][j1] + w[i2][j2];
                        f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][i1 - 1][i2 - 1] + t);
                        f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][i1 - 1][i2] + t);
                        f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][i1][i2 - 1] + t);
                        f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][i1][i2] + t);
                    }
                }
            }
        }
        System.out.println(f[m + n][m][m]);
    }

    static int N = 52, n, m;
    static int[][] w = new int[N][N];
    static int[][][] f = new int[N + N][N][N];

}
