package 尺取法;

/**
 * https://www.cnblogs.com/-Ackerman/p/11162833.html
 * 关于大佬的尺取法
 * 先看例题:
 * 给你一个序列(大小为n)，让你去找到一个区间[i,j],
 * 区间内所有数的和 sum >= 15
 * 求出满足这样要求的最小的区间长度
 * The truth that you leave
 * 双指针类似吧
 * 尺取法,通常用于区间有一定规律,或者说有一定变化趋势
 * 对所选取区间进行判断之后,可以推出满足条件的区间,
 * 如果判断目前所选区间,但无法确定所求解的区间,如何进一步推出,无法使用尺取法
 *
 * 尺取法的模型便是这样：
 * 根据区间的特征交替推进左右端点求解问题，
 * 其高效的原因在于避免了大量的无效枚举，
 * 其区间枚举都是根据区间特征有方向的枚举，
 * 如果胡乱使用尺取法的话会使得枚举量减少，
 * 因而很大可能会错误，所以关键的一步是进行问题的分析！
 */
@SuppressWarnings("all")
public class ruler {
    public static void main(String[] args) {

        int[] arr = {5, 1, 3, 5, 10, 7, 4, 9, 2, 13};
        System.out.println(chiqu(arr, 15));
        int[] a = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        System.out.println(two(a, 2));
    }

    /**
     * Poj 3061
     * 给你一个序列(大小为n)，让你去找到一个区间[i,j],
     * 区间内所有数的和 sum >= 15
     * 求出满足这样要求的最小的区间长度
     *
     * @param arr
     * @return
     */
    static int chiqu(int[] arr, int n) {
        int i = 0, j = 0, ans = Integer.MIN_VALUE;
        int sum = arr[0];
        while (i <= j && j < arr.length) {
            if (sum >= n) {//枚举所有区间!!!,会打印所有区间
                System.out.printf("%d,%d", i + 1, j + 1);
                System.out.println();
                ans = Math.max(j - i + 1, ans);
                sum -= arr[i];
                i++;
            } else {
                j++;
                if (j == arr.length) break;
                sum += arr[j];
            }
        }
        return ans;
    }

    /**
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     * 返回仅包含 1 的最长（连续）子数组的长度。
     * 示例 1：
     * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：
     * [1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     * 示例 2：
     * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * 输出：10
     * 解释：
     * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     * 显然:看代码
     *
     * @return
     */
    static int two(int[] arr, int k) {
        int left = 0;
        int ans = 0;
        //显然使得left和right达到最长,怎么使用k
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == 0) {
                k--;//遇见一次0就使用一次k,维护k值
            }
            if (k < 0) {//如果k不够用了,舍弃左边界那个元素left++
                if (arr[left] == 0) {//如果左边为0,直接舍弃那个元素,left++
                    k++;
                }
                left++;//左边界++
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
