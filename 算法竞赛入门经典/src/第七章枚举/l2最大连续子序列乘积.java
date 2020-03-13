package 第七章枚举;

/**
 * 输入n个元素组成的序列S， 你需要找出一个乘积最大的连续子序列。 如果这个最大的乘
 * 积不是正数， 应输出0（ 表示无解） 。 1≤n≤18， -10≤Si≤10
 * 分析
 * 连续子序列有两个要素： 起点和终点，
 * 因此只需枚举起点和终点即可.由于每个元素的
 * 绝对值不超过10且不超过18个元素，
 * 最大可能的乘积不会超过10^18， long存储。
 * 也可使用dp解决参见LeetCode 152乘积最大子序列
 */
public class l2最大连续子序列乘积 {
    public static void main(String[] args) {

    }

    static int f(int[] arr) {
        if (arr.length == 1) return Math.max(arr[0], 0);
        return 0;

    }
}
