package DFS.多源bfs;

import java.util.*;

/**
 * 暴力搜索,依次枚举每个每个物品选还是不选
 * 但单纯暴力是2^46所以肯定超时
 * 用空间换时间
 * https://blog.csdn.net/qq_30277239/article/details/105754515
 * 达达帮翰翰给女生送礼物，翰翰一共准备了N个礼物，其中第i个礼物的重量是G[i]。
 * 达达的力气很大，他一次可以搬动重量之和不超过W的任意多个物品。
 * 达达希望一次搬掉尽量重的一些物品，请你告诉达达在他的力气范围内一次性能搬动的最大重量是多少。
 * 输入格式
 * 第一行两个整数，分别代表W和N。
 * 以后N行，每行一个正整数表示G[i]。
 * 输出格式
 * 仅一个整数，表示达达在他的力气范围内一次性能搬动的最大重量。
 * 数据范围
 * 1≤N≤46
 * 1≤W,G[i]≤2^31−1
 * 输入样例：
 * 20 5
 * 7
 * 5
 * 4
 * 18
 * 1
 * 输出样例：
 * 19
 * 本题如果数据范围不大的话，就是个简单的01背包问题的应用，但是数最大接近int的最大值，
 * 不论是时间上，还是空间上，直接用背包求解都是不能承受的，因此，
 * 可以使用dfs，dfs的最大深度是46,2^46相当大，肯定会爆栈，所以这里使用双向DFS，
 * 即从初始状态和结束状态同时搜索，当两次搜索相交时就搜到了解。
 * 对于本题而言，我们可以先把前n / 2 + 2个数搜一遍，记录下搜到的和；
 * 然后搜剩下的数，当剩下的数枚举完的时候去之前记录和的表中二分查找，
 * 直至找到一个数与当前的和相加比W小且最接近W。为什么不是对半划分数据集呢，
 * 因为这样划分比对半划分效率要高，可能是第二次dfs里面还要二分查找，因此给它稍微少分配点数据把。
 * 下面考虑剪枝，优化搜索顺序：为了尽快的接近W，尽量先去枚举较大的元素。
 * 是否有等效冗余呢？搜第u个数的时候如果前u个数选取的元素之和是s，
 * 后面再次枚举到第u个数，之前的元素和还是s时就没必要继续搜索了，
 * 虽然这里是冗余，但是具体实现的时候不会加上这个剪枝，
 * 一方面是hash的代价比较大，另一方面是数据范围大，这种冗余的状态比较少，
 * 如果加上了这个剪枝，速度反而会慢几倍。
 * 总结下解题步骤：要在编号为0到n-1的元素中选取若干个数使其和最接近m但不超过m，
 * 第一次搜编号为0-k - 1的元素，k = n / 2 + 2，将搜到的和存入数组，
 * 之后再搜编号为k-n-1的元素，搜到的和再去之前数组中二分查找合适的值，使得它们的和最接近m。
 * 几个细节需要注意下：第一，虽然元素的最大范围不超过int，但是元素和可能会超过int，
 * 因此，代码中执行元素相加的操作需要将中间结果强转为long long类型，防止溢出。
 * 第二，存储元素和的数组y总设置的是从下标为1的位置开始存储的，
 * 为的是保留下标为0的位置存储和为0的情况，实际上是没必要的，
 * 因为第一次dfs时包括了什么都不选的情况。第三，unique的作用是对有序数组去重，
 * 也就是将不重复的元素移动到数组的前面，然后返回不重复部分的后一个位置，
 * 第一次dfs后需要对和数组进行排序去重操作。第四，k = n / 2 + 2这样写之所以能ac，
 * 是样例比较水，当n比较小的时候，k就超过了n，所以第二次dfs的边界一定要写成u >= n，
 * 而不能写成u == n，不然不能处理n比较小的情况。
 * <p>
 * 使用双向dfs
 * 用空间换时间
 * 我们把n个物品看成一个区间
 * 把前n/2+2个物品所有能组合出的礼物重量的集合是什么
 */
public class 送礼物 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            w.add(sc.nextInt());
        }
        Collections.sort(w, new Comparator<Integer>() {
            @Override
            public int compare(Integer t2, Integer t1) {
                return t1 - t2;
            }
        });
        k = n / 2 + 2;
        dfs1(0, 0);
        Collections.sort(weight);
        cnt = unique();
        dfs2(k, 0);
        System.out.println(ans);

    }

    static int unique() {
        int j = 0;
        for (int i = 0; i < weight.size(); i++) {
            if (i == 0 || !weight.get(i).equals(weight.get(i - 1))) {
                weight.set(j++, weight.get(i));
            }
        }
        return j;
    }

    static void dfs2(int u, int s) {
        if (u >= n) {
            int l = 0, r = cnt - 1;//只有cnt个数据,最大索引为cnt-1
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (weight.get(mid) <= m - s) l = mid;
                else r = mid - 1;
            }
            ans = Math.max(ans, s + weight.get(l));
            return;
        }
        dfs2(u + 1, s);
        if ((long) s + w.get(u) <= m) dfs2(u + 1, s + w.get(u));
    }

    /**
     * @param u   物品位置
     * @param sum 当前和
     */
    static void dfs1(int u, int sum) {
        if (u == k) {
            weight.add(sum);
            cnt++;
            return;
        }
        dfs1(u + 1, sum);
        //不选当前物品
        if ((long) sum + w.get(u) <= m) dfs1(u + 1, sum + w.get(u));
        //可选当前物品,带上当前物品
    }

    static ArrayList<Integer> weight = new ArrayList<Integer>();
    static int n, N = 46, cnt = 1, k, m, ans;//0可以是可行解
    static ArrayList<Integer> w = new ArrayList<Integer>();
}
