package String;

import java.io.*;
import java.util.StringTokenizer;

public class 洛谷kmp {
    public static void main(String[] args) throws IOException {
        a = next().toCharArray();
        p = next().toCharArray();
        init();
        int i = 0, m = a.length, j = 0, n = p.length;
        while (i < m && j < n) {
            if (j == -1 || p[j] == a[i]) {
                i++;
                j++;
            } else j = ne[j];
            if (j == n) {
                bw.write(i - j + 1 + "\n");
                j = ne[j];
            }
        }
        for (int k = 1; k <= p.length; k++) {
            bw.write(ne[k] + " ");
        }
        bw.flush();
    }

    private static void init() {
        ne[0] = -1;
        int t = 0;
        for (int i = 1; i <= p.length; i++) {
            t = ne[i - 1];
            while (t != -1 && p[i - 1] != p[t]) t = ne[t];
            ne[i] = t + 1;
        }
    }

    static int[] ne = new int[1000];
    static char[] p;
    static char[] a;

    static int n;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tk = new StringTokenizer("");

    static String next() throws IOException {
        while (!tk.hasMoreTokens()) {
            tk = new StringTokenizer(br.readLine());
        }
        return tk.nextToken();
    }
}
