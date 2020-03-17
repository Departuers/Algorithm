package wei;

import java.util.Random;

/* 题解，一个数组除了某一个数字出现了1次，其他的数都出现了2次，
* 要找出只出现一次的数字，主要就是A^A^B^B^C=C，直接异或就行
*/
public class 落单的数字 {
    public static void main(String[] args) {
        int N = 11;
        int[] arr = new int[N];
        for (int i = 0; i <= N - 1; i++) {
            arr[i] = i / 2 + 1;
        }
        arr[arr.length - 1] = new Random().nextInt(N - 1) + 1;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
        System.out.println();
        System.out.println(getone(arr));

    }

    public static int getone(int[] arr) {
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            x = x ^ arr[i];
        }
        return x;
    }
}