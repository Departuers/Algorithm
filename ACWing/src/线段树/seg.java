package 线段树;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class seg {
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
                update(1, l, r, d);
            } else {
                l = nextInt();
                r = nextInt();
                bw.write(query(1, l, r) + "\n");
            }
        }
        bw.flush();
    }

    static class node {
        int l, r, lazy;
        long v;

        public node(int l, int r, long v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }
    }

    static void build(int k, int l, int r) throws IOException {
        if (l == r) {
            tr[k] = new node(l, r, nextInt());
        } else {
            tr[k] = new node(l, r, 0);
            int mid = l + r >> 1;
            build(k << 1, l, mid);
            build(k << 1 | 1, mid + 1, r);
            pushup(k);
        }
    }

    private static void pushup(int k) {
        tr[k].v = tr[k << 1].v + tr[k << 1 | 1].v;
    }

    static long query(int k, int l, int r) {
        if (tr[k].l >= l && tr[k].r <= r) {
            return tr[k].v;
        }
        down(k);
        int mid = tr[k].l + tr[k].r >> 1;
        long res = 0;
        if (l <= mid) res += query(k << 1, l, r);
        if (r > mid) res += query(k << 1 | 1, l, r);
        return res;
    }

    private static void down(int k) {
        if (tr[k].lazy != 0) {
            tr[k << 1].v += (tr[k << 1].r - tr[k << 1].l + 1) * tr[k].lazy;
            tr[k << 1 | 1].v += (tr[k << 1 | 1].r - tr[k << 1 | 1].l + 1) * tr[k].lazy;
            tr[k << 1].lazy = tr[k].lazy;
            tr[k << 1 | 1].lazy = tr[k].lazy;
            tr[k].lazy = 0;
        }
    }

    static void update(int k, int l, int r, int d) {
        if (tr[k].l >= l && tr[k].r <= r) {
            tr[k].v += (tr[k].r - tr[k].l + 1) * d;
            tr[k].lazy += d;
            return;
        }
        down(k);
        int mid = tr[k].l + tr[k].r >> 1;
        if (l <= mid) update(k << 1, l, r, d);
        if (r > mid) update(k << 1 | 1, l, r, d);
        pushup(k);
    }

    static int N = (int) (1e5 + 2), n, m;
    static node[] tr = new node[N * 4];
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
