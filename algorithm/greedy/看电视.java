package greedy;

import java.util.*;

/**
 * 问题:看电视
 * 题目描述
 * 暑假到了，小明终于可以开心的看电视了。但是小明喜欢的节目太多了，他希望尽量多的看到完整的节目。
 * 现在他把他喜欢的电视节目的转播时间表给你，你能帮他合理安排吗？
 * 输入
 * 输入包含多组测试数据。每组输入的第一行是一个整数n（n<=100），表示小明喜欢的节目的总数。
 * 接下来n行，每行输入两个整数si和ei（1<=i<=n），表示第i个节目的开始和结束时间，为了简化问题，每个时间都用一个正整数表示。
 * 当n=0时，输入结束。
 * 输出
 * 对于每组输入，输出能完整看到的电视节目的个数。
 * 样例输入
 * 12
 * 1 3
 * 3 4
 * 0 7
 * 3 8
 * 15 19
 * 15 20
 * 10 15
 * 8 18
 * 6 12
 * 5 10
 * 4 14
 * 2 9
 * 0
 * 样例输出
 * 5
 * 贪心之活动安排问题:
 * 求出能看到最多的完整的节目,按结束时间升序排列,
 * 当结束时间相同时再按开始时间升序排列,最后从前往后计数即可
 * 如果后面一个的开始时间,比前面那个的结束早或者相等,就代表这两个不冲突
 */
public class 看电视 {
    static class Node {
        public int start;
        public int end;

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

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
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
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        ArrayList<Node> data = new ArrayList<Node>();
        for (int i = 0; i < count; i++) {
            data.add(new Node(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(data, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getEnd() - o2.getEnd();
            }
        });
        Set<Node> set = new HashSet<Node>();
        System.out.println(data);
        int k = 0;
        int sum = 1;
        for (int i = 1; i < count; i++) {
            if (data.get(i).getStart() >= data.get(k).end) {//如果后面一个的开始时间,比前面那个的结束早,就代表这两个不冲突
                set.add(data.get(i));
                set.add(data.get(k));
                sum++;
                k = i;
            }
        }
        System.out.println(sum);
        for (Node node : set) {
            System.out.println(node);
        }
    }
}
