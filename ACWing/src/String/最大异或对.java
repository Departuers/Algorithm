package String;

import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

/**
 * 在给定的N个整数A1，A2……AN中选出两个进行xor（异或）运算，得到的结果最大是多少？
 * 输入格式
 * 第一行输入一个整数N。第二行输入N个整数A1～AN。
 * 输出格式
 * 输出一个整数表示答案。
 * 数据范围
 * 1≤N≤10^5,0≤Ai<2^31
 * 输入样例：
 * 3
 * 1 2 3
 * 输出样例：
 * 3
 * 考虑暴力,两两异或,显然O(n^2),10^10超时
 * 异或就是二进制位运算.两个二进制位对应相同就是1,不同就是0
 * 考虑把二进制数做成trie
 * 可以O(log n)寻找与当前数异或值最大的数
 * 算法复杂度最终为O(n logn)
 */
public class 最大异或对 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }

    public static void main(String[] args) throws IOException {
        n = nextInt();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int t = nextInt();
            insert(t);
            int q = query(t);
            res = Math.max(res, q ^ t);
        }
        System.out.println(res);
    }

    static int N = 100100, M = 31 * N, n, idx = 1;
    static int[][] son = new int[M][2];
    static int[] a = new int[N];

    static int query(int x) {
        int p = 0, res = 0;
        StringBuilder s = new StringBuilder();
        for (int i = 30; i >= 0; i--) {
            int u = x >> i & 1;
            if (son[p][1 - u] != 0) {
                p = son[p][1 - u];
                s.append(1 - u);
            } else {
                p = son[p][u];
                s.append(u);
            }
        }
        return Integer.valueOf(s.toString(), 2);
    }

    static void insert(int x) {
        int p = 0;
        for (int i = 30; i >= 0; i--) {
            int u = x >> i & 1;
            if (son[p][u] == 0)
                son[p][u] = idx++;
            p = son[p][u];
        }
    }
}
