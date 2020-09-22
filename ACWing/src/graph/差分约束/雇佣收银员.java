package graph.差分约束;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/weixin_45080867/article/details/108041493
 * https://blog.csdn.net/qq_44828887/article/details/107291492
 * 一家超市要每天24小时营业，为了满足营业需求，需要雇佣一大批收银员。
 * 已知不同时间段需要的收银员数量不同，为了能够雇佣尽可能少的人员，从而减少成本，这家超市的经理请你来帮忙出谋划策。
 * 经理为你提供了一个各个时间段收银员最小需求数量的清单R(0),R(1),R(2),…,R(23)。
 * R(0)表示午夜00:00到凌晨01:00的最小需求数量，R(1)表示凌晨01:00到凌晨02:00的最小需求数量，以此类推。
 * 一共有N个合格的申请人申请岗位，第 i 个申请人可以从ti时刻开始连续工作8小时。
 * 收银员之间不存在替换，一定会完整地工作8小时，收银台的数量一定足够。
 * 现在给定你收银员的需求清单，请你计算最少需要雇佣多少名收银员。
 * 输入格式
 * 第一行包含一个不超过20的整数，表示测试数据的组数。
 * 对于每组测试数据，第一行包含24个整数，分别表示R(0),R(1),R(2),…,R(23)。
 * 第二行包含整数N。
 * 接下来N行，每行包含一个整数ti。
 * 输出格式
 * 每组数据输出一个结果，每个结果占一行。
 * 如果没有满足需求的安排，输出“No Solution”。
 * 数据范围
 * 0≤R(0)≤1000,
 * 0≤N≤1000,
 * 0≤ti≤23
 * 首先差分约束结合前缀和,这是最小值,所以用最长路
 * 划分时间段24
 * R0=00:00 - 01:00
 * R1=01:00 - 02:00
 * R2=02:00 - 03:00
 * ...
 * R23=23:00 -00:00
 * 一个人可以连续工作8小时,假如一个人可以从5点开始工作,可以到13:00截止
 * 那么覆盖了R5-R12   因为到13:00他就走了
 * 计算最少收银员.所以用最长路
 * 构造不等式
 * 每一个时刻划分
 * num[1]代表0点来的收银员
 * num[24]代表23点来的人数
 * 1. 0<=Xi<=num[i]
 * 2. X(i-7)+X(i-7)+...+Xi>=Ri   每个时间段要求的人数,转换不等式,发现是一段和
 * 所以用前缀和来做
 * 前缀和S0=0  Si=X1+X2+..+Xi
 * 替换该等式变成:
 * 1.    0<=Si-S(i-1)<=num[i]  前缀和,计算式子  1<=i<=24
 * 2.    由于是前缀和   如果i>=8  则Si-S(i-8)>=Ri
 * 2.1   如果 0<i<=7  有两段和,相加
 *       找规律,如果i=7 则S(7)+S(24)-S(23)>=R7
 *       S(24)-S(23)就是23点上班的人数
 *       如果i=6 则 S(6)+S(24)-S(22)>=R6
 *    ...
 * 2.2   那么S(i)+S(24)-S(i+16)>=Ri
 *       找到规律
 *  最终就是:
 *  1. Si>=S(i-1)+0  由于是前缀和,特判
 *  2. S(i-1)>=Si-num[i]
 *  3. i>=8 Si>=S(i-8)+Ri
 *  4. 0<i<=7  Si>= S(i+16)-S(24)+ri
 *  最后一组有3个变量,不把S(24)当变量,枚举S(24)所有取值0-1000,
 *  最终求所有收银员的和,其实就是S(24)
 *      //体现S24是个定值
 *         //使得S24>=C S24<=C 看做 S(24)<=S(0)+C  同理S(0)<=S(24)-C
 *  超级源点,0号点就是超级源点.
 *  枚举所有取值,合法即有解,
 *  数据范围只有24个点,100条边,
 *  O(2*10^6)
 *  已经ac
 */
public class 雇佣收银员 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        while (T-- != 0) {
            for (int i = 1; i <= 24; i++) {
                r[i] = sc.nextInt();
            }
            n = sc.nextInt();
            Arrays.fill(num, 0);
            for (int i = 0; i < n; i++) {
                int t = sc.nextInt();//时刻
                num[t + 1]++;
            }
            boolean success = false;
            for (int i = 0; i <= 1000; i++) {
                if (spfa(i)) {
                    System.out.println(i);//S(24)
                    success = true;
                    break;
                }
            }
            if (!success) System.out.println("No Solution");
        }
    }

    static int inf = 0x3f3f3f3f;

    static boolean spfa(int c) {
        build(c);
        Arrays.fill(cnt, 0);//
        Arrays.fill(st, false);
        Arrays.fill(dist, -inf);//最长路,初始化为负无穷
        dist[0] = 0;
        q.add(0);
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = h[t]; i != 0; i = ne[i]) {
                int j = e[i];
                if (dist[j] < dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if (cnt[j] > 25) {
                        return false;
                    }
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return true;
    }

    static void build(int c) {
        Arrays.fill(h, 0);
        Arrays.fill(ne, 0);
        idx = 1;
        for (int i = 1; i <= 24; i++) {
            add(i - 1, i, 0);

            add(i, i - 1, -num[i]);
        }
        for (int i = 8; i <= 24; i++) {
            add(i - 8, i, r[i]);
        }
        for (int i = 1; i <= 7; i++) {
            add(i + 16, i, -c + r[i]);
        }
        //体现S24是个定值
        //使得S24>=C S24<=C 看做 S(24)<=S(0)+C  同理S(0)<=S(24)-C
        add(0, 24, c);
        add(24, 0, -c);
        //略显尴尬...
    }

    static int n, m, N = 30, M = 100, idx = 1, T;
    static int[] h = new int[N];
    static int[] w = new int[M];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] r = new int[N];
    static int[] num = new int[N];//24个位置
    static int[] dist = new int[N];
    static int[] cnt = new int[N];
    static boolean[] st = new boolean[N];
    static ArrayDeque<Integer> q = new ArrayDeque<Integer>();

    static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
