package search;

import java.util.PriorityQueue;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 天使被关在了一个监狱里面，a代表天使，r代表天使的朋友，x是警卫，#是墙。r可以杀死所以的警卫，
 * 杀一个警卫需要1个单位时间，移动一步也需要一个单位时间。问，天使的朋友r最短需要多久才可以把天使a救出来。
 * 如果不能救出天使，输出:Poor ANGEL has to stay in the prison all his life.
 * 思路:接近天使最短的时间,杀个守卫走一步,都是一个时间,
 * 7 8
 * #.#####.
 * #.a#..r.
 * #..#x...
 * ..#..#.#
 * #...##..
 * .#......
 * ........
 * out:
 * 13
 */
public class HDoj1242拯救天使 {
    static int n, m;
    static char[][] map = new char[205][205];
    static boolean[][] vis = new boolean[205][205];
    static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    static class node implements Comparable<node> {
        int x, y, step;

        public node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }

        @Override
        public int compareTo(node node) {
            return step - node.step;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String n = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = n.charAt(j);
            }
        }
        int s = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'a') {
                    s = bfs(i, j);
                    break;
                }
            }
        }

        if (s == 0)
            System.out.println("Poor ANGEL has to stay in the prison all his life.");
        else
            System.out.println(s);
    }

    //从天使反向找朋友
    static int bfs(int x, int y) {
        int ans = 0;
        PriorityQueue<node> pq = new PriorityQueue<node>();
        pq.add(new node(x, y, 0));
        vis[x][y] = true;

        while (!pq.isEmpty()) {
            node temp = pq.poll();
            if (map[temp.x][temp.y] == 'r') {
                ans = temp.step;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = temp.x + dirs[d][0], ny = temp.y + dirs[d][1], step = temp.step + 1;
                if (inArea(nx, ny) && !vis[nx][ny] && map[nx][ny] != '#') {
                    vis[nx][ny] = true;
                    if (map[nx][ny] == 'x')
                        step++;
                    pq.add(new node(nx, ny, step));
                }
            }
        }
        return ans;

    }

    static boolean inArea(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
