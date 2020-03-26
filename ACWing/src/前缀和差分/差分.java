package 前缀和差分;

import java.util.Arrays;

public class 差分 {
    public static void main(String[] args) {
        int arr[] = {3, 5, 1, 6, 9};
        int[] b = new int[20];
        for (int i = 1; i < arr.length; i++) {
            b[i] = arr[i] - arr[i - 1];
        }
        System.out.println(Arrays.toString(b));
        b[1] += 2;
        b[5] -= 2;
        for (int i = 1; i < 5; i++) {
            arr[i] = b[i] + arr[i - 1];
        }
        System.out.println(Arrays.toString(arr));

        b[2] += 3;
        b[5] -= 3;
        int t = 0;
        for (int i = 1; i < 5; i++) {
            arr[i] = b[i] + arr[i - 1];
        }
        System.out.println(Arrays.toString(arr));
    }
}
