package basic.sort;

import java.util.Scanner;

/**
 * 给定一个长度为n的整数数列，请你计算数列中的逆序对的数量。
 * 逆序对的定义如下：对于数列的第 i 个和第 j 个元素，如果满足 i < j 且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * 输入格式
 * 第一行包含整数n，表示数列的长度。
 * 第二行包含 n 个整数，表示整个数列。
 * 输出格式
 * 输出一个整数，表示逆序对的个数。
 * 数据范围
 * 1≤n≤100000
 * 输入样例：
 * 6
 * 2 3 4 5 6 1
 * 输出样例：
 * 5
 * 会变成两两对比
 */
public class 逆序对 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(merge(0, n - 1));
    }

    private static long merge(int l, int r) {
        if (l >= r) return 0;
        int mid = l + r >> 1;
        long res = merge(l, mid) + merge(mid + 1, r);
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else {
                temp[k++] = a[j++];
                res += mid - i + 1;//统计逆序对!!!
            }
        }
        while (i <= mid) temp[k++] = a[i++];
        while (j <= r) temp[k++] = a[j++];
        for (i = l, j = 0; i <= r; i++, j++) a[i] = temp[j];
        return res;
    }

    static int[] a = new int[100010];
    static int[] temp = new int[100010];
    static int n;
}
