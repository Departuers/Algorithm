package RMQ;

import java.io.*;
import java.util.StringTokenizer;

public class 洛谷ST {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }
        init();
        int a, b;
        /**
         * 索引从1开始!!!
         */
        for (int i = 0; i < m; i++) {
            a = nextInt();
            b = nextInt();
            bw.write(query(a, b) + "\n");
        }
        bw.flush();
    }

    static void init() {
        log[1] = 0;
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        for (int i = 1; i <= n; i++) {
            st[i][0] = a[i];
        }
        for (int j = 1; 1 << j <= n; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                st[i][j] = Math.max(st[i][j - 1], st[i + (1 << j - 1)][j - 1]);
            }
        }
    }

    static int query(int l, int r) {
        int k = log[r - l + 1];
        return Math.max(st[l][k], st[r - (1 << k) + 1][k]);
    }

    static int[] a = new int[100010];
    static int n, m;
    static int[] log = new int[(int) 1e5];
    static int[][] st = new int[100100][20];


    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk = new StringTokenizer("");

    static String next() throws IOException {
        while (!stk.hasMoreTokens()) {
            stk = new StringTokenizer(br.readLine());
        }
        return stk.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
