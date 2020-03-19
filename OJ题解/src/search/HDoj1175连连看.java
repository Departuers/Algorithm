package search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.in;

/**
 *   “连连看”相信很多人都玩过。没玩过也没关系，下面我给大家介绍一下游戏规则：
 * 在一个棋盘中，放了很多的棋子。如果某两个相同的棋子，
 * 可以通过一条线连起来（这条线不能经过其它棋子），而且线的转折次数不超过两次，
 * 那么这两个棋子就可以在棋盘上消去。不好意思，由于我以前没有玩过连连看，咨询了同学的意见，
 * 连线不能从外面绕过去的，但事实上这是错的。现在已经酿成大祸，就只能将错就错了，连线不能从外围绕过。
 *   玩家鼠标先后点击两块棋子，试图将他们消去，然后游戏的后台判断这两个方格能不能消去。
 * 现在你的任务就是写这个后台程序。
 * Input
 *     输入数据有多组。每组数据的第一行有两个正整数n,m(0<n<=1000,0<m<1000)，分别表示棋盘的行数与列数。在接下来的n行中，每行有m个非负整数描述棋盘的方格分布。0表示这个位置没有棋子，正整数表示棋子的类型。接下来的一行是一个正整数q(0<q<50)，表示下面有q次询问。在接下来的q行里，每行有四个正整数x1,y1,x2,y2,表示询问第x1行y1列的棋子与第x2行y2列的棋子能不能消去。n=0,m=0时，输入结束。
 *     注意：询问之间无先后关系，都是针对当前状态的！
 * Output
 *      每一组输入数据对应一行输出。如果能消去则输出"YES",不能则输出"NO"。
 * Sample Input
 *     3 4
 *     1 2 3 4
 *     0 0 0 0
 *     4 3 2 1
 *     4
 *     1 1 3 4
 *     1 1 2 4
 *     1 1 3 3
 *     2 1 2 4
 *     3 4
 *     0 1 4 3
 *     0 2 4 1
 *     0 0 0 0
 *     2
 *     1 1 2 4
 *     1 3 2 3
 *     0 0
 * Sample Output
 *     YES
 *     NO
 *     NO
 *     NO
 *     NO
 *     YES
 * 思路：
 * 一条路径最多只能转向2次，有一些情况可能得到了从起点到终点的路径，但是它的转向次数已经超过的2次，这样这条路径就不符合要求，得重新找一条。
 * 一个一般的结论：如果某一点记录的转向次数大于当前路径在该点的转向次数，那么还能从该点再发出一条路径来查找。
 * <p>
 * n/m最多1000,用邻接矩阵DFS是5000毫秒我吐了白写这么久
 * 用BFS是170ms书上说,但Java是1700ms
 */
public class HDoj1175连连看 {

    static int n, m;
    static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    static boolean[][] vis = new boolean[1010][1010];
    static int[][] g = new int[1010][1010];

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(in);
        while (true) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0) break;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    g[i][j] = sc.nextInt();
                }
            }
            int q = sc.nextInt();//q个查询;
            for (int z = 0; z < q; z++) {
                for (int i = 0; i < vis.length; i++) {
                    Arrays.fill(vis[i], false);
                }
                int x1, x2, y1, y2;
                x1 = sc.nextInt();
                y1 = sc.nextInt();
                x2 = sc.nextInt();
                y2 = sc.nextInt();
                if (g[x1][y1] != 0 && g[x1][y1] == g[x2][y2] && (x1 != x2 || y1 != y2)) {
                    //起点和终点有棋子,颜色相同,位置不同
                    if (bfs(x1, y1, x2, y2)) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                } else System.out.println("NO");
            }
        }
    }

    //判断节点合法
    static boolean inArea(int x, int y) {
        if (x < 1 || x > n || y < 1 || y > m || vis[x][y]) return false;
        return true;
    }

    //(x1,y1)为当前位置(x2,y2)是目标位置,dir是方向,num是转折次数
    static boolean dfs(int x1, int y1, int x2, int y2, int dir, int num) {
        vis[x1][y1] = true;
        for (int d = 0; d < 4; d++) {
            int x = x1 + dirs[d][0], y = y1 + dirs[d][1];
            int tem = ((dir == d) ? num : num + 1);
            if (tem <= 2 && inArea(x, y)) {//判断转折次数小于2,节点合法
                if (x == x2 && y == y2) return true;//判断终点
                else if (!vis[x][y] && g[x][y] == 0 && dfs(x, y, x2, y2, d, tem)) return true;
            }//是0才能走
        }
        vis[x1][y1] = false;
        return false;
    }

    static class node implements Cloneable {
        int x, y;
        int num;//转弯次数
        int dir;//方向

        public node(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new node(x, y, num, dir);
        }
    }

    static int[][] step = new int[1010][1010];//节点拐弯次数

    static boolean bfs(int x1, int y1, int x2, int y2) throws CloneNotSupportedException {
        for (int i = 0; i < step.length; i++) {
            Arrays.fill(step[i], 1);
        }
        Queue<node> queue = new LinkedList<node>();
        node st = new node(x1, y1, -1, -1);//书里num为0,写-1才能ac
        queue.add(st);
        while (!queue.isEmpty()) {
            node temp = queue.poll();
            if (temp.x == x2 && temp.y == y2 && temp.num <= 2) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                node s = (node) temp.clone();
                s.x += dirs[d][0];
                s.y += dirs[d][1];
                if ((s.x >= 1 && s.x <= n && s.y >= 1 && s.y <= m && g[s.x][s.y] == 0) || (s.x == x2 && s.y == y2)) {
                    if (s.dir != -1) {
                        if (s.dir != d) {
                            s.dir = d;
                            s.num++;
                        }
                    } else s.dir = d;
                    if (s.num > 2) continue;
                    if (s.num <= step[s.x][s.y]) { //我是没看懂这玩意有啥用...step初始化为1就错,
                        //出口在前面,添加了这个,状态就添加不进去
                        step[s.x][s.y] = s.num;
                        queue.add(s);
                    }
                    //queue.add(s);
                }
            }
        }
        return false;
    }
}
