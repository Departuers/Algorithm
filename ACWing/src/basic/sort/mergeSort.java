package basic.sort;

import java.util.Arrays;

/**
 * 源数组,假设有n个数
 * |_______|_______|   划分成n/2
 * |___|___|___|___|  划分成n/4
 * |_|_|_|_|_|_|_|_|  划分成n/8
 * 直到划分成1个数
 * 递归层数最多有log n
 * 处理每一层都是O(N)
 * 所以时间复杂度是O(N log n)
 * 空间复杂度是O(N)
 * 需要辅助数组
 * 开始l,r指向两个区间的区间首
 * 区间首元素较小者放进辅助数组,维护双指针之一
 * 重复如上操作
 * 直到有一个区间没有数了,直接把另一个还有数的区间放在辅助数组的后面
 * 最后把排好序(未完全排好)的数组放回源数组
 */
public class mergeSort {
    public static void main(String[] args) {
        mergeSort(0, q.length - 1);
        System.out.println(Arrays.toString(q));
    }

    static int[] q = {2, 4, 1, 5, 7, 2, 123, 12, 3, 1};
    static int[] temp = new int[1000];

    static void mergeSort(int l, int r) {
        if (l == r) return;//如果只有1个数或者没有数,就不需要排序
        int mid = l + r >> 1;
        mergeSort(l, mid);//合并左半部分
        mergeSort(mid + 1, r);//合并右半部分
        //有一个辅助数组
        //k代表源数组取到什么位置
        //把排序后(不完全有序)的数组放进辅助数组
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (q[i] <= q[j]) {
                temp[k++] = q[i++];
            } else {
                temp[k++] = q[j++];
            }
        }
        //左半部分如果还有元素,有且只有一个还有元素
        while (i <= mid) temp[k++] = q[i++];
        //右半部分如果还有元素,有且只有一个还有元素
        while (j <= r) temp[k++] = q[j++];
        //放回原数组
        for (i = l, j = 0; i <= r; i++, j++) {
            q[i] = temp[j];
        }
    }
}
