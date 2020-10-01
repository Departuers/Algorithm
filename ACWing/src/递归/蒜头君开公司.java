package 递归;

import java.util.Scanner;

/**
 * 2020 年，蒜头君自己开了一家拥有N个员工的大公司。
 * 每天，蒜头君都要分配N项工作给他的员工，
 * 但是，由于能力的不同，每个人对处理相同工作所需要的时间有快有慢。
 * 众所周知，蒜头君是一个非常重视效率的人，他想知道该如何分配工作
 * 才能使得完成所有工作的时间总和最小（每个员工只可以被分配到一个工作）。
 * 但是我们也都知道蒜头君不是一般的懒，所以蒜头君找到了你，
 * 请你拯救一下蒜头君吧！
 * 第一行输入一个整数N，代表有N个员工，员工编号从1到N。
 * （1≤N≤10）
 * 接着输入一个N*N的二维矩阵task[N][N]，
 * task[i][j]指的是第i项工作如果由j号员工完成所需要的时间。
 * （0≤task[i][j]≤1000）
 * 输出格式
 * 输出结果包括一个整数，代表所需要的最少时间（求和）。
 * 样例输入
 * 6
 * 10 11 12 11 9 11
 * 11 9 10 13 11 12
 * 12 10 11 10 13 9
 * 9 14 9 10 10 11
 * 10 10 9 11 12 11
 * 10 7 10 10 10 8
 * 样例输出
 * 54
 */
public class 蒜头君开公司 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                task[i][j] = sc.nextInt();
            }
        }
        dfs(0, 0);
        System.out.println(ans);
    }

    private static void dfs(int x, int sum) {
        if (sum >= ans) return;
        if (x == n) {
            ans = sum;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                dfs(x + 1, sum + task[x][i]);
                vis[i] = false;
            }
        }
    }

    static int[][] task = new int[1010][1010];
    static int n, ans = Integer.MAX_VALUE;
    static boolean[] vis = new boolean[14];
}
