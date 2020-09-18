package DFS.剪枝;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104813141
 * 翰翰和达达饲养了N只小猫，这天，小猫们要去爬山。
 * 经历了千辛万苦，小猫们终于爬上了山顶，但是疲倦的它们再也不想徒步走下山了（呜咕>_<）。
 * 翰翰和达达只好花钱让它们坐索道下山。
 * 索道上的缆车最大承重量为W，而N只小猫的重量分别是C1、C2……CN。
 * 当然，每辆缆车上的小猫的重量之和不能超过W。
 * 每租用一辆缆车，翰翰和达达就要付1美元，所以他们想知道，
 * 最少需要付多少美元才能把这N只小猫都运送下山？
 * 输入格式
 * 第1行：包含两个用空格隔开的整数，N和W。
 * 第2..N+1行：每行一个整数，其中第i+1行的整数表示第i只小猫的重量CiCi。
 * 输出格式
 * 输出一个整数，表示最少需要多少美元，也就是最少需要多少辆缆车。
 * 数据范围
 * 1≤N≤18,
 * 1≤Ci≤W≤10^8
 * 输入样例：
 * 5 1996
 * 1
 * 2
 * 1994
 * 12
 * 29
 * 输出样例：
 * 2
 * 分析：
 * 本题与上一题都是求满足一定条件下的最小分组数，整体思路一致，只是多了一些剪枝。
 * 本题的搜索过程为：搜索第u只小猫时，先尝试把它放入现有的缆车中（如果放得下），
 * 最后尝试新开一个缆车放进猫，搜索完所有的放猫组合，找出最优解。很明显的剪枝是最优性剪枝，
 * 如果前面有个方案只花费了k辆缆车，后面搜索过程中枚举到第k辆车时就不用继续搜索了，
 * 因为不可能更新最优解。可行性剪枝就是加入当前猫时重量超过w的情况就不再枚举，
 * 比较不容易想到的是优化搜索顺序，由于最优性剪枝的存在，我们希望尽可能的先枚举缆车数少的情况，
 * 如果有一种方案只用了2辆车，就可以剪掉后面所有大于等于2的方案数了，
 * 如果我们知道怎样枚举花的缆车是最少的，就不用再去搜索了，所以我们只要用一种优于直接搜索的搜索顺序即可。
 * 比如有四只猫，重量分别为2 4 6 8，如果我们按照这样的顺序枚举，最先搜索的分支是第一辆车放2 4，
 * 第二三辆车放6和8，最先搜到的解是3辆；如果我们按照从大到小的顺序去搜索，也就是8 6 4 2的顺序，
 * 首先前两辆车分别放8和6，第三只猫放置第二辆车上，第四只猫放在第一辆车上，这样就只花了两辆车了，
 * 所以按照从大到小的重量去枚举猫，得到的解比较小，有利于最优性剪枝，
 * 这个优化搜索顺序的方法差不多可以减少三倍的dfs时间。
 *
 * 当然，本题数据范围小一方面是可以用暴搜做，另一方面也可以用状态压缩DP去做，
 * f[i][j]表示放置小猫状态为j时第i辆车已经消耗重量的最小值，
 *状态转移方程为
 * f[i][j  | 1<< k] = min(f[i][j] + w[k]),f[i][j] + w[k] <= w。f[i+1][j | 1 << k] = min(w[k]),f[i][j] + w[k] > w时，
 * 放不下就再开一辆。鉴于状压的思路比较复杂且未必优于剪枝过后的dfs，这里就只用dfs去实现本题了。
 */
public class 运输小猫 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        Arrays.sort(w, 0, n, new Comparator<Integer>() {
            @Override
            public int compare(Integer t2, Integer t1) {
                return t1 - t2;
            }
        });//从大到小排序,优先放重的,可以优化搜索顺序,使得分支变少
//        dfs(0, 0);
        f(0);

        System.out.println(ans);
    }

    /**
     * @param u 走到第几个小猫,下标
     * @param k 当前是第几辆车
     */
    static void dfs(int u, int k) {
        if (k >= ans) return;
        if (u == n) {
            ans = k;
            return;
        }
        for (int i = 0; i < k; i++) {
            if (sum[i] + w[u] <= m) {
                sum[i] += w[u];
                dfs(u + 1, k);
                sum[i] -= w[u];
            }
        }
        //i的取值是0~k-1,所以下一次k就是下一辆车
        sum[k] = w[u];
        dfs(u + 1, k + 1);
        sum[k] = 0;
    }

    /**
     * 外部搜索,len是代表第几辆车,
     *
     * @param u
     */
    static void f(int u) {
        if (len >= ans) return;//最优性剪枝
        if (u == n) {
            ans = len;
            System.out.println(Arrays.toString(car));//每辆车放多少个
            return;
        }
        for (int i = 0; i < len; i++) {
            if (car[i] + w[u] <= m) {
                car[i] += w[u];
                f(u + 1);
                car[i] -= w[u];
            }
        }
        car[len++] = w[u];
        f(u + 1);
        car[--len] -= w[u];
    }

    static int[] car = new int[20];

    static Integer[] w = new Integer[20];//记录每只小猫的重量
    static int[] sum = new int[20];//每辆车的重量
    static int n, m, ans = 20, len;//最坏20辆车
}