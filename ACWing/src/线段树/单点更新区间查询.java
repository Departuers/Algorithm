package 线段树;

//线段树
public class 单点更新区间查询 {
    public static void main(String[] args) {

    }

    static int[] max = new int[800010];

    static void build(int k, int l, int r) {
        if (l == r) {

            return;
        }
        int mid = l + r >> 1;
        build(k << 1, l, mid);
        build(k << 1 | 1, mid + 1, r);

    }
}
