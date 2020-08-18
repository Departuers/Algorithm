package dp.区间dp;

import java.util.Scanner;

/**
 * 给出一个只有() []四种字符组成的字符串，取出一个最长的子序列使得他们满足括号匹配。
 * 样例:
 * ([]])        (答案: 4)取出来([])
 * ([][][)     (答案: 6)  取出来([][])
 * f[i,j]表示[i,j]区间中的最长子序列
 * 如果S[i]和S[j]可以匹配那么f[i,j]=f[i+1][j-1]+2
 * 显然上述,也就是i+1~j-1这个小区间最长匹配+2
 * 如果S[i]和S[j]不能匹配
 * 那么i~j的答案也可以由两个子区间的答案合并而来
 * f[i,j]=max(f[i,j] , f[i,k]+f[k+1,j])
 * 如([])([])从中间分界线,那么它们依然可以匹配,符合要求
 * O(n^3)
 */
public class 括号匹配 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] t = sc.next().toCharArray();
        for (int i = 0; i < t.length; i++) {
            s[i + 1] = t[i];
        }
        int n = t.length;
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                if ((s[l] == '(' && s[r] == ')') || s[l] == '[' && s[r] == ']') {
                    f[l][r] = f[l + 1][r - 1] + 2;
                }//查看头尾是否匹配
                //如([])([])从中间分界线,那么它们依然可以匹配,符合要求
                //枚举小区间,推出大区间最长序列
                for (int k = l; k < r; k++) {
                    f[l][r] = Math.max(f[l][k] + f[k + 1][r], f[l][r]);
                }
            }
        }
        System.out.println(f[1][n]);
    }

    static char[] s = new char[1000];
    static int[][] f = new int[2000][2000];
}
