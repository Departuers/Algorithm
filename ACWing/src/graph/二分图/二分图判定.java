package graph.二分图;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://blog.csdn.net/qq_30277239/article/details/101471978
 * 给定一个n个点m条边的无向图，图中可能存在重边和自环。请你判断这个图是否是二分图。
 * 输入样例：
 * 4 4
 * 1 3
 * 1 4
 * 2 3
 * 2 4
 * 输出样例：
 * Yes
 *
 */
public class 二分图判定 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b;
        while (m-- != 0) {
            a = nextInt();
            b = nextInt();
            add(a, b);
            add(b, a);
        }
        boolean f = true;
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {//有好几个连通块可能,我们要判定每一个能不能成为二分图
                if (!dfs(i, 1)) {//不能构成二分图就break
                    f = false;
                    break;
                }
            }
        }
        if (f) System.out.println("YES");
        else System.out.println("NO");
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer sk = new StringTokenizer("");

    static String next() throws IOException {
        while (!sk.hasMoreTokens()) {
            sk = new StringTokenizer(br.readLine());
        }
        return sk.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static int N = 100010, M = 201000, n, m, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] color = new int[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }


    static boolean dfs(int u, int c) {
        color[u] = c;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (color[j] == 0) {//该点未被访问过
                if (!dfs(j, 3 - c)) return true;
            } else if (color[j] == c) return false;
            //u点和j点,也就是一条边的两边的颜色相同,我们就说他不是二分图
        }
        return true;
    }
}
