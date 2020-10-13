package dp.单调队列dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104582203
 * 数学《绿色通道》总共有 n  道题目要抄，编号 1,2,…,n，抄第 i 题要花 ai 分钟。
 * 小 Y 决定只用不超过 t  分钟抄这个，因此必然有空着的题。
 * 每道题要么不写，要么抄完，不能写一半。
 * 下标连续的一些空题称为一个空题段，它的长度就是所包含的题目数。
 * 这样应付自然会引起马老师的愤怒，最长的空题段越长，马老师越生气。
 * 现在，小 Y 想知道他在这 t 分钟内写哪些题，才能够尽量减轻马老师的怒火。
 * 由于小 Y 很聪明，你只要告诉他最长的空题段至少有多长就可以了，不需输出方案。
 * 输入格式
 * 第一行为两个整数 n,t。
 * 第二行为 n 个整数，依次为 a1,a2,…,an。
 * 输出格式
 * 输出一个整数，表示最长的空题段至少有多长。
 * 数据范围
 * 0<n≤5×10^4,
 * 0<ai≤3000,
 * 0<t≤10^8
 * 输入样例：
 * 17 11
 * 6 4 5 2 5 3 4 5 2 3 4 5 2 3 6 3 5
 * 输出样例：
 * 3
 * 二分+单调队列,求空题段长度的最小值是多少
 * <p>
 * 最长空题段的长度可能是1到n，
 * 只要在最长空题段不超过某个长度len的前提下消耗的最少时间不超过t，
 * 那么这个len就是合法的，可以用二分法来枚举可能的解，从这个方向考虑问题就会简单很多，
 * 因为求空题段长度不超过len的最小消耗时间与上一题AcWing 1089 烽火传递基本一致。
 * 考虑二分性质
 * 二分区间左边满足一个性质,右边不满足性质,二分可以找到区间分界点
 * 假设二分长度为limit
 * 求一个空题长度不超过limit的情况下,最少需要的时间是多少
 * 求完之后判断是否小于等于t,能否容忍.
 * 状态定义f[i] 表示前i道题中合法,并且一定做第i道题的最小值,
 * 属性min
 * 空题长度的可选范围就是[0,n],假设空题长度的最优解为limit,
 * 可以使用dp求出空题区间最大不超过limit的最小花费时间,转化成烽火传递
 */
public class 绿色通道 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        int l = 0, r = n;
        //最大空题长度,0-n都有可能
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) r = mid;
                //这个长度是可以写完作业的
            else l = mid + 1;
            //这个长度是不能做完作业的
        }
        System.out.println(l);
    }

    private static boolean check(int limit) {
        int tt = 0, hh = 0;
        for (int i = 1; i <= n; i++) {
            if (q[hh] < i - limit - 1) hh++;
            //最远可以用到i-limit-1
            f[i] = f[q[hh]] + w[i];
            while (hh <= tt && f[q[tt]] >= f[i]) tt--;//维护最小值
            q[++tt] = i;
        }

        for (int i = n - limit; i <= n; i++) {
            if (f[i] <= m) {//根据状态定义,
                // [n-limit , n]之间任意一个满足条件的数都可以使得[1,n]最大不超过空题limit的最小花费时间<=m,
                return true;
            }
        }
        return false;
    }

    static int n, m, N = 50005;
    static int[] w = new int[N], f = new int[N], q = new int[N];

}
