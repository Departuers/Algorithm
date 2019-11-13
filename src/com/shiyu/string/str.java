package com.shiyu.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class str {
    public static void main(String[] args) {
        // System.out.println(replace("12 34 "));
//        System.out.println(tj("aadddd"));
        System.out.println(check("123SFD","1112222233333FFSSD"));

    }

    /**
     * 1.有无重复字符
     * 比如给定一串字符abcdedfa1321.，、,返回有无重复字符
     * 哈希函数，把key值转换成数组下标索引
     *
     * @param str
     * @return
     */
    public static boolean cf(String str) {
        if (str == null || str.length() == 0) {
            System.out.println("hello");
            return true;
        }
        int[] flag = new int[128];
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (flag[c] > 0)
                return false;
            else flag[c]++;
        }
        return true;
    }


    //2.翻转字符串
    public static String reverse(String a) {
        return new StringBuilder(a).reverse().toString();
    }

    /**
     * 3.变形词，给定2个字符串，其中一个能否变成另一个字符串考虑空格与大小写
     * 思路一，转换成数组排序
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean bi(String a, String b) {
        int lena = a.length();
        int lenb = b.length();
        if (lena != lenb)
            return false;
        char[] chars = a.toCharArray();
        char[] chars1 = b.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        return Arrays.equals(chars, chars1);
    }

    /**
     * 3.变形词，给定2个字符串，其中一个能否变成另一个字符串考虑空格与大小写
     * 思路三，哈希函数，字符转数组下标,a和b遍历添加进数组，不为偶数就返回false
     * 思路三，哈希函数，字符转数组下标,a遍历添加进去，b遍历一遍减出来，为负数返回false
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean b(String a, String b) {
        if (a.length() != b.length())
            return false;
        int[] arr = new int[128];
        for (int i = 0; i < a.length(); i++) {
            int c = a.charAt(i);
            arr[c]++;
        }
        for (int i = 0; i < b.length(); i++) {
            int c = b.charAt(i);
            arr[c]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1)
                return false;
        }
        return true;
    }

    /**
     * 4.替换空格
     * 将字符串中的空格全部换成%20
     * 思路 先遍历找到所有空格，2个指针，p1指向str的长度，p2指向str加上空格的长度，
     * p1的位置是空格，p2从字符串末尾添加%20，p1左移一位，直到p1指向0
     *
     * @param str
     * @return
     */
    public static String replace(String str) {
        char[] chars = str.toCharArray();
        int count = str.length();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 32)
                count += 2;
        }
        char[] chars1 = new char[count];
        System.arraycopy(chars, 0, chars1, 0, chars.length);
        int p1 = str.length() - 1;
        int p2 = count - 1;
        while (p1 >= 0) {
            if (chars1[p1] == ' ') {
                chars1[p2--] = '0';
                chars1[p2--] = '2';
                chars1[p2--] = '%';
            } else
                chars1[p2--] = chars[p1];
            p1--;
        }
        return new String(chars1);
    }

    /**
     * 5.字符串压缩，或者字符串统计
     * aaabccc压缩成a3b1c3
     * 压缩后的字符串没有变短，就返回原来的字符串
     * 思路就是遍历每次记录上一个字符,及出现次数，对stringbuilder第一个最后一个字符特殊处理
     * 第一个先添加进sb，最后一个手动添加字符出现次数
     * 遍历过程中，如果不同count重置为1，如果相同count++；
     *
     * @param data
     * @return
     */
    public static String tj(String data) {
        int count = 0;
        char last = data.charAt(0);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char charAt = data.charAt(i);
            if (sb.length() == 0) {
                sb.append(charAt);
                count++;
            } else if (last == charAt) {//和上一个字符相同
                count++;
            } else {//和上一个字符不同
                sb.append(count).append(charAt);
                count = 1;//遍历走到了新字符前面的逻辑没走，所以重置为1
            }
            last = charAt;
        }
        //处理最后一个字符重复次数
        if (count >= 1) {
            sb.append(count);
        }
        return sb.toString().length() > data.length() ? data : sb.toString();
    }

    /**
     * 6.两个字符串的字符集是否相同
     * 使用map
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean check(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (map.get(s1.charAt(i))==null)
                map.put(s1.charAt(i),1);
        }
        for (int i = 0; i < s2.length(); i++) {
            char charAt = s2.charAt(i);
            if (map.get(charAt)==null){//说明s2此处不在key列表中
                return false;
            }
        }
        return true;
    }
}
