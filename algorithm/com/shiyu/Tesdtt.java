package com.shiyu;

import java.util.ArrayDeque;

public class Tesdtt {
    public static void main(String[] args) {
        ArrayDeque<Integer> arr = new ArrayDeque<Integer>();
        arr.offer(12);
        arr.offer(2123);
        arr.offer(22);
        System.out.println(arr.poll());
    }
}
