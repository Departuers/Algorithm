package basic.二分;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/solution/content/6883/
 * 儿童节那天有 K 位小朋友到小明家做客。小明拿出了珍藏的巧克力招待小朋友们。
 * 小明一共有 N 块巧克力，其中第 i 块是 Hi×Wi 的方格组成的长方形。
 * 为了公平起见，小明需要从这 N 块巧克力中切出 K 块巧克力分给小朋友们。
 * 切出的巧克力需要满足：
 * 形状是正方形，边长是整数大小相同
 * 例如一块 6×5 的巧克力可以切出 6 块 2×2 的巧克力或者 2 块 3×3 的巧克力。
 * 当然小朋友们都希望得到的巧克力尽可能大，你能帮小明计算出最大的边长是多少么？
 * 输入格式
 * 第一行包含两个整数 N 和 K。
 * 以下 N 行每行包含两个整数 Hi 和 Wi。
 * 输入保证每位小朋友至少能获得一块 1×1 的巧克力。
 * 输出格式
 * 输出切出的正方形巧克力最大可能的边长。
 * 数据范围
 * 1≤N,K≤105,
 * 1≤Hi,Wi≤105
 * 输入样例：
 * 2 10
 * 6 5
 * 5 6
 * 输出样例：
 * 2
 * 二分
 */
public class 分巧克力 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            b[i] = nextInt();
        }
        int l = 0, r = 100001;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        System.out.println(l);
    }

    static boolean check(int k) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (a[i] / k) * (b[i] / k);
        }
        return sum >= m;
    }

    static int n, m, N = 100010;
    static int[] a = new int[N], b = new int[N];

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
