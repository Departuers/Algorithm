package com.shiyu;

import java.util.PriorityQueue;

public class tsdt {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        PriorityQueue<Integer> a = new PriorityQueue<Integer>();

        a.add(6);
        a.add(5);

        System.out.println(a.poll());

    }

    public static boolean isPalindrome(int x) {
        return new StringBuilder(x + "").reverse().toString().equals(Integer.toString(x));
    }

}
