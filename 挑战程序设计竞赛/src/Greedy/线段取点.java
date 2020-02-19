package Greedy;

import java.util.Arrays;

/**
 * POJ 3069
 * https://blog.csdn.net/zwj1452267376/article/details/49890895
 * 题意：在一条直线上，有n个点。从这n个点中选择若干个，给他们加上标记。
 * 对于每一个点，其距离为R以内的区域里必须有一个被标记的点。
 * 问至少要有多少点被加上标记。
 * 题解：我们从最左边的开始考虑。对于这个点，到距其R以内的区域必须要有带有标记的点。
 * 带有标记的点一定在其右侧（包含这个点本身）。
 * 给从最左边开始，距离为R以内的最远的点加上标记，尽可能的覆盖更靠右边的点。
 * 对于添加了标记的点右侧相距超过R的下一个点，采用同样的方法找到最右侧R距离以内最远的点添加标记。
 * 在所有点都被覆盖之前不断重复这一过程。
 *
 *
 */
public class 线段取点 {
    public static void main(String[] args) {
        int[] arr = {1, 7, 15, 20, 30, 50};
        System.out.println(Qu(6, 10, arr));
    }

    public static int Qu(int N, int R, int[] arr) {
        Arrays.sort(arr);
        int ans = 0, i = 0;
        while (i < N) {
            int s = arr[i++];//先找到,s是最左边没有被覆盖的点作为初始点,i++意味着从下一个点开始找
            while (i < N && arr[i] <= s + R) i++;//i找到距离初始点最远但超过R的第一个元素,
            int p = arr[i - 1];//此时p就是距离初始点不超过R的最后一个元素,作为标记的点
            while (i < N && arr[i] <= p + R) i++;//i作为,上次操作找到的标记点,
            //距离该标记点距离超过R的第一个元素,作为新一轮循环的初始点
            ans++;
        }
        return ans;
    }
}
