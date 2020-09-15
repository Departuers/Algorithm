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
        });//从大到小排序,优先放重的
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
    static int n, m, ans = 20, len;
}