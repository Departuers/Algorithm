package dp.区间dp;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/content/8651/
 * X星球的考古学家发现了一批古代留下来的密码。
 * 这些密码是由A、B、C、D 四种植物的种子串成的序列。
 * 仔细分析发现，这些密码串当初应该是前后对称的（也就是我们说的镜像串）。
 * 由于年代久远，其中许多种子脱落了，因而可能会失去镜像的特征。
 * 你的任务是：
 * 给定一个现在看到的密码串，计算一下从当初的状态，它要至少脱落多少个种子，才可能会变成现在的样子。
 * 输入格式
 * 共一行，包含一个由大写字母ABCD构成的字符串，表示现在看到的密码串。
 * 输出格式
 * 输出一个整数，表示至少脱落了多少个种子。
 * 数据范围
 * 输入字符串长度不超过1000
 * 输入样例1：
 * ABCBA
 * 输出样例1：
 * 0
 * 输入样例2：
 * ABDCDCBABC
 * 输出样例2：
 * 3
 * f[l,r] 集合:所有从s[l,r]之间的所有回文子序列的集合
 * 属性长度最大值,max
 * 状态划分:选不选第l个,选不选第r个
 * 状态计算:
 * 选l选r :f[l+1,r-1]+2
 * 选l不选r : f[l+1,r-1]
 * 选r不选l : f[l+1,r]
 * 均不选: f[l+1,r-1]
 */
public class 密码脱落 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int[][] f = new int[1010][1010];
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                if (len == 1) f[l][r] = 1;//只有1个那就是回文子序列
                else {
                    f[l][r] = Math.max(f[l][r - 1], f[l + 1][r]);
                    if (s.charAt(l) == s.charAt(r)) f[l][r] =
                            Math.max(f[l][r], f[l + 1][r - 1] + 2);//如果两个相等那么
                }
            }
        }

        System.out.println(n - f[0][n - 1]);
    }
}
