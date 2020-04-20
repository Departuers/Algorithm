package 线段树;

//单点修改,区间查询
public class 树状数组 {
    public static void main(String[] args) {
        int[] t = {4, 7, 2, 1, 5, 3};
        for (int i = 0; i < 6; i++) {
            add(i + 1, t[i]);
        }
        for (int i = 1; i <= 6; i++) {
            System.out.println(query(1, i));
        }
    }

    static int[] par = new int[100005];
    static int n = 6;

    static void add(int i, int x) {
        while (i <= n) {
            par[i] += x;
            i += lowbit(i);
        }
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    static int query(int l, int r) {
        return get(r) - get(l - 1);
    }

    static int get(int x) {
        int res = 0;
        while (x != 0) {
            res += par[x];
            x -= lowbit(x);
        }
        return res;
    }
}
