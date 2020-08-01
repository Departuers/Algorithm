package dp.区间dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104377152
 * 环形dp,环展开成链求解
 * 状态表示f[L,R]{
 * 集合:所有将[L,R]合并到一起的矩阵的方式
 * 属性:Max
 * 状态计算:
 * }状态划分:{
 * 考虑最后的不同点:分界线
 * 分成若干个子集:
 * L+1,L+2...R-2,R-1;
 * 考虑第k个分界线
 * (L,K)(K,R)+cost
 * 使得上式最大,f[L,K]+f[K,R]+w[i]*w[k]*w[r]
 * }
 * f[1,n]即为答案
 * 环展开为链
 */
public class 能量项链 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            a[i + n] = a[i];
        }
        for (int len = 3; len <= n + 1; len++) {//枚举长度,起码三个数才能表示2个矩阵,消掉中间那个数
            for (int l = 1; l + len - 1 <= n * 2; l++) {//枚举起点
                int r = l + len - 1;
                for (int k = l + 1; k < r; k++) {//枚举分界线
                    f[l][r] = Math.max(f[l][r], f[l][k] + f[k][r] + a[l] * a[r] * a[k]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, f[i][i + n]);
        }
        System.out.println(res);
    }

    static int[] a = new int[210];
    static int[][] f = new int[210][210];
    static int n;

}
