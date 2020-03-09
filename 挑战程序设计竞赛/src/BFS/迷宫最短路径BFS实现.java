package BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * POJ 3984
 * https://blog.csdn.net/Jason_Ranger/article/details/50768661
 * https://blog.csdn.net/a739260008/article/details/89166428
 * 给定一个大小为N*M的迷宫，迷宫由通道和墙壁组成
 * 每一步可以向左右上下四个方向走一步，
 * 请计算从出发点到终点最少需要多少步，
 * 若不能到达终点则输出0
 * N,M<=100
 * n=10 m=10
 * #S######.#
 * ......#..#
 * .#.##.##.#
 * .#........
 * ##.##.####
 * ....#....#
 * .#######.#
 * ....#.....
 * .####.###.
 * ....#...G#
 *
 * BFS其实与与DFS一样,都会生成所有能遍历到的状态,
 * 递归函数可以代码简短,状态的管理更简单,求取最短路的时候使用BFS,
 * 因为求取最短路时,DFS需要反复经过同样的状态
 *
 * BFS会把状态逐个加入队列,因此通常需要与状态数成正比的内存空间,
 * 反之,DFS是与最大递归深度成正比的,一般与状态数相比,递归的深度并不会太大
 * 一般来说DFS比BFS更节省内存,
 *
 */
public class 迷宫最短路径BFS实现 {

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sx = 0;
        int sy = 0;
        data = new char[n][m];
        sc.nextLine();//换行,再输入迷宫数据
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextLine().toCharArray();
            System.out.println(Arrays.toString(data[i]));
        }
        int res = BFS(n, m, 0, 1, 9, 8);
        System.out.println(res);
    }

    public static char[][] data;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    /**
     * @param N  长度
     * @param M  宽度
     * @param sx 起点横坐标
     * @param sy 起点纵坐标
     */
    public static int BFS(int N, int M, int sx, int sy, int gx, int gy) {
        int[][] d = new int[N][M];//到各个位置的最短距离的数组
        for (int i = 0; i < N; i++) {
            Arrays.fill(d[i], -1);
        }

        Queue<Node> queue = new ArrayDeque<Node>();
        Node first = new Node(sx, sy);
        queue.offer(first);//初始化起点入队
        d[sx][sy] = 0;//初始化起点的,最短距离数组为0

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int cx = cur.x;
            int cy = cur.y;
            if (cx == gx && cy == gy) {
                return d[gx][gy];//如果取到了终点,直接返回结束方法
            }
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];//取出偏移量
                int ny = cy + dy[i];//取出偏移量
                // 判断是否访问过，越界，可行
                if ((0 <= nx && nx < N)//边界控制
                        && (0 <= ny && ny < M)//边界控制
                        && (d[nx][ny] == -1)//判断这个位置是否遍历过
                        && (data[nx][ny] != '#')) {//判断这个位置是否是墙壁,能不能走
                    Node nextNode = new Node(nx, ny);
                    queue.add(nextNode);//将经过筛选可以遍历到的位置,加入队列
                    d[nx][ny] = d[cx][cy] + 1;//维护,加入队列的那个节点的距离起点的距离
                }
            }
        }
        return d[gx][gy];
    }
}