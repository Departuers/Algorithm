package dp.状态压缩dp;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104206827
 * 农夫约翰的土地由M*N个小方格组成，现在他要在土地里种植玉米。
 * 非常遗憾，部分土地是不育的，无法种植。
 * 而且，相邻的土地不能同时种植玉米，也就是说种植玉米的所有方格之间都不会有公共边缘。
 * 现在给定土地的大小，请你求出共有多少种种植方法。
 * 土地上什么都不种也算一种方法。
 * 输入格式
 * 第1行包含两个整数M和N。
 * 第2..M+1行：每行包含N个整数0或1，用来描述整个土地的状况，1表示该块土地肥沃，0表示该块土地不育。
 * 输出格式
 * 输出总种植方法对100000000取模后的值。
 * 数据范围
 * 1≤M,N≤12
 * 输入样例：
 * 2 3
 * 1 1 1
 * 0 1 0
 * 输出样例：
 * 9
 * 样例:种0块1种,种1块4种,种2块3种,种3块1种,一共9种
 * 本题要求种植玉米的方案数，没有种多少的限制。
 * 状态表示f[i][j]表示种了前i行玉米田且第i行的状态为j的方案数。
 * 题目给出了两个限制，第一个是相邻的土地不能同时种玉米，
 * 可以翻译为同一行不存在相邻的两个1，即状态st & (st >> 1)为0；
 * 并且相邻两行相同的列不能同时为1，即对第i-1行的状态s和第i行的状态t有s & t为0.
 * 第二个限制是不育的土地不能种植，可以将各行不育的土地坐标读入向量，
 * 然后在枚举状态时判断下是否合法即可，更方便的办法是：
 * <p>
 * for(int i = 1;i <= m;i++){
 * for(int j = n - 1;j >= 0;j--){
 * cin>>x;
 * if(!x)  a[i] += 1 << j;
 * }
 * }
 * <p>
 * 将一行中不肥沃的土地同样存储为一个状态，比如第一块和第四块不肥沃，
 * 就可以存储为1001，下次在枚举状态时，直接与不合法的状态与一下，
 * 结果不为0就不合法。每一行的合法方案数都是从上一行的合法方案数中转移过来的，
 * 所以状态转移方程为f[i][j] = f[i][j] + f[i-1][k]，
 * 其中k为上一行能够转化到第i行的合法状态。
 */
public class 种玉米 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int x = 0;
        //二进制存储地图
        for (int i = 1; i <= m; i++) {//有m行
            for (int j = n - 1; j >= 0; j--) {
                //高位开始读取
                x = sc.nextInt();
                if (x == 0) a[i] += 1 << j;
            }
        }
        //比如第一块和第四块不肥沃，就可以存储为1001
        f[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                if (judge(j) && (j & a[i]) == 0) {
                    for (int k = 0; k < 1 << n; k++) {
                        if (judge(k) && (k & a[i - 1]) == 0 && (j & k) == 0)
                            f[i][j] = (f[i][j] + f[i - 1][k]) % (int) 1e8;

                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 1 << n; i++) {
            res = (res + f[m][i]) % (int) 1e8;
        }
        System.out.println(res);
    }

    //如果没有相邻的两个一为true
    static boolean judge(int n) {
        return (n & (n >> 1)) == 0;
    }

    static int[] a = new int[15];
    static int m, n;
    static int[][] f = new int[15][1 << 15];
}
