package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_37373250/article/details/86750656
 * 任务(活动)安排,与看电视一样的问题,这个是求数量思路,
 * 区间调度问题
 * 有n项工作，每项工作分别在si时间开始，在ti时间结束。对于每项工作，你都可以选择参加与否。
 * 如果选择了参加，那么自始至终都必须全程参加。此外，参加工作的时间段不能重叠(即使是开始的瞬间
 * 和结束的瞬间的重叠也是不允许的)。
 * 你的目标是参与尽可能多的工作，那么最多能参与多少项工作呢？
 * 限制条件：
 * 1≤N≤100000
 * 1≤si≤ti≤10的9次方
 * 输入:
 * 第一行:n有多少个
 * 第二行:n个整数空格隔开代表,n个工作开始时间
 * 第三行:n个整数空格隔开代表,n个工作结束时间
 * <p>
 * 5
 * 1 2 4 6 8
 * 3 5 7 9 10
 * 输出:
 * 3
 * 思路:每个任务,按照结束任务的时间升序排列,当结束任务时间相同时,按照开始时间升序排序
 */
public class 区域调度 {
    static class Node {
        public int start;
        public int end = -1;

        public Node(int start) {
            this.start = start;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public int hashCode() {
            return start * 100 + end * 10;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return start == node.start &&
                    end == node.end;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

    }

    public static void main(String[] args) {
        F();
    }

    public static void F() {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        ArrayList<Node> data = new ArrayList<Node>();
        for (int i = 0; i < count; i++) {
            data.add(new Node(sc.nextInt()));
        }
        for (int i = 0; i < count; i++) {
            data.get(i).end = sc.nextInt();
        }
        Collections.sort(data, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getEnd() == o2.getEnd())
                    return o1.getStart() - o2.getStart();
                return o1.getEnd() - o2.getEnd();
            }
        });

        int cnt = 1;
        int y = data.get(0).end;//第一个任务结束时间
        for (int i = 1; i < count; i++) {
            if (data.get(i).start >= y) {
                System.out.println(data.get(i) + " ");
                cnt++;
                y = data.get(i).end;
            }
        }
        System.out.println(cnt);
    }

    public static void dpp() {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        ArrayList<Node> data = new ArrayList<Node>();
        for (int i = 0; i < count; i++) {
            data.add(new Node(sc.nextInt()));
        }
        for (int i = 0; i < count; i++) {
            data.get(i).end = sc.nextInt();
        }
    }

}
