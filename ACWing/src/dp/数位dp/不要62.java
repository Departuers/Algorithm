package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104502932
 * 杭州交通管理局经常会扩充一些的士车牌照，新近出来一个好消息，以后上牌照，不再含有不吉利的数字了，
 * 这样一来，就可以消除个别的士司机和乘客的心理障碍，更安全地服务大众。
 * 不吉利的数字为所有含有 4 或 62 的号码。例如：62315,73418,88914 都属于不吉利号码。
 * 但是，61152 虽然含有 6 和 2，但不是 连号，所以不属于不吉利数字之列。
 * 你的任务是，对于每次给出的一个牌照号区间 [n,m]，
 * 推断出交管局今后又要实际上给多少辆新的士车上牌照了。
 * 输入格式
 * 输入包含多组测试数据，每组数据占一行。
 * 每组数据包含一个整数对 n 和 m。
 * 当输入一行为“0 0”时，表示输入结束。
 * 输出格式
 * 对于每个整数对，输出一个不含有不吉利数字的统计个数，该数值占一行位置。
 * 数据范围
 * 1≤n≤m≤10^9
 * 输入样例：
 * 1 100
 * 0 0
 * 输出样例：
 * 80
 * 不能有4,不能有连续的62
 */
public class 不要62 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        r = sc.nextInt();
        System.out.println(dp(r) - dp(l - 1));
    }

    static int l, r, N = 9;
    static int[][] f = new int[N][10];


    /**
     * 树的左侧分支用dp预处理
     * f[i,j]表示最高位是j,一共有i位的不包含4或者62的集合
     * 属性;count,计数
     * 状态计算划分,f[i,j]
     * 考虑last,找到一个分界线,最高位为j,根据次高位来划分,
     * j _ _ _ _... 第一位填j,第二位可以选填j,j+1,j+2...9
     * 假设第2位填k,满足下面两个条件,那么变成最高位为k,且只用填i-1位,恰好为f[i-1,k]
     * 满足条件
     * 1. j和k不等于4
     * 2. jk不等于62
     * f[i,j]=f[i-1,k] k的取为j<=k<=9求和,值
     */
    static void init() {
        for (int i = 0; i <= 9; i++) {
            if (i != 4)
                f[1][i] = 1;
        }
        for (int i = 2; i < N; i++) {//一共只有i位
            for (int j = 0; j <= 9; j++) {//最高位填j
                if (j != 4)
                    for (int k = 0; k <= 9; k++) {//枚举第二位
                        if (k == 4 || (j == 6 && k == 2)) continue;
                        f[i][j] += f[i - 1][k];
                    }
            }
        }
    }

    static int dp(int n) {
        if (n == 0) return 1;//0既不包含4也不包含62
        ArrayList<Integer> num = new ArrayList<Integer>();
        while (n != 0) {
            num.add(n % 10);
            n /= 10;
        }

        int res = 0, last = 0;//last存储前一位是几
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            for (int j = 0; j < x; j++) {//枚举左边分支0-(an)-1
                if (j == 4 || (last == 6 && j == 2)) continue;
                res += f[i + 1][j];
            }
            if (x == 4 || (last == 6 && x == 2)) break;//当前填原数第i位上的值
            // 上一位数字是6,这一位是2,break
            last = x;
            if (i == 0) res++;
        }
        return res;
    }
}
