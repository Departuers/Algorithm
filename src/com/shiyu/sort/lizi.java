package com.shiyu.sort;

import java.util.Arrays;

public class lizi {
    public static void main(String[] args) {
        int[] data = {3, 2, 1, 4, 5, 7, 8};
        jishuzaizuo(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * 1.奇数在左
     * 给一个整数数组，调整数组数字的位置，
     * 使所有奇数放在数组前半部分，所有偶数位放在数组后半部分，
     * 要求时间复杂度为O(n)
     * 思路:2个指针一个指向奇数，一个指向偶数，从头开始，
     * 一个while循环，左边指针找到偶数，右边指针找到奇数就交换，如果找不到左边指针往右，右边指针往左
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

    public static void selectK(int[] arr, int k) {

    }
}
