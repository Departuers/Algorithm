package DFS.搜索顺序;

import java.util.Scanner;

/**
 *https://blog.csdn.net/qq_30277239/article/details/104782198
 */
public class 单词接龙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            word[i] = sc.next();
        }
        char start = sc.next().charAt(0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String a = word[i], b = word[j];
                for (int k = 1; k < Math.min(a.length(), b.length()); k++) {
                    if (a.substring(a.length() - k, k).equals(b.substring(0, k))) {
                        //a的后缀,和b的前缀匹配
                        g[i][j] = k;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (word[i].charAt(0) == start) {
                dfs(word[i], i);
            }
        }
    }

    static void dfs(String s, int last) {
        ans = Math.max(s.length(), ans);
        vis[last]++;
        for (int i = 0; i < n; i++) {
            if (g[last][i] != 0 && vis[i] < 2) {
                dfs(s + word[i].substring(g[last][i]), i);
            }
        }
    }

    static int n, N = 21, ans;
    static String[] word = new String[N];
    static int[] vis = new int[N];
    static int[][] g = new int[N][N];
}
