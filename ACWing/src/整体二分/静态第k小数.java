package 整体二分;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

/**
 * https://www.acwing.com/solution/content/7691/
 */
public class 静态第k小数 {

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

    static int N = 100010, M = 50010, idx, n, m;
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

    static void solve(int L, int R, int l, int r) {
        if (l > r || L > R) return;
        if (L == R) {
            for (int i = l; i <= r; i++) {
                ans[q[i].id] = a[L].x;
            }
            return;
        }
        int mid = (L + R) >> 1;
        for (int i = L; i <= mid; i++) {
            add(a[i].id, 1);
        }
        int t, nl = 0, nr = 0;
        for (int i = l; i <= r; i++) {
            t = query(q[i].r) - query(q[i].l - 1);
            if (q[i].k <= t) lq[++nl] = q[i];
            else {
                q[i].k -= t;
                rq[++nr] = q[i];
            }
        }
        for (int i = L; i <= mid; i++) {
            add(a[i].id, -1);
        }
        for (int i = 1; i <= nl; i++) {
            q[i + l - 1] = lq[i];
        }
        for (int i = 1; i <= nr; i++) {
            q[i + l + nl - 1] = rq[i];
        }
        solve(L, mid, l, nl + l - 1);
        solve(mid + 1, R, nl + l, r);
    }
}
