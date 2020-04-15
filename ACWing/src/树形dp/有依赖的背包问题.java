package 树形dp;

import java.util.Scanner;

/**
 * 有n个物品和容量为V的背包
 * 物品之间有依赖关系,关系组成一颗树的形状,
 * 如果要选一个物品,则必选他的父节点
 * 求最大价值
 * dfs+dp 树形dp
 * 状态定义:集合f[u,j]:所有从以u为根的子树中选,且总体积不超过j的选法
 * 属性:max最大价值
 * 集合划分:子树1,子树3,子树3
 * 再根据体积划分子树:体积是0-m
 * 把每一颗子树看做物品组,分组背包问题
 * 链式前向星建图
 */
public class 有依赖的背包问题 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        v = sc.nextInt();

    }

    static int N = 110;
    static int[] head = new int[N], e = new int[N], ne = new int[N];

    static int n, v, cnt = 1;

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = head[a];
        head[a] = cnt++;
    }
}
