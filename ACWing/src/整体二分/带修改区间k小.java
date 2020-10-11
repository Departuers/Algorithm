package 整体二分;


import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.luogu.com.cn/problem/P2617
 * 已经ac
 * 区间第k小
 * 树状数组+整体二分
 * 带修改区间第k大，主席树不便于修改，此问题离线，
 * 而整体二分天然带修改，并且复杂度也很优秀O(nlog2n)O(nlog2n)
 */
public class 带修改区间k小 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = nextInt();
        m = nextInt();
        int x, y, z;
        for (int i = 1; i <= n; i++) {
            x = nextInt();
            q[++idx] = new node(1, i, x, 1);
            now[i] = x;
        }
        char tem;
        for (int i = 1; i <= m; i++) {
            ans[i] = -1;
            tem = next().charAt(0);
            if (tem == 'C') {
                x = nextInt();
                y = nextInt();
                if (now[x] != 0) {
                    q[++idx] = new node(1, x, now[x], -1);
                    //k=-1  代表删除x位置
                    q[++idx] = new node(1, x, y, 1);
                    //k=1 代表添加x位置
                    now[x] = y;
                } else {
                    q[++idx] = new node(1, x, y, 1);
                    now[x] = y;
                }
            } else {
                x = nextInt();
                y = nextInt();
                z = nextInt();
                q[++idx] = new node(2, x, y, z, i);
            }
            //查询[x,y]区间中的第k小
        }
        solve(0, (int) 1e10, 1, idx);
        for (int i = 1; i <= m; i++) {
            if (ans[i] != -1) bw.write(ans[i] + "\n");
        }
        bw.flush();
    }

    static class node {
        int op;//操作类型,1代表修改,2代表查询
        int x, y, k;
        int id;

        /**
         * @param op //操作类型,1代表修改,2代表查询
         * @param x  将x修改成y
         * @param y  将x修改成y
         * @param k  k 表示当前操作是插入(1)还是擦除(-1), 更新树状数组时使用.
         */
        public node(int op, int x, int y, int k) {
            this.op = op;
            this.x = x;
            this.y = y;
            this.k = k;
        }

        /**
         * @param op 操作类型,1代表修改,2代表查询
         * @param x  左区间
         * @param y  右区间
         * @param k  区间第k大
         * @param id 查询id
         */
        public node(int op, int x, int y, int k, int id) {
            this.op = op;
            this.x = x;
            this.y = y;
            this.k = k;
            this.id = id;
        }
    }

    static int M = 300100, n, idx, Z = 100100, m;
    static int[] tree = new int[Z], ans = new int[Z], now = new int[Z];
    static node[] q = new node[M], lq = new node[M], rq = new node[M];

    static void add(int x, int d) {
        for (; x <= n; x += lowbit(x)) {
            tree[x] += d;
        }
    }

    static long query(int k) {
        long res = 0;
        for (; k != 0; k -= lowbit(k)) {
            res += tree[k];
        }
        return res;
    }

    static int lowbit(int x) {
        return x & -x;
    }

    /**
     * @param vl 值域区间
     * @param vr 值域右区间
     * @param ql 处理查询的左区间 ,查询是离线的
     * @param qr 处理查询的右区间
     */
    static void solve(int vl, int vr, int ql, int qr) {
        if (ql > qr) return;
        if (vl == vr) {//最终查询到只有一个数的区间,那么vl或者vr就是答案l
            for (int i = ql; i <= qr; i++) {
                if (q[i].op == 2) ans[q[i].id] = vl;
            }
            return;
        }
        int mid = vl + vr >> 1, l = 0, r = 0;
        for (int i = ql; i <= qr; i++) {
            if (q[i].op == 1) {//是修改
                if (q[i].y <= mid) {
                    add(q[i].x, q[i].k);
                    lq[++l] = q[i];
                } else {
                    rq[++r] = q[i];
                }
            } else {//是询问
                long tem = query(q[i].y) - query(q[i].x - 1);
                //查询区间有多少
                if (q[i].k <= tem) {
                    lq[++l] = q[i];
                } else {//放到左边继续查询
                    q[i].k -= tem;
                    rq[++r] = q[i];//放到右边继续查询
                }
            }
        }
        for (int i = ql; i <= qr; i++) {
            if (q[i].op == 1 && q[i].y <= mid) {
                add(q[i].x, -q[i].k);
            }
        }//清空树状数组

        for (int i = 1; i <= l; i++) {
            q[ql + i - 1] = lq[i];
        }//辅助查询放回原查询
        for (int i = 1; i <= r; i++) {
            q[ql + l + i - 1] = rq[i];
        }//辅助查询放回原查询
        solve(vl, mid, ql, ql + l - 1);
        solve(mid + 1, vr, ql + l, qr);
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
