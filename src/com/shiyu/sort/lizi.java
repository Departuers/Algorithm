package com.shiyu.sort;

import java.util.Arrays;

public class lizi {
    public static void main(String[] args) {
        int[] data = {3, 2, 1, 4, 5, 7, 8};
        // System.out.println(selectK(data, 0, data.length - 1, 7));
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));


    }

    /**
     * 1.奇数在左
     * 给一个整数数组，调整数组数字的位置，
     * 使所有奇数放在数组前半部分，所有偶数位放在数组后半部分，
     * 要求时间复杂度为O(n)
     * 思路:2个指针一个指向奇数，一个指向偶数，从头开始，
     * 一个while循环，左边指针找到偶数，右边指针找到奇数就交换，
     * 如果找不到左边指针往右，右边指针往左,两个相交就交换完了
     *
     * @param arr
     */
    public static void jishuzaizuo(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (arr[left] % 2 == 0 && arr[right] % 2 == 1) {
                swap(arr, left, right);
                left++;
                right--;
            }
            if (arr[left] % 2 == 1) {
                left++;
            }
            if (arr[right] % 2 == 0) {
                right--;
            }
        }
    }

    public static void swap(int[] arr, int q, int p) {
        int temp = arr[q];
        arr[q] = arr[p];
        arr[p] = temp;
    }

    /**
     * 2.第K个元素
     * 以高效率求出一个乱序数组中，按数值顺序的第k个元素值
     * 思路:快排的分治思想能找到基准元素在有序情况下的下标，通过这个来缩小范围
     *
     * @param arr
     * @param l
     * @param r
     * @param k
     * @return
     */

    public static int selectK(int[] arr, int l, int r, int k) {
        if (arr.length < k || k <= 0)
            throw new IllegalArgumentException("out");

        int temp = part(arr, l, r);
        int qK = temp - l + 1;
        if (k == qK)
            return arr[temp];
        else if (k < qK) {
            return selectK(arr, l, temp - 1, k);
        } else {
            return selectK(arr, temp + 1, r, k - qK);
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        int temp = part(arr, l, r);
        quickSort(arr, l, temp - 1);
        quickSort(arr, temp + 1, r);
    }

    private static int part(int[] arr, int l, int r) {
        int pivot = arr[l];
        int left = l + 1;
        int right = r;
        while (left <= right) {
            //left一直往右走，循环退出left一定指向第一个大于基准元素的位置
            while (left <= right && arr[left] <= pivot) {//要控制边界
                left++;
            }
            //right一直往左走，循环退出left一定指向第一个大于基准元素的位置
            while (left <= right && arr[right] > pivot) {//要控制边界
                right--;
            }
            if (left < right)//要控制边界
                swap(arr, left, right);
        }
        //最外层循环退出，right指向最后一个小于等于基准元素的位置，也是基准元素应该在的位置
        swap(arr, l, right);
        return right;
    }
}
