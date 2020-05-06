package dp.线性dp.LIS模型;

import java.util.Scanner;

/**
 * LIS和LCS的结合
 * 显然属性是max
 * f[i][j]
 * 状态表示:所有由第一个序列的前i个字母和第二个序列的前j个字母
 * 且以b[j]结尾的公共上升子序列
 * <p>
 * 状态计算:f[i][j]
 * 考虑last
 * 如何划分呢,
 * 分成两大部分:
 * 所有不包含a[i]的公共上升子序列
 * 恰好就是f[i-1][j]
 * 所有包含a[i]的最长上升子序列
 * 这个部分可以根据LIS再划分
 * 考虑last
 * 分别为 空,b[1],b[2]...b[j-1]
 */
public class 最长公共上升子序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            B[i] = sc.nextInt();
        }
        //o(n^3)
        int len = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j];
                if (A[i] == B[j]) {
                    for (int k = 1; k < j; k++) {
                        dp[i][j] = 1;//空集
                        if (B[k] < B[j]) {
                            dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
                            //已经找到一个,再往前找接在最长公共上升子序列里面最长
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            len = Math.max(len, dp[N][i]);
        }
        System.out.println(len);


    }

    static int N;
    static int[] A = new int[3005], B = new int[3005];
    static int[][] dp = new int[3005][3005];
    //优化成O(n^2)
    static void youhua() {
        int len = 0;
        for (int i = 1; i <= N; i++) {
            int maxv = 1;
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j];
                if (A[i] == B[j]) {
                    dp[i][j] = Math.max(dp[i][j], maxv);
                }
                if (B[j] > A[i]) {
                    maxv = Math.max(maxv, dp[i][j] + 1);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            len = Math.max(len, dp[N][i]);
        }
        System.out.println(len);
    }
}
