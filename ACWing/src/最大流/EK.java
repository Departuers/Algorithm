package 最大流;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class EK {

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        s = nextInt();
        t = nextInt();
        int a, b, c;
        for (int i = 1; i <= m; i++) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            if (flag[a][b] == 0) {
                add(a, b, c);
                flag[a][b] = cnt;
            } else {
                w[flag[a][b] - 1] += c;
            }
        }
        while (bfs()) {
            update();
        }
        System.out.println(ans);
    }

    static int[][] flag = new int[1001][1001];
    static int cnt = 1, n, inf = Integer.MAX_VALUE / 2, t, s, ans = 0, m;
    static int[] h = new int[1010];
    static int[] e = new int[20010];
    static int[] ne = new int[20010];
    static int[] w = new int[20010];
    static boolean[] st = new boolean[1002];
    static int[] dis = new int[1002];
    static int[] pre = new int[1010];

    //加双向边
    static void add(int a, int b, int c) {
       e[++cnt]=b;
       w[cnt]=c;
       ne[cnt]=h[a];
       h[a]=cnt;
       e[++cnt]=a;
       w[cnt]=0;
       ne[cnt]=h[b];
       h[b]=cnt;
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    //使用bfs寻找增广路
    static boolean bfs() {
        Arrays.fill(st, false);
        q.clear();
        q.push(s);
        st[s] = true;
        dis[s] = inf;
        while (!q.isEmpty()) {
            int x = q.pollFirst();
            for (int i = h[x]; i != 0; i = ne[i]) {
                if (w[i] == 0) continue;
                int v = e[i];
                if (st[v]) continue;
                dis[v] = Math.min(dis[x], w[i]);
                pre[v] = i;
                q.push(v);
                st[v] = true;
                if (v == t) return true;
            }
        }
        return false;
    }

    static void update() {
        int x = t;
        while (x != s) {
            int v = pre[x];
            w[v] -= dis[t];
            w[v ^ 1] += dis[t];
            x = e[v ^ 1];
        }
        ans += dis[t];
    }

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
