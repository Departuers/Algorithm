package graph.二分图;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acwing.com/solution/content/3042/
 * 二分+染色判断
 * 使得二分图,求一边的罪犯仇恨值的最大值,最小
 * 答案0-10^9之间,看是否具有二分性质
 * check()怎么写至关重要
 * 也就是把答案区间分成两段,最优解把答案区间分成两段,一边具备这个性质,另一边不具备这个性质
 * 使得最优解是这两段的边界
 *
 * 把大于mid边都删除,对应题目就是,把所有仇恨值大于mid的两个罪犯,分到两个监狱,
 * 如果取值更大,是否成立?成立,一张图是二分图,去掉一些边也是二分图
 *
 * 是不是二分图,可不可行
 * 如果可行那么最优解一定小于等于mid
 * 对于最优解的右边一定也是满足性质,因为最优解释最小的那个点,
 * 选择大于最优解的值,去掉一些边,肯定还是二分图
 * 答案区间左边为不满足二分性质,
 * 一般二分图指的都是无向图
 */
public class 关押罪犯 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        int a, b, c;
        while (m-- != 0) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            add(a, b, c);
            add(b, a, c);
        }
        int l = 0, r = (int) 1e9;
        while (l < r) {
            int mid = l + r >> 1;//二分仇恨值
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        System.out.println(l);
    }

    private static boolean check(int mid) {
        Arrays.fill(color, 0);//染色重置
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, mid)) return false;
            }
        }
        return true;
    }
    //染色函数
    static boolean dfs(int u, int colo, int mid) {
        color[u] = colo;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (w[i] <= mid) continue;
            if (color[j] == 0) {
                if (!dfs(j, 3 - colo, mid)) return false;
            } else if (color[j] == colo) return false;
        }
        return true;
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

    static int n, m, N = 20010, M = 201000, idx = 1;
    static int[] h = new int[N];
    static int[] e = new int[M];
    static int[] w = new int[M];
    static int[] ne = new int[M];
    static int[] color = new int[N];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

}
