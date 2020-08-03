package basic.stack;

import java.util.Scanner;

/**
 * 给定一个长度为N的整数数列，输出每个数左边第一个比它小的数，如果不存在则输出-1。
 * 输入格式
 * 第一行包含整数N，表示数列长度。
 * 第二行包含N个整数，表示整数数列。
 * 输出格式
 * 共一行，包含N个整数，其中第i个数表示第i个数的左边第一个比它小的数，如果不存在则输出-1。
 * 数据范围
 * 1≤N≤105
 * 1≤数列中元素≤109
 * 输入样例：
 * 5
 * 3 4 2 7 5
 * 输出样例：
 * -1 3 -1 2 2
 */
public class 单调栈 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int x = 0;
        while (n-- != 0) {
            x = sc.nextInt();
            while (tt != 0 && stk[tt] >= x) tt--;
            //tt==0代表找不到比它小的数
            if (tt == 0) System.out.print(-1+" ");
            else System.out.print(stk[tt]+" ");
            stk[++tt] = x;
            //stk[0]这个位置什么都不存,到了这个位置代表找不到!!!
        }
    }

    static int[] stk = new int[100010];
    static int n, tt = 0;
}
