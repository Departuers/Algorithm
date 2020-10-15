package 树状数组;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/m0_38033475/article/details/80330157
 * 离散化+树状数组求逆序对
 * 5
 * 13 6 9 11 5
 * 每个数所对应的换位次数（逆序对）=前面比它大的+后面比它小的 咳咳，貌似又废话了一遍~~~
 * 每次读入一个数就先把它放到树状数组中去，但这个树状数组保存的并不是这个数，而是这个数出现的次数。
 */
public class 逆序数 {
    static class node implements Comparable<node> {
        int v, index;

        public node(int v, int index) {
            this.v = v;
            this.index = index;
        }

        @Override
        public int compareTo(node node) {
            return v - node.v;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            de[i] = new node(sc.nextInt(), i);
        }
        Arrays.sort(de, 1, n + 1);
        /**
         * 正序循环,巧妙想法,也可以求逆序数,而树状数组存储每个值出现的次数
         * res记录每个数左边有多少个比它大的,
         */
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int y = de[i].index;
            res += ask(n) - ask(y);
            add(y, 1);//存储每个值出现的次数
        }
        System.out.println(res);
    }

    static int N = (int) (2e5 + 200), n;
    static int[] c = new int[N];
    static node[] de = new node[N];

    static long ask(int x) {
        long ans = 0;
        while (x != 0) {
            ans += c[x];
            x -= lowbit(x);
        }
        return ans;
    }

    static void add(int x, int value) {
        for (int i = x; i <= n; i += lowbit(i)) {
            c[i] += value;
        }
    }

    private static int lowbit(int x) {
        return x & (-x);
    }

}
