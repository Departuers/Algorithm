package UnionFind;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * 并查集用来快速处理
 * 1.将两个集合合并
 * 2.询问两个元素是否在一个集合中
 * 考虑暴力做法,
 * belong[x]=a 表示x在a集合中
 * 显然判断是否属于统一集合If(belong[x]==belong[y]) O(1)判断
 * 然而合并两个集合费很大劲起码是O(n)
 * <p>
 * 并查集:可以在近乎O(1)的时间复杂度内完成这两个操作
 * 用树的方式来维护集合
 * 每一个集合的编号是根节点的编号
 * 对于每一个点都存储它的父节点是谁,
 * 当查找一个点所属的集合时候,一直找父节点,直到根节点,可以快速找到根节点
 * 基本原理:每个集合用一颗树来表示,树根的编号就是整个集合的编号
 * 每个节点存储它的父节点
 * p[x]表示x的父节点
 * 如何判断树根, 等价于p[x]==x,就是树根
 * 如何求x的集合编号 while(p[x]!=x) x=p[x]
 * 询问两个元素是否在同一个集合中,求x,y所在的集合编号
 * 如何合并两个集合,对于树这种结构,两个集合的根节点连一条单向边即可,
 * 根据p[x]表示x的父节点,则显然入度+1的那个集合的编号作为两个集合的新编号
 * px是x的集合编号,py是y的集合编号,p[px]=py
 * 考虑路径压缩
 */
public class 并查集 {
    public static void main(String[] args) {
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {

        }
    }

    static int N = 1010010, n, m;
    static int[] p = new int[N];

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void union(int x, int y) {
        int ra = find(x);
        int rb = find(y);
        if (ra != rb) p[ra] = rb;
    }
}
