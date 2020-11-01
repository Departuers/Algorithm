package graph.最小生成树拓展;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_42279796/article/details/103211715
 * 北极的某区域共有 n 座村庄，每座村庄的坐标用一对整数 (x,y) 表示。
 *
 * 为了加强联系，决定在村庄之间建立通讯网络，使每两座村庄之间都可以直接或间接通讯。
 *
 * 通讯工具可以是无线电收发机，也可以是卫星设备。
 *
 * 无线电收发机有多种不同型号，不同型号的无线电收发机有一个不同的参数 d，两座村庄之间的距离如果不超过 d，就可以用该型号的无线电收发机直接通讯，d 值越大的型号价格越贵。现在要先选择某一种型号的无线电收发机，然后t统一给所有村庄配备，数量不限，但型号都是 相同的。
 *
 * 配备卫星设备的两座村庄无论相距多远都可以直接通讯，但卫星设备是 有限的，只能给一部分村庄配备。
 *
 * 现在有 k 台卫星设备，请你编一个程序，计算出应该如何分配这 k 台卫星设备，才能使所配备的无线电收发机的 d 值最小。
 *
 * 例如，对于下面三座村庄：
 * 其中，|AB|=10,|BC|=20,|AC|=10√5≈22.36。
 *
 * 如果没有任何卫星设备或只有 1 台卫星设备 (k=0 或 k=1)，则满足条件的最小的 d=20，因为 A 和 B，B 和 C 可以用无线电直接通讯；而 A 和 C 可以用 B 中转实现间接通讯 (即消息从 A 传到 B，再从 B 传到 C)；
 *
 * 如果有 2 台卫星设备 (k=2)，则可以把这两台设备分别分配给 B 和 C ，这样最小的 d 可取 10，因为 A 和 B 之间可以用无线电直接通讯；B 和 C 之间可以用卫星直接通讯；A 和 C 可以用 B 中转实现间接通讯。
 *
 * 如果有 3 台卫星设备，则 A,B,C 两两之间都可以直接用卫星通讯，最小的 d 可取 0。
 *
 * 输入格式
 *
 * 第一行为由空格隔开的两个整数 n,k;
 *
 * 接下来 n 行，每行两个整数，第 i 行的 xi,yi 表示第 i 座村庄的坐标 (xi,yi)。
 *
 * 输出格式
 *
 * 一个实数，表示最小的 d 值，结果保留 2 位小数。
 *
 * 数据范围
 *
 * 1≤n≤500,
 * 0≤x,y≤10^4,
 * 0≤k≤100
 *
 * 输入样例：
 *
 * 3 2
 * 10 10
 * 10 0
 * 30 0
 * 输出样例：
 *
 * 10.00
 * 问题的抽象对于图论的扩展应用太重要了，一般的最小生成树问题是在图中选出n - 1条边，
 * 连通n个顶点，使得生成树边权和最小。本题是可以在k个节点上设置卫星，
 * 在其他点间用无线电通信，要求无线电通信需要的最大距离的最小值。
 * 本质上就是构造k棵生成树构成的生成森林，其中每棵生成树的的根节点就是卫星节点。
 * 与上一题AcWing 1146 新的开始不同的是，上一题挖矿井需要代价，但是矿井数量是不受限的，
 * 只要最终代价最小就行。本题是不用考虑设置卫星的代价，但是最多只能选k个节点放置卫星，
 * 就不能用虚拟源点的办法解决了，因为从虚拟源点向所有点引出的虚拟边数是有限制的。
 * 不妨考虑慢慢往生成树节点中设置卫星对最大距离的影响，只设置一个卫星的话完全没有影响，
 * 因为两个卫星间通信不受距离的限制，一个卫星并没有改变生成树的任何边权；设置两个卫星，
 * 那么这两个卫星可以通信，就相当于连通了，设置k个卫星就相当于将这k个点连通起来，
 * 需要再将剩下的n - k个点连通起来使得剩下点间通信的最大距离最小，
 * 这就相当于找一棵生成树使得这棵生成树的第k + 1大边最小，为了第k + 1大的边最小，
 * 我们应该尽可能的选取边权小的加入生成树，这也就是kruskal算法的过程，
 * 给原图的节点中n - k个节点生成一棵最小生成树，剩下的k个节点就作为卫星节点不用考虑了。总的代码如下：
 *
 * 做最小生成树,把最大的几条边用卫星连接
 * 抽象题目为:找一个最小的d值,是得所有权值大于d的边删去过后,整个图的连通块不超过k
 * 具有单调性,随着d的增加,k单调减少
 * 最终Kruskal本质上在求连通性
 * 当前循环完第i条边,求出由前i条边所构成的所有连通块
 * 输入样例：
 * 3 2
 * 10 10
 * 10 0
 * 30 0
 * 输出样例：
 * 10.00
 * Kruskal进行到一半也是正确的
 * 给原图的节点中n - k个节点生成一棵最小生成树，剩下的k个节点就作为卫星节点不用考虑了。
 */
public class 北极通讯网络 {
    public static void main(String[] args) {
        for (int i = 1; i < par.length; i++) {
            par[i] = i;
        }
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(new node(sc.nextInt(), sc.nextInt()));
        }
        PriorityQueue<node> q = new PriorityQueue<node>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                q.add(new node(i, j, getdist(list.get(i), list.get(j))));
            }
        }//预处理出所有的连接边,
        int cnt = n;
        double res = 0;
        //枚举所有的,
        while (!q.isEmpty()) {
            if (cnt <= k) break;//剩下的村庄可以通过卫星连接,不需要权值边,蛮优秀的想法
            node p = q.poll();
            int a = fin(p.x), b = fin(p.y);
            double w = p.w;
            if (a != b) {
                par[a] = b;
                cnt--;
                res = w;//求最小的d值
            }
        }
        System.out.printf("%.2f", res);
    }

    private static double getdist(node a, node b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static ArrayList<node> list = new ArrayList<node>();

    static class node implements Comparable<node> {
        int x, y;
        double w;

        public node(int x, int y, double w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return (int) (w * 1000 - node.w * 1000);
        }
    }

    static int[] par = new int[250000];
    static int n, m;

    static int find(int x) {
        while (x != par[x]) {
            par[x] = par[par[x]];
            x = par[x];
        }
        return x;
    }

    static int fin(int x) {
        if (par[x] == x) return x;
        return par[x] = fin(par[x]);
    }
}
