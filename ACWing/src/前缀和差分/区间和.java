package 前缀和差分;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 坐标范围在10^9,需要使用离散化
 * 值域跨度非常大,但非常数值分布稀疏,可以用离散化
 * 把所有用到的下标,全部存起来,映射成从1开始的自然数
 * 原数组可以离散化,查询也可以离散化
 */
public class 区间和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int x, c;
        for (int i = 0; i < n; i++) {
            x = sc.nextInt();
            c = sc.nextInt();
            add.add(new node(x, c));//离线增加
            all.add(x);
        }
        int l, r;
        for (int i = 0; i < m; i++) {
            l = sc.nextInt();
            r = sc.nextInt();
            query.add(new node(l, r));//记录查询
            all.add(l);
            all.add(r);
        }
        Collections.sort(all);

        u();

        for (node t : add) {
            int at = find(t.x);
            a[at] += t.y;
        }
        for (int i = 1; i <= all.size(); i++) {
            s[i] = s[i - 1] + a[i];
        }
        for (node t : query) {
            l = find(t.x);
            r = find(t.y);
            System.out.println(s[r] - s[l - 1]);
        }

    }

    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 找到离散化之后的值
     *
     * @param x
     * @return
     */
    static int find(int x) {
        int l = 0, r = all.size() - 1, mid;
        while (l < r) {
            mid = l + r >> 1;
            if (all.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return r + 1;
    }

    static ArrayList<Integer> all = new ArrayList<Integer>();
    static ArrayList<node> add = new ArrayList<node>(), query = new ArrayList<node>();
    static int n, m;
    static int[] s = new int[(int) (3e5 + 10)], a = new int[(int) (3e5 + 10)];

    static int unique(int[] a) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                a[j++] = a[i];
            }
        }
        return j;
    }

    /**
     * 双指针
     * 去重
     * 1 1 2 2 3 4 5 5
     * 他是第一个
     * a[i]≠a[i-1]
     * 满足该性质的即是去重后的元素
     */
    static void u() {
        int j = 0;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0 || !all.get(i).equals(all.get(i - 1))) {
                all.set(j++, all.get(i));
            }
        }
    }
}
