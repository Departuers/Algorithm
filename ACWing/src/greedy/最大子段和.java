package greedy;

/**
 * 一个长度为n的序列,a[1...n],元素可正可负,求一个子序列
 * 使得sum(a[l,r])最大,
 * 如a=[1,-2,3,-2,4]
 * 答案为3-2+5=6
 * O(n)的贪心算法
 */
public class 最大子段和 {
    public static void main(String[] args) {
        int ans = 0, sum = 0;
        //sum代表当前序列的和
        for (int i = 1; i <= n; i++) {
            sum += a[i];
            ans = Math.max(ans, sum);
            if (sum < 0) sum = 0;
        }
    }

    static int[] a = new int[100010];
    static int n;
}
