package dp.单调队列dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 输入一个长度为n的整数序列，从中找出一段长度不超过m的连续子序列，使得子序列中所有数的和最大。
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
 * 只能用单调队列优化,其他都会超时,
 * 有限集合求最值,求个数
 * 考虑last
 * 划分依据,长度为m的子序列的终点在哪一个位置
 * 所有长度不超过m的子序列,按照m的终点来分类,分成n类,不重不漏,
 * 求得每一类最大值,取max
 * 假设以A[k]结尾,往前枚举,长度1的,长度2的,长度3...
 * 结合区间和数组为S[],那么以A[k]结尾,长度为j的子序列和为,
 * S[k]-S[k-j](1<=j<=m)
 * 可以使用单调队列优化
 */
public class 最大序列和 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = nextInt() + s[i - 1];
        }
        f();
    }

    /**
     * 单调队列一般存下标,
     * 因为这个可以让我们快速判断当前位置是否已经划出窗口了
     */
    static void f() {
        int res = (int) -1e8;
        int hh = 0, tt = 0;
        //tt初始化为0,数组初始化不为空,说明队列中有一个元素s[0],当然s[0]=0
        //因为S[k]-S[k-j](1<=j<=m), 存在k<=m 也就是,结果S[k]-0,然而取max为s[i]-s[q[hh]],
        //这么写最简单
        for (int i = 1; i <= n; i++) {
            if (q[hh] < i - m) hh++;
            res = Math.max(res, s[i] - s[q[hh]]);
            while (hh <= tt && s[q[tt]] >= s[i]) tt--;
            //考虑S[k]-S[k-j](1<=j<=m),S[k-j]应该越小越好
            q[++tt] = i;//当前元素加到队列当中
        }
        System.out.println(res);
    }

    static int[] q = new int[300010];

    static int[] s = new int[300009];
    static int n, m;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }
}
