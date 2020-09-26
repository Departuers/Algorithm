package dp.线性dp.LIS模型;

import java.util.Scanner;

/**
 * 找出一个子序列,两个相邻数不能相同,开始单调上升,一旦选择下降之后单调下降,
 * 并且只能按照编号递增的顺序进行选择,不然可以随便跳...
 * 求最长的序列是多长?
 * 每次求以第k个元素结尾的最长上升子序列 f[i]
 * 再求出以第k个元素结尾的最长下降子序列 g[i]
 * 从后面往前找上升子序列就是下降子序列
 * 通过枚举上升下降之间的分界线,来找到最大值
 * f[i]+g[i]-1因为第k个被算了2次,结合dp语义可以得出
 * O(n^2*2)
 */
public class 登山 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            g[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    g[i] = Math.max(g[i], g[j] + 1);
                }
            }
        }

        for (int i = N; i >= 1; i--) {
            f[i] = 1;
            for (int j = N; j > i; j--) {
                if (arr[i] > arr[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, g[i] + f[i] - 1);
        }
        System.out.println(res);
    }

    static int[] g = new int[1004];
    static int[] f = new int[1004];
    static int[] arr = new int[1004];
    static int N;
}
