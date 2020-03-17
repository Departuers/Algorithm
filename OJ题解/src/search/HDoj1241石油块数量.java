package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 给出地图规模n * m，地图中 *（星号）代表空白， @ 代表油田。一群@联通在一起称为油田块（此处的联通为八方向联通）。求地图中油田块的个数。
 * 分析：
 * 既然是求解油田块的个数，自然先想到的办法就是先处理一个油田块，
 * 然后处理下一个油田块……然后依次计数油田块的个数，也就是每次处理一个油田块的时候+1。
 * 我们按照这种方法来实现。
 * 与之前的选数字，或者是给出指定入口求解是否能走地图的题目不同。本题需要全部遍历地图，
 * 也就是说需要一个一个格子来遍历地图，采用双重的for循环来实现。
 * 试想一下：当某一个格子是@时候，我们就从这个格子开始进行dfs，
 * dfs的目的是处理掉与@相连的所有的@，于此同时计数+1。处理完成后，找到下一个是@的格子，
 * 再处理掉与此相连的@，计数+1。如此往复，直到处理完整个地图，搜索结束。
 * 那么不难看出，递归边界就是：这个格子在地图外边。进行递归的条件是：当且仅当这个格子是@并且还没有访问过。
 * 还有一点别忘记，此题判定@@相邻的条件是八向联通 也就是左上左下右上右下相邻也算联通，所以此题是八向搜素。
 * <p>
 * 自己思路:水题,八联通,把@换成*就是联通了
 */
public class HDoj1241石油块数量 {
    static int n = -1, m = -1;
    static char[][] arr;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0) break;
            arr = new char[n][m];
            for (int i = 0; i < n; i++) {
                String n = sc.next();
                for (int j = 0; j < m; j++) {
                    arr[i][j] = n.charAt(j);
                }
            }
            ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == '@') {
                        bfs(i, j);
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }

    }

    static void dfs(int x, int y) {
        arr[x][y] = 'x';
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nx = i + x, ny = j + y;
                if (inArea(nx, ny) && arr[nx][ny] == '@') {
                    dfs(nx, ny);
                }
            }
        }
    }

    static boolean inArea(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int x, int y) {//不知道咋的就超时了
        Queue<node> queue = new LinkedList<node>();
        queue.add(new node(x, y));
        node temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            arr[temp.x][temp.y] = 'x';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    int nx = i + temp.x, ny = j + temp.y;
                    if (inArea(nx, ny) && arr[nx][ny] == '@') {
                        queue.add(new node(nx, ny));
                    }
                }
            }
        }
    }
}
