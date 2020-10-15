package night;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 小明维护着一个程序员论坛。现在他收集了一份”点赞”日志，日志共有 N行。
 * 其中每一行的格式是：ts id
 * 表示在 ts时刻编号 id的帖子收到一个”赞”。
 * 现在小明想统计有哪些帖子曾经是”热帖”。
 * 如果一个帖子曾在任意一个长度为 D
 * 的时间段内收到不少于 K
 * 个赞，小明就认为这个帖子曾是”热帖”。
 * 具体来说，如果存在某个时刻 T
 * 满足该帖在 [T,T+D) 这段时间内(注意是左闭右开区间)收到不少于 K个赞，该帖就曾是”热帖”。
 * 给定日志，请你帮助小明统计出所有曾是”热帖”的帖子编号。
 * 输入格式
 * 第一行包含三个整数 N,D,K
 * 以下 N
 * 行每行一条日志，包含两个整数 ts 和 id
 * 输出格式
 * 按从小到大的顺序输出热帖 id
 * 每个 id
 * 占一行。
 * 数据范围
 * 1≤K≤N≤105
 * 0≤ts,id≤105,
 * 1≤D≤10000
 * 输入样例：
 * 7 10 2
 * 0 1
 * 0 10
 * 10 10
 * 10 1
 * 9 1
 * 100 3
 * 100 3
 * 输出样例：
 * 1
 * 3
 * 双指针
 */
public class 日志统计 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            logs[i] = new log(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(logs, 0, n);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0, j = 0; i < n; i++) {
            int id = logs[i].id;
            cnt[id]++;
            while (logs[i].time - logs[j].time >= d) {
                cnt[logs[j].id]--;
                j++;
            }
            if (cnt[id] >= k) {
                st[id] = true;
            }
        }
        for (int i = 0; i < 1e5; i++) {
            if (st[i])
                System.out.println(i);
        }
    }

    static int n, d, k, N = (int) (1e5 + 10);
    static log[] logs = new log[N];
    static int[] cnt = new int[N];
    static boolean[] st = new boolean[N];

    static class log implements Comparable<log> {
        int time, id;

        public log(int time, int id) {
            this.time = time;
            this.id = id;
        }

        @Override
        public int compareTo(log log) {
            return time - log.time;
        }
    }

}
