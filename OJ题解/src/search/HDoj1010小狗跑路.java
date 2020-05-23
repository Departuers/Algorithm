package search;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * 狗可以感觉到地下沉。 他意识到骨头是一个陷阱，他拼命地走出了这个迷宫。
 * 迷宫是M的大小为N的矩形。迷宫里有一扇门。 一开始，门关闭，它将在第T秒打开一段时间（不到1秒）。
 * 因此，小狗不得不在第T秒钟到达门口。 在每秒钟，他可以将一个块移动到上，下，左和右相邻块之一。
 * 一旦他进入一个街区，这个街区的地面将在下一秒开始下沉并消失。
 * 他不能停留在一个街区超过一秒钟，也不能进入一个被访问的块。 可怜的小狗可以生存吗？ 请帮助他
 * 'X': 不能进入
 * 'S': 起点
 * 'D': 出口
 * '.': 路
 * 思路：这是一道标准的dfs题，但是dfs不剪枝的话一般都会超时。这里介绍一种奇偶剪枝的方法（也是看别人的博客学习的~）
 * 奇偶剪枝:
 * 把矩阵标记成如下形式:
 * 0,1,0,1,0
 * 1,0,1,0,1
 * 0,1,0,1,0
 * 1,0,1,0,1
 * 很明显,如果起点在0 而终点在1 那显然 要经过奇数步才能从起点走到终点,
 * 依次类推,奇偶相同的偶数步,奇偶不同的奇数步。在读入数据的时候就可以判断,
 * 并且做剪枝,当然做的时候并不要求把整个矩阵0,1刷一遍,读入的时候起点记为(Si,Sj)
 *  终点记为(Di,Dj) 判断(Si+Sj) 和 (Di+Dj) 的奇偶性就可以了。
 * |cur.x-end.x|+|cur.y-end.y|（因为只能上下左右行走）表示当前位置和终点的距离
 * 若该距离为偶数，表明奇偶相同，要经过偶数步才能到达，剩余步数为M，（M-距离)&1，则表明M为奇数，不行；
 * 若距离为奇数，需要经过奇数步。M也要为奇数。
 * 所以temp=T-cnt-(|cur.x-end.x|+|cur.y-end.y|),if(temp&1||temp<0)直接retrun ;
 * 当cnt==T到达终点时，返回true
 * <p>
 * 自己思路:必须时间刚好是t,不可以使用bfs,因为bfs是最短路径,路径也不一定要最短
 * dfs飘准写法
 * 传说中的奇偶剪枝
 * 设起点为(sx,sy)终点为(ex,ey),只能走t步刚好终点
 * 曼哈顿距离:abs(ex-sx)+abs(ey-sy)是此类问题任意情况,起点到终点的最短步数
 * 非最短路径长度与最短路径长度的奇偶性是相同的,它们的差一定是偶数!!!
 * 若剩余时间为t1,t1-[abs(ex-sx)+abs(ey-sy)]结果为奇数,则无法在t秒内到达
 */
public class HDoj1010小狗跑路 {
    static char[][] arr;
    static int n, m, sx, sy, ex, ey, t, wall;//n行m列,起点终点坐标,最后是时间,必须是准点到,不能早,不能晚
    static boolean flag = false;//小狗是否可以生存

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = sc.next().charAt(0);
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'D') {
                    ex = i;
                    ey = j;
                } else if (c == 'X') {
                    wall++;//墙的数量
                }
            }
        }

        if (n * m - wall < t) System.out.println("No");
        arr[sx][sy] = 'X';
        DFS(sx, sy, 0);
        if (flag) System.out.println("Yes");
        else System.out.println("No");
    }

    static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    static void DFS(int x, int y, int time) {
        if (x == ex && y == ey && t == time) {//终点!
            flag = true;
            return;
        }
        if (flag) return;//剪枝,保险写法
        //奇偶剪枝
        int temp = (t - time) - Math.abs(x - ex) - Math.abs(y - ey);
        //剩余时间,剩余到达终点的最短步数,看他们的奇偶性是否相同
        if (temp < 0 || (temp & 1) == 1) return;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d][0], ny = y + dirs[d][1];
            if (inArea(nx, ny) && arr[nx][ny] != 'X') {
                arr[nx][ny] = 'X';
                DFS(nx, ny, t + 1);
                arr[nx][ny] = '.';//回溯
                if (flag) return;//剪枝,保险写法
            }
        }
    }

    //越界检查
    static boolean inArea(int x, int y) {
        return !(x < 0 || x >= n || y < 0 || y <= m);
    }
}
