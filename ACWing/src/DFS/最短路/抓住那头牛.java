package DFS.最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104680308
 * bfs
 * 显然边界问题
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
