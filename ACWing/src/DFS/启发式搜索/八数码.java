package DFS.启发式搜索;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104754393
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
 * 把“X”与上下左右方向数字交换的行动记录为“u”、“d”、“l”、“r”。
 * 现在，给你一个初始网格，请你通过最少的移动次数，得到正确排列。
 * 输入格式
 * 输入占一行，将3×3的初始网格描绘出来。
 * 例如，如果初始网格如下所示：
 * 1 2 3
 * x 4 6
 * 7 5 8
 * 则输入为：1 2 3 x 4 6 7 5 8
 * 输出格式
 * 输出占一行，包含一个字符串，表示得到正确排列的完整行动记录。如果答案不唯一，输出任意一种合法方案即可。
 * 如果不存在解决方案，则输出”unsolvable”。
 * 输入样例：
 * 2  3  4  1  5  x  7  6  8
 * 输出样例
 * ullddrurdllurdruldr
 */
public class 八数码 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start, seq = "";
        StringBuilder s = new StringBuilder();
        s.append(sc.nextLine());
        start = s.toString().replace(" ", "");
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'x') seq += start.charAt(i);
        }
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = i; j < 8; j++) {
                if (seq.charAt(i) > seq.charAt(j)) {
                    cnt++;
                }
            }
        }
        if ((cnt & 1) == 1) System.out.println("无解");
        else {
//            System.out.println(bfs());
        }
    }
//
//    static int bfs(String start) {
//        HashMap<String, Integer> dist = new HashMap<String, Integer>();
//        HashMap<String, p> prev = new HashMap<String, p>();
//        PriorityQueue<node> heap = new PriorityQueue<node>();
//        dist.put(start, 0);
//        heap.add(new node(f(state), state));
//        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//        while (!heap.isEmpty()) {
//            node t = heap.poll();
//            String state = t.y;
//            if (state == end) break;
//            int x, y;
//            for (int i = 0; i < 9; i++) {
//                if (state.charAt(i) == 'x') {
//                    x = i / 3;
//                    y = i % 3;
//                    break;
//                }
//            }
//            String source = state;
//        }
//    }
//
//    static int f(String state) {
//
//    }

    static class p {
        int x, y;

        public p(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class node implements Comparable<node> {
        int x;
        String y;

        public node(int x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return x - node.x;
        }
    }

}
