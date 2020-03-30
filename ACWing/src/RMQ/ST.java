package RMQ;

/**
 * https://blog.csdn.net/qq_41311604/article/details/79900893
 * 只给出模板
 * dp[i][j] = min(dp [i][j - 1], dp [i + (1 << j - 1)][j - 1])
 * 由此给出下列代码：
 * void rmq_init()
 * {
 * for(int i=1;i<=N;i++)
 * dp[i][0]=arr[i];//初始化
 * for(int j=1;(1<<j)<=N;j++)
 * for(int i=1;i+(1<<j)-1<=N;i++)
 * dp[i][j]=min(dp[i][j-1],dp[i+(1<<j-1)][j-1]);
 * }
 * 查询
 * int rmq(int l,int r)
 * {
 * int k=log2(r-l+1);
 * return min(dp[l][k],dp[r-(1<<k)+1][k]);
 * }
 */
public class ST {
    public static void main(String[] args) {
        init();
        System.out.println(rmq(1, 2));
        for (int i = 0; i < 1000000; i++) {
            if (1<<i-1!=(1<<(i-1)))
                System.out.println("NO");
        }

    }

    static int N = 6;
    static int[][] dp = new int[N + 1][N + 1];

    static int[] arr = {22, 23, 5, 67, 7, 8};//0不算


    static void init() {
        for (int i = 1; i <= N; i++) {
            dp[i][0] = arr[i - 1];
        }
        for (int j = 1; 1 << j <= N; j++) {
            for (int i = 1; i + (1 << j) - 1 <= N; i++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    static int rmq(int l, int r) {
        int k = (int) (Math.log(r - l + 1) / Math.log(2));
        return Math.min(dp[l][k], dp[r - (1 << k) + 1][k]);
    }
}
