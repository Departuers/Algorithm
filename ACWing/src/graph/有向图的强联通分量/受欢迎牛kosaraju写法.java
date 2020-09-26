package graph.有向图的强联通分量;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 受欢迎牛kosaraju写法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int a, b;
        while (m-- != 0) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(h, a, b);
            add(rh, b, a);
        }
        for (int i = 1; i <= n; i++) {
            if (id[i] == 0) {
                dfs(i);
            }
        }
        Collections.reverse(post);
        Arrays.fill(id, -1);

        int sccCount = 0;
        for (Integer w : post) {
            if (id[w] == -1) {
                sccCount++;
                dfs(w, sccCount);
            }
        }
        for (int i = 1; i <= n; i++) {
            size[id[i]]++;
        }

        //缩点
        for (int i = 1; i <= n; i++) {//遍历所有点
            for (int j = h[i]; j != 0; j = ne[j]) {//遍历i所有的邻边
                int k = e[j];
                a = id[i];
                b = id[k];
                if (a != b) {
                    dout[a]++;//出度
                }
            }
        }
        int zeros = 0, sum = 0;
        for (int i = 1; i <= sccCount; i++) {
            if (dout[i] == 0) {
                zeros++;
                sum = size[i];//出度为0的强连通分量,有多少个点
                if (zeros > 1) {//拓扑图,缩点后,如果有超过一个出度为0的点,那么无解
                    sum = 0;
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    static int n, m, N = 10010, M = 100100, idx = 1;
    static int[] h = new int[N];
    static int[] rh = new int[N];
    static int[] ne = new int[M];
    static int[] e = new int[M];
    static int[] id = new int[N];
    static int[] dout = new int[N];
    static int[] size = new int[N];
    static ArrayList<Integer> post = new ArrayList<Integer>();

    static void add(int[] h, int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void dfs(int u, int ccid) {
        id[u] = ccid;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (id[j] == -1) {
                dfs(j, ccid);
            }
        }
    }

    static void dfs(int u) {
        id[u] = 1;
        for (int i = rh[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (id[j] == 0) {
                dfs(j);
            }
        }
        post.add(u);
    }
}
