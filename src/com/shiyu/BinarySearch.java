package com.shiyu;

/**
 * 二分查找，仅适用于有序的情况，有序！！！！！！！！！！！！！！
 */

public class BinarySearch {
    public static int search(int[] data, int target) {
        int begin = 0;
        int end = data.length - 1;// 最大索引
        while (begin <= end) {
            int indexOfMid = begin + ((end - begin) >> 1);// (end - begin)>>1就是需要改变的距离
            if (target > data[indexOfMid]) {
                begin = indexOfMid + 1; // 如果目标元素大于中间，往右走，移动左指针，
            } else if (target < data[indexOfMid]) {
                end = indexOfMid - 1; // 如果目标元素小于中间，往左走，移动右指针，
            } else {
                return indexOfMid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = { 131, 141, 142, 152, 162, 163, 177 };
        System.out.println(search(data, 177));
    }
}