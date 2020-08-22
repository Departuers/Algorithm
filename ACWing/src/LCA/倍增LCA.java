package LCA;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://nekrozcoder.github.io/2019/07/28/%E3%80%90%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0%E3%80%91LCA%20%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/#more
 * 预处理O(nlogn) 在线
 * 查询O(log n)
 * 洛谷3379 tle3个
 */
public class 倍增LCA {
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

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        root = nextInt();
        for (int i = 1; i < n; i++) {
            add(nextInt(), nextInt());
        }
        init(root, n);
        for (int i = 0; i < m; i++) {
            bw.write(query(nextInt(), nextInt()) + "\n");
        }
        bw.flush();
    }

    static int maxn = 500004;
    static int[] e = new int[maxn << 1];
    static int[] he = new int[maxn];
    static int[] ne = new int[maxn << 1];
    static int[] w = new int[maxn << 1];
    static int n, m, cnt = 1, root;
    static int[][] up = new int[maxn][20];
    static int[] depth = new int[maxn];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
        e[cnt] = a;
        ne[cnt] = he[b];
        he[b] = cnt++;
    }

    //dist[a]存的是a往上直到根节点的权值和
    static int[] dist = new int[100060];

    /**
     * 求取每个节点深度
     * 和dist[a]存的是a往上直到根节点的权值和
     *
     * @param u  当前节点
     * @param fa 父节点
     * @param d  该节点的深度
     */
    static void dfs(int u, int fa, int d) {
        depth[u] = d;
        up[u][0] = fa;
        for (int i = he[u]; i != 0; i = ne[i]) {
            if (e[i] != fa) {
                dist[e[i]]=dist[u]+w[i];
                dfs(e[i], u, d + 1);
            }
        }
    }

    //预处理:O(n Log n)
    static void init(int root, int n) {
        dist[root] = 0;
        dfs(root, root, 1);
        for (int j = 1; 1 << j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                //根据语义,up[i][k]代表i往上爬2^k的终点
                //不可能超过根节点
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
    }
    //O(log n)
    static int swim(int x, int h) {
        for (int i = 0; h > 0; i++) {
            if ((h & 1) == 1) {
                x = up[x][i];
            }
            h >>= 1;
        }
        //二进制优化向上爬
        return x;//爬到哪个节点上了
    }

    //每次询问(n log n ~log n)
    static int query(int x, int y) {
        if (depth[x] < depth[y]) return query(y, x);
        //使得x在下方
        x = swim(x, depth[x] - depth[y]);//使得两个点处于同一层
        if (x == y) return x;//如果两个点已经是同一个点,那么return
        for (int i = 19; i >= 0; i--) {//从高位开始枚举
            if (up[x][i] != up[y][i]) {//跳到最近公共祖先的下面一层,若up[a,k]和up[b,k]相等无法判断是不是最近的公共祖先
                x = up[x][i];
                y = up[y][i];
            }
        }
        return up[x][0];
    }
}
