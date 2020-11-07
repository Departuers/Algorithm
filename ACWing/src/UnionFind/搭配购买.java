package UnionFind;

import java.util.Scanner;

/**
 * https://blog.csdn.net/weixin_44922845/article/details/104517313
 * Joe觉得云朵很美，决定去山上的商店买一些云朵。
 * 商店里有 n 朵云，云朵被编号为 1,2,…,n，并且每朵云都有一个价值。
 * 但是商店老板跟他说，一些云朵要搭配来买才好，所以买一朵云则与这朵云有搭配的云都要买。
 * 但是Joe的钱有限，所以他希望买的价值越多越好。
 * 输入格式
 * 第 1 行包含三个整数 n，m，w，表示有 n 朵云，m 个搭配，Joe有 w 的钱。
 * 第 2∼n+1行，每行两个整数 ci，di 表示 i 朵云的价钱和价值。
 * 第 n+2∼n+1+m 行，每行两个整数 ui，vi，表示买 ui 就必须买 vi，同理，如果买 vi 就必须买 ui。
 * 输出格式
 * 一行，表示可以获得的最大价值。
 * 数据范围
 * 1 ≤ n ≤ 10000
 * 0≤m≤5000
 * 1≤w≤10000
 * 1≤ci≤5000
 * 1≤di≤100
 * 1≤ui,vi≤n
 * 输入样例
 * 5  3  10
 * 3  10
 * 3  10
 * 3  10
 * 5  100
 * 10  1
 * 1  3
 * 3  2
 * 4  2
 * 输出样例
 * 1
 * 题解
 * 思路
 * 买u必须买v,买v必须买u,无向边
 * 买1必须买2,买2必须买3,则买1,2,3其中一个都要把1,2,3全部买掉
 * 显然:先用并查集把搭配购买的合成为一个商品
 * 再01背包计算出最大价值
 * 最坏是一万个物品
 * 最坏(n*w)
 * 显然:在算每个集合的总体积,总价值是可以维护的
 * 和并查集维护集合大小是一样的,把总价值和总体积维护(绑定)到根节点,
 */
public class 搭配购买 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();//钱
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }//维护并查集,同时维护总体积和总价值,绑定到根节点,当然和连通块中点的数量是一样的思路
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        int a, b, pa, pb;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            pa = find(a);
            pb = find(b);
            if (pa != pb) {
                v[pb] += v[pa];
                w[pb] += w[pa];
                p[pa] = p[pb];
            }
        }
        for (int i = 1; i <= n; i++) {
            if (p[i] == i) {
                for (int j = k; j >= v[i]; j--) {
                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
                }
            }
        }
        System.out.println(f[k]);
    }

    static int n, m, k;
    static int[] w = new int[10010], v = new int[10010], p = new int[10010], f = new int[10010];

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

}
