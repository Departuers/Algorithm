package DFS.搜索;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * n个人参加某项特殊考试。
 * 为了公平，要求任何两个认识的人不能分在同一个考场。
 * 求是少需要分几个考场才能满足条件。
 * 输入
 * 第一行，一个整数n(1<n<100)，表示参加考试的人数。
 * 第二行，一个整数m，表示接下来有m行数据
 * 以下m行每行的格式为：两个整数a，b，用空格分开 (1<=a,b<=n) 表示第a个人与第b个人认识。
 * 输出
 * 一行一个整数，表示最少分几个考场。
 * 5
 * 8
 * 1 2
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 2 5
 * 3 4
 * 4 5
 * out:4
 */
public class 分考场 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = s.nextInt();
            b = s.nextInt();
            p[a][b] = p[b][a] = 1;
        }
        dfs(1);
        System.out.println(ans);
    }

    static void dfs(int u) {
        if (list.size() >= ans) return;//最优性剪枝
        if (u == n) {
            ans = list.size();
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (check(i, u)) {
                list.get(i).add(u);
                dfs(u + 1);
                list.get(i).remove(list.get(i).size() - 1);//回溯
            }
        }
        ArrayList<Integer> q = new ArrayList<Integer>();
        q.add(u);
        list.add(q);
        dfs(u + 1);
        list.remove(list.size() - 1);
    }

    /**
     * @param i 第i组
     * @param u 当前数
     * @return 能否把u放进第i组
     */
    private static boolean check(int i, int u) {
        for (int j = 0; j < list.get(i).size(); j++) {
            if (p[list.get(i).get(j)][u] == 1) return false;
        }
        return true;
    }

    static int ans = 100;
    static int[][] p = new int[310][310];//每一组,放多少
    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    static int n, m;
}
