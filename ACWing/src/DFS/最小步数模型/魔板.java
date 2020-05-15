package DFS.最小步数模型;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 思路不难,代码非常复杂...
 * https://blog.csdn.net/qq_30277239/article/details/104701375
 * 八数码同类题
 * 有向图最短步数模型,要把状态存下来
 * 用hash法存状态,或者康托展开
 * 最小字典序,在拓展每个状态的时候,先A,B,C
 * 显然bfs一定是最短路,假设最后一步,走A和B都能达到最短路,
 * 则走到A就结束搜索,满足字典序,
 * 则显然同理可证,多条最短路中第一个字典序最小
 * 因为先拓展的状态,先放进队列.
 * 2 6 8 4 5 7 3 1
 * 7
 * B C A B C C B
 */
public class 魔板 {
    static class node {
        char x;
        String s;

        public node(char x, String s) {
            this.x = x;
            this.s = s;
        }
    }

    public static void main(String[] args) {
        StringBuilder start = new StringBuilder(), end = new StringBuilder();
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            end.append(s.nextInt());
        }
        for (int i = 0; i < 8; i++) {
            start.append(i + 1);
        }
        String st = start.toString();
        String ed = end.toString();
        bfs(st, ed);
        if (st.equals(ed)) System.out.println(0);
        else {
            System.out.println(dist.get(ed));
            StringBuilder res = new StringBuilder();
            while (!ed.equals(st)) {
                res.append(pre.get(ed).x);
                ed = pre.get(ed).s;
            }
            res.reverse();
            for (int i = 0; i < res.length(); i++) {
                System.out.print(res.charAt(i) + " ");
            }
        }
    }

    private static void bfs(String start, String end) {
        if (start.equals(end)) return;
        q.add(start);
        dist.put(start, 0);
        while (!q.isEmpty()) {
            String t = q.poll();
            String[] tem = new String[3];
            tem[0] = move0(t);
            tem[1] = move1(t);
            tem[2] = move2(t);

            for (int i = 0; i < 3; i++) {
                String m = tem[i];
                if (!dist.containsKey(m)) {
                    dist.put(m, dist.get(t) + 1);
                    pre.put(m, new node((char) (i + 'A'), t));
                    if (m.equals(end)) return;
                    q.add(m);
                }
            }
        }
    }

    static int[][] g = new int[2][4];

    static void set(String s) {
        for (int i = 0; i < 4; i++) {
            g[0][i] = s.charAt(i) - '0';
        }
        for (int i = 3, j = 4; i >= 0; i--, j++) {
            g[1][i] = s.charAt(j) - '0';
        }
    }

    static String get() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            res.append(g[0][i]);
        }
        for (int i = 3; i >= 0; i--) {
            res.append(g[1][i]);
        }
        return res.toString();
    }

    private static String move2(String t) {
        set(t);
        int a = g[0][1];
        g[0][1] = g[1][1];
        g[1][1] = g[1][2];
        g[1][2] = g[0][2];
        g[0][2] = a;
        return get();
    }

    private static String move1(String t) {
        set(t);
        int v0 = g[0][3], v1 = g[1][3];
        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < 2; j++) {
                g[j][i] = g[j][i - 1];
            }
        }
        g[0][0] = v0;
        g[1][0] = v1;
        return get();
    }

    private static String move0(String t) {
        set(t);
        for (int i = 0; i < 4; i++) {
            int e = g[0][i];
            g[0][i] = g[1][i];
            g[1][i] = e;
        }
        return get();
    }

    static Map<String, Integer> dist = new HashMap<String, Integer>();
    static Map<String, node> pre = new HashMap<String, node>();
    static ArrayDeque<String> q = new ArrayDeque<String>();
}
