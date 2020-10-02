package basic.search;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * 在一个3×3的网格中，1~8这8个数字和一个“X”恰好不重不漏地分布在这3×3的网格中。
 * 例如：
 * 1 2 3
 * X 4 6
 * 7 5 8
 * 在游戏过程中，可以把“X”与其上、下、左、右四个方向之一的数字交换（如果存在）。
 * 我们的目的是通过交换，使得网格变为如下排列（称为正确排列）：
 * 1 2 3
 * 4 5 6
 * 7 8 X
 * 例如，示例中图形就可以通过让“X”先后与右、下、右三个方向的数字交换成功得到正确排列。
 * 交换过程如下：
 * 1 2 3   1 2 3   1 2 3   1 2 3
 * X 4 6   4 X 6   4 5 6   4 5 6
 * 7 5 8   7 5 8   7 X 8   7 8 X
 * 现在，给你一个初始网格，请你求出得到正确排列至少需要进行多少次交换。
 * 输入格式
 * 输入占一行，将3×3的初始网格描绘出来。
 * 例如，如果初始网格如下所示：
 * 1 2 3
 * x 4 6
 * 7 5 8
 * 则输入为：1 2 3 x 4 6 7 5 8
 * 输出格式
 * 输出占一行，包含一个整数，表示最少交换次数。
 * 如果不存在解决方案，则输出”-1”。
 * 输入样例：
 * 2  3  4  1  5  x  7  6  8
 * 输出样例
 * 19
 */
public class 八数码 {
    public static void main(String[] args) {
        System.out.println(bfss("412503"));
    }

    static HashMap<String, Integer> st = new HashMap<String, Integer>();
    static int n, m;
    static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    static ArrayDeque<String> q = new ArrayDeque<String>();

    static int bfs(String s) {
        st.put(s, 0);
        q.add(s);
        String end = "123456780";
        while (!q.isEmpty()) {
            String v = q.poll();
            int dis = st.get(v);//当前位置到起点的距离
            if (v.equals(end)) return dis;
            int x = v.indexOf("0");
            int a = x / 3, b = x % 3;//x在网格中的坐标
            for (int i = 0; i < 4; i++) {
                int nx = a + dir[i][0], ny = b + dir[i][1];
                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    StringBuilder tem = new StringBuilder(v);
                    char tmp = v.charAt(x);
                    tem.setCharAt(x, v.charAt(nx * 3 + ny));
                    tem.setCharAt(nx * 3 + ny, tmp);
                    if (!st.containsKey(tem.toString())) {
                        q.add(tem.toString());
                        st.put(tem.toString(), dis + 1);
                    }
                }
            }
        }
        return -1;
    }

    public int slidingPuzzle(int[][] board) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                s.append(board[i][j]);
            }
        }
        return bfss(s.toString());
    }
    static int bfss(String s) {
        st.clear();
        q.clear();
        st.put(s, 0);
        q.add(s);
        String end = "123450";
        while (!q.isEmpty()) {
            String v = q.poll();
            int dis = st.get(v);//当前位置到起点的距离
            if (v.equals(end)) return dis;
            int x = v.indexOf("0");
            int a = x / 3, b = x % 3;//x在网格中的坐标
            for (int i = 0; i < 4; i++) {
                int nx = a + dir[i][0], ny = b + dir[i][1];
                if (nx >= 0 && nx < 2 && ny >= 0 && ny < 3) {
                    StringBuilder tem = new StringBuilder(v);
                    char tmp = v.charAt(x);
                    tem.setCharAt(x, v.charAt(nx * 3 + ny));
                    tem.setCharAt(nx * 3 + ny, tmp);
                    if (!st.containsKey(tem.toString())) {
                        q.add(tem.toString());
                        st.put(tem.toString(), dis + 1);
                    }
                }
            }
        }
        return -1;
    }
}
