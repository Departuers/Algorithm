package dp.背包模型;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * 岩石怪物杜达生活在魔法森林中，他在午餐时收集了N块能量石准备开吃。
 * 由于他的嘴很小，所以一次只能吃一块能量石。能量石很硬，吃完需要花不少时间。
 * 吃完第 i 块能量石需要花费的时间为Si秒。杜达靠吃能量石来获取能量。
 * 不同的能量石包含的能量可能不同。
 * 此外，能量石会随着时间流逝逐渐失去能量。第 i 块能量石最初包含Ei单位的能量，
 * 并且每秒将失去Li单位的能量。
 * 当杜达开始吃一块能量石时，他就会立即获得该能量石所含的全部能量
 * （无论实际吃完该石头需要多少时间）。
 * 能量石中包含的能量最多降低至0。
 * 请问杜达吃完所有石头可以获得的最大能量是多少？
 * 输入格式
 * 第一行包含整数T，表示共有T组测试数据。
 * 每组数据第一行包含整数N，表示能量石的数量。
 * 接下来N行，每行包含三个整数Si,Ei,Li。
 * 输出格式
 * 每组数据输出一个结果，每个结果占一行。
 * 结果表示为“Case #x: y”，其中x是组别编号（从1开始），y是可以获得的最大能量值。
 * 数据范围
 * 1≤T≤10,
 * 1≤N≤100,
 * 1≤Si≤100,
 * 1≤Ei≤10^5,
 * 0≤Li≤10^5
 * 输入样例：
 * 3
 * 4
 * 20 10 1
 * 5 30 5
 * 100 30 1
 * 5 80 60
 * 3
 * 10 4 1000
 * 10 3 1000
 * 10 8 1000
 * 2
 * 12 300 50
 * 5 200 0
 * 输出样例：
 * Case #1: 105
 * Case #2: 8
 * Case #3: 500
 * 贪心+01背包
 * Si吃掉它需要Si秒, Ei最初的能量, Li表示从开始吃每秒损失Li的能量
 * 所有不同吃法的最优解
 * 选择吃哪些.不用吃变成0的能量石,按照什么样的顺序来吃
 * f[i,j]所有从前i块能量石中选,写总体积恰好是j的方案
 * 属性max
 * f[i,j]=max(f[i-1,j],f[i-1,j-s]+e-(j-s)*L
 * 所以优化空间为1维,且
 * <p>
 * 由于吃能量石的时间会影响能量石的能量，按照什么顺序去吃
 * 设有两个能量石，先吃能量石1，获得的能量是e1 + e2 - l2 * s1；
 * 先吃能量石2，获得的能量是e2 + e1 - l1 * s2；
 * 要使e1 + e2 - l2 * s1 > e2 + e1 - l1 * s2，
 * 只需要l2 * s1 < l1*s2即可，即按照s / l自小到大的顺序吃能量石即可。
 * 如果要求吃完所有的能量石，则只需要按照贪心的顺序去吃即可。
 * 但是题目加了个条件能量石的能量最多降低到0，
 * 这意味着吃能量为0的石头只需要放到最后去吃，相当于没吃该石头，
 * 该吃哪些石头不该吃哪些石头只用贪心很难去解决，因为判断某个石头是否此时需要吃，
 * 不仅要考虑其能量是否大于0，还应该考虑吃了它加的能量是否比吃它的时间内剩余石头损耗的能量多，
 * 剩余石头哪些要吃还未确定，该不该吃这块石头就没有准确的判断依据。
 * 将能量石按照上面的原则排序后，再决定每块石头吃与不吃，相当于01背包问题。
 * f[i][j]表示在前i块石头中吃了j时间能够获得的最大能量，这里的j是恰好吃了j时间，
 * 而不是在j时间内，因为计算能量的损耗需要准确地时间。
 * 状态转移方程为f[i][j] = max(f[i-1][j],f[i-1][j-s]+e-(j-s)*l。边界条件是f[0][j] = 负无穷，j>0，
 * 表示不合法的方案，f[0][0] = 0。最后求出所有状态后再遍历下最后一层状态求出可获得的能量的最大值即可。
 */
public class 能量石 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int dd = 1; dd <= T; dd++) {
            int m = 0;
             n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                int l = sc.nextInt();
                stone[j] = new node(s, e, l);
                m += s;
            }
            Arrays.sort(stone, 0, n);
            Arrays.fill(f, (int) -1e9);
            f[0] = 0;
            for (int i = 0; i < n; i++) {
                int s = stone[i].s, e = stone[i].e, l = stone[i].l;
                for (int j = m; j >= s; j--) {
                    f[j] = Math.max(f[j], f[j - s] + e - (j - s) * l);
                }
            }
            int res = 0;
            for (int i = 0; i <= m; i++) {
                res = Math.max(res, f[i]);
            }
            System.out.printf("Case #%d:%d\n", dd, res);
        }
    }

    static int N = 11000, T, n;
    static node[] stone = new node[N];
    static int[] f = new int[N];

    static class node implements Comparable<node> {
        int s, e, l;//消耗时间,获得能量

        public node(int s, int e, int l) {
            this.s = s;
            this.e = e;
            this.l = l;
        }

        @Override
        public int compareTo(node o) {
            return s * o.l - l * o.s;
        }
    }
}
