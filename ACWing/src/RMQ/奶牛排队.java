package RMQ;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 题目:https://www.acwing.com/problem/content/description/1276/
 * https://www.acwing.com/file_system/file/content/whole/index/content/568662/
 */
public class 奶牛排队 {
    public static void main(String[] args) throws IOException {
        N = nextInt();
        Q = nextInt();
        init();
        int a, b;
        for (int i = 0; i < Q; i++) {
            a = nextInt();
            b = nextInt();
                bw.write(query(a, b) + "\n");
        }
        bw.flush();
    }

    static void init() throws IOException {
        log[1] = 0;
        for (int i = 2; i < 50000; i++) {
            log[i] = log[i / 2] + 1;
        }
        for (int i = 1; i <= N; i++) {
            max[i][0] = nextInt();
            min[i][0] = max[i][0];
        }
        for (int j = 1; 1 << j <= N; j++) {
            for (int i = 1; i + (1 << j) - 1 <= N; i++) {
                max[i][j] = Math.max(max[i][j - 1], max[i + (1 << j - 1)][j - 1]);
                min[i][j] = Math.min(min[i][j - 1], min[i + (1 << j - 1)][j - 1]);
            }
        }
    }

    static int query(int l, int r) {
        int k = log[r - l + 1];
        int t1 = Math.max(max[l][k], max[r - (1 << k) + 1][k]);
        int t2 = Math.min(min[l][k], min[r - (1 << k) + 1][k]);
        return t1 - t2;
    }

    static int[] log = new int[50010];
    static int[][] max = new int[50010][19];
    static int[][] min = new int[50010][19];

    static int[] h = new int[50010];

    static int N, Q;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }
}
