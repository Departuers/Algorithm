package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * https://blog.csdn.net/u013480600/article/details/37922977
 * https://blog.csdn.net/qq_43464645/article/details/95963425
 * 有n个区间,最少有多少个点,命中所有区间
 * 题意:有n个如下形式的条件:
 * ai bi ci,表示在区间[ai, bi]内至少要选择ci个整数点.
 * 问你满足n个条件的情况下,最少需要选多少个点?
 * 分析:
 * 令s[x]表示从区间[0,x]中选择的整数点个数.那么对于条件[ai, bi]选数>=ci个,就是 s[bi]-s[ai-1]>=ci. 即s[ai-1]<=s[bi]+ (-ci)
 * 假设有下面两个条件:
 * 1 2 3  5 6 3, 那么我们自然得出了s[2]-s[0]>=3 且s[6]-s[4]>=3.
 * 如果s[2]=3,s[0]=0,s[6]=3,s[4]=0 是满足上面不等式的.但是其实是不合题意的.因为s[4]必然要>=s[2]和s[0]的.所以我们需要把s的相对值也联系起来.
 * 这样就需要添加下面的边. 假设我们令输入读取的区间最大值为max_v. 那么对于所有 0<i<=max_v的值来说,有
 * 0<=s[i]-s[i-1]<=1 .转换一下即是: s[i]<=1+s[i-1] && s[i-1]<=0+s[i].
 * 根据上面的分析,我们要建的有向图是一个具有max_v+1个点的图.其中的边有:
 * n条s[bi]-s[ai-1]>=ci条件构成的从bi到 (ai-1)的长-ci的边.
 * 还有所有 0<i<=max_v的值构成的 i到i-1的长0的边和 i-1到i的长1的边.所以这样我们就形成了一个具有点[0,max_v]的有向图.在原图处理时,为了避免ai-1==-1,我们令所有ai与bi都自增了10.所以我们形成了一个具有点[9,max_v]的有向图(其实就是差分约束系统).我们的目标是令S[max_v]-S[9]的值最小.(切记这里不是仅使S[max_v]的值最小,因为我们这里只有从9 ,10,11,…max_v 的值构成了一个差分约束系统.max_v的值和0号点的值是不在一个系统的. 0号点是超级源点,它的值与系统中其他点的值是无关的.)
 * 然后我们上面已经知道了希望让系统中S[max_v]和S[9]的值差距尽量小.(构成差分约束系统时,1.如果在所有点外添加一个超级源0号点,并使得超级源到所有其他点的距离为0,那么最终求出的0号点到其他所有原始点的最短距离就是本系统的一个可行解,且可行解之间的差距最小.      2.如果初始时不添加超级源,只是将原始点的初始距离设为INF,且令其中一个原始点的初始距离为0,然后求该点到其他所有点的最短距离,那么最短距离的集合就是一个可行解,且该可行解两两之间的差距最大.注意方案2只能在该问题一定存在解的时候即肯定不存在负权环的时候用.否则从1号点到其他点没有路,但是其他点的强连通分量中有负权环,这样根本探测不到错误) 所以我们需要采取方案1.
 * 转自大佬:
 * 贪心策略,尽量先选区间右边的点
 * 还有树状数组
 * 拆分约束
 */
public class 区间选点 {
    static class Node {
        public int start;
        public int end = -1;
        public int num;

        public Node(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return start == node.start &&
                    end == node.end &&
                    num == node.num;
        }

        @Override
        public int hashCode() {
            return start * 100 + end * 10 + num;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("start=").append(start);
            sb.append(", end=").append(end);
            sb.append(", num=").append(num);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Node(3, 7, 3));
        list.add(new Node(8, 10, 3));
        list.add(new Node(6, 8, 1));
        list.add(new Node(1, 3, 1));
        list.add(new Node(10, 11, 1));

        Q(list.size(), list);
    }

    /**
     * 贪心策略实现
     *
     * @param n    区间数量
     * @param list 区间对象
     */
    public static int Q(int n, ArrayList<Node> list) {
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.end == o2.end)
                    return o1.start - o2.start;
                else
                    return o1.end - o2.end;
            }
        });

        int max = list.get(list.size() - 1).end;//右端最大值,区间最右边到哪
        int[] axis = new int[max + 1];//标记数轴上的点是否已经被选中
        for (int i = 0; i < n; i++) {
            int start = list.get(i).start;
            int end = list.get(i).end;
            int cnt = sum(axis, start, end);//查询数轴上(数组start到end)这个范围已经存在的点,
            list.get(i).num -= cnt;//当前该区间需要多少个点(去掉已经,已经包含数量的点)
            while (list.get(i).num > 0) {
                if (axis[end] == 0) {//如果该区间右边界如果未被标记
                    axis[end] = 1;//标记区间右边界
                    list.get(i).num--;//维护该区间需要的点
                    end--;//维护end,右边界左移一位
                } else {
                    end--;//右边界左移一位
                }
            }
        }
        System.out.println(sum(axis, 0, max));
        return sum(axis, 0, max);//整个数轴上有几个点
    }

    /**
     * 统计数轴axis上s-t区间已经有多少个点被选中
     * 贪心策略辅助统计方法
     *
     * @param axis 数轴
     * @param s    区间左边界
     * @param t    区间右边界
     * @return 有多少个点
     */
    public static int sum(int[] axis, int s, int t) {
        int sum = 0;
        for (int i = s; i < t; i++) {
            sum += axis[i];
        }
        return sum;
    }

    public static void updateSums(int t, int[] sums) {
        for (int i = t; i < sums.length; i++) {
            sums[i]++;
        }
    }
}