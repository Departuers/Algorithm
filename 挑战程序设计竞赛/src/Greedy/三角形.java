package Greedy;

import java.util.Arrays;

/**
 * 有n根棍子,棍子长度为ai,从中选出周长尽可能长的三角形,请输出最大周长,
 * 无法组成三角形则输出0
 */
public class 三角形 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 11, 10};
        System.out.println(San(arr, arr.length));
    }

    /**
     * @param arr 每根棍子的长度
     * @param n   一共有n根棍子
     * @return 最终三角形的周长
     * 两边之和大于第三边,便符合三角形的定义
     */
    public static int San(int[] arr, int n) {
        Arrays.sort(arr);//排序使其符合贪心策略
        int ans = 0;
        for (int i = n - 1; i >= 2; i--) {
            if (arr[i] < arr[i - 1] + arr[i - 2]) {
                ans = arr[i] + arr[i - 1] + arr[i - 2];
                //两边之和大于第三边,便符合三角形的定义
                break;
            }
        }
        return ans;
    }
}
