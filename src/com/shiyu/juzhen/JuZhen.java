package com.shiyu.juzhen;

public class JuZhen {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
//        System.out.println(arr[1][0]);
        print(arr);
//        System.out.println(arr[3][1]);
    }

    /**
     * 顺时针打印二维数组
     *
     * @param arr
     */
    public static void print(int[][] arr) {
        int leftUpRow = 0, leftUpCol = 0, rightDownRow = arr.length - 1, rightDownCol = arr[0].length - 1;

        while (leftUpCol <= rightDownCol) {
            System.out.println(arr[leftUpRow][leftUpCol++]);
        }
        leftUpCol = rightDownCol;
        leftUpRow++;
        while (leftUpRow <= rightDownRow) {
            System.out.println(arr[leftUpRow++][leftUpCol]);
        }
    }
}
