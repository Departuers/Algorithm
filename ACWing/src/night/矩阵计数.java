package night;

import java.util.Scanner;

/**
 * 一个 N × M 的方格矩阵，每一个方格中包含一个字符 O 或者字符 X。
 * 要求矩阵中不存在连续一行 3 个 X 或者连续一列 3 个 X。
 * 问这样的矩阵一共有多少种？
 * 【输入格式】
 * 输入一行包含两个整数 N 和 M。
 * 【输出格式】
 * 输出一个整数代表答案。
 * 【样例输入】
 * 2 3
 * 1
 * 【样例输出】
 * 49
 * 1
 * 【数据规模与约定】
 * 对于所有评测用例，1 ≤ N, M ≤ 5。
 */
public class 矩阵计数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        cs = new int[n][m];
        dfs(0, 0);
        System.out.println(sum);
    }

    static void dfs(int i, int j) {
        if (i == n) {
            sum++;
            return;
        }
        if (judge(i, j)) {
            cs[i][j] = 1;
            dfs(i + (j + 1) / m, (j + 1) % m);
            //枚举下一位,j+1满了m,i就加一位, (j+1)%m就是下一个位置
            cs[i][j] = 0;
        }
        dfs(i + (j + 1) / m, (j + 1) % m);
    }

    /**
     * 判断坐标(i,j)是否可以放置X
     *
     * @param i
     * @param j
     * @return
     */
    static boolean judge(int i, int j) {
        if (n > 2 && i >= 2) {
            if (cs[i - 1][j] == cs[i - 2][j] && cs[i - 2][j] == 1)
                return false;
        }
        if (m > 2 && j >= 2) {
            if (cs[i][j - 1] == cs[i][j - 2] && cs[i][j - 2] == 1)
                return false;
        }
        return true;
    }

    static int n, m, sum;
    static int[][] cs;
}
