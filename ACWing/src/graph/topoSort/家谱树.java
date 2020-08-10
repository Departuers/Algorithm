package graph.topoSort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 这道题一定有解
 * 拓扑排序
 * https://www.hzxueyan.com/archives/103/
 * 入度为0的点加入队列,取出入度为0的点,并减去该点相邻的入度,
 * 队列中实际上是存的所有入度为0的点,使用pq可以达到字典序
 * 知道队列没有元素,该题一定有解,无解情况用vis判断,
 */
public class 家谱树 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int s = 0;
        for (int i = 1; i <= n; i++) {
            while (true) {
                s = sc.nextInt();
                if (s == 0) break;
                add(i, s);
                in[s]++;
            }
        }
        topo();
    }

    private static void topo() {
        ArrayList<Integer> g = new ArrayList<Integer>();//拓扑排序的结果
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                q.push(i);
                g.add(i);
            }
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = he[t]; i != 0; i = ne[i]) {
                int j = e[i];
                --in[j];
                if (in[j] == 0) {
                    q.add(j);
                    g.add(j);
                }
            }
        }
        for (Integer w : g) {
            System.out.print(w + " ");
        }
    }

    static int N = 110, M = N * N / 2, n, cnt = 1;
    static int[] he = new int[N];
    static int[] ne = new int[M];
    static int[] e = new int[M];
    static int[] in = new int[N];
//    static int[] out = new int[N];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }


}
