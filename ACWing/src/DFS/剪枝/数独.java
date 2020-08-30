package DFS.剪枝;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/105734349
 * 搜索做数独
 * 数独是一种传统益智游戏，你需要把一个9 × 9的数独补充完整，
 * 使得图中每行、每列、每个3 × 3的九宫格内数字1~9均恰好出现一次。
 * 请编写一个程序填写数独。
 * 输入格式
 * 输入包含多组测试用例。
 * 每个测试用例占一行，包含81个字符，代表数独的81个格内数据
 * （顺序总体由上到下，同行由左到右）。
 * 每个字符都是一个数字（1-9）或一个”.”（表示尚未填充）。
 * 您可以假设输入中的每个谜题都只有一个解决方案。
 * 文件结尾处为包含单词“end”的单行，表示输入结束。
 * 输出格式
 * 每个测试用例，输出一行数据，代表填充完全后的数独。
 * 输入样例：
 * 4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......
 * ......52..8.4......3...9...5.1...6..2..7........3.....6...1..........7.4.......3.
 * end
 * 输出样例：
 * 417369825632158947958724316825437169791586432346912758289643571573291684164875293
 * 416837529982465371735129468571298643293746185864351297647913852359682714128574936
 * 分析：
 * 位运算+剪枝
 */
public class 数独 {
    public static void main(String[] args) {
        int t = 1 << 10;
        for (int i = 0; i < t; i++) {
            int j = i;
            while (j != 0) {
                j -= j & -j;
                one[i]++;
            }
        }

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Arrays.fill(g, 0);
            Arrays.fill(r, 0);
            Arrays.fill(c, 0);
            Arrays.fill(w, 0);
            s = sc.next();
            if (s.equals("end")) {
                break;
            }
            t = 0;
            flag = false;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.') {
                    t++;
                    continue;
                }
                int x = i / 9, y = i % 9;
                g[x][y] = s.charAt(i) - '0';
                r[x] += 1 << g[x][y];
                c[y] += 1 << g[x][y];
                int u = get(x, y);
                w[u] += 1 << g[x][y];
            }
        }
    }

    static int get(int x, int y) {
        x /= 3;
        y /= 3;
        return x * 3 + y;
    }

    static boolean flag;

    static int N = 10, M = 1 << 10 + 1;
    static int[] r = new int[N];
    static int[] one = new int[M];
    static int[] w = new int[N];
    static int[] c = new int[N];
    static int[][] g = new int[N][N];
    static String s;
}
