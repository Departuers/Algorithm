package com.shiyu;

import java.util.Arrays;

/**
 * 统计一个字符出现了几次
 * 用一个26的数组，不区分大小写，全转成小写
 * 大写的A是65，，小写的a是97，转大小写
 */

public class tongjizifu {
    public static void tongji(String s) {
        System.out.println(s);
        String ranString = s.toLowerCase();
        char[] arr = ranString.toCharArray();
        int[] res = new int[26];
        for (char c : arr) {
            res[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            System.out.println((char) (i + 'a') + "  " + res[i]);
        }
    }

    public static void main(String[] args) {
        char a = 'B';
        System.out.println(a + 0);
        String[] s = {"123", "asf"};
        for (String bb : s) {
            bb = "42";
            System.out.println(bb);
        }
        System.out.println(Arrays.toString(s));
    }

}