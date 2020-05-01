package greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 有 n 个人排队到 1 个水龙头处打水，第 i 个人装满水桶所需的时间是 ti，
 * 请问如何安排他们的打水顺序才能使所有人的等待时间之和最小？
 * 输入格式
 * 第一行包含整数 n。第二行包含 n个整数，其中第 i 个整数表示第 i 个人装满水桶所花费的时间 ti。
 * 输出格式
 * 输出一个整数，表示最小的等待时间之和。
 * 数据范围
 * 1≤n≤10^5,
 * 1≤ti≤10^4
 * 输入样例：
 * 7
 * 3 6 1 4 2 5 7
 * 输出样例：
 * 56
 * 本题是要求排队问题的最小等待时间，也就是使用操作系统里常用的SJF算法，
 * 即短作业优先算法可以使得总的等待时间最小。在任务只有两个的时候，
 * 完成任务一的时间t1小于完成任务二的时间t2，如果先完成t1，总的等待时间是t1，先完成t2
 * ，总的等待时间是t2，t1 < t2，所以短作业优先得到的等待时间最小。
 * 一般的，如果按照完成作业时间自小到大的顺序排队，
 * 则总的等待时间是T(n) = (n-1)t1 + (n-2)t2 + ... + tn-1
 * 交换任意两个相邻任务的执行顺序，例如交换ti和ti-1，ti前面的系数是k，
 * ti-1前面的系数是k + 1，
 * 即T（n） = (n-1)t1 + (n-2)t2 + ...+（k+1）ti-1 + kti + ... + tn-1 ，
 * 交换后T1（n） = (n-1)t1 + (n-2)t2 + ...+（k+1）ti + kti-1 + ... + tn-1，T1（n） - T（n） = ti - ti-1 > 0.
 * 故改变相邻的两个执行顺序会导致总的等待时间增加，也就是说，SJF算法的总等待时间最小。
 */
public class 排队打水 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a, 0, n);
        int s = 0;
        long sum = 0;
        /**
         * 显然a[]是升序的
         * 第1个需要等待0
         * 第2个需要等待a[0]
         * 第3个需要等待a[0]+a[1]
         * 第4个需要等待a[0]+a[1]+a[2]
         * ...
         * 第i个需要∑ a[i] i=0->i-2   i从0开始到i-2
         */
        for (int i = 0; i < n; i++) {
            sum += s;
            s += a[i];
        }
        System.out.println(sum);
    }

    static int[] a = new int[100005];
    static int n;
}
