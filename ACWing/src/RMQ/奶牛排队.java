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
        for (int i = 1; i <= N; i++) {
            h[i] = nextInt();
        }

    }

    static void init() {

    }

    static int[] log = new int[23];

    static int h[] = new int[50010];

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
