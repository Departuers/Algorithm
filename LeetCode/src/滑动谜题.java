import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 773 滑动谜题
 * 华容道
 */
public class 滑动谜题 {
    public int reverse(int x) {
        if (x < 0) {
            return -get(-x);
        } else return get(x);
    }

    public int get(int a) {
        StringBuilder res = new StringBuilder().append(a).reverse();
        int i = 0;
        try {
            i = Integer.parseInt(res.toString());
        } catch (Exception e) {
            return 0;
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        System.out.println();
    }

    public int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<String>();
        HashMap<String, Integer> visited = new HashMap<String, Integer>();
        String init = boardToString(board);//初始值
        if (init.equals("123450")) return 0;

        queue.add(init);
        visited.put(init, 0);

        while (!queue.isEmpty()) {
            String cur = queue.remove();
            ArrayList<String> nexts = getNexts(cur);
            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if (next.equals("123450"))//如果找到结果
                        return visited.get(next);
                }
            }

        }
        return -1;
    }

    private int[][] stringToBoard(String s) {
        int[][] res = new int[2][3];
        for (int i = 0; i < 6; i++) {
            res[i / 3][i % 3] = s.charAt(i) - '0';//把一维索引转换成二维索引
        }
        return res;
    }

    private ArrayList<String> getNexts(String s) {
        int[][] cur = stringToBoard(s);
        int zx = 0, zy = 0;
        for (int i = 0; i < 6; i++) {
            if (cur[i / 3][i % 3] == 0) {
                zx = i / 3;
                zy = i % 3;
                break;
            }
        }
        ArrayList<String> res = new ArrayList<String>();

        for (int d = 0; d < 4; d++) {
            int nextx = zx + dirs[d][0], nexty = zy + dirs[d][1];
            if (inArea(nextx, nexty)) {
                swap(cur, zx, zy, nextx, nexty);
                res.add(boardToString(cur));
                swap(cur, zx, zy, nextx, nexty);
            }
        }

        return res;
    }

    private void swap(int[][] cur, int zx, int zy, int nextx, int nexty) {
        int temp = cur[zx][zy];
        cur[zx][zy] = cur[nextx][nexty];
        cur[nextx][nexty] = temp;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < 2 && y >= 0 && y < 3;
    }

    public String boardToString(int[][] board) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                res.append(board[i][j]);
            }
        }
        return res.toString();
    }


}
