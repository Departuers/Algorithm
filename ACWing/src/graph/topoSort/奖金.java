package graph.topoSort;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.hzxueyan.com/archives/104/
 * 由于无敌的凡凡在 2005 年世界英俊帅气男总决选中胜出，Yali Company 总经理 Mr.Z 心情好，决定给每位员工发奖金。
 * 公司决定以每个人本年在公司的贡献为标准来计算他们得到奖金的多少。
 * 于是 Mr.Z 下令召开 m 方会谈。
 * 每位参加会谈的代表提出了自己的意见：“我认为员工 a 的奖金应该比 b高！”
 * Mr.Z 决定要找出一种奖金方案，满足各位代表的意见，且同时使得总奖金数最少。
 * 每位员工奖金最少为 100 元，且必须是整数。
 * 输入
 * 第一行包含整数 n, m分别表示公司内员工数以及参会代表数。
 * 接下来 m 行，每行 2 个整数 a,b，表示某个代表认为第 a 号员工奖金应该比第 b号员工高。
 * 输出
 * 若无法找到合理方案，则输出Poor Xed；
 * 否则输出一个数表示最少总奖金。
 * 数据范围
 * 1≤n≤10000
 * 1≤m≤20000
 * 输入样例
 * 2 1
 * 1 2
 * 输出样例
 * 201
 * input
 * 3 2
 * 1 2
 * 1 3
 * out:
 * 301
 * 差分约束问题, a>=b+1
 * “我认为员工 a 的奖金应该比 b 高！”，相当于存在一条有向边,边权为1,从 b 连接到 a，
 * 三角不等式
 * 求最长路,最小值
 * 所以用拓扑排序
 * <p>
 * 表示 a 比 b 的奖金高，按照此方法建立一个图出来
 * 为了方便理解，记umap[i] = 100表示员工 i 的奖金为 100100
 * 每次将入度为0 的点加入进队列时，需要将这些点的奖金设置为 100100
 * 然后取出对头点 nownow 所连接的点 v，它的奖金就应该是umap[v] = umap[now] + 1，
 * 因为入度为 0 的点所指向的点 v，表示员工 now 比员工 v 的奖金低，
 * 由于奖金是整数，所以这里加 1。
 * <p>
 * 起始奖金为100,虚拟源点,连向其他点,边权为100
 * 初值设为100,dist[i]=100
 */
public class 奖金 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b;
        while (m-- != 0) {
            a = nextInt();
            b = nextInt();
            add(b, a);
            d[a]++;//入度
        }
        if (toposort()) {
            for (int i = 1; i <= n; i++) {
                dist[i] = 100;
            }//所有初始值为100

            //拓扑图求最长路
            for (int i = 0; i < n; i++) {
                int j = res.get(i);
                for (int k = h[j]; k != 0; k = ne[k]) {
                    int tt = e[k];
                    dist[tt] = Math.max(dist[tt], dist[j] + 1);
                }
            }
            int res = 0;
            for (int i = 1; i <= n; i++) {
                res += dist[i];
            }
            System.out.println(res);
        } else System.out.println("Poor Xed");
    }

    static ArrayList<Integer> res = new ArrayList<Integer>();

    private static boolean toposort() {
        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) {
                q.add(i);
                res.add(i);
            }
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                --d[j];
                if (d[j] == 0) {
                    res.add(j);
                    q.add(j);
                }
            }
        }
        return res.size() == n;
    }

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static int N = 10010, M = 20010, n, m, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] d = new int[N];
    static int[] dist = new int[N];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
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

}
