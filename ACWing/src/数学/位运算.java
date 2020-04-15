package 数学;

import java.util.Scanner;

//统计一个数二进制有多少个1
public class 位运算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int res = 0;
        while (n != 0) {
            n -= lowbit(n);
            res++;
        }
        System.out.println(res);
    }

    private static int lowbit(int n) {
        return n&-n;
    }

    static int n;
}
