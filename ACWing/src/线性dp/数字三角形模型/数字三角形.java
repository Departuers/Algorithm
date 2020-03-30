package 线性dp.数字三角形模型;

import java.util.Scanner;

/**
 * 数字三角形模型!
 */
public class 数字三角形 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                arr[i][j] = arr[i][j]+Math.max(arr[i + 1][j], arr[i + 1][j + 1]);
            }
        }
        System.out.println(arr[1][0]);
    }

    static int n;
    static int[][] arr = new int[504][504];

}
