package 线段树;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42815188/article/details/105234150
 * 单点修改,区间查询
 * 在第n+1位置添加元素,查询最后L个数的最大值
 * 10 100
 * A 97
 * Q 1
 * Q 1
 * A 17
 * Q 2
 * A 63
 * Q 1
 * Q 1
 * Q 3
 * A 99
 * out:
 * 97
 * 97
 * 97
 * 60
 * 60
 * 97
 */
public class 最大数 {
    public static void main(String[] args) {
        int n = 1, last = 0;
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int p = sc.nextInt();
        char a;
        int t = 0;
        for (int i = 0; i < m; i++) {
            a = sc.next().charAt(0);
            if (a == 'A') {
                t = sc.nextInt();
                update(1, 1, M, n, (t + last) % p);
                n++;
            } else {
                t = sc.nextInt();
                last = query(1, n - t, n, 1, M);
                System.out.println(last);
            }
        }
    }

    static int M = (int) (2e5 + 1);
    static int[] max = new int[800010];

    static void build(int k, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + r >> 1;
        build(k << 1, l, mid);
        build(k << 1 | 1, mid + 1, r);
    }

    static void update(int k, int l, int r, int index, int value) {
        if (l == r) {
            max[k] = value;
        } else {
            int mid = l + r >> 1;
            if (index <= mid) update(k << 1, l, mid, index, value);
            else update(k << 1 | 1, mid + 1, r, index, value);
            max[k] = Math.max(max[k << 1], max[k << 1 | 1]);
        }
    }

    //l,r是整个线段树的边界,LL,RR是查询区间!!!
    static int query(int k, int LL, int RR, int l, int r) {
        if (LL <= l && r <= RR) {
            return max[k];
        }
        int mid = l + r >> 1, ans = 0;
        if (LL <= mid) ans += query(k << 1, LL, RR, l, mid);
        if (mid < RR) ans += query(k << 1 | 1, LL, RR, mid + 1, r);
        return ans;
    }
}
