package DFS.连通性;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 一天Extense在森林里探险的时候不小心走入了一个迷宫，迷宫可以看成是由 n∗n的格点组成，每个格点只有2种状态，.和#，前者表示可以通行后者表示不能通行。
 * 同时当Extense处在某个格点时，他只能移动到东南西北(或者说上下左右)四个方向之一的相邻格点上，Extense想要从点A走到点B，问在不走出迷宫的情况下能不能办到。
 * 如果起点或者终点有一个不能通行(为#)，则看成无法办到。
 * 注意：A、B不一定是两个不同的点。
 * 输入格式
 * 第1行是测试数据的组数 k，后面跟着 k 组输入。
 * 每组测试数据的第1行是一个正整数 n，表示迷宫的规模是 n∗n 的。
 * 接下来是一个 n∗n 的矩阵，矩阵中的元素为.或者#。
 * 再接下来一行是 4 个整数 ha,la,hb,lb，描述 AA 处在第 ha 行, 第 la 列，BB 处在第 hb 行, 第 lb 列。
 * 注意到 ha,la,hb,lb全部是从 0 开始计数的。
 * 输出格式
 * k行，每行输出对应一个输入。
 * 能办到则输出“YES”，否则输出“NO”。
 * 数据范围
 * 1≤n≤100
 * 输入样例：
 * 2
 * 3
 * .##
 * ..#
 * #..
 * 0 0 2 2
 * 5
 * .....
 * ###.#
 * ..#..
 * ###..
 * ...#.
 * 0 0 4 0
 * 输出样例:
 * YES
 * NO
 * 分析：
 * 本题同样是Flood Fill问题，不过只用求连通性而不用求最短距离，
 * 这时DFS更加有优势，只要不爆栈，DFS可以一直深入直到走到终点，而BFS差不多要遍历完所有的状态，
 * 比较不划算。本题要注意后台数据的起点和终点位置都可能有障碍物，这时候就是不连通的了；
 * 另外，已经走过的点必须要置为已访问，下次遇见不再扩展，不然重复搜索效率极低。
 */
public class 迷宫 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        t = s.nextInt();
        while (t-- != 0) {
            for (int i = 0; i <= 100; i++) {
                Arrays.fill(st[i], false);
            }
            n = s.nextInt();
            for (int i = 0; i < n; i++) {
                map[i] = s.next().toCharArray();
            }
            int sx = s.nextInt();
            int sy = s.nextInt();
            tx = s.nextInt();
            ty = s.nextInt();
            if (dfs(sx, sy)) System.out.println("YES");
            else System.out.println("NO");
        }

    }

    static int tx, ty;

    //是否能到达,不要求最短距离
    static boolean dfs(int sx, int sy) {
        if (map[sx][sy] == '#') return false;
        if (sx == tx && sy == ty) return true;
        st[sx][sy] = true;
        //dfs在开头判掉,bfs在内部判掉
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + sx, ny = sy + dir[i][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !st[nx][ny]) {
                st[nx][ny] = true;
                if (dfs(nx, ny)) return true;
            }
        }
        return false;
    }

    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static boolean[][] st = new boolean[110][110];
    static int n, t;
    static char[][] map = new char[110][110];
}
