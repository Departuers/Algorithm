package dp;

import java.util.*;

/**
 * 带权的区间调度(挺重要,细节需要注意)
 * https://blog.csdn.net/hongchh/article/details/52914507
 * 存在单一资源R，有n个需求{1, 2, …, n}，每个需求指定一个开始时间bi与一个结束时间ei，在时间区间[bi, ei]内该需求想要占用资源R，资源R一旦被占用则无法被其他需求利用。每个需求i带有一个权值vi，代表该需求被满足之后能够带来的价值或者贡献。如果两个需求的时间区间不重叠，那么它们是相容的。带权值的区间调度问题即，对上面所描述的情境，求出一组相容子集S使得S中的区间权值之和最大。
 * <p>
 * 解决此类问题一般采用动态规划的思路，执行步骤分以下几个阶段：
 * <p>
 * 对所有的需求按照结束时间升序排序。
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
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0, 4, 2));
        nodes.add(new Node(1, 6, 4));
        nodes.add(new Node(5, 7, 4));
        nodes.add(new Node(2, 9, 7));
        nodes.add(new Node(8, 10, 2));
        nodes.add(new Node(8, 11, 1));
        ComputeP(nodes);
    }

    public static void ComputeP(ArrayList<Node> nodes) {
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.end == o2.end)
                    return o1.start - o2.start;
                return o1.end - o2.end;
            }
        });//对所有的需求按照结束时间升序排序。

        int[] p = new int[nodes.size()];
        Arrays.fill(p, -1);
        for (int i = 1; i < nodes.size(); i++) {//先选中一个区间
            for (int j = i - 1; j >= 0; j--) {//从后往前找前面所有的需求中和它相容的最后一个需求
                if (nodes.get(j).end <= nodes.get(i).start) {
                    p[i] = j;
                    break;
                }
            }
        }//求出所有需求前面所有的需求中和它相容的最后一个需求，记为pi。
        System.out.println(Arrays.toString(p));

//        for (int i = 0; i < nodes.size(); i++) {
//            for (int j = 0; j < nodes.size(); j++) {
//                if (nodes.get(j).start >= nodes.get(i).end) {
//                    p[j] = i;
//                    nodes.get(j).p = i;
//                }
//            }
//        }
        int[] dp = new int[nodes.size()];
        dp[0] = nodes.get(0).weight;

        for (int i = 1; i < nodes.size(); i++) {
            if (p[i] == -1) {
                dp[i] = nodes.get(i).weight;
                continue;
            }

            if (dp[p[i]] + nodes.get(i).weight > dp[i - 1]) {
                dp[i] = dp[p[i]] + nodes.get(i).weight;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        System.out.println(Arrays.toString(dp));
    }
}