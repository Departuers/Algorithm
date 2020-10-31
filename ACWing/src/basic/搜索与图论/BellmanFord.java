package basic.搜索与图论;

import java.util.Arrays;
import java.util.Scanner;

/**
 * O(nm)
 * for i=0;i<n;i++{  迭代次数,有什么含义,加入迭代k次,经过不超过k条边的最短路的距离
 * for 所有边 a,b,w    有一条a->b权重是w//由于是所有边,可能发生串联
 * dis[b]=min( dis[b], dis[a]+w )   松弛操作
 * 从1-a的路径加上a到b的路径是不是会是的1到b的距离更短
 * }
 * 再循环n次过后,对于所有的边都满足 dis[b]<=dis[a]+w
 * 推广求出最多经过k条边的最短路
 */
public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            nodes[i] = new node(a, b, c);
        }
        int t = bf();
        if (t > 1e9 / 2) System.out.println(-1);
        else System.out.println(t);
    }

    static int n, m, k;
    static int[] dis = new int[510], temp = new int[541];

    static class node {
        int x, y, z;

        public node(int a, int b, int w) {
            this.x = a;
            this.y = b;
            this.z = w;
        }
    }

    static node[] nodes = new node[10100];

    static int bf() {
        Arrays.fill(dis, (int) 1e9);
        dis[1] = 0;
        for (int i = 0; i < k; i++) {
            System.arraycopy(dis, 0, temp, 0, dis.length);
            for (int j = 0; j < m; j++) {//遍历每条边
                int a = nodes[j].x, b = nodes[j].y, c = nodes[j].z;
                dis[b] = Math.min(dis[b], temp[a] + c);//使用上一次的结果来更新,用备份来更新不会串联
            }
        }
        return dis[n];
    }

}
