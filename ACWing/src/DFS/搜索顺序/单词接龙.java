package DFS.搜索顺序;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104782198
 * 单词接龙是一个与我们经常玩的成语接龙相类似的游戏。
 * 现在我们已知一组单词，且给定一个开头的字母，要求出以这个字母开头的最长的“龙”，每个单词最多被使用两次。
 * 在两个单词相连时，其重合部分合为一部分，例如 beast 和 astonish ，如果接成一条龙则变为 beastonish。
 * 我们可以任意选择重合部分的长度，但其长度必须大于等于1，且严格小于两个串的长度，例如 at 和 atide 间不能相连。
 * 输入格式
 * 输入的第一行为一个单独的整数 n 表示单词数，
 * 以下 n 行每行有一个单词（只含有大写或小写字母，长度不超过20），输入的最后一行为一个单个字符，表示“龙”开头的字母。
 * 你可以假定以此字母开头的“龙”一定存在。
 * 输出格式
 * 只需输出以此字母开头的最长的“龙”的长度。
 * 数据范围
 * n≤20
 * 输入样例：
 * 5
 * at
 * touch
 * cheat
 * choose
 * tact
 * a
 * 输出样例：
 * 23
 * 提示
 * 连成的“龙”为 atoucheatactactouchoose。
 */
public class 单词接龙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            word[i] = sc.next();
        }
        char start = sc.next().charAt(0);
        //预处理,g[i][j]表示第i个单词的后缀和第j个单词的前缀的最大匹配
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String a = word[i], b = word[j];
                for (int k = 1; k < Math.min(a.length(), b.length()); k++) {
                    if (a.substring(a.length() - k).equals(b.substring(0, k))) {
                        g[i][j] = k;//a的后缀,和b的前缀相匹配的最短长度
                        break;
                    }
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                int p = word[i].length();
//                int q = word[j].length();
//                for (int k = 1; k < p && k < q; k++) {
//                    if (word[i].substring(p - k).equals(word[j].substring(0, k))) {
//                        g[i][j] = k;
//                        break;
//                    }
//                }
//            }
//        }
        for (int i = 0; i < n; i++) {
            if (word[i].charAt(0) == start) {
                dfs(word[i], i);
//                ans = max(dfs(i), ans);
            }
        }
        System.out.println(ans);
    }

    /**
     * @param u 记录上一个单词
     * @return
     */
    static int dfs(int u) {
        int res = 0;
        cnt[u]++;
        for (int i = 0; i < n; i++) {
            if (cnt[i] >= 2) continue;
            if (g[u][i] > 0) res = max(res, dfs(i) - g[u][i]);
        }
        cnt[u]--;
        return res + word[u].length();
    }

    static int[] cnt = new int[30];

    /**
     * @param s    当前拼接单词
     * @param last 上一个单词
     */
    static void dfs(String s, int last) {
        ans = Math.max(s.length(), ans);
        vis[last]++;
        for (int i = 0; i < n; i++) {//枚举和上一个单词
            if (g[last][i] > 0 && vis[i] < 2) {
                dfs(s + word[i].substring(g[last][i]), i);
            }
        }
        vis[last]--;
    }

    static int n, N = 21, ans;
    static String[] word = new String[N];
    static int[] vis = new int[N];
    static int[][] g = new int[N][N];
}
