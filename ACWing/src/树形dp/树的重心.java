package 树形dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/100988651
 * 树的重心是指树上一点，去掉后最大子树可以取得最小值的点。
 * 去掉重心后,最大子树大小不超过n/2
 * 下面简单描述下本题的解题过程。要想找到树的重心，需要知道去掉某节点后剩下连通块中节点的数量，
 * 对于某个节点u，以u为根节点的子树的节点总数为x，则其父节点（若存在）所在连通块节点的数目为n-x。
 * 要求以某节点为根节点子树的节点的个数，只需要递归的求以其孩子节点为根节点子树的总和即可。
 * 设int dfs(int u)这个函数能够实现该功能，则想要统计u所有子树节点之和，
 * 只需要int sum = 0；u的孩子节点比如有a，b，sum + dfs(a)+dfs(b) + 1即是u所在子树节点的总和了，
 * 同时，还可以求各个连通块节点的最大值，找到树的重心。
 */
public class 树的重心 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b;
        for (int i = 1; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            add(b, a);
        }
        dfs(1);
        System.out.println(ans);
        System.out.println(Arrays.toString(count));
    }

    private static int dfs(int u) {
        vis[u] = true;
        int size = 0, sum = 1;
        //sum为以sum节点为根的子树,节点数量
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            if (vis[j]) continue;//不用遍历父节点
            int s = dfs(j);
            size = Math.max(size, s);
            sum += s;
        }
        size = Math.max(size, n - sum);
        count[u] = size;
        ans = Math.min(ans, size);
        //   System.out.println(ans);
        return sum;//返回sum,为以sum节点为根的子树,节点数量
    }

    static int ans = Integer.MAX_VALUE;
    static int[] count = new int[(int) 1e5];
    static boolean[] vis = new boolean[(int) (1e5 + 4)];
    static int[] he = new int[(int) (1e5 + 4)];
    static int[] ne = new int[(int) (2e5 + 4)];
    static int[] e = new int[(int) (2e5 + 4)];

    static int cnt = 1, n;

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }
}
