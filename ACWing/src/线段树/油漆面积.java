package 线段树;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acwing.com/solution/content/8908/
 * 经过各种测量，每个机器人都会报告一个或多个矩形区域，作为优先考古的区域。
 * 矩形的表示格式为 (x1,y1,x2,y2)，代表矩形的两个对角点坐标。
 * 为了醒目，总部要求对所有机器人选中的矩形区域涂黄色油漆。
 * 小明并不需要当油漆工，只是他需要计算一下，一共要耗费多少油漆。
 * 其实这也不难，只要算出所有矩形覆盖的区域一共有多大面积就可以了。
 * 注意，各个矩形间可能重叠。
 * 输入格式
 * 第一行，一个整数 n，表示有多少个矩形。
 * 接下来的 n 行，每行有 4 个整数 x1,y1,x2,y2，空格分开，表示矩形的两个对角顶点坐标。
 * 输出格式
 * 一行一个整数，表示矩形覆盖的总面积。
 * 数据范围
 * 1≤n≤10000,
 * 0≤x1,x2,y2,y2≤10000
 * 数据保证 x1<x2 且 y1<y2。
 * 输入样例1：
 * 3
 * 1 5 10 10
 * 3 1 20 20
 * 2 7 15 17
 * 输出样例1：
 * 340
 * 输入样例2：
 * 3
 * 5 2 10 6
 * 2 7 12 10
 * 8 1 15 15
 * 输出样例2：
 * 128
 * 线段树+扫描线
 */
public class 油漆面积 {
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

    public static void main(String[] args) throws IOException {
        n = nextInt();
        int x1, y1, x2, y2;
        int m = 0;
        for (int i = 0; i < n; i++) {
            x1 = nextInt();
            y1 = nextInt();
            x2 = nextInt();
            y2 = nextInt();
            seg[m++] = new seg(x1, y1, y2, 1);
            seg[m++] = new seg(x2, y1, y2, -1);
        }
        Arrays.sort(seg, 0, m);
        build(1, 0, 10000);
        int res = 0;
        for (int i = 0; i < m; i++) {//积分思想,从左往右竖着扫描
            if (i > 0) res += node[1].len * (seg[i].x - seg[i - 1].x);
            add(1, seg[i].y1, seg[i].y2 - 1, seg[i].k);
            //扫描线竖着扫
        }
        System.out.println(res);
    }

    static void build(int k, int l, int r) {
        node[k] = new node(l, r);
        if (l == r) return;
        int mid = l + r >> 1;
        build(k << 1, l, mid);
        build(k << 1 | 1, mid + 1, r);
    }

    static void add(int k, int l, int r, int c) {
        if (node[k].l >= l && node[k].r <= r) {
            node[k].cnt += c;
        } else {
            int mid = node[k].l + node[k].r >> 1;
            if (l <= mid) add(k << 1, l, r, c);
            if (r > mid) add(k << 1 | 1, l, r, c);
        }
        pushup(k);
    }

    static void pushup(int k) {//最重要的
        if (node[k].cnt > 0) {
            node[k].len = node[k].r - node[k].l + 1;
        } else if (node[k].r == node[k].l) {
            node[k].len = 0;
        } else {
            node[k].len = node[k << 1].len + node[k << 1 | 1].len;
        }
    }

    static int N = 10040, n;
    static node[] node = new node[N << 2];
    static seg[] seg = new seg[N << 1];

    static class node {
        int l, r;
        int len;  // len代表至少被覆盖一次的区间长度,
        int cnt;  // cnt代表当前区间被覆盖次数

        public node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static class seg implements Comparable<seg> {
        int x, y1, y2, k;

        public seg(int x, int y1, int y2, int k) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.k = k;
        }

        @Override
        public int compareTo(seg seg) {
            return x - seg.x;
        }
    }
}
