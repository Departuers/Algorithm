package RMQ;

import java.util.Random;

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
        for (int z = 20; z < 20000; z++) {
            N = z;
            arr = ran(N);
            init();
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    if (rmq(i, j) != rmqq(i, j))
                        System.out.println("No");
                }
            }
        }


    }

    static int[] ran(int a) {
        Random r = new Random();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = r.nextInt(500);
        }
        return arr;
    }

    static int N = 7;
    static int[][] st;
    static int[] log;
    static int[] arr = {22, 23, 8, 67, 7, 7, 2};

    static void init() {
        log = new int[N + 1];
        log[1] = 0;
        for (int i = 2; i <= N; i++) {
            log[i] = log[i / 2] + 1;
        }
        st = new int[N + 2][log[N] + 2];
        for (int i = 1; i <= N; i++) {
            st[i][0] = arr[i - 1];
        }
        for (int j = 1; 1 << j <= N; j++) {
            for (int i = 1; i + (1 << j) - 1 <= N; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    static void init2() {
        log = new int[N + 2];
        log[1] = 0;
        for (int i = 2; i <= N; i++) {
            log[i] = log[i / 2] + 1;
        }
        st = new int[N + 2][log[N] + 2];
        for (int i = 1; i <= N; i++) {
            st[i][0] = arr[i - 1];
        }
        for (int j = 1; 1 << j <= N; j++) {
            for (int i = 1; i + (1 << j) - 1 <= N; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    static int rmq(int l, int r) {
        int k = (int) (Math.log(r - l + 1) / Math.log(2));
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }

    static int rmqq(int l, int r) {
        int k = log[r - l + 1];
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }
}