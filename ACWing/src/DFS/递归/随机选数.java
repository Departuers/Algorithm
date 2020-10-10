package DFS.递归;

/**
 * 92. 递归实现指数型枚举
 * 从 1~n 这 n 个整数中随机选取任意多个，输出所有可能的选择方案。
 * 输入格式
 * 输入一个整数n。
 * 输出格式
 * 每行输出一种方案。
 * 同一行内的数必须升序排列，相邻两个数用恰好1个空格隔开。
 * 对于没有选任何数的方案，输出空行。
 * 本题有自定义校验器（SPJ），各行（不同方案）之间的顺序任意。
 * 数据范围
 * 1≤n≤15
 * 输入样例：
 * 3
 * 输出样例：
 * 3
 * 2
 * 2 3
 * 1
 * 1 3
 * 1 2
 * 1 2 3
 */
public class 随机选数 {
    public static void main(String[] args) {

//        Scanner sc = new Scanner(in);
//        n = sc.nextInt();
//        long l = System.nanoTime();
//        f(0);
//        long r = System.nanoTime();
//        System.out.println((r - l) / 1e8);
//        l = System.nanoTime();

        //dfs(0, 0);
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1)
                    System.out.print(j + 1 + " ");
            }
            System.out.println();
        }
        //位运算求子集
//        r = System.nanoTime();
//        System.out.println((r - l) / 1e8);
    }

    static int n = 4;
    static int[] arr = {1, 2, 3, 4, 5, 6};
    static int[] vis = new int[10];

    //dfs求子集,选和不选
    static void dfs(int u) {
        if (u == n) {//经过所有的数
            for (int i = 0; i < n; i++) {
                if (vis[i] == 1)
                    System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
//        vis[u] = 2;
        dfs(u + 1);//不用第u个数字
//        vis[u] = 0;

        vis[u] = 1;
        dfs(u + 1);//用第u个数字,把state的第u位变成为1
        vis[u] = 0;
    }

    static boolean[] st = new boolean[23];

    static void f(int u) {
        if (u == n) {
            for (int i = 0; i < n; i++) {
                if (st[i]) System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        f(u + 1);
        st[u] = true;
        f(u + 1);
        st[u] = false;
    }

    static void dfs(int u, int state) {
        if (u == n) {
            if (state == 0) return;
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1)
                    System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        dfs(u + 1, state);
        dfs(u + 1, state | (1 << u));
    }
}