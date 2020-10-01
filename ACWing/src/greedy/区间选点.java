package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103995999
 * 给定N个闭区间[ai,bi]，请你在数轴上选择尽量少的点，使得每个区间内至少包含一个选出的点。
 * 输出选择的点的最小数量。位于区间端点上的点也算作区间内。
 * 输入格式
 * 第一行包含整数N，表示区间数。
 * 接下来N行，每行包含两个整数ai,bi，表示一个区间的两个端点。
 * 输出格式
 * 输出一个整数，表示所需的点的最小数量。
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
 * <p>
 * 本题是区间贪心的经典题，要求选出尽可能少的点，使得所有区间都有包含的点。
 * 相当于给学生发书，每个人有空的时间是一段区间，要求尽可能少的选择若干个时间点，
 * 将书发放给所有的学生。这类贪心问题一般按照所有区间的右端点从小到大排序，
 * 然后选择按照右端点自小到大的遍历区间，如果某个区间内没有选择点，则选择区间的右端点。
 * 比如按照区间右端点排序得到[1,3],[2,5],[4,6]，第一个点选择3，
 * 然后判断下第二个区间是否包含3，由于第二个区间右端点是大于3的，
 * 只需要左端点小于3便与第一个区间有了交集，即[l1,r1],[l2,r2]，有r1 < r2；
 * 当l2 < r1时两个区间有交集，且第一个区间右端点r1必然在两个区间交集内。2 < 3，
 * 所以第二个区间包含3，故遍历到下一个区间[4,6]，4 > 3，所以不包含3，选择第二个点6。
 * 至于为什么对右端点自小到大排序后每次要选择右端点，
 * 因为如果两个区间有交集，右端点较小的那个区间的右端点必然处于两个区间的交集内。
 * 下面证明这样选择的点是最少的。我们知道，
 * 每次需要选择一个新的点的时候都有当前遍历到区间的左端点大于上一个被选择区间的右端点，
 * 即有多少个点被选择，原来的区间集合就存在多少个互不相交的区间，因此，这样选择的点数是最少的。
 * 贪心排序推测.看对不对..
 * 证明:按照区间右端点从小到大排序,然后依次枚举每个区间
 * 在第一个选的区间就选区间的右端点,因为右端点,将来有可能覆盖其他区间
 * 对于第一个区间:选择第一个区间右端点
 * 对于之后的区间,如果该端点的左端点已经被上一个区间的右端点覆盖,那么pass
 * 否则 选择当前区间的右端点,并且res++,加一个区间
 * 单独查看,没有被pass的区间,上一个区间的右端点无法覆盖新区间,只能res++
 * 可以找到cnt 相互没有交集的区间,如果要覆盖所有的区间,则至少需要cnt个点
 *
 * 从局部最优解,一直可以走到全局最优解
 * 单峰会走到最大点
 * 多峰不一定会走到最大点,只会走到局部最优解
 */
public class 区间选点 {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            e.add(new node(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(e);
        int u = e.get(0).y;
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (e.get(i).x <= u) continue;//新区间被上一个区间的右端点覆盖了,就跳过
            u = e.get(i).y;
            res++;
        }
        System.out.println(res);
    }

    static ArrayList<node> e = new ArrayList<node>();
    static int n;
}
