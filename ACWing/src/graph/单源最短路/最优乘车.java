package graph.单源最短路;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/SWEENEY_HE/article/details/81413683
 * Ｈ城是一个旅游胜地，每年都有成千上万的人前来观光．为方便游客，巴士公司在各个旅游景点及宾馆、
 * 饭店等地都设置了巴士站，并开通了一些单向巴士线路。每条单向巴士线路从某个巴士站出发，
 * 依次途径若干个巴士站，最终到达终点巴士站。
 * 阿昌最近到Ｈ城旅游，住在ＣＵＰ饭店。他很想去Ｓ公园游玩。听人说，
 * 从ＣＵＰ饭店到Ｓ公园可能有也可能没有直通巴士。如果没有，就要换乘不同线路的单向巴士，
 * 还有可能无法乘巴士到达。现在用整数１，２，...，ｎ给Ｈ城的所有巴士站编号，约定ＣＵＰ饭店的巴士站编号为１，
 * Ｓ公园巴士站的编号为Ｎ。写一个程序，帮助阿昌寻找一个最优乘车方案，使他在从ＣＵＰ饭店到Ｓ公园的过程中换车的次数最少。
 * 车站：1    2    3    4    5
 * 第一条路线：1 2 3 5
 * 第二条路线：1 4
 * 第三条路线：4 5
 * 则直接选择第一条路线，换车次数最少为0
 * 输入
 * 第1行是一个数字M（1≤M≤100）表示开通了M条单向巴士线路，第2行是一个数字N（1<N≤500），
 * 表示共有N个车站。从第3行到第M+2行依次给出了第一条到第M条巴士线路的信息。
 * 其中第i+2行给出的是第i条巴士线路的信息，从左至右依次按行行驶顺序给出了该线路上的所有站点，相邻两个站号之间用一个空格隔开。
 * 输出
 * 为最少换车次数（在0，1，…，M-1中取值），0表示不需换车即可达到。如果无法乘车达到S公园，则输出"NO"。
 * 该题可将一趟公交车经过的站点看成一张图中连通的顶点，且站台之间的距离都为1。
 * 每换乘一次会必会使得路径长度加1（因为换乘的目的就是向终点站靠近，
 * 所以换乘后的公交到达的最远站点序号必定大于换乘前能到达的最远序号，
 * 又由于第一次换乘前至少搭过一个站点（每趟公交至少经过两个站点否则无法换乘），即初始路径为1，所以有：换乘数 = 路径-1；
 * 如：样例中 1 - 4的距离为1，2-3，2-4，2-5，3-4，4-5 的距离均为1。
 * 第一条公交：1->4，路径 = 1，换乘到第二条公交：4->5，路径 = 2。换乘数 = 路径 - 1 = 1
 * 2 5
 * 1 4
 * 2 3 4 5
 * out
 * 1
 * 样例输出
 * 3 7
 * 6 7
 * 4 7 3 6
 * 2 1 3 5
 * out
 * 2
 * 建图,问题,
 * 2 3 4 5可以看做2-3  2-4  2-5  3-4  3-5 4-5
 * 抽象建图!想法很重要!
 * 显然:2可以直接到3,2也可以直接到4,2也可以直接到5
 * 边权都为1可以使用bfs做
 * 有点像传递闭包,但不用Floyd求解
 * 则显然求最小换乘次数,转换成求最短路径问题
 */
public class 最优乘车 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        System.out.println(sc.nextLine());
        int[] stop = new int[510];
        for (int t = 0; t < m; t++) {
            String[] s2 = sc.nextLine().split(" ");
            for (int i = 0; i < s2.length; i++) {
                stop[i] = Integer.parseInt(s2[i]);
            }
            for (int i = 0; i < s2.length - 1; i++)
                for (int j = i + 1; j < s2.length; j++)
                    g[stop[i]][stop[j]] = 1;
        }
        bfs();
        if (dist[n] == inf) System.out.println("NO");
        else
            System.out.println(Math.max(dist[n] - 1, 0));
    }

    static int[][] g = new int[510][510];
    static int[] dist = new int[510];
    static int n, m, inf = 0x3f3f3f3f;

    static void bfs() {
        Arrays.fill(dist, inf);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(1);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = 1; i <= n; i++) {
                if (g[t][i] == 1 && dist[i] > dist[t] + 1) {
                    dist[i] = dist[t] + 1;
                    q.add(i);
                }
            }
        }
    }
}
