package 线段树;

/**
 * 树状数组+整体二分
 * 带修改区间第k大，主席树不便于修改，此问题离线，
 * 而整体二分天然带修改，并且复杂度也很优秀O(nlog2n)O(nlog2n)
 */
public class 第八大奇迹 {
    public static void main(String[] args) {

    }

    static int N = 200100, n;
    static int[] tree = new int[N];

    static void update(int x, int d) {
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
}
