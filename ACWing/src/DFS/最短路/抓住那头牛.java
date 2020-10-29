package DFS.最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104680308
 * 农夫知道一头牛的位置，想要抓住它。
 * 农夫和牛都位于数轴上，农夫起始位于点 N，牛位于点 K。
 * 农夫有两种移动方式：
 * 从 X 移动到 X−1 或 X+1，每次移动花费一分钟
 * 从 X 移动到 2∗X，每次移动花费一分钟
 * 假设牛没有意识到农夫的行动，站在原地不动。
 * 农夫最少要花多少时间才能抓住牛？
 * 输入格式
 * 共一行，包含两个整数N和K。
 * 输出格式
 * 输出一个整数，表示抓到牛所花费的最少时间。
 * 数据范围
 * 0≤N,K≤10^5
 * 输入样例：
 * 5 17
 * 输出样例：
 * 4
 * bfs
 * 考虑边界问题
 * n*2不会超过20w+10
 * n-1不会低于0
 * 如果越界则无需记录
 */
public class 抓住那头牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        Arrays.fill(d, -1);
        d[n] = 0;
        q.add(n);
        //bfs
        while (!q.isEmpty()) {
            int t = q.poll();
            if (t == k) {
                System.out.println(d[t]);
                break;
            }
            if (t + 1 < N && d[t + 1] == -1) {
                d[t + 1] = d[t] + 1;
                q.add(t + 1);
            }
            if (t - 1 >= 0 && d[t - 1] == -1) {
                d[t - 1] = d[t] + 1;
                q.add(t - 1);
            }
            if (t * 2 < N && d[t * 2] == -1) {
                q.add(t * 2);
                d[t * 2] = d[t] + 1;
            }
        }
    }

    static int N = 201000;
    static int[] d = new int[N];
    static int n, k;
}
