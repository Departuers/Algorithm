package graph.最小生成树拓展;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/103211427
 * https://blog.csdn.net/qq_30277239/article/details/108033213
 * 发展采矿业当然首先得有矿井，小 F 花了上次探险获得的千分之一的财富请人在岛上挖了 n 口矿井，但他似乎忘记了考虑矿井供电问题。
 * 为了保证电力的供应，小 F 想到了两种办法：
 * 在矿井 i 上建立一个发电站，费用为 vi（发电站的输出功率可以供给任意多个矿井）。
 * 将这口矿井 i 与另外的已经有电力供应的矿井 j 之间建立电网，费用为 pi,j。
 * 小 FF 希望你帮他想出一个保证所有矿井电力供应的最小花费方案。
 * 输入格式
 * 第一行包含一个整数 n，表示矿井总数。
 * 接下来 n 行，每行一个整数，第 i 个数 vi 表示在第 i 口矿井上建立发电站的费用。
 * 接下来为一个 n×n 的矩阵 P，其中 pi,j 表示在第 i 口矿井和第 j 口矿井之间建立电网的费用。
 * 数据保证 pi,j=pj,i，且 pi,i=0。
 * 输出格式
 * 输出一个整数，表示让所有矿井获得充足电能的最小花费。
 * 数据范围
 * 1≤n≤300,
 * 0≤vi,pi,j≤10^5
 * 输入样例：
 * 4
 * 5
 * 4
 * 4
 * 3
 * 0 2 2 2
 * 2 0 3 3
 * 2 3 0 4
 * 2 3 4 0
 * 输出样例：
 * 9
 * 分析：
 * 本题可以在图上选若干个点作为发电站，然后将这些发电站与剩下的点连接起来，
 * 其实最后需要构建的网络就是一棵生成森林，每个发电站都是树根，然后连接剩下的节点。
 * 我们可以构建一个虚拟的超级源点，从该源点到各发电站的距离就是建造发电站的代价，
 * 所以最后需要构造的就是这n+1个点构成的最小生成树了，可以用prim算法继续去求解。
 * 本题的核心在于虚拟超级源点的构造，将生成森林转化为一棵生成树。总的代码如下：
 *
 * 如果不考虑方法（1），则这就是个最小生成树模板题，但由于矿井必须连上发电厂才能供电，
 * 所以我们将发电场视为点0(设矿井从1到n编号)，在i上建立发电站认为是从0到i建立一条边
 * 然后简单证明下：首先只有一个发电站就是普通最小生成树，肯定正确；
 * 当存在多个发电站时，因为建了发电站的矿井已经通过0点连通了，其他点的连通问题就转换成了0~n这(n+1)个点的最小生成树。
 * 本来不是最小生成树问题
 * 把在该节点新建一条边,看做向虚拟源点(0号点)连一条边,代表修发电站
 * 虚拟源点,是个非常重要的技巧,这样就变成了最小生成树问题
 * 图论问题,一定要先想到虚拟源点技巧
 */
public class 新的开始 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            g[0][i] = g[i][0] = sc.nextInt();
        }//把0号点看做超级源点
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        System.out.println(prim());
    }

    static int[][] g = new int[310][310];

    static int n;
    static int[] dis = new int[310];
    static boolean[] vis = new boolean[310];

    static int prim() {
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        dis[0] = 0;
        int ans = 0;
        //n+1个点,找n条边
        for (int i = 0; i <= n; i++) {
            int s = -1;
            for (int j = 0; j <= n; j++) {
                if (!vis[j] && (s == -1 || dis[j] < dis[s]))
                    s = j;
            }
            vis[s] = true;
            ans += dis[s];
            for (int j = 0; j <= n; j++) {
                dis[j] = Math.min(dis[j], g[s][j]);
            }
        }
        return ans;
    }
}
