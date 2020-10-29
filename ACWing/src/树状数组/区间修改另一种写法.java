package 树状数组;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class 区间修改另一种写法 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }
        for (int i = 1; i <= n; i++) {
            add(i, a[i] - a[i - 1]);
        }//构造差分数组

        int x, y, z, t;
        while (m-- != 0) {
            t = nextInt();
            if (t == 1) {
                x = nextInt();
                y = nextInt();
                z = nextInt();
                add(x, z);//树状数组维护差分数组
                // 巧妙的思想
                add(y + 1, -z);
            } else if (t == 2) {
                x = nextInt();
                bw.write(ask(x) + "\n");
            }
        }
        bw.flush();
    }

    static void add(int x, int c) {
        for (; x <= n; x += x & -x) tr[x] += c;
    }

    static long ask(int x) {
        long res = 0;
        for (; x != 0; x -= x & -x) res += tr[x];
        return res;
    }

    static int n, m;

    static int[] tr = new int[500001], a = new int[500001];
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
}
