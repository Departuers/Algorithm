package 树状数组;

import java.io.*;
import java.util.StringTokenizer;


/**
 * 1. 区间加,用差分来做 b[]
 * 使a[L,R]+=C   b[L]+=C   b[R+1]-=C
 * 区间求和a1+a2+a3+...+ax
 * 其实是一个两维和
 * 维护两个树状数组
 * bi的前缀和
 * i*bi的前缀和
 */
public class 区间加区间求和 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }
        for (int i = 1; i <= n; i++) {
            int b = a[i] - a[i - 1];
            add(tr1, i, b);
            add(tr2, i, (long) i * b);
        }

        while (m-- > 0) {
            String s = next();
            int l = nextInt();
            int r = nextInt();
            if (s.charAt(0) == 'Q') {
                bw.write(pre(r) - pre(l - 1) + "\n");
            } else {
                int d = nextInt();
                add(tr1, l, d);
                add(tr2, l, (long) l * d);
                add(tr1, r + 1, -d);
                add(tr2, r + 1, (long) (r + 1) * -d);
            }
        }
        bw.flush();
    }

    static int N = 100100, n, m;
    static int[] a = new int[N];//源数组
    static long[] tr1 = new long[N];//bi的前缀和
    static long[] tr2 = new long[N];//bi*i的前缀和

    static long pre(int x) {
        return ask(tr1, x) * (x + 1) - ask(tr2, x);
    }

    static void add(long[] tr, int x, long v) {
        for (int i = x; i <= n; i += (i & -i)) {
            tr[i] += v;
        }
    }

    static long ask(long[] tr, int x) {
        long rse = 0;
        for (int i = x; i != 0; i -= (i & -i)) {
            rse += tr[i];
        }
        return rse;
    }
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
