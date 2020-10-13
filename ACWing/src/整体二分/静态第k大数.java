package 整体二分;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/solution/content/7691/
 */
public class 静态第k大数 {
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

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = new node(i, nextInt());
        }
        for (int i = 1; i <= m; i++) {
            q[i] = new quer(nextInt(), nextInt(), nextInt(), i);
        }
        Arrays.sort(a, 1, n + 1);
        solve(1, n, 1, m);
        for (int i = 1; i <= m; i++) {
            System.out.println(ans[i]);
        }
    }

    static class node implements Comparable<node> {
        int id, x;

        public node(int id, int x) {
            this.id = id;
            this.x = x;
        }

        @Override
        public int compareTo(node node) {
            return x - node.x;
        }
    }

    static class quer {
        int l, r, k, id;

        public quer(int x, int y, int k, int id) {
            this.l = x;
            this.r = y;
            this.k = k;
            this.id = id;
        }
    }

    static int N = 200010, M = 200010, idx, n, m;
    static int[] tree = new int[N], ans = new int[M];
    static quer[] q = new quer[N], rq = new quer[N], lq = new quer[N];
    static node[] a = new node[N];

    static int query(int x) {
        int res = 0;
        for (; x != 0; x -= x & -x) {
            res += tree[x];
        }
        return res;
    }

    static void add(int x, int d) {
        for (; x <= n; x += x & -x) {
            tree[x] += d;
        }
    }

    static void solve(int vl, int vr, int ql, int qr) {
        if (ql > qr || vl > vr) return;
        if (vl == vr) {
            for (int i = ql; i <= qr; i++) {
                ans[q[i].id] = a[vl].x;//离散化对应的值
            }
            return;
        }
        int mid = (vl + vr) >> 1;
        for (int i = mid + 1; i <= vr; i++) {
            add(a[i].id, 1);
        }
        int t, nl = 0, nr = 0;
        for (int i = ql; i <= qr; i++) {
            t = query(q[i].r) - query(q[i].l - 1);
            if (q[i].k <= t) {
                rq[++nr] = q[i];
            } else {
                q[i].k -= t;
                lq[++nl] = q[i];
            }
        }
        for (int i = mid + 1; i <= vr; i++) {
            add(a[i].id, -1);
        }
        for (int i = 1; i <= nl; i++) {
            q[i + ql - 1] = lq[i];
        }
        for (int i = 1; i <= nr; i++) {
            q[i + ql + nl - 1] = rq[i];
        }
        solve(vl, mid, ql, ql + nl - 1);
        solve(mid + 1, vr, ql + nl, qr);
    }
}