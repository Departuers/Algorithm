package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Windy 定义了一种 Windy 数：不含前导零且相邻两个数字之差至少为 2 的正整数被称为 Windy数。
 * Windy 想知道，在 A 和 B 之间，包括 A 和 B，总共有多少个 Windy 数？
 * 输入格式
 * 共一行，包含两个整数 A 和 B。
 * 输出格式
 * 输出一个整数，表示答案。
 * 数据范围
 * 1≤A≤B≤2×10^9
 * 输入样例1：
 * 1 10
 * 输出样例1：
 * 9
 * 输入样例2：
 * 25 50
 * 输出样例2：
 * 20
 * 不包含前导0!
 */
public class Windy数 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        System.out.println(dp(B) - dp(A - 1));
    }

    /**
     * f[i,j]表示,最高位填j,且一共只有i位且满足windy数的所有集合
     * 属性;count,计数
     * 状态计算划分,f[i,j]
     * 考虑last,找到一个分界线,枚举第二位
     * j _ _ _ _... 第一位填j,第二位可以选填0~9,且满足|j-k|>=2
     * 假设第2位填k,组成不降数,那么变成最高位为k,且只用填i-1位,为f[i-1,k]
     * f[i,j]=f[i-1,k] k的取为0~9且|j-k|>=2求和,值
     */
    private static void init() {
        for (int i = 0; i <= 9; i++) {
            f[1][i] = 1;
        }
        for (int i = 2; i < N; i++) {//从数字位数为2开始枚举
            for (int j = 0; j <= 9; j++) {//最高位为j
                for (int k = 0; k <= 9; k++) {//枚举倒数第二位,0~9可选,但满足绝对值之差大于等于2
                    if (Math.abs(j - k) >= 2) f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    static int dp(int n) {
        if (n == 0) return 0;//0不是windy数
        ArrayList<Integer> num = new ArrayList<Integer>();
        while (n != 0) {
            num.add(n % 10);
            n /= 10;
        }
        int res = 0, last = -2;//last要与0~9都满足相差大于等于-2,可选范围为last<=-2 last>=11
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            for (int j = i == num.size() - 1 ? 1 : 0; j < x; j++) {//如果是第一位,从1开始枚举否则从0开始
                if (Math.abs(j - last) >= 2) {
                    res += f[i + 1][j];
                }
            }
            if (Math.abs(x - last) >= 2) last = x;
            else break;
            if (i == 0) res++;//走到了最后
        }
        //枚举有前导0的数
        for (int i = 1; i < num.size(); i++) {
            for (int j = 1; j <= 9; j++) {
                res += f[i][j];
            }
        }
        return res;
    }

    static int A, B, N = 11;
    static int[][] f = new int[N][10];
}