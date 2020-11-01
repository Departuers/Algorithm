package graph.topoSort;

import java.io.*;
import java.util.BitSet;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://blog.csdn.net/qq_41280600/article/details/102544874
 * 给定一张N个点M条边的有向无环图，分别统计从每个点出发能够到达的点的数量。
 * 输入格式
 * 第一行两个整数N,M，接下来M行每行两个整数x,y，表示从x到y的一条有向边。
 * 输出格式
 * 输出共N行，表示每个点能够到达的点的数量。
 * 数据范围
 * 1≤N,M≤30000
 * 输入样例：
 * 10 10
 * 3 8
 * 2 3
 * 2 5
 * 5 9
 * 5 9
 * 2 3
 * 3 9
 * 4 8
 * 2 10
 * 4 9
 * 输出样例：
 * 1
 * 6
 * 3
 * 3
 * 2
 * 1
 * 1
 * 1
 * 1
 * 1
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * 8
 * 9
 * 10
 * 二、思路
 * 首先求出这幅图的拓扑序列，然后从最后往前进行统计每个点能够到达的点数。
 * 由于N=3e4 所以我们用二进制进行状态压缩。用一个N位二进制进行压缩，
 * 每位1代表该点可以到达，0表示不可到达。
 * <p>
 * 由于是DAG,做dp
 * 假设i能到j1,j2,j3...jn
 * f[i]代表所有能从i点到的集合
 * f[i]={i}并上f[j1]并上f[j2]兵上f[j3]...∪f[jn]
 * 所以可以按照topo排序的逆序遍历,分别求每个f[i]即可
 * 集合使用二进制表示,状态压缩10010  0代表不能到,1代表可以到,长度为n的二进制串
 */
public class 可达性统计 {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < f.length; i++) {
            f[i] = new BitSet();
        }
        n = nextInt();
        m = nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            add(a, b);
            d[b]++;
        }
        toposort();
        for (int i = n - 1; i >= 0; i--) {//从后往前,
            int j = q[i];
            f[j].set(j);
            for (int k = h[j]; k != 0; k = ne[k]) {
                f[j].or(f[e[k]]);
            }//并上相邻的f[j]
        }
        for (int i = 1; i <= n; i++) {
            bw.write(get(f[i]) + "\n");
        }
        bw.flush();
    }

    static int get(BitSet b) {
        int r = 0;
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i)) r++;
        }
        return r;
    }

    static int N = 30010, M = 30010, n, m, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] d = new int[N];
    static int[] dist = new int[N];
    static BitSet[] f = new BitSet[N];//使用二进制记录集合
    static int[] q = new int[N];

    static void toposort() {
        int hh = 0, tt = -1;
        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) {
                q[++tt] = i;
            }
        }//入度为0的点加入队列

        while (hh <= tt) {
            int t = q[hh++];//出队
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                --d[j];
                if (d[j] == 0) {//入度为0加入队列
                    q[++tt] = j;
                }
            }
        }
    }

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }


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
