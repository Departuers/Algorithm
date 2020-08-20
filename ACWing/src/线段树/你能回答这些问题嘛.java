package 线段树;

import java.util.Scanner;

/**
 * 题目
 * https://www.acwing.com/problem/content/246/
 * 给定长度为N的数列A，以及M条指令，每条指令可能是以下两种之一：
 * 1、“1 x y”，查询区间 [x,y] 中的最大连续子段和，即 MAXx≤l≤r≤y{∑r,i=l A[i]}。
 * 2、“2 x y”，把 A[x] 改成 y。
 * 对于每个查询指令，输出一个整数表示答案。
 * 输入样例：
 * 5 3
 * 1 2 -3 4 5
 * 1 2 3
 * 2 2 -1
 * 1 3 2
 * 输出样例：
 * 2
 * -1
 * <p>
 * 思路：
 * 用线段树维护一个区间和sum以及区间最大连续子段和dat，
 * 前缀最大连续子段和lmax，后缀最大连续子段和rmax。
 * 容易推出pushup为
 * t[p].sum=t[p<<1].sum+t[p*2+1].sum;
 * t[p].lmax=max(t[p<<1].lmax,t[p<<1].sum+t[p*2+1].lmax);
 * t[p].rmax=max(t[p*2+1].rmax,t[p*2+1].sum+t[p<<1].rmax);
 * t[p].dat=max(max(t[p<<1].dat,t[p*2+1].dat),t[p<<1].rmax+t[p*2+1].lmax);
 * <p>
 * 区间最大连续子段和,单点更新
 * [        这是父亲       ]
 * [ 左儿子   ][  右儿子    ]
 * 能不能通过两个儿子的最大连续字段和求出父亲的最大连续字段和呢
 * 需要最大后缀和,和最大前缀和
 * 横跨左右子区间的最大连续子段和=左子区间的最大后缀+右子区间的最大前缀
 */
public class 你能回答这些问题嘛 {
    static class node {
        int l, r;
        int tmax;//最大连续字段和
        int lmax;//左子区间的最大后缀
        int rmax;//右子区间的最大前缀
        int sum;//区间和

        public node(int l, int r, int tmax, int lmax, int rmax, int sum) {
            this.l = l;
            this.r = r;
            this.tmax = tmax;
            this.lmax = lmax;
            this.rmax = rmax;
            this.sum = sum;
        }
    }

    static void pushup(node k, node l, node r) {
        k.sum = l.sum + r.sum;
        k.lmax = Math.max(l.lmax, l.sum + r.lmax);
        k.rmax = Math.max(r.rmax, r.sum + l.rmax);
        k.tmax = Math.max(Math.max(l.tmax, r.tmax), l.rmax + r.lmax);
    }

    static int N = 500010;
    static node[] node = new node[N * 4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }

    static void build(int k, int l, int r) {
        if (l == r) {
            node[k] = new node(l, r, w[r], w[r], w[r], w[r]);
            return;
        } else {
            int mid = l + r >> 1;
            build(k << 1, l, mid);
            build(k << 1 | 1, mid + 1, r);
            pushup(k);
        }
    }

    static void update(int k, int x, int v) {
        if (node[k].l == x && node[k].r == x) node[k] = new node(x, x, v, v, v, v);
        else {
            int mid = (node[k].l + node[k].r) >> 1;
            if (x <= mid) update(k << 1, x, v);
            else update(k << 1 | 1, x, v);
            pushup(k);
        }
    }

//    static node query(int k, int l, int r) {
//        if (node[k].l >= l && node[k].r <= r) return node[k];
//        else {
//            int mid = node[k].l + node[k].r >> 1;
//
//        }
//    }

    static void pushup(int k) {
        pushup(node[k], node[k << 1], node[k << 1 | 1]);
    }

    static int[] w = new int[N];
}
