package 线性dp.数字三角形模型;

import java.util.Scanner;

/**
 * 先说前提,如果只能往下或者往右走,只有走的步数相同,路线才有可能重合
 * 从左上角走到右下角,走两次f[i1][j1][i2][j2]  每个值只能取一次,怎么走2次最多
 * 从(1,1)(1,1)代表分别走到(i1,j1)(i2,j2)的路径的最大值
 * 这里使用同时走,或者走一个标记一下,
 * 同一个格子不能重复选择:什么时候两条路会有交集呢,都走到同一个格子的时候
 * 两条路走到同样一个格子会有一个什么样的条件呢?两条路线走过的总步数是一样的
 * 只有i1+j1=i2+j2的时候,两条路径的格子才会重合
 * f[k][i1][i2] 其中k代表总步数,
 * 该状态就表示从(1,1)(1,1)代表分别走到(i1,k-i1)(i2,k-i2)路径的最大值
 * <p>
 * 如何看状态是如何转移的,也就是集合如何划分,考虑 last
 * 最后一步,每条路线都能往右或者往下,所以可以划分成2*2=4个部分
 * f[k][i1][i2]
 * 代表第一条路最后一步向下走,第二条路最后一步也是向下走,下面同理
 * 1. 下 下
 * 单独说第一种情况分为2部分:第一部分:(1,1)->(i1-1,j1)->(i1,j1)
 * 第二部分:(1,1)->(i2-1,j2)->(i2,j2)
 * 从(1,1)->(i1-1,j1)和(1,1)->(i2-1,j2)有很多种情况,取max,恰好根据状态的定义就是f[k-1][i1-1][i2-1]
 * 注意是状态定义是2条路的共同的最大和
 * 2. 下 右
 * 3. 右 下
 * 4. 右 右
 * 求max 自然是4种情况取最大值
 * 两条路径不一定有重合,要在重合时,一个格子只算一次
 */
public class 方格取数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int a, b, c;
        while (true) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            if (a == b && b == c && c == 0) break;
            arr[a][b] = c;
        }
        for (int k = 2; k <= N + N; k++) {//左上角(1,1)的k值起码就是2
            for (int i1 = 1; i1 <= N; i1++) {
                for (int i2 = 1; i2 <= N; i2++) {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 >= 1 && j1 <= N && j2 >= 1 && j2 <= N) {//判断顶点是否合法
                        int t = arr[i1][j1];//重合就只添加一个
                        if (i1 != i2) t += arr[i2][j2];//判断是否重合!
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1 - 1][i2 - 1] + t);//下下
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1 - 1][i2] + t);//右下
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1][i2 - 1] + t);//下右
                        dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][i1][i2] + t);//右右
                    }
                }
            }
        }
        System.out.println(dp[N + N][N][N]);
    }

    static int N;
    static int[][] arr = new int[14][14];
    static int[][][] dp = new int[28][14][14];
}
