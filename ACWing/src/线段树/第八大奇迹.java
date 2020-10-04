package 线段树;


import java.util.Scanner;

/**
 * 区间第8大
 * 树状数组+整体二分
 * 带修改区间第k大，主席树不便于修改，此问题离线，
 * 而整体二分天然带修改，并且复杂度也很优秀O(nlog2n)O(nlog2n)
 */
public class 第八大奇迹 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        n = sc.nextInt();
        int cnt = 0;
        char tem;
        int a, b;
        for (int i = 1; i <= n; i++) {
            ans[i] = -1;
            tem = sc.next().charAt(0);
            a = sc.nextInt();
            b = sc.nextInt();
            if (tem == 'C') {
                if (now[a] != 0) {
                    q[++idx] = new node(1, a, now[a], -1);
                    q[++idx] = new node(1, a, b, 1);
                } else {
                    q[++idx] = new node(1, a, b, 1);
                    now[a] = b;
                }
            } else q[++idx] = new node(2, a, b, 8, i);
        }
        for (int i = 1; i <= n; i++) {
            if (ans[i] != -1) System.out.println(ans[i]);
        }
    }

    static class node {
        int op, x, y, k, id;

        public node(int op, int x, int y, int k) {
            this.op = op;
            this.x = x;
            this.y = y;
            this.k = k;
        }

        public node(int op, int x, int y, int k, int id) {
            this.op = op;
            this.x = x;
            this.y = y;
            this.k = k;
            this.id = id;
        }
    }

    static int M = 200100, L, n, idx;
    static int[] tree = new int[M], ans = new int[M], now = new int[M];
    static node[] q = new node[M], lq = new node[M], rq = new node[M];

    static void update(int x, int d) {
        for (; x <= L; x += lowbit(x)) {
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

    static void solve(int vl, int vr, int ql, int qr) {
        if (ql > qr) return;
        if (vl == vr) {
            for (int i = ql; i <= qr; i++) {
                if (q[i].op == 2) ans[q[i].id] = vl;

            }
            return;
        }
        int mid = vl + vr >> 1, l = 0, r = 0;
        for (int i = ql; i <= qr; i++) {
            if (q[i].op == 1) {
                if (q[i].y <= mid) lq[++l] = q[i];
                else {
                    update(q[i].x, q[i].k);
                    rq[++r] = q[i];
                }
            } else {
                long tem = query(q[i].y) - query(q[i].x - 1);
                if (q[i].k <= tem) {
                    rq[++r] = q[i];
                } else {
                    q[i].k -= tem;
                    lq[++l] = q[i];
                }
            }
        }
        for (int i = ql; i <= qr; i++) {
            if (q[i].op == l && q[i].y > mid) {
                update(q[i].x, -q[i].k);
            }
        }
        for (int i = 1; i <= l; i++) {
            q[ql + i - 1] = lq[i];
        }
        for (int i = 1; i <= r; i++) {
            q[ql + l + i - 1] = rq[i];
        }
        solve(vl, mid, ql, ql + l - 1);
        solve(mid + 1, vr, ql + l, qr);
    }

}
