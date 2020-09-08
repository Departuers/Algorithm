package DFS.多源bfs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 多源bfs
 * 173. 矩阵距离
 * 给定一个N行M列的01矩阵A，A[i][j] 与 A[k][l] 之间的曼哈顿距离定义为：
 * dist(A[i][j],A[k][l])=|i−k|+|j−l|
 * 输出一个N行M列的整数矩阵B，其中：
 * B[i][j]=min1≤x≤N,1≤y≤M,A[x][y]=1dist(A[i][j],A[x][y])
 * 输入格式
 * 第一行两个整数n,m。
 * 接下来一个N行M列的01矩阵，数字之间没有空格。
 * 输出格式
 * 一个N行M列的矩阵B，相邻两个整数之间用一个空格隔开。
 * 数据范围
 * 1≤N,M≤1000
 * 输入样例：
 * 3 4
 * 0001
 * 0011
 * 0110
 * 输出样例：
 * 3 2 1 0
 * 2 1 0 0
 * 1 0 0 1
 * 找每个点到指定最近的终点
 * 显然:多源最短路,求每个点到一堆起点的距离,终点不唯一找出最近,可以转化成单源最短路
 * 有一个虚拟头结点,与所有起点有一条边权为0的边,
 * 体现在bfs中就是队列中添加所有起点!!!也就是把要到的位置加入对队列作为起点
 * 体现在dijkstra就是要真的把那个源点建立出来
 */
public class 矩阵距离 {
    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = nextInt();
        m = nextInt();

        for (int i = 0; i < n; i++) {
            g[i] = next().toCharArray();
        }

        ArrayDeque<node> q = new ArrayDeque<node>();
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], -1);//dis当做vis数组使用
            for (int j = 0; j < m; j++) {
                if (g[i][j] == '1') {
                    q.add(new node(i, j));
                    dis[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            node p = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = p.x + dir[i][0], y = dir[i][1] + p.y;
                if (x < 0 || x >= n || y < 0 || y >= m || dis[x][y] != -1) continue;//dis当做vis数组使用
                dis[x][y] = dis[p.x][p.y] + 1;
                q.add(new node(x, y));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(dis[i][j] + "");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int n, m, N = 1010;
    static char[][] g = new char[N][N];
    static int[][] dis = new int[N][N];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}
