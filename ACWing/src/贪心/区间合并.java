package 贪心;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//https://blog.csdn.net/qq_30277239/article/details/100880649?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522158685398819724845039319%2522%252C%2522scm%2522%253A%252220140713.130056874..%2522%257D&request_id=158685398819724845039319&biz_id=0&utm_source=distribute.pc_search_result.none-task-blog-blog_SOOPENSEARCH-1

/**
 * 给定 n 个区间 [li,ri]，要求合并所有有交集的区间。
 * 注意如果在端点处相交，也算有交集。
 * 输出合并完成后的区间个数。
 * 例如：[1,3]和[2,6]可以合并为一个区间[1,6]。
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
 * 排序贪心,
 * 本题考察贪心，将待合并的区间按左端点从小到大排序后，
 * 维持两个变量l,r，每次遍历到下一个区间，其左端点肯定是大于l的，
 * 若其左端点还大于r，则说明l-r的区间不会再与后面的区间进行合并了
 * 因为后续区间的左端点都会大于l；反之，
 * 若l小于右端点，则合并两个区间，并更新r。
 */
public class 区间合并 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            segs.add(new node(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(segs);
        megers();
    }

    static void meger() {
        int st = (int) -2e9, ed = (int) -2e9;
        int res = 0;
        for (node seg : segs) {
            if (ed < seg.x) {
                if (st != -2e9) res++;
                st = seg.x;
                ed = seg.y;
            } else ed = Math.max(ed, seg.y);
        }
        if (st != -2e9) res++;//最后一个没有更新
        System.out.println(res);
    }

    static void megers() {
        int st = segs.get(0).x;
        int ed = segs.get(0).y;
        int res = 1;
        for (int i = 1; i < segs.size(); i++) {
            if (ed < segs.get(i).x) {
                st = segs.get(i).x;
                ed = segs.get(i).y;
                res++;
            } else {
                ed = Math.max(ed, segs.get(i).y);
            }
        }
        System.out.println(res);
    }

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

    static int n;
    static ArrayList<node> segs = new ArrayList<node>();
}
