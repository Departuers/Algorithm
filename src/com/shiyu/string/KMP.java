package com.shiyu.string;

/**
 * KMP字符串匹配
 */
public class KMP {
    public static void main(String[] args) {

    }

    //暴力匹配，比较低效O(m*n)
    public static int indexOf(String str, String key) {
        int i = 0;//指向开始匹配的第一个元素
        int sc = i; //指针从i开始匹配
        int j = 0;  //key数组指针
        while (sc < str.length()) {
            if (str.charAt(sc) == key.charAt(j)) {
                sc++;
                j++;
                if (j == key.length()) {
                    return i;
                } else {
                    i++;
                    sc = i;
                    j = 0;
                }
            }
        }
        return -1;
    }


}
