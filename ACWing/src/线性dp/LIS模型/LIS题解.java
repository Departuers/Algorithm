package 线性dp.LIS模型;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class LIS题解 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        System.out.println(lis());
    }

    static int n;
    static int[] arr = new int[100005];
    static int[] dp = new int[100005];

    static int lis() {
        int i = 0, len = 0;
        for (int j = 0; j < n; j++) {
            i = Arrays.binarySearch(dp, 0, len, arr[j]);
            if (i < 0) i = -(i + 1);
            dp[i] = arr[j];
            if (i == len) len++;
        }
        return len;
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
