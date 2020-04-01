package 前缀和差分;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class 二维前缀和 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        q = nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                zh[i][j] += zh[i - 1][j] + zh[i][j - 1] - zh[i - 1][j - 1] + nextInt();
            }
        }
        while (q-- != 0) {
            System.out.println(get(nextInt(), nextInt(), nextInt(), nextInt()));
        }
    }

    static int[][] zh = new int[1004][1004];
    static int n, m, q;

    static int get(int x1, int y1, int x2, int y2) {
        return zh[x2][y2] - zh[x1 - 1][y2] - zh[x2][y1 - 1] + zh[x1 - 1][y1 - 1];
    }

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
