package 线段树;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.luogu.com.cn/problemnew/solution/P3368
 * 区间修改,单点查询
 * tle1个
 */
public class 树状数组区间修改 {
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
            cha[i] = nextInt();
        }
        int x, y, z, t;
        while (m-- != 0) {
            t = nextInt();
            if (t == 1) {
                x = nextInt();
                y = nextInt();
                z = nextInt();
                add(x, z);
                add(y + 1, -z);
            } else if (t == 2) {
                x = nextInt();
                bw.write((ask(x) + cha[x]) + "\n");
            }
        }
        bw.flush();
    }

    static int maxn = 500001, n, m;
    static long tree[] = new long[maxn];
    static long cha[] = new long[maxn];
    //差分数组

    static void add(int s, int value) {
        for (int i = s; i <= n; i += lowbit(i)) {
            tree[i] += value;
        }
    }

    static int lowbit(int x) {
        return x & -x;
    }

    static long ask(int s) {
        long ans = 0;
        for (int i = s; i >= 1; i -= lowbit(i)) {
            ans += tree[i];
        }
        return ans;
    }

}
