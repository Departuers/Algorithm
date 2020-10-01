package UnionFind;

import java.util.Scanner;

/**
 * https://blog.csdn.net/weixin_44922845/article/details/104516620
 * Alice和Bob玩了一个古老的游戏：首先画一个 n×n 的点阵（下图 n=3 ）。
 * 接着，他们两个轮流在相邻的点之间画上红边和蓝边：
 * 直到围成一个封闭的圈（面积不必为 1）为止，“封圈”的那个人就是赢家。因为棋盘实在是太大了，他们的游戏实在是太长了！
 * 他们甚至在游戏中都不知道谁赢得了游戏。
 * 于是请你写一个程序，帮助他们计算他们是否结束了游戏？
 * 输入格式
 * 输入数据第一行为两个整数 n 和 m。n表示点阵的大小，m 表示一共画了 m 条线。
 * 以后 m 行，每行首先有两个数字 (x,y)，代表了画线的起点坐标，接着用空格隔开一个字符，假如字符是 D，则是向下连一条边，如果是 R 就是向右连一条边。
 * 输入数据不会有重复的边且保证正确。
 * 输出格式
 * 输出一行：在第几步的时候结束。
 * 假如 m 步之后也没有结束，则输出一行“draw”。
 * 数据范围
 * 1≤n≤2001≤m≤240001≤n≤200
 * 1≤m≤24000
 * 输入样例
 * 3  5
 * 1  1  D
 * 1  1  R
 * 1  2  D
 * 2  1  R
 * 2  2  D
 * 输出样例
 * 4
 * 问有没有结束...则显然
 * 问题在问第一次形成环在什么时候
 * 输出在第几步结束
 * 用tarjan双连通分量来做.没必要
 * 如果两个点在连边之前已经在一个集合里,那么连接过后变成一个环
 * 把二维坐标转化为一维坐标
 * (x,y)-> x*n+y
 * 前提是x和y都是从0开始的
 * O(n+m)
 */
public class 格子游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n * n; i++) {
            p[i] = i;
        }

        int res = 0;
        int a, b, x, y, pa, pb;
        String s, t;
        for (int i = 1; i <= m; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            t = sc.next();
            x--;//由于下标从1开始
            y--;//下标也需要映射,对应

            a = get(x, y);
            if (t.charAt(0) == 'D') b = get(x + 1, y);
            else b = get(x, y + 1);
            pa = find(a);
            pb = find(b);
            if (pa == pb) {
                res = i;
                break;
            }
            p[pa] = pb;
        }
        if (res == 0) System.out.println("draw");
        else System.out.println(res);
    }

    static int get(int a, int b) {
        return a * n + b;
    }

    static int n, m;
    static int[] p = new int[40010];

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

}
