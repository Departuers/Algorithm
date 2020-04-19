package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目描述：
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环。
 * 请输出任意一个该有向图的拓扑序列，如果拓扑序列不存在，则输出-1。
 * 若一个由图中所有点构成的序列A满足：对于图中的每条边(x, y)，x在A中都出现在y之前，则称A是该图的一个拓扑序列。
 * 输入格式
 * 第一行包含两个整数n和m
 * 接下来m行，每行包含两个整数x和y，表示点x和点y之间存在一条有向边(x, y)。
 * 输出格式
 * 共一行，如果存在拓扑序列，则输出拓扑序列。
 * 否则输出-1。
 * 数据范围
 * 1≤n,m≤10^5
 * 输入样例：
 * 3 3
 * 1 2
 * 2 3
 * 1 3
 * 输出样例：
 * 1 2 3
 */
public class 拓扑排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            inDegree[b]++;
        }
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            a = q.poll();
            res.add(a);
            for (int i = he[a]; i != 0; i = ne[i]) {
                b = e[i];
                inDegree[b]--;
                if (inDegree[b] == 0) q.add(b);
            }
        }
        if (res.size()==n){
            System.out.println(res);
        }else System.out.println("No");
    }

    private static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int cnt = 1;
    static int[] he = new int[100005];
    static int[] ne = new int[100005];
    static int[] e = new int[100005];
    static int[] inDegree = new int[100005];

    static int n, m;
}
