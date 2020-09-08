package 线段树;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 不能用废弃
 * https://www.acwing.com/blog/content/487/
 * 静态区间第k大
 * 我们可以建立一颗权值线段树，每个点存储的信息为该值域区间存在的数的个数。
 */
public class 静态主席树结点版本 {
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

    static class seg {
        int l, r, v;

        public seg(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }
    }

    static seg[] tr = new seg[200100 * 20];

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            d[i] = a[i];
        }
        Arrays.sort(d, 1, n + 1);
        int len = unique(d, n);
        for (int i = 1; i <= n; i++) {
            a[i] = Arrays.binarySearch(d, 1, 1 + len, a[i]);
        }
        T[0] = build(1, len);
        int l, r, k;
        while (m-- != 0) {
            l = nextInt();
            r = nextInt();
            k = nextInt();
            int ans = query(T[l - 1], T[r], l, len, k);
            bw.write(d[ans] + "\n");
        }
        bw.flush();
    }

    static int[] a = new int[200010];
    static int[] d = new int[200010];
    static int[] T = new int[200010];
    static int tot = 0, n, m;

    static int unique(int[] t, int n) {
        int j = 1;
        for (int i = 1; i <= n; i++) {
            if (j == 1 || t[i] != t[i - 1]) {
                t[j++] = t[i];
            }
        }
        return j;
    }

    /**
     * 建树,
     *
     * @param l 左区间
     * @param r
     * @return 根节点位置
     */
    static int build(int l, int r) {
        int p = ++tot, mid = l + r >> 1;
        tr[p] = new seg(l, r, 0);
        if (l < r) {
            tr[p].l = build(l, mid);
            tr[p].r = build(mid + 1, r);
        }
        return p;
    }

    static int update(int pre, int l, int r, int v) {
        int p = ++tot, mid = l + r >> 1;
        tr[p].l = tr[pre].l;
        tr[p].r = tr[pre].r;
        tr[p].v = tr[pre].v + 1;
        if (l < r) {
            //应该更新哪一个值域区间
            if (v <= mid) tr[p].l = update(tr[pre].l, l, mid, v);
            else tr[p].r = update(tr[pre].r, mid + 1, r, v);
        }
        return p;
    }

    static int query(int x, int y, int l, int r, int k) {
        if (l == r) return l;
        //对位相减
        int sum = tr[tr[y].l].v - tr[tr[x].l].v, mid = l + r >> 1;
        if (k <= sum) return query(tr[x].l, tr[y].l, l, mid, k);
        else return query(tr[x].r, tr[y].r, mid + 1, r, k - sum);
    }

}
