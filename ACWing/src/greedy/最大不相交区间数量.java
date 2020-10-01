package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103996364
 * 给定N个闭区间[ai,bi]，请你在数轴上选择若干区间，使得选中的区间之间互不相交（包括端点）。
 * 输出可选取区间的最大数量。
 * 输入格式
 * 第一行包含整数N，表示区间数。
 * 接下来N行，每行包含两个整数ai,bi，表示一个区间的两个端点。
 * 输出格式
 * 输出一个整数，表示可选取区间的最大数量。
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
 * 分析：
 * 在AcWing 905 区间选点中，我们证明了将区间按照右端点排序，然后选择不相交的区间的右端点，
 * 可以使得所有区间都至少包含了一个选择出来的点。即被选择出来多少个点，原区间集合就有多少个不相交的区间，
 * 设选择出来ans个点，可以证明区间集合中有ans个不相交的区间。如果原区间集合中存在res个不相交的区间，
 * 且res > ans，则至少应该有res个点才能使得各个区间都包含了被选择的点，
 * 但是已经选出了ans个点足以使得所有区间内都有被选择的点，说明区间集合内不存在res个不相交的区间，
 * 即ans是不相交区间的最大数量。因此，本题代码与上一题代码完全一致，
 * 按区间右端点自小到大排序然后取右端点的贪心策略既可以解决区间选点问题，也可以解决最大不相交区间问题。
 * <p>
 * 不冲突的课程选择最多
 */
public class 最大不相交区间数量 {
    static class node implements Comparable<node> {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node node) {
            return y - node.y;
        }
    }

    static ArrayList<node> no = new ArrayList<node>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            no.add(new node(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(no);

    }

    static int n, m;

}
