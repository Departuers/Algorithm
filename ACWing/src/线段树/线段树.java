package 线段树;

/**
 * 数组版本
 */
public class 线段树 {
    public static void main(String[] args) {

    }

    static final int maxn = 100004;
    static long[] tree = new long[maxn << 2];
    static long[] tag = new long[maxn << 2];
    static long[] a = new long[maxn];

    static void build(int k, int l, int r) {
        if (l == r) tree[k] = a[l];
        int mid = l + r >> 1;
        build(k << 1, 1, mid);
        build(k << 1 | 1, mid + 1, r);
        tree[k] = tree[k << 1] + tree[k << 1 | 1];
    }

    static void update(int k, int l, int r, int index, int value) {
        if (l == r) {
            tree[k] = value;
            return;
        }
        int mid = l + r >> 1;
        if (index <= mid) update(k << 1, l, mid, index, value);
        else update(k << 1 | 1, mid + 1, r, index, value);
        tree[k] = tree[k << 1] + tree[k << 1 | 1];
    }

    static long query(int LL, int RR, int l, int r, int x) {
        if (LL <= l && r <= RR) {
            return tree[x];
        }
        int mid = l + r >> 1, ans = 0;
        if (LL <= mid) ans += query(LL, RR, l, mid, x << 1);
        if (mid < RR) ans += query(LL, RR, mid + 1, r, x << 1 | 1);
        //往右边找
        return ans;
    }
}
