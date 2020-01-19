package Think;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * poj 1852
 * 有n只蚂蚁在木棍上爬行，每只蚂蚁的速度都是每秒1单位长度，
 * 现在给你所有蚂蚁初始的位置（蚂蚁运动方向未定），
 * 蚂蚁相遇会掉头反向运动，让你求出所有蚂蚁都掉下木棍的最短时间和最长时间。
 * 解题思路：
 * 因为蚂蚁都是一样的，所以当两只蚂蚁相遇时转向跟没转向没什么区别，
 * 因为每只蚂蚁都会继承另一只蚂蚁的方向，所以可以视为蚂蚁方向不变，
 * 所以最短时间就是所有蚂蚁距端点最近距离的最大值，
 * 最长时间就是所有蚂蚁距端点最远距离的最大值。
 */
public class 蚂蚁 {
    public static void main(String[] args) {
        int[] arr = {2, 6, 7};
        Ants(10, 3, arr);
    }

    /**
     * @param L   木棍长度
     * @param n   有n只蚂蚁
     * @param arr 每只蚂蚁距离木棍左端的距离
     */
    public static void Ants(int L, int n, int[] arr) {
        int minT = 0;
        int maxT = -1;
        for (int i = 0; i < n; i++) {
            maxT = max(max(arr[i], L - arr[i]), maxT);
            minT = max(min(arr[i], L - arr[i]), minT);
        }
        System.out.println(minT);
        System.out.println(maxT);

    }
}
