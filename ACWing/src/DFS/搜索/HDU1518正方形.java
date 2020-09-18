package DFS.搜索;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 属于分组搜索
 * <p>
 * 正方形问题：
 * 要求给定的所有木棍能否组成一个正方形
 * 那么正方形的变长应该是所有木棍长度之和的四分之一 eage，如果不能整除一定不能构成正方形，
 * 而且当给定的数组中最大值大于eage的时候也不能组成正方形
 * 定义sum[4]数组，用来分别存放每条边的长度，
 * 每条边都有 n  种木棍可以选择，而且一条边可以有多个木棍，
 * 所以回溯过程中，将每一条木棍加入每个边
 * 如果，t==n 到达解空间，此时如果 sum[4] 中的每一个都等于开始算出的边长eage则可组成正方形，反之不可以
 */
public class HDU1518正方形 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        t = nextInt();
        while (t-- != 0) {
            half = 0;
            Arrays.fill(st, false);
            n = nextInt();
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
                half += a[i];
            }
            if (half % 4 != 0) {
                System.out.println("no");
            } else {
                half /= 4;
                f = false;
                dfs(0, 0, 0, 0);
                if (f) System.out.println("yes");
                else System.out.println("no");
            }
        }
    }

    static int n, half = 0, t;
    static boolean[] st = new boolean[10005];
    static int[] a = new int[10005];
    static boolean f = false;

    /**
     * @param u     u代表扫到了数组哪一个位置
     * @param sum   当前长度之和,等于half也就是总长度/4代表找完一组边可以组成边
     * @param edges 边数
     * @param x     下一层的枚举起点
     */
    static void dfs(int u, int sum, int edges, int x) {
        if (f) return;
        if (sum == half) {
            sum = 0;
            edges++;
            x = 0;
        }
        if (u == n) {
            if (edges == 4) {
                f = true;
            }
            return;
        }
        for (int i = x; i < n; i++) {
            if (!st[i] && sum + a[i] <= half) {
                st[i] = true;
                dfs(u + 1, sum + a[i], edges, i + 1);
                st[i] = false;
            }
        }
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
