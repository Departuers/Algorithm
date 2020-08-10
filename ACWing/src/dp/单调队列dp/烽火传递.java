package dp.单调队列dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104580927
 * f[i]表示前1~i且点燃第i个烽火台,
 * 属性:最小代价
 * f[i]找倒数第2个, i-1 i-2...i-m+1 i-m
 * f[i]=min(f[j] | i-m<=j<i )+w[i] 单调队列优化
 */
public class 烽火传递 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }
        ydp();
        f();
    }

    //单调队列dp
    static void ydp() {
        int hh = 0, tt = 0;

        int[] q = new int[(int) (2e5 + 10)];
        for (int i = 1; i <= n; i++) {

            if (i - q[hh] > m) hh++;
            f[i] = f[q[hh]] + a[i];
            while (hh <= tt && f[i] <= f[q[tt]]) tt--;
            q[++tt] = i;
            System.out.print(f[i] + " ");
        }
        System.out.println();
        int res = (int) 1e9;
        for (int i = n - m + 1; i <= n; i++) {
            res = Math.min(res, f[i]);
        }
        System.out.println(res);
    }

    //朴素dp
    public static void f() {
        for (int i = 1; i <= n; i++) {
            if (i <= m) {
                f[i] = a[i];
            } else {
                int t = f[i - 1];
                for (int j = i - m; j < i - 1; j++) {
                    t = Math.min(f[j], t);
                }
                f[i] = t + a[i];
            }
        }
        System.out.println(Arrays.toString(f));
        System.out.println(f[n]);
    }

    static int[] f = new int[(int) (2e5 + 10)];

    static int[] a = new int[(int) (2e5 + 10)];
    static int n, m;
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
