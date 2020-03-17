package Math;

/**
 * 当有n个盘子在A上,先把n-1个盘子放在C盘子上,再把最大的盘子放在B上
 * 再把那n-1个盘子放回B上面
 * 即:f(n)=2f(n-1)+1
 * 盘子数量     移动次数
 * 1           1
 * 2           3
 * 3          3*2+1
 * <p>
 * 不难得出数学通项公式为,f(n)=2^(n-1)
 * 推导过程:f(n-1)=(2^n-1)-1 提出假设
 * 则代入递推式可得:f(n)=2((2^n-1)-1)+1=2^n-2+1=2^n-1
 */
public class 汉诺塔 {
    public static void main(String[] args) {

    }

    public static long han(int n) {
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] * 2 + 1;
        }
        return dp[n - 1];
    }

    public static long haNN(int n) {
        return (long) (Math.pow(2, n) - 1);
    }

    public static void Test() {
        for (int i = 1; i < 32; i++) {
            if (han(i) != haNN(i)) {
                System.out.println(i);
            }
        }
    }
}
