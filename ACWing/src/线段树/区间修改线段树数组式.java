package 线段树;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 快不了多少
 */
public class 区间修改线段树数组式 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        build(1, 1, n);
        int l, r, d;
        while (m-- != 0) {
            if (next().charAt(0) == 'C') {
                l = nextInt();
                r = nextInt();
                d = nextInt();
                update(1, l, r, 1, n, d);
            } else {
                l = nextInt();
                r = nextInt();
                bw.write(query(1, l, r, 1, n) + "\n");
            }
        }
        bw.flush();
    }

    /**
     * 区间增加
     *
     * @param k
     * @param A
     * @param B
     * @param l
     * @param r
     * @param v
     */
    static void update(int k, int A, int B, int l, int r, int v) {
        if (A <= l && r <= B) {
            tag[k] += v;
            sum[k] += v * (r - l + 1);
            return;
        }
        if (tag[k] != 0) down(k, l, r);
        int mid = l + r >> 1;
        if (A <= mid) update(k << 1, A, B, l, mid, v);
        if (mid < B) update(k << 1 | 1, A, B, mid + 1, r, v);
        sum[k] = sum[k << 1] + sum[k << 1 | 1];
        //回溯更新
        //向上合并
    }

    static long query(int k, int LL, int RR, int l, int r) {
        if (LL <= l && r <= RR) {
            return sum[k];
        }
        if (tag[k] != 0) down(k, l, r);
        int mid = (l + r) >> 1;
        long ans = 0;
        if (LL <= mid) ans += query(k << 1, LL, RR, l, mid);
        if (mid < RR) ans += query(k << 1 | 1, LL, RR, mid + 1, r);
        //往右边找
        return ans;
    }

    static int N = (int) (1e5 + 1), n, m;
    static long[] sum = new long[N * 4];
    static int[] tag = new int[N * 4];

    static void build(int k, int l, int r) throws IOException {
        if (l == r) {
            sum[k] = nextInt();
            return;
        }
        int mid = (l + r) >> 1;
        build(k << 1, l, mid);
        build(k << 1 | 1, mid + 1, r);
        sum[k] = sum[k << 1] + sum[k << 1 | 1];
    }

    /**
     * 区间增加的tag标记
     *
     * @param k
     * @param l
     * @param r
     */
    static void down(int k, int l, int r) {
        int mid = l + r >> 1;
        if (tag[k] != 0) {
            tag[k << 1] += tag[k];
            tag[k << 1 | 1] += tag[k];
            sum[k << 1] += (mid - l + 1) * tag[k];
            sum[k << 1 | 1] += (r - mid) * tag[k];
            tag[k] = 0;
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