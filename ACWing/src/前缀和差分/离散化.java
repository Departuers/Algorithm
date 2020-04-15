package 前缀和差分;

import java.util.TreeSet;

/**
 * a[i]  1  3  100 200  50000
 * 0  1   2  3      4
 * 算出x离散化后的值,二分
 * a[i]中应该去重有序
 */
public class 离散化 {
    public static void main(String[] args) {
        for (int i : arr) {
            t.add(i);
        }
        int c = 0;
        for (Integer w : t) {
            arr[c++] = w;
        }
        System.out.println(find(8));
    }

    static TreeSet<Integer> t = new TreeSet<Integer>();

    static int[] arr = {2, 5, 1, 7, 9, 2};

    static int find(int x) {
        int l = 0, r = t.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (arr[mid] >= x) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}
