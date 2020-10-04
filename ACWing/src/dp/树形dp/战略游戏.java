package dp.树形dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104417024
 * 和没有上司的舞会有很像
 * 鲍勃喜欢玩电脑游戏，特别是战略游戏，但有时他找不到解决问题的方法，这让他很伤心。
 * 现在他有以下问题。
 * 他必须保护一座中世纪城市，这条城市的道路构成了一棵树。
 * 每个节点上的士兵可以观察到所有和这个点相连的边。
 * 他必须在节点上放置最少数量的士兵，以便他们可以观察到所有的边。
 * 你能帮助他吗？
 * 例如，下面的树：
 * 只需要放置1名士兵（在节点1处），就可观察到所有的边。
 * 输入格式
 * 输入包含多组测试数据，每组测试数据用以描述一棵树。
 * 对于每组测试数据，第一行包含整数N，表示树的节点数目。
 * 接下来N行，每行按如下方法描述一个节点。
 * 节点编号：(子节点数目) 子节点 子节点 …
 * 节点编号从0到N-1，每个节点的子节点数量均不超过10，每个边在输入数据中只出现一次。
 * 输出格式
 * 对于每组测试数据，输出一个占据一行的结果，表示最少需要的士兵数。
 * 数据范围
 * 0<N≤1500
 * 输入样例：
 * 4
 * 0:(1) 1
 * 1:(2) 2 3
 * 2:(0)
 * 3:(0)
 * 5
 * 3:(3) 1 4 2
 * 1:(1) 0
 * 2:(0)
 * 0:(0)
 * 4:(0)
 * 输出样例：
 * 1
 * 2
 * 显然改个样例
 * 4 3  四个点3条边无向图
 * 0 1
 * 1 3
 * 1 2
 * 5 4
 * 3 1
 * 3 4
 * 3 2
 * 1 0
 * 每条边上最少选择一个点,求最小权值,和没有上司的舞会对称
 * f[i,j] j=0,1 状态机,每个状态转换
 * 所有在以i为根的子树中选,且点i的状态是j的所有选法
 * 属性min权值.
 * <p>
 * f[i,0]所有以i为根的子树,并且不选第i点的最小权值
 * i的子树,都有一个方案,让所有子树分别取得最小价值
 * f[i,0]=min( f[s1,1]+f[s2,1]+f[s3,1]+... )所有子树的最小值,
 * 对于一个父节点不选,由于一条边至少有一个点选择,所以下一层子节点都要选
 * <p>
 * f[i,1]=min( min( f[s1,0] ,f[s1,1]) ,min( f[s2,0] ,f[s2,1]) ..... )要覆盖所有子树
 * 由于一条边至少有一个点选择,所以下一层子节点可以选也可以不选,所以取min
 */
public class 战略游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int id = 0, cnt = 0, t;
        while (sc.hasNext()) {
            idx = 1;
            Arrays.fill(he, 0);
            Arrays.fill(ne, 0);
            Arrays.fill(vis, false);
            n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                id = s.charAt(0) - '0';
                cnt = s.charAt(3) - '0';
                for (int j = 0; j < cnt; j++) {
                    t = sc.nextInt();
                    add(id, t);
                    vis[t] = true;
                }
            }
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    dfs(i);
                    System.out.println(Math.min(f[i][1], f[i][0]));
                    break;
                }
            }
        }


    }

    static boolean[] vis = new boolean[1502];
    static int n, idx = 1;
    static int[] he = new int[1502];
    static int[] ne = new int[1502];
    static int[] e = new int[1502];
    static int[][] f = new int[1502][2];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }

    static void dfs(int u) {
        f[u][0] = 0;
        f[u][1] = 1;
        for (int i = he[u]; i != 0; i = ne[i]) {
            int j = e[i];
            dfs(j);
            f[u][0] += f[j][1];
            f[u][1] += Math.min(f[j][1], f[j][0]);
        }
    }
}
