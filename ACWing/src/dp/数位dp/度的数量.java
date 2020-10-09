package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104468664
 * 求给定区间 [X,Y]中满足下列条件的整数个数：这个数恰好等于 K 个互不相等的 B 的整数次幂之和。
 * 例如，设 X=15,Y=20,K=2,B=2，则有且仅有下列三个数满足题意：
 * 17=2^4+2^0
 * 18=2^4+2^1
 * 20=2^4+2^2
 * 输入格式
 * 第一行包含两个整数 X 和 Y，接下来两行包含整数 K 和 B。
 * 输出格式
 * 只包含一个整数，表示满足条件的数的个数。
 * 数据范围
 * 1≤X≤Y≤2^31−1
 * 1≤K≤20
 * 2≤B≤10
 * 输入样例：
 * 15 20
 * 2
 * 2
 * 输出样例：
 * 3
 * 489199 894799999 15 3
 * out 3876
 * 15 20 2 2
 * out
 * 3
 * 题目意思是给定一个区间[X,Y]，求这个区间中有多少个数满足条件，
 * 即这个数的B进制表示中恰好由K个1，并且剩下的位置上的数都是0。
 * 首先，数位DP常用的技巧有：求区间[l,r]中满足条件数的个数，
 * 只需要求0到r中满足条件数的个数s[r]以及0到l-1中满足条件数的个数s[l-1]，
 * 所求数的个数等于s[r] - s[l - 1]，这个技巧类似于前缀和。
 * 也就是说，本题中我们只需要求0到n中有多少个数满足条件即可。
 * <p>
 * 设n的B进制表示为an,an-1,...,ai,...,a1,a0。我们可以从高位向低位遍历这 个B进制数。
 * 要求B进制表示有且恰好只有K个1
 * 当ai = 0时：这个位置只能放0，所以可以跳过；
 * 当ai > 0时：
 * 如果ai > 1，那么第i位可以放0也可以放1，并且后面的位置不管怎么取都不会超过n了，
 * 设last为已经放置1的个数，
 * 1. 则在ai处放0时，只需要在后面的位置选K - last个放1即可，即一共C(i,K-last)种情况。
 * 2. 在ai处放1时，后面i位还需要放置K - last - 1个1，一共C(i,K-last-1)种放法。
 * 如果ai = 1，这个位置放0是可以的，也就是需要在后面的位置继续放K - last个1，
 * 也可以放1，但是后面的位置就不能随便放数了，因为可能超过n，
 * 所以放1就暂且先将last++，继续考察下一位。
 * <p>
 * 边界情况：某时刻last超过了K，就可以退出枚举的循环了。
 * 如果n恰好是K个1和剩下的0组成的，则枚举到最后一位时，方案数应该加1。
 * 本题涉及到组合数，可以先预处理出来需要的组合数，
 * 用递推式C(n,m) = C(n-1,m-1) + C(n-1,m)即可，表示第n个物品选与不选的两种情况。
 */
public class 度的数量 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();
        r = sc.nextInt();
        K = sc.nextInt();
        B = sc.nextInt();
        System.out.println(dp(r) - dp(l - 1));
    }

    //求出从0~n中求出满足要求的数
    static int dp(int n) {
        if (n == 0) return 0;
        ArrayList<Integer> num = new ArrayList<Integer>();
        //把数字转换成B进制,每一位都填上有且只有K个1,其余全是0
        while (n != 0) {
            num.add(n % B);
            n /= B;
        }
        int res = 0, last = 0;//这里last存的是前面有多少个1,含义不同题目不一样
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            //每一位只能填0或者1
            if (x != 0) {//求左边分支,如果这一位能填
                res += f[i][K - last];
                if (x > 1) {//枚举左边分支
                    if (K - last - 1 >= 0) res += f[i][K - last - 1];//这一位填1
                    break;//由于只能填1或0,若x>1左边分支不存在
                } else {//x==1
                    last++;//进入右边分支,ai填上1
                    if (last > K) break;//
                }
            }
            if (i == 0 && last == K) res++;//最右侧分支的方案,也就是n本身合不合法
        }
        return res;
    }

    /**
     * 求组合数
     * 状态定义:f[i,j]前i个数可选,选j个有多少种组合方案
     * 状态划分2个:j个数已经选完,那么f[i-1][j]
     * j个数选的是第i个元素,f[i-1][j-1]
     */
    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) f[i][j] = 1;
                else f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
            }
        }
    }

    static int l, r, K, B, N = 35;
    static int[][] f = new int[N][N];
}
