package 基础练习;

import java.util.Scanner;

public class 字符串对比 {
    public static void main(String[] args) {
        System.out.println((int)'A');
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        if (s1.length() != s2.length()) {
            System.out.println(1);
        } else if (s1.equals(s2)) {
            System.out.println(2);
        } else if (s1.toLowerCase().equals(s2.toLowerCase())) {
            System.out.println(3);
        } else if (!s1.equals(s2)) {
            System.out.println(4);
        }
    }
}
