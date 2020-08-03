package dp.区间dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 给定一个字符串，问最少加几个字符可以使它变为回文串。
 * 比如Ab3bd,添加两个字符后可以变成dAb3bAd或Adb3bdA。
 * 显然这是最优方案。
 * 字符串长度≤5000
 * f[i,j]表示Si~Sj(也就是字符串范围)至少要插入的字符数量,
 * 转移方程:
 * 如果S[i]==S[j]也就是该区间头尾相等,,则有f[i,j]=f[i+1,j-1]
 * 若头尾不相等则f[i,j]=min( f[i,j-1] ,f[i+1,j] )+1
 * 头尾不相等有两种方法更新:从头部插,还是从尾部插,使得对称
 * 因为S[i]不等于S[j]考虑i~j-1范围内的最小添加已经求出
 * 则 s[j]  i....j-1  s[j]  拼接一个字符串到最前面使得对称
 * 则 i i+1....j  s[i]  拼接一个字符串到最后面使得对称
 * Sample Input
 * 5
 * Ab3bd
 * Sample Output
 * 2
 */
public class POJ1159添加字符回文串 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        char[] t = next().toCharArray();
        //只能倒着枚举
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) continue;
                //在计算f[i,j]时,f[i+1][j-1]已经算好了
                //i是由i+1更新的所以从大到小,而j是通过j-1更新的要从小到大
                if (t[i] == t[j]) {
                    f[i][j] = f[i + 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i][j - 1], f[i + 1][j]) + 1;
                }
            }
        }
        bw.write(f[0][n - 1] + " ");
        bw.flush();
    }

    static int n, m;
    //    static char[] s = new char[5001];
    static int[][] f = new int[5010][5010];
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

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}
