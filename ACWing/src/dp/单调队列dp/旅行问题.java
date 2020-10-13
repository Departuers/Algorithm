package dp.单调队列dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104578677
 * John 打算驾驶一辆汽车周游一个环形公路。
 * 公路上总共有 n 个车站，每站都有若干升汽油（有的站可能油量为零），每升油可以让汽车行驶一千米。
 * John 必须从某个车站出发，一直按顺时针（或逆时针）方向走遍所有的车站，并回到起点。
 * 在一开始的时候，汽车内油量为零，John 每到一个车站就把该站所有的油都带上（起点站亦是如此），
 * 行驶过程中不能出现没有油的情况。
 * 任务：判断以每个车站为起点能否按条件成功周游一周。
 * 输入格式
 * 第一行是一个整数 n，表示环形公路上的车站数；
 * 接下来 n 行，每行两个整数 pi,di，分别表示表示第 i 号车站的存油量和第 i 号车站到下一站的距离。
 * 输出格式
 * 输出共 n 行，如果从第 i 号车站出发，一直按顺时针（或逆时针）方向行驶，能够成功周游一圈，则在第 i 行输出 TAK，否则输出 NIE。
 * 数据范围
 * 3≤n≤10^6,
 * 0≤pi≤2×10^9,
 * 0<di≤2×10^9
 * 输入样例：
 * 5
 * 3 1
 * 1 2
 * 5 2
 * 0 1
 * 5 4
 * 输出样例：
 * TAK
 * NIE
 * TAK
 * NIE
 * TAK
 * 环展开成链
 * 顺时针做一遍,逆时针做一遍
 * 也就是链两条
 * 维护一个从前到后长度为n的区间最小值,Sj-Si-1>=0
 * 最小值大于等于0,就是安全的,否则不安全
 * 一共有n个点,展开环就有2n个点,对于任意小于等于n的区间,及其子区间都要大于等于0
 * 找到一个区间最小值,看是不是大于等于0,如果最小的,所有的长度小于等于n的区间都大于等于0,那就是成功
 * i~i+n-1   si-s(i-1)>=0,所有的区间和都是>=0
 * 区间最值
 */
public class 旅行问题 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            b[i] = nextInt();
        }
        for (int i = 1; i <= n; i++) {
            s[i] = s[i + n] = a[i] - b[i];
        }//两两差值
        for (int i = 1; i <= n * 2; i++) {
            s[i] += s[i - 1];
        }
        int hh = 0, tt = -1;
        for (int i = n * 2; i != 0; i--) {
            if (hh <= tt && q[hh] >= i + n) hh++;//由于算本身,所以>=就要删掉
            while (hh <= tt && s[q[tt]] >= s[i]) tt--;
            q[++tt] = i;
            if (i <= n) {
                if (s[q[hh]] >= s[i - 1]) ans[i] = true;
            }
        }
        b[0] = b[n];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i + n] = a[i] - b[i - 1];
        }
        for (int i = 1; i <= n * 2; i++) {
            s[i] += s[i - 1];
        }
        hh = 0;
        tt = -1;
        for (int i = 1; i <= n * 2; i++) {
            if (hh <= tt && q[hh] < i - n) hh++;
            if (i > n) {
                if (s[q[hh]] <= s[i]) ans[i - n] = true;
            }
            while (hh <= tt && s[q[tt]] <= s[i]) tt--;
            q[++tt] = i;
        }
        for (int i = 1; i <= n; i++) {
            if (ans[i]) System.out.println("TAK");
            else System.out.println("NIE");
        }
    }

    static int n, N = (int) (2e6 + 10);
    static boolean[] ans = new boolean[N];
    static long[] s = new long[N];
    static int[] q = new int[N];
    static long[] a = new long[N];
    static long[] b = new long[N];

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
}
