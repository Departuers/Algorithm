package 树状数组;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 单点修改,区间查询
 * https://www.luogu.com.cn/problemnew/solution/P3368
 * tle2个
 */

public class 树状数组 {
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

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            add(i, nextInt());
        }
        int x, y, z;
        while (m-- != 0) {
            x = nextInt();
            if (x == 1) {
                y = nextInt();
                z = nextInt();
                add(y, z);
            } else {
                y = nextInt();
                z = nextInt();
                bw.write(query(y, z ) + "\n");
            }
        }
        bw.flush();
    }

    static int[] par = new int[500005];
    static int n, m;

    static void add(int i, int x) {
        while (i <= n) {
            par[i] += x;
            i += lowbit(i);
        }
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    static int query(int l, int r) {
        return get(r) - get(l - 1);
    }

    static int get(int x) {
        int res = 0;
        while (x != 0) {
            res += par[x];
            x -= lowbit(x);
        }
        return res;
    }
}
