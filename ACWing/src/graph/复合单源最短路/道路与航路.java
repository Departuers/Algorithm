package graph.复合单源最短路;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/106629032
 * 农夫约翰正在一个新的销售区域对他的牛奶销售方案进行调查。
 * 他想把牛奶送到T个城镇，编号为1~T。
 * 这些城镇之间通过R条道路 (编号为1到R) 和P条航线 (编号为1到P) 连接。
 * 每条道路 i 或者航线 i 连接城镇Ai到Bi，花费为Ci。
 * 对于道路，0≤Ci≤10,000;然而航线的花费很神奇，花费Ci可能是负数(−10,000≤Ci≤10,000)。
 * 道路是双向的，可以从Ai到Bi，也可以从Bi到Ai，花费都是Ci。
 * 然而航线与之不同，只可以从Ai到Bi。
 * 事实上，由于最近恐怖主义太嚣张，为了社会和谐，出台了一些政策：
 * 保证如果有一条航线可以从Ai到Bi，那么保证不可能通过一些道路和航线从Bi回到Ai。
 * 由于约翰的奶牛世界公认十分给力，他需要运送奶牛到每一个城镇。
 * 他想找到从发送中心城镇S把奶牛送到每个城镇的最便宜的方案。
 * 输入格式
 * 第一行包含四个整数T,R,P,S。
 * 接下来R行，每行包含三个整数（表示一个道路）Ai,Bi,Ci。
 * 接下来P行，每行包含三个整数（表示一条航线）Ai,Bi,Ci。
 * 输出格式
 * 第1..T行：第i行输出从S到达城镇i的最小花费，如果不存在，则输出“NO PATH”。
 * 数据范围
 * 1≤T≤25000,
 * 1≤R,P≤50000,
 * 1≤Ai,Bi,S≤T,
 * 输入样例：
 * 6 3 3 4
 * 1 2 5
 * 3 4 5
 * 5 6 10
 * 3 5 -100
 * 4 6 -100
 * 1 3 -10
 * 输出样例：
 * NO PATH
 * NO PATH
 * 5
 * 0
 * -95
 * -100
 * 如果边权非负使用Dijkstra算法
 * spfa会被卡
 * 把双向的非负块节点,用Dijkstra算法
 * 把一群节点,看做一个节点块,按照拓扑序跑
 */
public class 道路与航路 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < block.length; i++) {
            block[i] = new ArrayList<Integer>();
        }
        n = sc.nextInt();
        mr = sc.nextInt();
        mp = sc.nextInt();
        S = sc.nextInt();
        int a, b, c;
        while (mr-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        for (int i = 1; i <= n; i++) {
            if (id[i] == 0) {
                dfs(i, ++bcnt);
            }
        }
        while (mp-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
        }

    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();
    private static void dfs(int u, int bid) {
        id[u] = bid;
        block[bid].add(u);
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (id[j] == 0)
                dfs(j, bid);
        }
    }

    private static void add(int a, int b, int c) {
        e[cnt] = b;
        w[cnt] = c;
        ne[cnt] = h[a];
        h[a] = cnt++;
    }

    static int n, mr, mp, S, N = 25010, M = 150010, cnt = 1, bcnt = 0;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static int[] id = new int[N];
    static int[] dis = new int[N];
    static boolean[] st = new boolean[N];
    static ArrayList<Integer> block[] = new ArrayList[N];


}
