package BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acwing.com/file_system/file/content/whole/index/content/1372134/
 * 三维bfs
 * 你现在被困在一个三维地牢中，需要找到最快脱离的出路！
 * 地牢由若干个单位立方体组成，其中部分不含岩石障碍可以直接通过，部分包含岩石障碍无法通过。
 * 向北，向南，向东，向西，向上或向下移动一个单元距离均需要一分钟。
 * 你不能沿对角线移动，迷宫边界都是坚硬的岩石，你不能走出边界范围。
 * 请问，你有可能逃脱吗？
 * 如果可以，需要多长时间？
 * 输入格式
 * 输入包含多组测试数据。
 * 每组数据第一行包含三个整数 L,R,C
 * 分别表示地牢层数，以及每一层地牢的行数和列数。
 * 接下来是 L
 * 个 R 行 C
 * 列的字符矩阵，用来表示每一层地牢的具体状况。
 * 每个字符用来描述一个地牢单元的具体状况。
 * 其中, 充满岩石障碍的单元格用”#”表示，不含障碍的空单元格用”.”表示，你的起始位置用”S”表示，终点用”E”表示。
 * 每一个字符矩阵后面都会包含一个空行。
 * 当输入一行为”0 0 0”时，表示输入终止。
 * 输出格式
 * 每组数据输出一个结果，每个结果占一行。
 * 如果能够逃脱地牢，则输出”Escaped in x minute(s).”，其中X为逃脱所需最短时间。
 * 如果不能逃脱地牢，则输出”Trapped!”。
 * 数据范围
 * 1≤L,R,C≤100
 */
public class 地牢大师 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            l = sc.nextInt();
            r = sc.nextByte();
            c = sc.nextInt();
            if (l == 0 && r == 0 && c == 0) break;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    g[i][j] = sc.next().toCharArray();
                    for (int k = 0; k < c; k++) {
                        char t = g[i][j][k];
                        if (t == 'S') { //找到起点和终点
                            start = new node(i, j, k);
                        } else if (t == 'E') {
                            end = new node(i, j, k);
                        }
                    }
                }
            }
            int dis = bfs();
            if (dis == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.printf("Escaped in %d minute(s).\n", dis);
            }
        }
    }

    static int bfs() {
        ArrayDeque<node> q = new ArrayDeque<node>();
        q.add(start);
        for (int i = 0; i < 110; i++) {
            for (int j = 0; j < 110; j++) {
                Arrays.fill(path[i][j], -1);
            }
        }
        path[start.x][start.y][start.z] = 0;
        while (!q.isEmpty()) {
            node t = q.poll();
            for (int i = 0; i < 6; i++) {
                int x = t.x + dx[i], y = t.y + dy[i], z = t.z + dz[i];
                if (x < 0 || x >= l || y < 0 || y >= r || z < 0 || z >= c) continue;
                if (path[x][y][z] != -1 || g[x][y][z] == '#') continue;
                path[x][y][z] = path[t.x][t.y][t.z] + 1;
                if (end.x == x && end.y == y && end.z == z) return path[x][y][z];
                q.add(new node(x, y, z));
            }
        }
        return -1;
    }

    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int l, r, c;//层数,行数,列数
    static char[][][] g = new char[110][110][110];
    static node start, end;
    static int[][][] path = new int[110][110][110];

    static class node {
        int x, y, z;

        public node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
