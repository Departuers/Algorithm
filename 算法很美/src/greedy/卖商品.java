package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 题意：有N种商品，每种商品有价值和保质期。物品在保质期时间内卖出去才会有回报。问怎么卖才能得到最大回报？
 * 测试用例 4  50 2  10 1   20 2   30 1      表示共有四个商品，第一个价值为50保质期为两天，第二个价值为10保质期为一天。
 * 输出80
 * 测试用例7  20 1   2 1   10 3  100 2   8 2  5 20   50 10
 * 输出185
 * 思路:要在最后一天之前销售出去,并且销售该商品需要1天时间,一天只能销售1个商品,求销售的最大利润,利用贪心策略
 * 商品的价值从大到小排列,找到销售的最大期限,用visit标记,如果它的期限没有被占用就占用这一天,在这天销售
 * 如果占用了,则从前一天开始,向前查找有没有空闲的日期,如果有则占用
 */
public class 卖商品 {
    static class Node {
        int profit;//价值
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
        ArrayList<Node> list = new ArrayList<Node>();
        for (int i = 0; i < count; i++) {
            list.add(new Node(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.profit - o1.profit;
            }
        });
        //按照商品价格从大到小排列,因为是按商品价格从大到小排的序,使得同一天,价格更高的商品排在更前面,
        // 所以从头开始遍历才能,只判断visit有没有被占用,没有占用直接往里占,占用了往前找
        boolean[] visit = new boolean[1005];//实际上是天数,不知道多少天,所以多写点
        System.out.println(list);
        for (int i = 0; i < count; i++) {
            if (!visit[list.get(i).deadline]) {//如果这一天没有被占用
                maxProfit += list.get(i).profit;//要了这一天
                visit[list.get(i).deadline] = true;//占用这一天
            } else {
                for (int j = list.get(i).deadline - 1; j >= 1; j--) {
                    if (!visit[j]) {
                        maxProfit += list.get(i).profit;
                        visit[j] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(maxProfit);
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
