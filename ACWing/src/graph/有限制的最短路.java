package graph;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * POJ 1724
 * 你手里有 K 个金币，有一张 N 个点，M 条有向边的图。
 * 每条边用 (S, D, L, T) 来表示从 S 到 D 有一条有向边，距离为
 * L，过路费为 T。
 * 现在希望你求出从 1 走到 N 在使用的金币数不超过 K 的情况下
 * 的最短路。
 * K <= 10000, N <= 100, R <= 10000
 * 样例：（输出7）
 * 5 6 7
 * 1 2 2 3
 * 2 4 3 3
 * 3 4 2 4
 * 1 3 4 1
 * 4 6 2 1
 * 3 5 2 0
 * 5 4 3 2
 * 显然题目要求k最小,dis最小是多少
 * 还可以改成...
 * 与以往的最短路问题不同的是，每条边上有两个参数。
 * 我们要保证在花费的钱不超过 K 的情况下，最小化距离之和。
 * https://blog.csdn.net/constbh/article/details/78176879
 */
public class 有限制的最短路 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }

    public static void main(String[] args) throws IOException {
        k = nextInt();
        n = nextInt();
        m = nextInt();
        for (int i = 0; i < m; i++) {
            add(nextInt(), nextInt(), nextInt(), nextInt());
        }
        diss();
    }

    static void diss() {
        Arrays.fill(mon, Integer.MAX_VALUE / 2);
        q.add(new node(1, 0, 0));
        dis[1] = 0;
        mon[1] = 0;
        while (!q.isEmpty()) {
            node poll = q.poll();
            int st = poll.to;
            if (poll.to == n) {
                System.out.println(poll.dis);
                System.out.println(poll.cost);
                break;
            }
            for (int i = he[st]; i != 0; i = ne[i]) {
                int ed = e[i];
                if (k >= poll.cost + money[i]) {
                    mon[ed] = poll.cost + money[i];
                    dis[ed] = poll.dis + w[i];
                    q.add(new node(ed, dis[ed], mon[ed]));
                }
            }
        }
    }

    //k最小,dis最小是多少
    static void dij() {
        q.add(new node(1, 0, 0));
        boolean f = false;
        while (!q.isEmpty()) {
            node poll = q.poll();
            int st = poll.to;
            if (poll.to == n) {
                System.out.println(poll.dis);
                System.out.println(poll.cost);
                f = true;
                break;
            }
            for (int i = he[st]; i != 0; i = ne[i]) {
                int cost = poll.cost + money[i];
                if (k >= cost) {
                    q.add(new node(e[i], w[i] + poll.dis, cost));
                }
            }
        }
        if (!f) System.out.println(-1);
    }

    static PriorityQueue<node> q = new PriorityQueue<node>();

    static class node implements Comparable<node> {
        int to, dis, cost;

        public node(int to, int dis, int cost) {
            this.to = to;
            this.dis = dis;
            this.cost = cost;
        }

        @Override
        public int compareTo(node node) {
            if (this.dis == node.dis)
                return this.cost - node.cost;
            return this.dis - node.dis;
        }
    }

    //static int[][] dis = new int[102][10010];// dis[i][j]表示从点1到点i花费的钱数为j的最短距离
    static int[] he = new int[110];
    static int[] ne = new int[11000];
    static int[] e = new int[11000];
    static int[] w = new int[11000];
    static int[] money = new int[10010];

    static void add(int a, int b, int c, int d) {
        e[cnt] = b;
        money[cnt] = d;
        w[cnt] = c;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int n, m, k, cnt = 1;
    static int[] dis = new int[10005];
    static int[] mon = new int[10005];

}
