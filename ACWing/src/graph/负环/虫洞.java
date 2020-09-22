package graph.负环;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_44828887/article/details/107271471
 * 农夫约翰在巡视他的众多农场时，发现了很多令人惊叹的虫洞。
 * 虫洞非常奇特，它可以看作是一条 单向 路径，通过它可以使你回到过去的某个时刻
 * （相对于你进入虫洞之前）。
 * 农夫约翰的每个农场中包含N片田地，M条路径（双向）以及W个虫洞。
 * 现在农夫约翰希望能够从农场中的某片田地出发，经过一些路径和虫洞回到过去，
 * 并在他的出发时刻之前赶到他的出发地。
 * 他希望能够看到出发之前的自己。
 * 请你判断一下约翰能否做到这一点。
 * 下面我们将给你提供约翰拥有的农场数量F，以及每个农场的完整信息。
 * 已知走过任何一条路径所花费的时间都不超过10000秒，
 * 任何虫洞将他带回的时间都不会超过10000秒。
 * 输入格式
 * 第一行包含整数F，表示约翰共有F个农场。
 * 对于每个农场，第一行包含三个整数N，M，W。
 * 接下来M行，每行包含三个整数S，E，T，表示田地S和E之间存在一条路径，经过这条路径所花的时间为T。
 * 再接下来W行，每行包含三个整数S，E，T，表示存在一条从田地S走到田地E的虫洞，走过这条虫洞，可以回到T秒之间。
 * 输出格式
 * 输出共F行，每行输出一个结果。
 * 如果约翰能够在出发时刻之前回到出发地，则输出“YES”，否则输出“NO”。
 * 数据范围
 * 1≤F≤5
 * 1≤N≤500,
 * 1≤M≤2500,
 * 1≤W≤200,
 * 1≤T≤10000,
 * 1≤S,E≤N
 * 2
 * 3 3 1
 * 1 2 2
 * 1 3 4
 * 2 3 1
 * 3 1 3
 * 3 2 1
 * 1 2 3
 * 2 3 4
 * 3 1 8
 * out
 * NO
 * YES
 * 由于虫洞是回到之前 所以可以看做是田地为点的负权值边。
 * 田地之间是双向边。
 * 只要检测到图中存在负环，那么就可以无限穿越到之前的时间，
 * 而由于田地点之间是双向边，所以肯定能到达任意一个田地(也包括起点)。
 * 所以本题目就是判断图中是否存在负环。
 * 5200*500=250w
 * 250w*5约为10^7
 * 2*5200+10=5210条单向边最多
 */
public class 虫洞 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        int a, b, c;
        while (t-- != 0) {
            n = sc.nextInt();
            m1 = sc.nextInt();
            m2 = sc.nextInt();
            idx = 1;
            Arrays.fill(he, 0);
            while (m1-- != 0) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                add(a, b, c);
                add(b, a, c);
            }
            while (m2-- != 0) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                add(a, b, -c);
            }
            if (spfa()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    private static boolean spfa() {
        Arrays.fill(dis, 0);
        Arrays.fill(vis, false);
        Arrays.fill(cnt, 0);

        for (int i = 1; i <= n; i++) {
            q.add(i);
            vis[i] = true;
        }
        int p;
        while (!q.isEmpty()) {
            p = q.poll();
            vis[p] = false;
            for (int i = he[p]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[p] + w[i]) {
                    dis[j] = dis[p] + w[i];
                    cnt[j] = cnt[p] + 1;
                    if (cnt[j] >= n) return true;
                    if (!vis[j]) {
                        q.add(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return false;
    }

    static int t, n, m1, m2, idx = 1;
    static int[] he = new int[510];
    static int[] e = new int[5210];
    static int[] ne = new int[5210];
    static int[] w = new int[5210];
    static int[] cnt = new int[510];
    static int[] dis = new int[510];
    static boolean[] vis = new boolean[510];

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = he[a];
        he[a] = idx++;
    }

}