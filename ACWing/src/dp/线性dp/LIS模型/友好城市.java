package dp.线性dp.LIS模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定N对城市修桥申请,只有申请才能修桥,不能有交叉
 * 问怎么修才可以使得修的桥最多
 * 转换为LIS问题
 * 先按照第一个排序,再求第二个的最长上升子序列,按照第二个关键字排序也可以
 * 合法的建桥方式和按照第一关键字排序后,求第二关键字的上升子序列是一一对应的
 * 不能有交叉说明什么,不能有逆序,以一种升序排序,求另一个的LIS
 * 就能保持不交叉,就能求出结果
 */
public class 友好城市 {
    static class node implements Comparable<node> {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return y - node.y;
        }
    }

    static node[] f = new node[5006];
    static int[] dp = new int[5005];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            f[i] = new node(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(f, 0, n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (f[i].x > f[j].x) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
