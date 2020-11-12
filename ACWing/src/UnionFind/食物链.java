package UnionFind;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * https://blog.csdn.net/lisong_jerry/article/details/80029967
 * 带权并查集
 * 精髓:只要两个元素在一个集合里面，
 * 通过它们与根节点的距离就能知道它们的相对关系
 * 模3余0代表同类,余1代表a吃b,余2代表被a吃
 */
public class 食物链 {

    static int[] p = new int[100005], d = new int[1000005];

    static int find(int x) {
        if (p[x] != x) {
            int root = find(p[x]);
            //t代表最祖先节点
            d[x] += d[p[x]];
            //记录p[x]这个节点到祖先的距离,带权并查集精髓
            p[x] = root;
        }
        return p[x];
    }

    static int n, m;
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
        Scanner sc = new Scanner(System.in);
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        int res = 0;
        int t, x, y;
        while (m-- != 0) {
            t = nextInt();
            x = nextInt();
            y = nextInt();
            //如果没有这种动物,肯定就是假话
            if (x > n || y > n) res++;
            else {
                int px = find(x), py = find(y);
                if (t == 1) {//是同类
                    if (px == py && Math.abs((d[x] - d[y])) % 3 != 0) res++;
                    else if (px != py) {
                        p[px] = py;//px指向py,合并
                        d[px] = d[y] - d[x];//维护到根节点的距离
                    }
                } else {//x吃y
                    if (px == py && Math.abs(d[x] - d[y] - 1) % 3 != 0) res++;
                    else if (px != py) {
                        p[px] = py;//px指向py,合并
                        d[px] = d[y] + 1 - d[x];//维护到根节点的距离
                    }
                }
            }
        }
        System.out.println(res);
    }
}
