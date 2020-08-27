package 线段树;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/blog/content/487/
 * https://www.cnblogs.com/TaylorSwift13/p/11228276.html
 */
public class 动态主席树 {
    public static void main(String[] args) {

    }

    static int N = 200005, n, m, c1, c2;
    static int[] a = new int[N];//原数组
    static int[] tem = new int[N];
    static int[] x = new int[N];
    static int[] y = new int[N];

    static void d() {

    }

    static int un(int[] a, int n) {
        int j = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1 || a[i] != a[i - 1])
                a[j++] = a[i];
        }
        return j;
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
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
