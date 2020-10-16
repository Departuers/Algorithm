package 前缀和差分;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 假设原矩阵是Aij
 * 构造差分矩阵 Bi,j
 * 使得差分矩阵求了前缀和是Ai,j
 * 考虑更新
 */
public class 二维差分矩阵 {

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        q = nextInt();
        ca = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                insert(i, j, i, j, nextInt());
            }
        }
        while (q-- != 0) {
            insert(nextInt(), nextInt(), nextInt(), nextInt(), nextInt());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                ca[i][j] += ca[i - 1][j] + ca[i][j - 1] - ca[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                bw.write(ca[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    //给一个矩阵加上c,x1,y1是左上角的点坐标,x2,y2是右下角的坐标
    static void insert(int x1, int y1, int x2, int y2, int c) {
        ca[x1][y1] += c;//右下角全部加上c
        ca[x1][y2 + 1] -= c;//右边减去c
        ca[x2 + 1][y1] -= c;//下方减去c
        ca[x2 + 1][y2 + 1] += c;//重复减的加上c
    }

    static int[][] ca;
    static int n, m, q;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}
