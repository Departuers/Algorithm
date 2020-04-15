package RMQ;

/**
 * https://blog.csdn.net/sclong0218/article/details/97036282
 * ST表本质是动态规划算法,可以快速求出区间最值,
 * 预处理时间复杂度O(n log n)查询O(1) 离线算法,不支持在线
 * 倍增的产物
 * 预处理:
 * 状态表示:f[i,j] 表示数列A从第i个数起,连续2^j个数的最值
 * 递推边界是f[i,0]=A[i]
 * 2^0=1所以第一列存的是值本身
 * 递推时:我们把子区间成倍增加,即长度为2^j的子区间的最值,是左右两半2^j-1的子区间的最值
 * 即:f[i,j]=max(f[i-1,j-1] , f[i+2^j-1][j-1])
 * 例如:A数组为3 2 4 5 6 8 1 2 9 7
 * f[1,0]表示从第一个数开始,长度为2^0=1的最大值,也就是3这个数
 * f[2,0]表示从第二个数开始,长度为2^0=1的最大值,也就是2这个数
 * 同理f[2,2]表示从第二个数开始,长度为2^2=4的最大值,(2,4,5,6)结果为6
 * f[3,2]表示从第三个数开始,长度为2^2=4的最大值,(4,5,6,8)结果为8
 * f[i,j]分成两段计算,i~i+2^(j-1)-1这一段,和i+2^(j-1)~i+2^j这一段(两段的长度都是2^(j-1))
 * 从i开始,2^(j-1)+2^(j-1)=2^j
 * 区间范围肯定都是符合状态定义的
 * 状态转移方程:F[i,j]=max(F[i,j−1],F[i+2^(j−1),j−1])
 */
public class ST表最优写法 {
    public static void main(String[] args) {
        n = 7;
        init();
        for (int i = 1; i <= 7; i++) {
            System.out.println(query(1, i));
        }
    }

    static int n, m;
    static int[] log;
    static int[][] st;
    static int[] arr = {22, 23, 8, 67, 7, 7, 2};

    static void init() {
        log = new int[n + 1];
        log[1] = 0;
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        st = new int[n + 2][log[n] + 1];
        for (int i = 1; i <= n; i++) {
            st[i][0] = arr[i - 1];
        }
        for (int j = 1; 1 << j <= n; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << j - 1)][j - 1]);
            }
        }
    }

    static int query(int l, int r) {
        int k = log[r - l + 1];
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }
}