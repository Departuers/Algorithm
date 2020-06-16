package DFS.迭代加深;

import java.util.*;

/**
 * https://blog.csdn.net/qq_30277239/article/details/105752519
 * 迭代加深,虽然会重复搜,但层数非常深,但使用bfs内存不够,所以使用迭代加深
 */
public class 加成序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        path[0] = 1;

        while (sc.hasNext()) {
            n = sc.nextInt();
            if (n == 0) break;
            int depth = 1;
            while (!dfs(1, depth)) depth++;
            for (int i = 0; i < depth; i++) {
                System.out.print(path[i] + " ");
            }
        }
    }

    static boolean dfs(int u, int depth) {
        if (u > depth) return false;
        if (path[u - 1] == n) return true;
        boolean[] st = new boolean[110];
        for (int i = u - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int s = path[i] + path[j];
                if (s > n || s <= path[u - 1] || st[s]) continue;
                st[s] = true;
                path[u] = s;
                if (dfs(u + 1, depth)) return true;
            }
        }
        return false;
    }

    static int[] path = new int[110];
    static int n;
}
