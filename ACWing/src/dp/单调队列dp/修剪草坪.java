package dp.单调队列dp;

import java.util.Scanner;

/**
 * 在一年前赢得了小镇的最佳草坪比赛后，FJ 变得很懒，
 * 再也没有修剪过草坪。
 * 现在，新一轮的最佳草坪比赛又开始了，FJ 希望能够再次夺冠。
 * 然而，FJ 的草坪非常脏乱，
 * 因此，FJ 只能够让他的奶牛来完成这项工作。
 * FJ 有 N 只排成一排的奶牛，编号为 1 到 N。
 * 每只奶牛的效率是不同的，奶牛 i 的效率为 Ei。
 * 编号相邻的奶牛们很熟悉，如果 FJ 安排超过 K 只编号连续的奶牛，
 * 那么这些奶牛就会罢工去开派对。
 * 因此，现在 FJ 需要你的帮助，找到最合理的安排方案并计算 FJ 可以得到的最大效率。
 * 注意，方案需满足不能包含超过 K 只编号连续的奶牛。
 * 输入格式
 * 第一行：空格隔开的两个整数 N 和 K；
 * 第二到 N+1 行：第 i+1 行有一个整数 Ei。
 * 输出格式
 * 共一行，包含一个数值，表示 FJ 可以得到的最大的效率值。
 * 数据范围
 * 1≤N≤10^5
 * 0≤Ei≤10^9
 * 输入样例：
 * 5 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 输出样例：
 * 12
 * 样例解释
 * FJ 有 5 只奶牛，效率分别为 1、2、3、4、5。
 * FJ 希望选取的奶牛效率总和最大，但是他不能选取超过 2 只连续的奶牛。
 * 因此可以选择第三只以外的其他奶牛，总的效率为 1 + 2 + 4 + 5 = 12。
 * 选一些奶牛使得总价值最大,但不能包含编号连续超过k的奶牛
 *
 * f[i]表示从前i头牛中选,且合法的所有方案
 * 属性:max
 * f[i]第i头牛选还是不选作为划分依据
 * 不选上第i头,从前i-1头选的合法方案,根据状态定义为f[i-1]
 *
 * 选上第i头,继续划分,包括i往前连续长度不超过K的最大,长度为 1 2 ...K
 * f[i]=max{ f[i-j-1] -s[i-j] |1<=j<=k }+s[i]
 * 对于包含i往前连续不超过k,选上就是s[i]-s[i-j] (1<=j<=k),再根据状态定义加上之前的最小值f[i-j-1]
 */
public class 修剪草坪 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            s[i] = sc.nextInt() + s[i - 1];
        }
        int hh = 0, tt = 0;
        for (int i = 1; i <= n; i++) {
            if (q[hh] < i - m) hh++;
            f[i] = Math.max(f[i - 1], g(q[hh]) + s[i]);
            while (hh <= tt && g(q[tt]) <= g(i)) tt--;
            q[++tt] = i;
        }
        System.out.println(f[n]);

    }

    static int g(int i) {
        if (i - 1 < 0) return 0;
        return f[i - 1] - s[i];
    }

    static int n, m, N = (int) (1e5 + 10);
    static int[] s = new int[N];
    static int[] q = new int[N];
    static int[] f = new int[N];

}
