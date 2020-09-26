package dp.树形dp;

import java.util.Scanner;

/**
 * 有 N 个物品和一个容量是 V的背包。
 * 物品之间具有依赖关系，且依赖关系组成一棵树的形状。如果选择一个物品，则必须选择它的父节点。
 * 如下图所示：
 * 如果选择物品5，则必须选择物品1和2。这是因为2是5的父节点，1是2的父节点。
 * 每件物品的编号是 i
 * ，体积是 vi，价值是 wi，依赖的父节点编号是 pi。物品的下标范围是 1…N
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 * 输入格式
 * 第一行有两个整数 N，V
 * ，用空格隔开，分别表示物品个数和背包容量。
 * 接下来有 N
 * 行数据，每行数据表示一个物品。
 * 第 i 行有三个整数 vi,wi,pi，用空格隔开，分别表示物品的体积、价值和依赖的物品编号。
 * 如果 pi=−1
 * ，表示根节点。 数据保证所有物品构成一棵树。
 * 输出格式
 * 输出一个整数，表示最大价值。
 * 数据范围
 * 1≤N,V≤100
 * 1≤vi,wi≤100
 * 父节点编号范围：
 * 内部结点：1≤pi≤N
 * 根节点 pi=−1
 * 输入样例
 * 5 7
 * 2 3 -1
 * 2 2 1
 * 3 5 1
 * 4 7 2
 * 3 6 2
 * 输出样例：
 * 11
 * <p>
 * 有n个物品和容量为V的背包
 * 物品之间有依赖关系,关系组成一颗树的形状,
 * 如果要选一个物品,则必选他的父节点
 * 求最大价值
 * dfs+dp dp.树形dp
 * 状态定义:集合f[u,j]:所有从以u为根的子树中选,且总体积不超过j的选法
 * 属性:max最大价值
 * 集合划分:子树1,子树3,子树3
 * 划分依据:体积为0~m的子树,把每一棵子树看做一个物品做
 * 再根据体积划分子树:体积是0-m 有m+1总可能
 * 用一个数字表示一类方案
 * 把每一颗子树看做物品组,分组背包问题
 * 链式前向星建树
 */
public class 有依赖的背包问题 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int p, root = 0;
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            p = sc.nextInt();
            if (p == -1) root = i;
            else add(p, i);//p向i连一条边,父节点向子节点连边
        }
        dfs(root);
        System.out.println(f[root][m]);
    }

    static void dfs(int u) {
        for (int i = head[u]; i != 0; i = ne[i]) {
            int son = e[i];
            dfs(e[i]);
            //以u为根的子树,每一棵子树都是一个分组背包问题
            //抽象成2层
            for (int j = m - v[u]; j >= 0; j--) {//枚举体积,因为根节点必须要选,预留m-v[u
                for (int k = 0; k <= j; k++) {//枚举决策,分为以0~m的一些决策
                    f[u][j] = Math.max(f[u][j], f[u][j - k] + f[son][k]);
                    //以u为根节点,在剩余体积为0~m-v[u]的情况下,选子树
                }
            }
        }
        for (int i = m; i >= v[u]; i--) {
            f[u][i] = f[u][i - v[u]] + w[u];
        }//遍历完根节点的所有子树后
        //能选的一定要选上01背包问题
        for (int i = 0; i < v[u]; i++) {
            f[u][i] = 0;
        }//如果不能选赋值为0
    }

    static int[] v = new int[110], w = new int[110];
    static int[][] f = new int[110][110];
    static int N = 110;
    static int[] head = new int[N], e = new int[N], ne = new int[N];

    static int n, m, idx = 1;

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = head[a];
        head[a] = idx++;
    }
}
