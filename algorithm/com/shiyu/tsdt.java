package com.shiyu;

import java.util.Arrays;

public class tsdt {
    public static void main(String[] args) {
//        System.out.println(isPalindrome(121));
//        PriorityQueue<Integer> a = new PriorityQueue<Integer>();
//
//        a.add(6);
//        a.add(5);
//
//        System.out.println(a.poll());
        int d = 2 & 3;
        System.out.println(d);
        int[] arr = {3, 4, 6, 87, 478};
        System.out.println(Arrays.binarySearch(arr, 0, 2, 4));

    }

    public static boolean isPalindrome(int x) {
        return new StringBuilder(x + "").reverse().toString().equals(Integer.toString(x));
    }

}
