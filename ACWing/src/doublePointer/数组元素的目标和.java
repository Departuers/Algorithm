package doublePointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 数组元素的目标和
 * 两数之和
 * 给定两个升序排序的有序数组A和B，以及一个目标值x。数组下标从0开始。
 * 请你求出满足A[i] + B[j] = x的数对(i, j)。
 * 数据保证有唯一解。
 * 输入格式
 * 第一行包含三个整数n，m，x，分别表示A的长度，B的长度以及目标值x。
 * 第二行包含n个整数，表示数组A。
 * 第三行包含m个整数，表示数组B。
 * 输出格式
 * 共一行，包含两个整数 i 和 j。
 * 数据范围
 * 数组长度不超过100000。
 * 同一数组内元素各不相同。
 * 1≤数组元素≤10^9
 * 输入样例：
 * 4 5 6
 * 1 2 4 7
 * 3 4 6 8 9
 * 输出样例：
 * 1 1
 */
public class 数组元素的目标和 {
    public static void main(String[] args) {
        //hash();
//        Binary();
        douPoint();
    }

    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    static int n, m, x;

    static int[] a = new int[100003];
    static int[] b = new int[100003];

    /**
     * 哈希法
     * 遍历一遍a数组，将每个元素的值映射为在a中的下标，
     * 由于没有重复元素，且数据量才十万，哈希映射不会占用太大空间且可行。
     * 之后再遍历一遍b数组，看看x-b[i]是否在a中出现过。
     * 空间换时间，时空复杂度均为O（n）。
     */
    static void hash() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int t = 0;
        for (int i = 0; i < m; i++) {
            t = sc.nextInt();
            map.put(t, i);
        }
        for (int i = 0; i < n; i++) {
            t = x - a[i];
            if (map.containsKey(t)) {
                System.out.println(map.get(t) + " " + i);
            }
        }
    }

    /**
     * 给定的数组是升序的,可以二分找
     * 方法一使用哈希表同样没有利用上数组有序的性质，
     * 并且由于查找某元素是否在map中同样耗时，所以效率并不高。
     * 而由有序的性质很容易想到遍历a时可用二分法在b中查找x-a[i]的位置。
     * 时间复杂度为O（nlogn）。
     */
    static void Binary() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int t = 0, e = 0;
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            t = x - a[i];
            if ((e = Arrays.binarySearch(b, 0, m + 1, t)) > 0) {
                System.out.println(e + " " + i);
            }
        }
    }


    /**
     * 方法三：双指针
     * 令指针p指向数组a的第一个位置，指针q指向数组b的最后一个位置，
     * 一旦a[p] + b[q]比x大，说明要减小其中一方的值，q--即可，
     * 如果比x小，p++即可，时间复杂度为O（n）。
     * 思考下为什么这次的双指针要分别指向两个数组的开始和结束位置？
     * 因为若是开始p,q都指向数组开头，两数和小于x时，是要该执行p++，
     * 还是q++呢？当两数和大于x时，p，q肯定不能再往回移动了，
     * 而采取首尾指针的方法就能很好的解决了这一问题。
     * O(n)
     */
    static void douPoint() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        int st = 0, ed = m - 1;
        while (st < n && ed >= 0) {
            if (a[st] + b[ed] > x) ed--;
            else if (a[st] + b[ed] < x) st++;
            else {
                System.out.println(st + " " + ed);
                st++;
                ed--;
            }
        }
    }
}
