package 线段树;

import java.util.Arrays;

/**
 * 数组版本
 */
public class 线段树 {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            a[i] = i % 2 == 0 ? i + 2 : -i - 2;
        }
        build(1, 1, 5);
        System.out.println(Arrays.toString(a));

        for (int i = 1; i <= 5; i++) {
            //  System.out.println(query(1, 1, i, 1, 5));
            System.out.print(ask(1, 1, 5, i) + "  ");
        }
        System.out.println();
        add(1, 2, 4, 1, 5, 3);
        for (int i = 1; i <= 5; i++) {
            //  System.out.println(query(1, 1, i, 1, 5));
            System.out.print(ask(1, 1, 5, i) + "  ");
        }
        System.out.println();
        for (int i = 1; i <= 5; i++) {
            //  System.out.println(query(1, 1, i, 1, 5));
            System.out.println(askone(1, 1, i, 1, 5));
        }
    }

    static final int maxn = 104;
    static int[] tree = new int[maxn << 2];
    static int[] tag = new int[maxn << 2];
    static int[] a = new int[maxn];
    static int[] min = new int[maxn << 2];
    static int c = 0;

    /**
     * 递归地创建线段树,叶子节点就是节点本身,向上合并
     * 区间操作向上合并,要想清楚如何合并两个元素
     * 线段树的建立是自顶向下的
     * 而递归是自底向上的
     * bst递归后序遍历的性质:对于每个节点进行操作的时候,它的子节点一定都遍历过了!!!
     *
     * @param k 线段树的根
     * @param l 1
     * @param r 有多少个元素
     */
    static void build(int k, int l, int r) {
        if (l == r) {
            tree[k] = a[l];
            min[k] = a[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(k << 1, l, mid);
        build(k << 1 | 1, mid + 1, r);
        tree[k] = tree[k << 1] + tree[k << 1 | 1];
        min[k] = Math.min(min[k << 1], min[k << 1 | 1]);
    }

    /**
     * 单点更新
     * 递归到叶子节点,赋值给叶子节点,向上维护区间和
     *
     * @param k     线段树的根
     * @param l     左边界
     * @param r     右边界
     * @param index 修改index位置
     * @param value 为value
     */
    static void update(int k, int l, int r, int index, int value) {
        if (l == r) {
            tree[k] = value;
            return;
        }
        int mid = l + r >> 1;
        if (index <= mid) update(k << 1, l, mid, index, value);
        else update(k << 1 | 1, mid + 1, r, index, value);
        tree[k] = tree[k << 1] + tree[k << 1 | 1];
        min[k] = Math.min(min[k << 1], min[k << 1 | 1]);

    }

    /**
     * 区间查询
     * 假设求取区间是[LL,RR]  现在的区间表示为[l,r]
     * mid=l+r>>1
     * 左子节点的区间为[l,mid]  右子节点的区间为[mid+1,r]
     * 如果A<=mid  说明询问区间和当前mid的左子区间有重合,递归到左子节点
     * 如果B>=mid+1 则询问区间与右子区间重合,需要递归到右子节点
     * 当遍历到的区间[l,r]完全落在[LL,RR]之间就返回
     * 不然继续递归,可以找到所有区间
     * 可以把[LL,RR]区间的RR-LL+1个元素递归划分成log n个区间递归求解
     * O(log n)
     *
     * @param k  线段树的根
     * @param LL 询问的左区间
     * @param RR 询问的右区间
     * @param l  线段树的左边界
     * @param r  线段树的右边界
     * @return 区间和
     */
    static long query(int k, int LL, int RR, int l, int r) {
        if (LL <= l && r <= RR) {
            return tree[k];
        }
        if (tag[k] > 0) lazy(k, l, r);
        int mid = (l + r) >> 1, ans = 0;
        if (LL <= mid) ans += query(k << 1, LL, RR, l, mid);
        if (mid < RR) ans += query(k << 1 | 1, LL, RR, mid + 1, r);
        //往右边找
        return ans;
    }

    /**
     * 单点查询
     *
     * @param k 线段树的根
     * @param l 线段树边界
     * @param r 线段树的右边界
     * @param i 需要查询的位置
     * @return 元素
     */
    static long ask(int k, int l, int r, int i) {
        if (l == r) {
            return tree[k];
        }
        lazy(k, l, r);
        int mid = (l + r) >> 1;
        if (i <= mid) return ask(k << 1, l, mid, i);
        else return ask(k << 1 | 1, mid + 1, r, i);
    }

    /**
     * 区间和标记
     * 延迟修改操作,用于区间修改
     * 如果修改每个叶子节点,比模拟还多了几个常数
     * 所以如果不查询到子节点,就不更新,而是打上lazy标记
     * 依然划分,把要修改的区间划分为log n个区间,打上lazy标记
     * 查询到右lazy标记的节点,就标记下传给子节点更新
     * 比如[l,r]的lazy非0,我们就要把标记传给[l,mid]和[mid+1,r]
     * 以保证递归下去的查询正确
     * lazy标记保证当前k位置的tree的区间和是正确的
     * 但不保证子节点正确
     */
    static void lazy(int k, int l, int r) {
        int mid = l + r >> 1;
        if (tag[k] > 0) {
            tag[k << 1] = tag[k << 1 | 1] = tag[k];
            tree[k << 1] = (mid - l + 1) * tag[k];
            tree[k << 1 | 1] = (r - mid) * tag[k];
            tag[k] = 0;
        }
    }

    //区间修改
    static void change(int k, int A, int B, int v, int l, int r) {
        if (A <= l && r <= B) {
            //当前区间[l,r]完全落在需修改区间[A,B]里面
            tag[k] = v;
            tree[k] = v * (r - l + 1);
            return;
        }
        lazy(k, l, r);
        int mid = l + r >> 1;
        if (A <= mid) change(k << 1, A, B, v, l, mid);
        if (mid < B) change(k << 1 | 1, A, B, v, mid + 1, r);
        update(k);//回溯更新
    }

    static void update(int k) {
        tree[k] = tree[k << 1] + tree[k << 1 | 1];
        min[k] = Math.min(min[k << 1], min[k << 1 | 1]);
    }

    /**
     * 区间增加
     *
     * @param k
     * @param A
     * @param B
     * @param l
     * @param r
     * @param v
     */
    static void add(int k, int A, int B, int l, int r, int v) {
        if (A <= l && r <= B) {
            tag[k] += v;
            tree[k] += v * (r - l + 1);
            min[k] += v;
            return;
        }
        down(k, l, r);
        int mid = l + r >> 1;
        if (A <= mid) add(k << 1, A, B, l, mid, v);
        if (mid < B) add(k << 1 | 1, A, B, mid + 1, r, v);
        update(k);//回溯更新
        //向上合并
    }

    /**
     * 区间增加的tag标记
     *
     * @param k
     * @param l
     * @param r
     */
    static void down(int k, int l, int r) {
        int mid = l + r >> 1;
        if (tag[k] > 0) {
            tag[k << 1] += tag[k];
            tag[k << 1 | 1] += tag[k];
            tree[k << 1] += (mid - l + 1) * tag[k];
            tree[k << 1 | 1] += (r - mid) * tag[k];
            tag[k] = 0;
        }
    }

    static long askone(int k, int A, int B, int l, int r) {
        if (A <= l && r <= B) {
            //l和r落在需查询的A,B区间内
            return min[k];
        }
        down(k, l, r);
        long mid = l + r >> 1, ans = Integer.MAX_VALUE;
        if (A <= mid) ans = Math.min(ans, askone(k << 1, A, B, l, (int) mid));
        if (mid < B) ans = Math.min(ans, askone(k << 1 | 1, A, B, (int) (mid + 1), r));
        return ans;
    }

}
