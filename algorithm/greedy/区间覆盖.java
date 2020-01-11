package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 有n头牛，它们都有一个工作时间的区间s至e，
 * 给定一个总的工作时间t，
 * 问最少需要多少头牛才能覆盖从1到t的工作时间
 * <p>
 * 题目说人话就是:比如1点到10点需要有牛耕地,会给出,几点到几点需要工作
 * 有的牛只在3点到4点工作,会给出每头牛在什么时间会工作
 * 问最少需要多少头牛,才能在1-10点都有牛工作
 */
public class 区间覆盖 {
    static class Node {
        public int start = -1;
        public int end = -1;

        public Node(int start) {
            this.start = start;
        }

        public int getStart() {
            return start;
        }

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
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
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Node(1, 7));
        list.add(new Node(3, 6));
        list.add(new Node(6, 10));
        F(list, list.size(), 0, 10);
    }

    /**
     * @param list  区间对象
     * @param N     区间数量
     * @param begin 需要覆盖的区间开头,
     * @param last  需要覆盖的区间结尾
     */
    public static void F(ArrayList<Node> list, int N, int begin, int last) {
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.start == o2.start)
                    return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });//按照区间左边界升序排列

        int start = begin;//要覆盖的目标点
        int end = 1;
        int ans = 1;
        for (int i = 0; i < N; i++) {
            int s = list.get(i).start;
            int e = list.get(i).end;
            if (i == 0 && s > begin) break;//由于待选区间已经按开始时间排了序,
            // 如果第一个找不到需要被覆盖的区间左边界,则无解
            if (s <= start) {
                end = Math.max(e, end);
            } else {//开始下一个区间,超过end
                ans++;//上一个目标覆盖达成
                start = end + 1;
                if (s <= start) {
                    end = Math.max(end, e);
                } else {
                    break;//无法完全覆盖给定的区间,无解
                }
            }
            if (end >= last) {
                break;//如果找出来一个区间,可以覆盖区间的全部,那么就够了,不需要找了
            }

        }
        if (end < last) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
