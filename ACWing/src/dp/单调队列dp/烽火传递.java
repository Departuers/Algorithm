package dp.单调队列dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 烽火台是重要的军事防御设施，一般建在交通要道或险要处。
 * 一旦有军情发生，则白天用浓烟，晚上有火光传递军情。
 * 在某两个城市之间有 n 座烽火台，每个烽火台发出信号都有一定的代价。
 * 为了使情报准确传递，在连续 m 个烽火台中至少要有一个发出信号。
 * 现在输入 n,m和每个烽火台的代价，请计算在两城市之间准确传递情报所需花费的总代价最少为多少。
 * 输入格式
 * 第一行是两个整数 n,m，具体含义见题目描述；
 * 第二行 n 个整数表示每个烽火台的代价 ai。
 * 输出格式
 * 输出仅一个整数，表示最小代价。
 * 数据范围
 * 1≤n,m≤2×10^5,
 * 0≤ai≤1000
 * 输入样例：
 * 5 3
 * 1 2 5 6 2
 * 输出样例：
 * 4
 * https://blog.csdn.net/qq_30277239/article/details/104580927
 * 所有连续m个烽火台中其中必须至少要有一个发射信号
 * 等价于,任意两个烽火台之间的距离不能大于等于m个烽火台
 * <p>
 * f[i]表示前1~i且点燃第i个烽火台,
 * 属性:最小代价,min
 * f[i]找倒数第2个需要点燃的烽火台, i-1 i-2...i-m+1 i-m ,
 * 如果选i-m-1,那么[i-1,i-m-1]构成连续m个烽火台不点燃,状态非法
 * f[i]=min(f[j] | i-m<=j<i )+w[i] 单调队列优化
 * 本题属于单调队列优化DP的简单问题，
 * 状态表示：f[i]表示前i个烽火台中第i个烽火台发出了信号的最少代价。
 * 当然也可以使用状态机去解决，这里暂且用基本的线性DP的解法去解决，
 * 则状态转移方程为f[i] = min(f[j]) + w[i]，其中i - m <= j < i。
 * 直接写时间复杂度是O（mn），显然会超时，由于f[i]最多仅用到了前面的m个状态，
 * 所以可以使用单调队列优化，用单调队列求出区间长度是m的窗口中f的最小值。
 * 单调队列的具体实现不再赘述，只需要注意两点即可。
 * 第一点，连续m个烽火台中至少有一个要发出信号，需要仔细的理解这句话，
 * 比如m = 3时，烽火台1 2 3 4 5 6，如果6发出信号，那么4 5 6这三个连续的烽火台就有了可以发出信号的烽火，
 * 意味着上一个发出信号的最早可以是3，也就是枚举时i = 6，队头元素最小可以是3，
 * 即i - q[hh] > m时才需要出队头，不需要加等号，这里容易出错。
 * 第二点就是单调队列实现步骤中更新f[i]语句的位置，由于f[i]需要放进队列中，
 * 所以需要先计算完f[i]才能够再与队尾元素比较来决定f[i]在队列中的最终位置。具体实现见代码：
 * <p>
 * f[i]表示只看前i个烽火台,并且点燃第i个烽火台的所有方案
 * 属性min
 * 划分依据:找倒数第二个, f[i]找倒数第2个, i-1 i-2...i-m+1 i-m
 * f[i]=min(f[j] | i-m <=j <i)+w[i]
 * 用单调队列优化
 */
public class 烽火传递 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
        }
        ydp();
        f();
    }

    //单调队列dp
    static void ydp() {

        int hh = 0, tt = 0;//tt=0代表队列中有一个元素0
        int[] q = new int[(int) (2e5 + 10)];
        for (int i = 1; i <= n; i++) {
            if (i - q[hh] > m) hh++;
            f[i] = f[q[hh]] + a[i];
            while (hh <= tt && f[i] <= f[q[tt]]) tt--;
            q[++tt] = i;
            System.out.print(f[i] + " ");
        }
        System.out.println();
        int res = (int) 1e9;
        //从最后m个烽火台里选
        for (int i = n - m + 1; i <= n; i++) {
            res = Math.min(res, f[i]);
        }
        System.out.println(res);
    }

    //朴素dp
    public static void f() {
        for (int i = 1; i <= n; i++) {
            if (i <= m) {
                f[i] = a[i];
            } else {
                int t = f[i - 1];
                for (int j = i - m; j < i - 1; j++) {
                    t = Math.min(f[j], t);
                }
                f[i] = t + a[i];
            }
        }
        System.out.println(Arrays.toString(f));
        System.out.println(f[n]);
    }

    static int[] f = new int[(int) (2e5 + 10)];

    static int[] a = new int[(int) (2e5 + 10)];
    static int n, m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }
}
