package 前缀和差分;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 差分
 * a[] 3 5  2  1  9
 * b[] 3 2 -3 -1  8
 * a[i]=b[i]+b[i-1]+...+b[1]
 * a是b是前缀和数组,b是a的差分
 * 对a[l]+=c相当于对a[l]直到结尾都加上c
 * 对a[r+1]-=c相当于对a[r+1]直到结尾都减去c
 * 这样就可以O(1)实现对数组进行区间加减
 * 构造也是上述构造!!!
 * 不考虑前置构造,直接更新!!!
 * 第二种构造方法:如上找到规律
 * b[4]=a[4]-a[3]
 * b[3]=a[3]-a[2]
 * b[2]=a[2]-a[1]
 * b[1]=a[1]-a[0]
 * 所以可以倒序从n~1
 * b[i]=a[i]-a[i-1]
 * 这样构造,也可以
 * 给出证明:a[i]=b[i]+b[i-1]+...+b[1]=a[i]-a[i-1]+a[i-1]-a[i-2]+...+a[1]=a[i]
 */
public class 差分 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        ca = new int[n + 2];
        int d = 0;
        for (int i = 1; i <= n; i++) {
            d = nextInt();
            insert(i, i, d);
        }
        int a, b, c;
        while (m-- != 0) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            insert(a, b, c);
        }
        for (int i = 1; i <= n; i++) {
            ca[i] += ca[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(ca[i]);
        }
    }

    static void insert(int l, int r, int c) {
        ca[l] += c;
        ca[r + 1] -= c;
    }

    static int n, m;
    static int[] ca;
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
