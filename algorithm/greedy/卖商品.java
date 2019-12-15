package greedy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题意：有N种商品，每种商品有价值和保质期。物品在保质期时间内卖出去才会有回报。问怎么卖才能得到最大回报？
 * 测试用例 4  50 2  10 1   20 2   30 1      表示共有四个商品，第一个价值为50保质期为两天，第二个价值为10保质期为一天。
 * 输出80
 * 测试用例7  20 1   2 1   10 3  100 2   8 2  5 20   50 10
 * 输出185
 * 思路:要在最后一天之前销售出去,并且销售该商品需要1天时间,一天只能销售1个商品,求销售的最大利润,利用贪心策略
 * 商品的价值从大到小排列,找到销售的最大期限,
 */
public class 卖商品 {
    static class Node {
        int profit;
        int deadline;

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public int getDeadline() {
            return deadline;
        }

        public void setDeadline(int deadline) {
            this.deadline = deadline;
        }

        public Node(int profit, int deadline) {
            this.profit = profit;
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("profit=").append(profit);
            sb.append(", deadline=").append(deadline);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxProfit = 0;
        int count = sc.nextInt();
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Node(sc.nextInt(), sc.nextInt()));
        }
        list.sort((o1, o2) -> o2.profit - o1.profit);
        boolean[] visit = new boolean[count];
        System.out.println(list);
        for (int i = 1; i <= count; i++) {

        }
    }
}
/**
 * //#include<bits/stdc++.h>
 * #include<iostream>
 * #include<cstdio>
 * #include<algorithm>
 * #include<cstring>
 * using namespace std;
 * const int MAXN = 10005;
 * int n, f[MAXN];
 * pair<int, int> a[MAXN];
 * int Find(int x)
 * {
 * if (f[x] == -1) return x;//可用的时间点
 * return f[x] = Find(f[x]);
 * }
 * int main()
 * {
 * while(~scanf("%d", &n))
 * {
 * memset(f, -1, sizeof(f));//初始化
 * for (int i = 0; i < n; i++)
 * {
 * scanf("%d%d", &a[i].first, &a[i].second);
 * }
 * sort(a, a + n);//按价格从小到大排序
 * int ans = 0;
 * for (int i = n - 1; i >= 0; i--)
 * {
 * int day = Find(a[i].second);//找到第一个可用的时间点
 * if (day > 0)//不是第0天
 * {
 * f[day] = day - 1;
 * ans += a[i].first;
 * }
 * }
 * printf("%d\n", ans);
 * }
 * return 0;
 * }
 * /*
 * 4  50 2  10 1   20 2   30 1
 * 7  20 1   2 1   10 3  100 2   8 2
 * 5 20  50 10
 */
