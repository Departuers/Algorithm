package 树状数组;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/solution/content/21125/
 * 难点是推算出每个小朋友移动的次数是k1+k2是固定的
 * k1为小于这个小朋友高度的个数 k2为大于这个小朋友高度的个数
 * 利用树状数组快速求和
 */
public class 小朋友排队 {
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

    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt() + 1;//避免0
        }
        for (int i = 1; i <= n; i++) {
            b[i] = ask(N - 1) - ask(a[i]);
            add(a[i], 1);
        }

        Arrays.fill(tr, 0);

        for (int i = n; i >= 1; i--) {
            b[i] += ask(a[i] - 1);
            add(a[i], 1);
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += (1 + b[i]) * b[i] / 2;
        }
        System.out.println(res);
    }

    static int N = 1000005, n;
    static int[] tr = new int[N], a = new int[N];
    static long[] b = new long[N];

    static void add(int a, int b) {
        for (; a < N; a += a & -a) {
            tr[a] += b;
        }
    }

    static long ask(int a) {
        long res = 0;
        for (; a != 0; a -= a & -a) {
            res += tr[a];
        }
        return res;
    }
}
