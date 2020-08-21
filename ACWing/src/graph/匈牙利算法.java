package graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个二分图，其中左半部包含n1个点（编号1~n1），右半部包含n2个点（编号1~n2），二分图共包含m条边。
 * 数据保证任意一条边的两个端点都不可能在同一部分中。
 * 请你求出二分图的最大匹配数。
 * 二分图的匹配：给定一个二分图G，在G的一个子图M中，
 * M的边集{E}中的任意两条边都不依附于同一个顶点，则称M是一个匹配。
 * 二分图的最大匹配：所有匹配中包含边数最多的一组匹配被称为二分图的最大匹配
 * 其边数即为最大匹配数。
 * 输入格式
 * 第一行包含三个整数 n1、 n2 和 m。
 * 接下来m行，每行包含两个整数u和v，表示左半部点集中的点u和右半部点集中的点v之间存在一条边。
 * 输出格式
 * 输出一个整数，表示二分图的最大匹配数。
 * 数据范围
 * 1≤n1,n2≤500,1≤u≤n1,1≤v≤n2,1≤m≤105
 * 输入样例：
 * 2 2 4
 * 1 1
 * 1 2
 * 2 1
 * 2 2
 * 输出样例：
 * 2
 * 分析：
 * 二分图的最大匹配问题，就是对于二分图而言，
 * 将左边点集和右边点集配对，求配对的最大成功数目。
 * 用y总的话说就是，将二分图左边的点集看作男生，右边点集看作女生，
 * 每个男生都喜欢至少一个女生，现在找能凑成情侣的最大对数。
 * 比如上图左边点集对应编号为1到5的男生，右边点集对应1到5的女生，
 * 左边点集有指向右边点集的边表明有好感关系、二分图的最大匹配可以用匈牙利算法求解，
 * 具体步骤就是：1号男生和1号女生配对后，5号男生也喜欢1号女生，为了发扬坚持不懈的精神，
 * 尽管5号男生还有其他喜欢的女生，也要先尽力的得到1号女生。
 * 恰好此前和1号女生配对的1号男生还有另一个喜欢的2号妹纸，
 * 于是经过5号男生的劝说，1号男生就把1号女生让给了5号男生，自己跑去和2号女生配对了。
 * 具体的流程是，需要遍历每一个左边节点，然后用st数组表示有没有尝试和右边的某个节点匹配过，
 * 这是为了防止重复比对，比如A尝试和B匹配，但是失败了，然后继续尝试和C匹配，
 * 在这之前应该将st数组的B节点标记为已访问。另一个数组是match数组，
 * 表明右边结点有没有被其它左边节点匹配上了，在匹配过程中，
 * 如果待匹配的节点没有和其它节点匹配过，就匹配它，
 * 否则尝试让之前匹配它的节点换个节点进行匹配。也就是说看上一个妹纸，
 * 先看她有木有对象，没有就在一起，有了就去和她男友商量，让他换个对象，
 * 把女友让给自己；如果对方男友换了对象，这时就可以和该妹纸在一起了，
 * 要是对方男友不肯让，就只好尝试再去考虑其他喜欢的女生了。
 * 洛谷ac
 */
public class 匈牙利算法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            add(sc.nextInt(), sc.nextInt());
        }
        for (int i = 1; i <= n1; i++) {
            Arrays.fill(vis, false);
            if (find(i)) res++;
        }
        System.out.println(res);
    }

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }

    static int res = 0;

    static boolean find(int v) {
        for (int i = he[v]; i != 0; i = ne[i]) {
            int u = e[i];
            if (!vis[u]) {
                vis[u] = true;
                if (match[u] == 0 || find(match[u])) {
                    match[u] = v;
                    return true;
                }
            }
        }
        return false;
    }

    static int[] match = new int[10006];
    static boolean[] vis = new boolean[10006];
    static int n, m, cnt = 1;
    static int[] e = new int[20005];
    static int[] he = new int[10005];
    static int[] ne = new int[20005];
}
