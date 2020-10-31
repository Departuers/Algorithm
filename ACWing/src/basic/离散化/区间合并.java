package basic.离散化;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * 合并区间
 * 题目描述：
 * 给定 n 个区间 [li,ri]，要求合并所有有交集的区间。
 * 注意如果在端点处相交，也算有交集。
 * 输出合并完成后的区间个数。
 * 例如：[1,3]和[2,6]可以合并为一个区间[1,6]。
 * 输入格式
 * 第一行包含整数n。
 * 接下来n行，每行包含两个整数 l 和 r。
 * 输出格式
 * 共一行，包含一个整数，表示合并区间完成后的区间个数。
 * 数据范围
 * 1≤n≤100000,
 * −10^9≤li≤ri≤10^9
 * 输入样例：
 * 5
 * 1 2
 * 2 4
 * 5 6
 * 7 8
 * 7 9
 * 输出样例：
 * 3
 * 分析：
 * 本题考察贪心，将待合并的区间按左端点从小到大排序后，
 * 维持两个变量l,r，每次遍历到下一个区间，其左端点肯定是大于l的，
 * 若其左端点还大于r，则说明l-r的区间不会再与后面的区间进行合并了，
 * 因为后续区间的左端点都会大于l；反之，若l小于右端点，则合并两个区间，并更新r。
 * <p>
 * 按照左端点排序
 * 假设当前已经扫描到第i个区间
 * 第i+1个区间的左端点与第i个区间的关系有几种
 * 第一种,第i+1个区间的左端点在第i个区间的内部,这时候就可以合并,不做处理
 * 第二种,有交集但不在内部,这时候可以合并 ,更新新的尾端点
 * 第三种没有,交集,更新
 */
public class 区间合并 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            seg.add(new node(l, r));
        }
        Collections.sort(seg);
        int ans = 1;
        int st = seg.get(0).x, ed = seg.get(0).y;
        for (int i = 1; i < n; i++) {
            if (seg.get(i).x > ed) {
                st = seg.get(i).x;
                ed = seg.get(i).y;
                ans++;
            } else ed = Math.max(ed, seg.get(i).y);
        }
        System.out.println(ans);
    }

    static int N = 100110, n;
    static ArrayList<node> seg = new ArrayList<>();

    static class node implements Comparable<node> {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node o) {
            return x - o.x;
        }

    }
}
