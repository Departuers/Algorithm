package dp.树形dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104433574
 * 太平王世子事件后，陆小凤成了皇上特聘的御前一品侍卫。
 * 皇宫各个宫殿的分布，呈一棵树的形状，宫殿可视为树中结点，
 * 两个宫殿之间如果存在道路直接相连，则该道路视为树中的一条边。
 * 已知，在一个宫殿镇守的守卫不仅能够观察到本宫殿的状况，
 * 还能观察到与该宫殿直接存在道路相连的其他宫殿的状况。
 * 大内保卫森严，三步一岗，五步一哨，每个宫殿都要有人全天候看守，
 * 在不同的宫殿安排看守所需的费用不同。
 * 可是陆小凤手上的经费不足，无论如何也没法在每个宫殿都安置留守侍卫。
 * 帮助陆小凤布置侍卫，在看守全部宫殿的前提下，使得花费的经费最少。
 * 输入格式
 * 输入中数据描述一棵树，描述如下：
 * 第一行 n，表示树中结点的数目。
 * 第二行至第 n+1 行，每行描述每个宫殿结点信息，依次为：该宫殿结点标号 i，在该宫殿安置侍卫所需的经费 k，该结点的子结点数 m，接下来 m 个数，分别是这个结点的 m 个子结点的标号 r1,r2,…,rm。
 * 对于一个 n 个结点的树，结点标号在 1 到 n 之间，且标号不重复。
 * 输出格式
 * 输出一个整数，表示最少的经费。
 * 数据范围
 * 1≤n≤1500
 * 输入样例：
 * 6
 * 1 30 3 2 3 4
 * 2 16 2 5 6
 * 3 5 0
 * 4 4 0
 * 5 11 0
 * 6 5 0
 * 输出样例：
 * 25
 * 要么是这个点上放一个士兵,要么是与这个节点相连的节点放一个士兵,
 * 每个点花费不一样.问最小花费
 * 本题一个节点可以观察到其父节点和孩子节点，求看守所有的宫殿需要的最小花费。
 * 如果还是像之前的问题那样考虑两种状态，即一个节点放置守卫和不放置守卫。
 * 放置守卫时，其孩子节点可以放置也可以不放置；但是不放置守卫时，
 * 其孩子节点的情况就不那么明朗了，因为还需要考虑该节点的父节点有没有放置守卫，
 * 放置了就可以观察到该节点，其孩子节点放不放就无所谓了，但是万一父节点也没有放置守卫，
 * 孩子节点就至少有一个要放置守卫了。所以只设置两种状态并不合适，一个节点要么放置守卫，
 * 要么不放置，不放置又可以细分为两种情况，父节点放置了守卫和孩子节点放置了守卫。
 * 所以一共有三种状态，f[u][0]表示u的父节点放置了守卫但是u未放置守卫，
 * f[u][1]表示u的孩子节点放置了节点但是u未放置守卫，f[u][2]表示u节点放置了守卫。
 * u的父节点放置了守卫，故u的孩子节点可以放置可以不放置，
 * f[u][0] += min(f[j][1][,f[j][2])，因为u没有放置节点，
 * 而f[j][0]表示j的父节点放置了守卫，所以是不合法的状态。如果u放置了守卫，
 * 则f[u][2] += min(f[j][0],f[j][1],f[j][2])。比较麻烦的是f[u][1]，当u没有放置守卫，
 * 需要找u的一个孩子放置守卫，其它孩子可以放置可以不放置。设sum是u的所有孩子节点放置或者不放置守卫各种情况的最小花费，
 * 即sum += min(f[j][1],f[j][2])，可以发现sum与f[u][0]是相等的，所以不必单独设置sum来存储u的孩子节点的最小花费和，
 * 设u的某个孩子k放置了守卫，则f[u][1] = min(f[u][1],sum - min(f[k][1],f[k][2]) + f[k][2])。具体实现见代码：
 * <p>
 * f[i,0]表示:在点i可以被父节点所有摆放方案看到的最小花费
 * f[i,1]表示:在点i可以被子节点所有摆放方案看到的最小花费
 * f[i,2]表示:在点i上放警卫的所有方案的最小花费
 * f[i,0]=∑ min(f[j,1],f[j,2]),由于i点可以被父节点看到,它的子节点,要么被子节点的子节点看到,要么在子节点上放警卫
 * f[i,2]=∑ min(f[j,1] f[j,2],f[j,0])
 * f[i,1]= 枚举k个子节点 min(f[k,2])加上除开k的子节点,min(f[j,1],f[j,2]) 枚举哪一个字节点看到的i
 */
public class 皇宫看守 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int id, cost, cnt;
        for (int i = 1; i <= n; i++) {
            id = sc.nextInt();
            cost = sc.nextInt();
            cnt = sc.nextInt();
            w[id] = cost;
            while (cnt-- != 0) {
                int ver = sc.nextInt();
                st[ver] = true;//不是根节点
                add(id, ver);
            }
        }
        int root = 1;
        while (st[root]) root++;
        dfs(root);
        System.out.println(Math.min(f[root][1], f[root][2]));
        //最终,root没有父节点,所以这两个值取min
    }

    static int n, N = 1510, idx = 1;
    static int[] h = new int[N], e = new int[N], w = new int[N], ne = new int[N];
    static int[][] f = new int[N][3];
    static boolean[] st = new boolean[N];

    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    static void dfs(int u) {
        f[u][2] = w[u];
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            dfs(j);
            f[u][0] += Math.min(f[j][0], f[j][2]);
            f[u][2] += Math.min(f[j][0], Math.min(f[j][1], f[j][2]));
        }
        f[u][1] = 0x3f3f3f3f;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int j = e[i];
            f[u][1] = Math.min(f[u][1], f[u][2] + f[u][0] - Math.min(f[j][1], f[j][2]));
        }
    }

}
