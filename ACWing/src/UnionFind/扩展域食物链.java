package UnionFind;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * x是同类域
 * x+n是捕食域
 * x+n+n是天敌域
 */
public class 扩展域食物链 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 0; i <= 3 * n; i++) p[i] = i;
        int t, x, y, ans = 0;
        for (int i = 1; i <= m; i++) {
            t = nextInt();
            x = nextInt();
            y = nextInt();
            if (x > n || y > n) ans++;
            else if (t == 1) {
                if (find(x) == find(y + n) || find(x) == find(y + n + n)) ans++;
                else {
                    merge(x, y);
                    merge(x + n, y + n);
                    merge(x + n + n, y + n + n);
                }
            } else {
                if (x == y || find(x) == find(y) || find(x) == find(y + n)) {
                    ans++;
                } else {
                    merge(x, y + n + n);//y的捕食域加入x
                    merge(x + n, y);//x的天敌域加入y
                    merge(x + n + n, y + n);//x的捕食域是y的同类域
                }
            }
        }
    }

    static int[] p = new int[200100];
    static int n, m;

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    static void merge(int x, int y) {
        p[find(x)] = find(y);
    }

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
}
