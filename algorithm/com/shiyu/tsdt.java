package com.shiyu;

public class tsdt {
    public static void main(String[] args) {
        int[][] arr = {{3}, {2, 4}, {6, 7, 9}, {4, 2, 6}};
        for (int[] ints : arr) {
            //    System.out.println(Arrays.toString(ints));
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].length);
        }
        int[][] dp = new int[arr.length][arr[arr.length - 1].length];
        for (int i = 0; i < arr[arr.length - 1].length; i++) {
            dp[dp[dp.length - 1][i]][i] = arr[arr.length - 1][i];
        }

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 0; j < dp[i].length-1; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i + 1][j + 1], dp[i + 1][j]);
            }
        }
        System.out.println(dp[0][0]);
    }

}
