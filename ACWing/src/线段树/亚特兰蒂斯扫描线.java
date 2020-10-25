package 线段树;

import java.io.*;
import java.util.*;

/**
 * https://oi-wiki.org/geometry/scanning/#_3
 * https://www.acwing.com/solution/content/20903/
 * https://blog.csdn.net/narcissus2_/article/details/88418870
 * https://blog.csdn.net/wucstdio/article/details/80064738
 * 积分思想
 * 分成几个小块扫描
 * 把纵坐标做成一个线段树
 * 操作1:将某一个区间[l,r]+k   k=1或者k=-1
 * 操作2:求整个区间中长度大于0的区间总长度是多少
 * 线段树中的节点信息
 * 1.cnt 当前区间整个被覆盖次数
 * 2.len
 * 不用懒标记
 * 1.因为查询只用查询全体区间,query不用pushdown
 * 2. 所有操作必然成对出现,且先加后减,比如[1,5]+1 后面有[1,5]-1
 */
public class 亚特兰蒂斯扫描线 {
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
        int T = 1;
        double x1, y1, x2, y2;
        while (true) {
            ys.clear();
            n = nextInt();
            seg = new seg[n * 2];
            if (n == 0) break;
            int j = 0;
            for (int i = 0; i < n; i++) {
                x1 = nextDouble();
                y1 = nextDouble();
                x2 = nextDouble();
                y2 = nextDouble();
                seg[j++] = new seg(x1, y1, y2, 1);
                seg[j++] = new seg(x2, y1, y2, -1);
                ys.add(y1);
                ys.add(y2);
            }
            Collections.sort(ys);
            unique(ys);
            build(1, 0, ys.size() - 2);
            Arrays.sort(seg, 0, j);
            double res = 0;
            for (int i = 0; i < n * 2; i++) {
                if (i > 0) res += tr[1].len * (seg[i].x - seg[i - 1].x);
                up(1, query(seg[i].y1), query(seg[i].y2) - 1, seg[i].k);
            }
            System.out.println("Test case #" + T);
            T++;
            System.out.printf("Total explored area: %.2f\n\n", res);
        }
    }

    static void build(int k, int l, int r) {

        tr[k] = new node(l, r, 0, 0);
        if (l != r) {
            int mid = l + r >> 1;
            build(k << 1, l, mid);
            build(k << 1 | 1, mid + 1, r);
        }
    }

    public static void pushup(int u) {
        //如果cnt > 0， 则整个区间长度就是len
        if (tr[u].cnt > 0) {
            //tr[u].r,是区间从r到r + 1的左端点， 所以tr[u].r + 1 -> 右端点， 因为存的是区间
            tr[u].len = ys.get(tr[u].r + 1) - ys.get(tr[u].l);
        } else {
            if (tr[u].l != tr[u].r) {
                tr[u].len = tr[u << 1].len + tr[u << 1 | 1].len;
            } else {
                //如果tr[u].l == tr[u].r 叶节点的话， 区间长度为0
                tr[u].len = 0;
            }
        }
    }

    static void up(int k, int l, int r, int d) {
        if (tr[k].l >= l && tr[k].r <= r) {
            tr[k].cnt += d;
            pushup(k);
        } else {
            int mid = tr[k].l + tr[k].r >> 1;
            if (l <= mid)
                up(k << 1, l, r, d);
            if (r > mid)
                up(k << 1 | 1, l, r, d);
            pushup(k);
        }
    }

    static class seg implements Comparable<seg> {
        double x, y1, y2;
        int k;

        public seg(double x, double y1, double y2, int k) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.k = k;
        }

        @Override
        public int compareTo(seg seg) {
            return x > seg.x ? 1 : -1;
        }
    }

    static class node {
        int l, r;
        int cnt = 0;//当前区间整个被覆盖的次数
        double len; //不考虑祖先节点cnt的前提下， cnt > 0的区间总长

        public node(int l, int r, int cnt, double len) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
            this.len = len;
        }
    }

    static int N = 100010, n;
    static seg[] seg;
    static node[] tr = new node[N * 8];
    static ArrayList<Double> ys = new ArrayList<Double>();

    static int query(double target) {
        int l = 0, r = ys.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (ys.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    /**
     * 去重
     *
     * @param list
     */
    static void unique(ArrayList<Double> list) {
        int j = 0;
        int cnt = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 || !list.get(i - 1).equals(list.get(i))) {
                list.set(j, list.get(i));
                j++;
            }
        }
        for (int i = j; i < cnt; i++) {
            list.remove(list.size() - 1);
        }
    }
}
