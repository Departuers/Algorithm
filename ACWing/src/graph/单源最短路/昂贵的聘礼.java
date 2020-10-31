package graph.单源最短路;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/a1097304791/article/details/88971315
 * 你想娶酋长的女儿，但酋长要求你给一定数额金钱的聘礼。除了金钱外，酋长也允许你用部落里其他人的某物品加上一点钱作为聘礼。
 * 而其他人的物品也可以通过指定的另外一些人的某物品加上一些金钱获得。部落里的每个人有一个等级。
 * 你的整个交易过程涉及的人的等级只能在一个限定的差值内。问你最少需要多少金钱才能娶到酋长女儿。假定每个人只有一个物品。
 * Input
 * 输入第一行是两个整数M，N（1 <= N <= 100），依次表示地位等级差距限制和物品的总数。接下来按照编号从小到大依次给出了N个物品的描述。
 * 每个物品的描述开头是三个非负整数P、L、X（X < N），依次表示该物品的价格、主人的地位等级和替代品总数。
 * 接下来X行每行包括两个整数T和V，分别表示替代品的编号和"优惠价格"。
 * Output
 * 输出最少需要的金币数。
 * Examples
 * Sample Input
 * 1 4
 * 10000 3 2
 * 2 8000
 * 3 5000
 * 1000 2 1
 * 4 200
 * 3000 2 1
 * 4 200
 * 50 2 0
 * <p>
 * Sample Output
 * 5250
 * 例： 最高最低等级差不超过1，共4个物品 酋长的女儿 等级3 要求现金10000元 或甲的物品+8000元 或乙的物品+5000元 甲的物品 等级2 要求现金1000元
 * 或丙的物品+200元 乙的物品 等级2 要求现金3000元 或丙的物品+200元 丙的物品 等级2 要求现金50元 在这个例子中，
 * 最少花费方案是：买丙的东西（50）换乙的东西（+200）换酋长女儿（+5000）一共5250元。
 * POJ - 1062
 * 每一种购买方式
 * 都可以对应虚拟源点到到达终点的一条路径
 * 寻找最短路
 * 等级问题的话,枚举等级区间
 */
@SuppressWarnings("all")
public class 昂贵的聘礼 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        for (int i = 0; i < w.length; i++) {
            Arrays.fill(w[i], 0x3f3f3f3f);
            w[i][i] = 0;
        }
        int price, cnt;
        for (int i = 1; i <= n; i++) {
            price = sc.nextInt();
            level[i] = sc.nextInt();
            cnt = sc.nextInt();
            w[0][i] = Math.min(price, w[0][i]);
            //0 代表直接去买i号物品
            while (cnt-- != 0) {
                int id, cost;
                id = sc.nextInt();
                cost = sc.nextInt();
                w[id][i] = Math.min(w[id][i], cost);
                //id物品去买i号物品需要
            }
        }
        /**
         * 因为最终要娶到公主,所以等级只能只能在level[1]也就是酋长的等级
         * level[1]-m ~ level[1]+m 这个区间内
         */
        int res = 0x3f3f3f3f;
        for (int i = level[1] - m; i <= level[1]; i++) {
            res = Math.min(res, dijkstra(i, i + m));
        }
        System.out.println(res);
    }

    /**
     * 枚举等级区间!来实现等级差距过大无法交易
     * n^2实现Dijkstra
     *
     * @param down
     * @param up
     * @return
     */
    static int dijkstra(int down, int up) {
        Arrays.fill(dist, 0x3f3f3f3f);
        Arrays.fill(vis, false);
        dist[0] = 0;
        for (int i = 1; i <= n; i++) {//一共有n个点
            int t = -1;
            for (int j = 0; j <= n; j++) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) t = j;
            }//不用优先队列,直接找出最小的拓展,由于是邻接矩阵存储权值
            vis[t] = true;
            for (int j = 1; j <= n; j++) {
                if (level[j] >= down && level[j] <= up) {
                    dist[j] = Math.min(dist[j], dist[t] + w[t][j]);
                }
            }
        }
        return dist[1];
    }

    static int[][] w = new int[110][110];
    static int[] level = new int[110];
    static int[] dist = new int[110];
    static boolean[] vis = new boolean[110];
    static int n, m;
}
