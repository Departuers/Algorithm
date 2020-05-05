package 线性dp;

import java.util.Scanner;

/**
 * 输入一个长度为n的整数序列，从中找出一段长度不超过m的连续子序列，
 * 使得子序列中所有数的和最大。
 * 注意： 子序列的长度至少是1。
 * 输入格式
 * 第一行输入两个整数n,m。
 * 第二行输入n个数，代表长度为n的整数序列。
 * 同一行数之间用空格隔开。
 * 输出格式
 * 输出一个整数，代表该序列的最大子序和。
 * 数据范围
 * 1≤n,m≤300000
 * 输入样例：
 * 6 4
 * 1 -3 5 1 -2 3
 * 输出样例：
 * 7
 * 状态表示f[i]表示以第i个数字结尾的长度不超过m的子序列的最大和。
 * s[i]表示数组的前缀和，则a[l] + ... + a[r] = s[r] - s[l-1]。
 * 状态转移方程为f[i] = max(s[i] - s[j-1])，
 * 其中i - j大于等于0并且不超过m，因此不难得出以下的DP代码：
 */
public class 最大子序列和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] += sc.nextInt() + s[i - 1];
        }
        System.out.println(d());
    }

    /**
     * 枚举i~j区间,因为区间长度不超过m,O(n^2)
     *
     * @return 区间长度不超过m的最大值
     */
    static int on2() {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                if (i - j >= m) continue;
                //区间的话一共有i-j+1个值,同理可以写成i-j+1>m
                //则显然i-j>=m
                f[i] = Math.max(f[i], s[i] - s[j - 1]);
                //求以f[i]结尾长度为1~m的区间的最大值
            }
            ans = Math.max(f[i], ans);
        }
        return ans;
    }

    /**
     * 本题的数据范围是30w，上面平方级别复杂度的代码显然会超时。
     * 如果单纯的看这题，不去考虑DP的思想，
     * 无非是求以第i个数字结尾的长度不超过m的子序列的和，
     * 很容易想到单调队列去实现。如果按照DP的思想，也是可以推出使用单调队列优化的。
     * 我们在DP过程中求f[i]时，f[i]的值是s[i] - s[i-1],s[i] - s[i-2],...,s[i] - s[i-m]这m个数中的最大值，
     * 当然，当i小于m时，只用枚举到s[i] - s[0]，所以就是求滑动窗口大小是m的最大值了，正是单调队列的经典应用。
     * 在单调队列的实现时，需要先在队列中加入哨兵结点s[0]，
     * 后面就是解决四个问题了。第一，何时出队头，枚举到第i个数字时，
     * 第i个数字还没加入队列时，队头元素的下标允许的最小值是i - m，
     * 所以当i - q[hh] > m时就需要出队头了；第二，何时出队尾，
     * 我们需要队头的元素是s[q[hh]]最小的元素，所以当s[i] <= s[q[tt]]时，
     * 出队尾元素；第三，何时加入新元素，队尾元素该出的出完了就可以将i加入到队列中了；
     * 第四，何时更新我们要求的长度不超过m的子序列的和的最大值res，
     * 只要在确保队列中的元素个数不超过m时就可以尝试更新res了。
     * 使用单调队列优化DP的方法时间复杂度是O（n）。
     */
    static int d() {
        int ans = -19;
        int hh = 0, tt = 0;
        for (int i = 1; i <= n; i++) {
            if (i - q[hh] > m) hh++;
            ans = Math.max(ans, s[i] - s[q[hh]]);
            while (hh <= tt && s[q[tt]] >= s[i]) tt--;
            q[++tt] = i;
        }
        return ans;
    }

    static int n, m;
    static int[] f = new int[300010];
    static int[] q = new int[300010];

    static int[] s = new int[300010];
}
