package graph.最小生成树;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hzxueyan.com/archives/94/
 * 最小生成树模板
 * 农夫约翰被选为他们镇的镇长！
 * 他其中一个竞选承诺就是在镇上建立起互联网，并连接到所有的农场。
 * 约翰已经给他的农场安排了一条高速的网络线路，他想把这条线路共享给其他农场。
 * 约翰的农场的编号是 1，其他农场的编号是 2 ∼ n。
 * 为了使花费最少，他希望用于连接所有的农场的光纤总长度尽可能短。
 * 你将得到一份各农场之间连接距离的列表，你必须找出能连接所有农场并使所用光纤最短的方案。
 * 输入
 * 第一行包含一个整数 n，表示农场个数。
 * 接下来 n 行，每行包含 n 个整数，输入一个对角线上全是 0 的对称矩阵。
 * 其中第 x + 1 行 y 列的整数表示连接农场 x 和农场 y 所需要的光纤长度。
 * 输出
 * 输出一个整数，表示所需的最小光纤长度。
 * 数据范围
 * 3≤n≤100
 * 每两个农场间的距离均是非负整数且不超过 100000100000。
 * 输入样例
 * 4
 * 0  4  9  21
 * 4  0  8  17
 * 9  8  0  16
 * 21 17 16  0
 * 输出样例
 * 28
 */
public class 最短网络 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                g[i][j] = sc.nextInt();
            }
        }
        System.out.println(prim());
    }

    static int prim() {
        int res = 0;
        Arrays.fill(dis, Integer.MAX_VALUE / 2);
        dis[1] = 0;
        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (!vis[j] && (t == -1 || dis[t] > dis[j])) {
                    t = j;
                }
            }
            if (i != 0 && dis[t] == Integer.MAX_VALUE / 2) return Integer.MAX_VALUE / 2;
            //进行了一轮还是无穷,说明不联通,不存在最小生成树
            res += dis[t];
            vis[t] = true;
            for (int j = 1; j <= n; j++) {
                dis[j] = Math.min(dis[j], g[t][j]);
            }
        }
        return res;
    }

    static int[][] g = new int[110][110];
    static int n;
    static int[] dis = new int[110];
    static boolean[] vis = new boolean[110];
}
