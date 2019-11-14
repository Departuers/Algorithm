package com.shiyu.sort;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] data = {31, 8, 3, 1, 11, 13, 19, 14};
        XierSort(data);
        System.out.println(1/2);
    }

    /**
     * [1, 1241, 123, 1, 31, 4121, 515, 632]
     * [1, 1, 31, 632, 123, 1241, 515, 4121]
     * [1, 1, 31, 123, 515, 632, 1241, 4121]
     * @param arr
     */
    public static void XierSort(int[] arr) {

        for (int inter = arr.length /2; inter > 0; inter /= 2) { //不断地缩小增量,初始增量为数组长度的一半,控制增量每次增量为上次的一半
            for (int i = inter; i < arr.length; i++) {//初始化i值为增量开始，到数组结束，从数组后面往前找
                int target = arr[i];
                int j = i-inter;                      //i-增量就是寻找满足增量的一组数据
                while (j > -1 && target < arr[j]) {
                    arr[j + inter] = arr[j];
                    j -= inter;
                }
                arr[j + inter] = target;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
