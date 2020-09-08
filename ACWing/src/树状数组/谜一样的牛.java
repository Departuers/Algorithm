package 树状数组;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://blog.csdn.net/mrgaohaihang/article/details/107146133
 * 有n头奶牛，已知它们的身高为 1~n 且各不相同，但不知道每头奶牛的具体身高。
 * 现在这n头奶牛站成一列，已知第i头牛前面有Ai
 * 头牛比它低，求每头奶牛的身高。
 * 输入格式
 * 第1行：输入整数n。
 * 第2…n行：每行输入一个整数Ai
 * ,第i行表示第i头牛前面有Ai
 * 头牛比它低。
 * （注意：因为第1头牛前面没有牛，所以并没有将它列出）
 * 输出格式
 * 输出包含n行，每行输出一个整数表示牛的身高。
 * 第i行输出第i头牛的身高。
 * 数据范围
 * 1≤n≤105
 * 输入样例：
 * 5
 * 1
 * 2
 * 1
 * 0
 * 输出样例：
 * 2
 * 4
 * 5
 * 3
 * 1
 * 设A1 A2...An
 * 倒着往前推,
 * 那么第An位置,就是第An+1小的数
 * 第A(i)位置,有A(i)头比它少,就是剩下的第A(i)小的数
 * (1)从剩余的数中找到第k小的数
 * (2)删除某个数
 * 树状数组优化!
 * 树状数组,A1~An=1树状数组维护前缀和
 * 令树状数组每一个都等于1,代表这个数可用
 * sum(x)代表1~x有多少个可用
 * 则求第k小的数代表,可以二分求最小的sum(x)的x使得sum(x)=k
 */
public class 谜一样的牛 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 2; i <= n; i++) {
            a[i] = nextInt();
        }
        for (int i = 1; i <= n; i++) {
            add(i, 1);
        }
        for (int i = n; i != 0; i--) {
            int l = 1, r = n, k = a[i] + 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (ask(mid) >= k) r = mid;
                else l = mid + 1;
            }
            ans[i] = r;
            add(r, -1);
        }
        for (int i = 1; i <= n; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.flush();

    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = new StringTokenizer("");

    static String next() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static int n;
    static int[] tr = new int[(int) (1e5 + 10)];
    static int[] a = new int[(int) (1e5 + 10)];
    static int[] ans = new int[(int) (1e5 + 10)];

    static int lowbit(int x) {
        return x & -x;
    }

    static void add(int x, int v) {
        for (int i = x; i <= n; i += lowbit(i)) {
            tr[i] += v;
        }
    }

    static int ask(int x) {
        int res = 0;
        while (x != 0) {
            res += tr[x];
            x -= lowbit(x);
        }
        return res;
    }

}
