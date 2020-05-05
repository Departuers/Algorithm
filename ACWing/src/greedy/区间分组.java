package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定N个闭区间[ai,bi]，请你将这些区间分成若干组，
 * 使得每组内部的区间两两之间（包括端点）没有交集，并使得组数尽可能小。
 * 输出最小组数。
 * 输入格式
 * 第一行包含整数N，表示区间数。接下来N行，
 * 每行包含两个整数ai,bi，表示一个区间的两个端点。
 * 输出格式
 * 输出一个整数，表示最小组数。
 * 数据范围
 * 1≤N≤10^5,
 * −10^9≤ai≤bi≤10^9
 * 输入样例：
 * 3
 * -1 1
 * 2 4
 * 3 5
 * 输出样例：
 * 2
 */
public class 区间分组 {
    static class node implements Comparable<node> {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return x - node.x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a.add(new node(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(a);
        q.add(a.get(0).y);
        for (int i = 1; i < n; i++) {
            if (q.size() != 0 && q.peek() < a.get(i).x) {
                q.poll();
            }
            q.add(a.get(i).y);
        }
        System.out.println(q.size());
    }

    static PriorityQueue<Integer> q = new PriorityQueue<Integer>();
    static ArrayList<node> a = new ArrayList<node>();
    static int n, ans;
}
