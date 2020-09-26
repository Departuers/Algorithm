package dp.线性dp.LIS模型;

import java.util.Scanner;

/**
 * 1010拦截导弹
 * 有一个数列
 * 求其中最少可以分成几个最长下降子序列
 * 300 299 155 260 140 123 52
 * 首先第一个拦截系统使用拦截300这个导弹
 * 走到299这个导弹,
 * 有2种选择,
 * 使用第一个拦截系统拦截,
 * 或者新创建一个拦截系统拦截
 * 299必然是某一个最长下降子序列的结尾
 * ___贪心选择,每一个最长下降子序列的结尾越大越好,大的可以兼容小的,但小的不能兼容大的
 * 把299放在,某一个最长下降子序列的结尾大于等于299的,结尾最小的一个最长下降子序列,接到后面去,
 * 假设子序列结尾数为f[i],在所有满足f[i]>299的子序列中选一个也可以不是最小的,(最小的)接上去
 * 转换为贪心证明问题...
 * 如果f[i]均小于299,则创建一个新的最长下降子序列
 * 贪心流程:
 * 从前往后扫描每个数:
 * 情况1:如果现有的子序列的结尾都小于当前数,则创建新的子序列
 * 情况2:将当前数放到结尾大于等于它的最小子序列后面
 * 证明:A表示贪心得到的序列,B表示最优解
 * 假设最优解和贪心解方案数不同
 * 找到第一个不同的位置
 * 则贪心解不同点的后面的子序列和最优解不同点后面子序列,是可以互相替换的
 * 则贪心解和最优解得到的方案数量是一样的
 */
public class 拦截导弹 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            arr[N++] = sc.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] <= arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        System.out.println(ans);
        //非常牛逼了,贪心
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int k = 0;
            while (k < cnt && arr[i] > g[k]) {//只要g[k]的元素大于arr[i],就不能放在第k个导弹拦截系统的后面
                // 就一直往后找
                k++;//找到能放的位置,因为是下降子序列
            }
            //把arr[i]接在 g[k]后面
            //g[k]存储的是每一个元素存储的是,每一个最长下降子序列的结尾元素
            g[k] = arr[i];
            if (k >= cnt) cnt++;//如果现有的子序列的结尾都小于当前数,则创建新的子序列
            //显然第一次k=0 cnt=0,while不会执行,会cnt++
        }
        System.out.println(cnt);

    }

    static int N;
    static int[] arr = new int[1010];
    static int[] dp = new int[1010];
    static int[] g = new int[1010];
}
