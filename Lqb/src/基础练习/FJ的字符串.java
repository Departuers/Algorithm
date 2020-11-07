package 基础练习;

import java.util.Scanner;

public class FJ的字符串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] str = new String[28];
        str[0] = "A";
        for (int i = 1; i < 26; i++) {
            char t = (char) (i + 'A');
            str[i] = str[i - 1] + t + str[i - 1];
        }
        System.out.println(str[n-1]);
    }
}
