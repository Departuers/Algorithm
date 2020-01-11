package dp;

import java.util.*;

/**
 * 带权的区间调度
 * 存在单一资源R，有n个需求{1, 2, …, n}，每个需求指定一个开始时间bi与一个结束时间ei，在时间区间[bi, ei]内该需求想要占用资源R，资源R一旦被占用则无法被其他需求利用。每个需求i带有一个权值vi，代表该需求被满足之后能够带来的价值或者贡献。如果两个需求的时间区间不重叠，那么它们是相容的。带权值的区间调度问题即，对上面所描述的情境，求出一组相容子集S使得S中的区间权值之和最大。
 * <p>
 * 解决此类问题一般采用动态规划的思路，执行步骤分以下几个阶段：
 * <p>
 * 对所有的需求按照结束时间排序。
 * 求出所有需求前面所有的需求中和它相容的最后一个需求，记为pi。
 * 若只在前i个需求中求相容子集，则可以得到的最大权值之和为M[i]，
 * 可以推导出：M[i]=max(wi+M[pi],M[i-1])，即如果最大的相容子集中包含需求i，则其权值之和为需求i的权值加上之前所有和它相容的需求子集的最大相容子集的权值之和；若不包含需求i，则其权值之和为前i-1个需求而的最大相容子集的权值之和。而应在其中取最大值，作为M[i]。
 * 计算出来所有的M[i]之后，自顶而上，求出所有使权值之和变为最大的需求。
 */
public class 带权区域调度 {
    static class Node implements Comparator<Node> {
        public int start = -1;//起点
        public int end = -1;//终点
        public int weight = -1;//权重
        public int p = -1;//求出所有需求前面所有的需求中和它相容的最后一个需求，记为pi

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.end == o2.end)
                return o1.start - o2.start;
            return o1.end - o2.end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return start == node.start &&
                    end == node.end &&
                    weight == node.weight;
        }

        @Override
        public int hashCode() {
            return start * 100 + end * 10 + weight;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("start=").append(start);
            sb.append(", end=").append(end);
            sb.append(", num=").append(weight);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {

    }

    public static void ComputeP(ArrayList<Node> nodes) {
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.end == o2.end)
                    return o1.start - o2.start;
                return o1.end - o2.end;
            }
        });
        for (int i = 0; i < nodes.size(); i++) {
            for (Node node : nodes) {
                if (node.start >= nodes.get(i).end)
                    node.p = i;
            }
        }

        int[] temp = new int[nodes.size()];
        Arrays.fill(temp, -1);

    }
}