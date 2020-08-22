package 线段树;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/blog/content/487/
 * 需要背下来.tle2个
 */
public class 主席树 {
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

    static int get(int x) {
        return Arrays.binarySearch(c, 1, count, x);
    }

    static int unique(int[] a) {
        int j = 1;
        for (int i = 1; i < a.length; i++) {
            if (i == 1 || a[i] != a[i - 1]) {
                a[j++] = a[i];
            }
        }
        return j;
    }

    public static void main(String[] args) throws IOException {

        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            c[i] = a[i];
        }

        Arrays.sort(c, 1, n + 1);
        count = unique(c);

        for (int i = 1; i <= n; i++) {
            root[i] = update(1, n, root[i - 1], root[i], get(a[i]));
        }
        int l, r, k;
        while (m-- != 0) {
            l = nextInt();
            r = nextInt();
            k = nextInt();
            bw.write(c[query(1, n, root[l - 1], root[r], k)] + "\n");
        }
        bw.flush();
    }

    public static int maxn = 200002;
    public static int[] tree = new int[maxn * 32];
    public static int[] lson = new int[maxn * 32];
    public static int[] rson = new int[maxn * 32];
    public static int[] c = new int[maxn];
    public static int[] root = new int[maxn];
    public static int[] a = new int[maxn];
    public static int cnt = 0;
    static int n, m, count;

    static int update(int l, int r, int pre, int now, int value) {
        cnt++;
        tree[cnt] = tree[pre];
        lson[cnt] = lson[pre];
        rson[cnt] = rson[pre];
        now = cnt;
        tree[now]++;
        if (l == r) return now;
        int mid = l + r >> 1;
        if (value <= mid) {
            lson[now] = update(l, mid, lson[pre], lson[now], value);
        } else {
            rson[now] = update(mid + 1, r, rson[pre], rson[now], value);
        }
        return now;
    }

    //区间第k小
    static int query(int l, int r, int L, int R, int k) {
        if (l == r) return l;
        int mid = l + r >> 1;
        int m = tree[lson[R]] - tree[lson[L]];
        if (k <= m) return query(l, mid, lson[L], lson[R], k);
        else return query(mid + 1, r, rson[L], rson[R], k - m);
    }

    //区间第k大
    static int queryD(int l, int r, int L, int R, int k) {
        if (l == r) return l;
        int mid = l + r >> 1;
        int m = tree[lson[R]] - tree[lson[L]];
        if (k <= m) return queryD(mid + 1, r, rson[L], rson[R], k);
        else return queryD(l, mid, lson[L], lson[R], k - m);
    }


}
