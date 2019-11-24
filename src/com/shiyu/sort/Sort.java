package com.shiyu.sort;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] data = {31, 8, 3, 1, 11, 13, 19, 14, 123, 141, 15};
        System.out.println(Arrays.toString(selectSort(data)));
//        long MAX = 59084709587505l;
//        int count = 0;
//        for (long i = 0; Math.pow(3, i) < MAX; ++i)
//            for (long j = 0; Math.pow(5, j) < MAX; ++j)
//                for (long k = 0; Math.pow(7, k) < MAX; ++k)
//                    if (Math.pow(3, i) * Math.pow(5, j) * Math.pow(7, k) < MAX)
//                        count++;
//        System.out.println(count);
    }

    /**
     * 希尔排序
     * [1, 1241, 123, 1, 31, 4121, 515, 632]
     * [1, 1, 31, 632, 123, 1241, 515, 4121]
     * [1, 1, 31, 123, 515, 632, 1241, 4121]
     *
     * @param arr
     */
    public static void XierSort(int[] arr) {

        for (int inter = arr.length / 2; inter > 0; inter /= 2) { //不断地缩小增量,初始增量为数组长度的一半,控制增量每次增量为上次的一半
            for (int i = inter; i < arr.length; i++) {//初始化i值为增量开始，到数组结束，从数组后面往前找
                int target = arr[i];
                int j = i - inter;                      //i-增量就是寻找满足增量的一组数据
                while (j > -1 && target < arr[j]) {
                    arr[j + inter] = arr[j];
                    j -= inter;
                }
                arr[j + inter] = target;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序法,外层循环i控制轮数，如果有N个元素一共就要进行N-1轮比较
     * 每轮比较出剩余最大的元素，并交换到最后，
     * 外层循环i控制轮数，并间接控制内层循环边界，每轮的边界就是num.length - i
     * O(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] BubbleSort(int[] arr) {
        int[] ints = Arrays.copyOf(arr, arr.length);
        int count = 0;
        for (int i = 1; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                    count++;
                }
            }
        }
        System.out.println(count);
        return ints;
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     * <p>
     * 每轮找出最小元素，放在数组前面，外层循环i控制边界，代表已经有序了的元素
     * 外层i还代表最小元素已经交换到哪个位置了
     * 内层循环，往后比较，找出剩余最小元素的索引，在外层循环交换元素
     *
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        int[] nums = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        int[] nums = Arrays.copyOf(arr, arr.length);
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && temp < nums[j - 1]) {
                nums[j] = nums[j = 1];
                j--;
            }
            if (j != i) {
                nums[j] = temp;
            }
        }
        return nums;
    }
}