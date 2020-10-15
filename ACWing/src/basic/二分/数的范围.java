package basic.二分;

import java.util.Scanner;

/**
 * https://www.acwing.com/file_system/file/content/whole/index/content/375949/
 * 给定一个按照升序排列的长度为n的整数数组，以及 q 个查询。
 * 对于每个查询，返回一个元素k的起始位置和终止位置（位置从0开始计数）。
 * 如果数组中不存在该元素，则返回“-1 -1”。
 * 输入格式
 * 第一行包含整数n和q，表示数组长度和询问个数。
 * 第二行包含n个整数（均在1~10000范围内），表示完整数组。
 * 接下来q行，每行包含一个整数k，表示一个询问元素。
 * 输出格式
 * 共q行，每行包含两个整数，表示所求元素的起始位置和终止位置。
 * 如果数组中不存在该元素，则返回“-1 -1”。
 * 数据范围
 * 1≤n≤100000
 * 1≤q≤10000
 * 1≤k≤10000
 * 输入样例：
 * 6 3
 * 1 2 2 3 3 4
 * 3
 * 4
 * 5
 * 输出样例：
 * 3 4
 * 5 5
 * -1 -1
 * 整数二分
 * 二分的本质并不是单调性
 * 二分的本质是边界
 * 有一个整数区间,在这个区间上有一个性质,这个性质左半区间满足,右边区间不满足
 * 而二分可以找出这个分界点
 * 先写check函数,
 * 如果l=mid 补上l+r+1>>1
 */
public class 数的范围 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            q[i] = sc.nextInt();
        }
        while (m-- != 0) {
            int x = sc.nextInt();
            int l = 0, r = n - 1;
            //二分性质>=x 左边都满足<x 右边都满足>=x  找出第一个>=x的数的索引
            while (l < r) {
                int mid = l + r >> 1;
                if (q[mid] >= x) r = mid;
                else l = mid + 1;
            }
            if (q[l] != x) {
                System.out.println("-1 -1");
            } else {
                int t = l;
                l = 0;
                r = n - 1;
                //二分性质<=x 左边部分都满足<=x 右边部分都满足>x  找出最后一个<=x的数的索引
                while (l < r) {
                    int mid = l + r + 1 >> 1;
                    if (q[mid] <= x) l = mid;
                    else r = mid - 1;
                }
                System.out.println(t + " " + l);
            }
        }

    }

    static int n, m;
    static int[] q = new int[(int) (1e5 + 10)];

}
