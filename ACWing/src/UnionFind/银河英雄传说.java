package UnionFind;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/solution/content/11000/
 * 有一个划分为N列的星际战场，各列依次编号为1,2,…,N。
 * 有N艘战舰，也依次编号为1,2,…,N,其中第i号战舰处于第i列。
 * 有T条指令，每条指令格式为以下两种之一：
 * 1、M i j，表示让第i号战舰所在列的全部战舰保持原有顺序，接在第j号战舰所在列的尾部。
 * 2、C i j，表示询问第i号战舰与第j号战舰当前是否处于同一列中，如果在同一列中，它们之间间隔了多少艘战舰。
 * 现在需要你编写一个程序，处理一系列的指令。
 * 输入格式
 * 第一行包含整数T，表示共有T条指令。
 * 接下来T行，每行一个指令，指令有两种形式：M i j或C i j。
 * 其中M和C为大写字母表示指令类型，i和j为整数，表示指令涉及的战舰编号。
 * 输出格式
 * 你的程序应当依次对输入的每一条指令进行分析和处理：
 * 如果是M i j形式，则表示舰队排列发生了变化，你的程序要注意到这一点，但是不要输出任何信息；
 * 如果是C i j形式，你的程序要输出一行，仅包含一个整数，表示在同一列上，第i号战舰与第j号战舰之间布置的战舰数目，如果第i号战舰与第j号战舰当前不在同一列上，则输出-1。
 * 数据范围
 * N≤30000,T≤500000
 * 输入样例：
 * 4
 * M 2 3
 * C 1 2
 * M 2 4
 * C 4 2
 * 输出样例：
 * -1
 * 1
 * 1. 如果只问是否在同一列,那么直接并查集就可以,
 * 如何维护相差多少战舰呢
 * 2.同时维护间隔多少战舰,绑定到根节点,到排头的距离
 * 用前缀和的思想统一维护当前战舰和第一个的距离记为d[],显然...
 * 1.  |d(x)-d(y)|-1,由于是间隔多少战舰,距离转化为间隔要-1
 * 2.  特判如果 x==y 返回-1
 * 如何维护呢,
 * 1. 让排头当根节点
 * 2. d[pa]=d[pb]
 * 3. size[pb]+=size[pa]
 * 这个可以维护到根节点的距离
 *
 */
public class 银河英雄传说 {
    public static void main(String[] args) throws IOException {
        m = nextInt();
        for (int i = 0; i < N; i++) {
            p[i] = i;
            size[i] = 1;//初始化每一列只有一个
        }
        String ne;
        int a, b, pa, pb;
        while (m-- != 0) {
            ne = next();
            a = nextInt();
            b = nextInt();
            if (ne.charAt(0) == 'M') {
                pa = find(a);
                pb = find(b);
                //pa指向pb,pa这颗树挂在pb上
                //d[pa]初始化为pb这颗树的高度
                d[pa] = size[pb];//d表示,该节点到根节点的距离
                //pa这颗子树到pb的距离就是size[pb]
                size[pb] += size[pa];//pb这一列的元素多了pa个
                //pb这一列多了pa个元素
                p[pa] = pb;
                //pa指向pb
            } else {
                pa = find(a);//a,b都直接指向根节点
                pb = find(b);
                if (pa != pb) System.out.println(-1);
                else System.out.println(Math.max(Math.abs(d[a] - d[b]) - 1, 0));
                //d[a],d[b]都是他们到根的距离,相等返回0,和0取max
            }
        }
    }

    static int find(int x) {
        if (p[x] != x) {
            int root = find(p[x]);
            d[x] += d[p[x]];//让每个点,到pb的距离都加上size[pb],向上合并的时候增加
            p[x] = root;
        }
        return p[x];
    }

    static int n, m, N = 30010;
    static int[] p = new int[N];
    static int[] size = new int[N];
    static int[] d = new int[N];
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
