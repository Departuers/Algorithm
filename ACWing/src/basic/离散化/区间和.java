package basic.离散化;

import java.util.*;

/**
 * 假定有一个无限长的数轴，数轴上每个坐标上的数都是0。
 * 现在，我们首先进行 n 次操作，每次操作将某一位置x上的数加c。
 * 近下来，进行 m 次询问，每个询问包含两个整数l和r，
 * 你需要求出在区间[l, r]之间的所有数的和。
 * 输入格式
 * 第一行包含两个整数n和m。
 * 接下来 n 行，每行包含两个整数x和c。
 * 再接下里 m 行，每行包含两个整数l和r。
 * 输出格式
 * 共m行，每行输出一个询问中所求的区间内数字和。
 * 数据范围
 * −10^9≤x≤10^9,
 * 1≤n,m≤10^5,
 * −10^9≤l≤r≤10^9,
 * −10000≤c≤10000
 * 输入样例：
 * 3 3
 * 1 2
 * 3 6
 * 7 5
 * 1 3
 * 4 6
 * 7 8
 * 输出样例：
 *
 * 8
 * 0
 * 5
 * 分析：
 *  本题操作的数可能在-10^9到10^9之间，如果开数组的话便过于庞大了，
 *  但是操作的次数n在十万以内，意味着最终出现的非0的数字会不超过十万，
 *  考虑采取离散化，将每次操作的数离散化为一个较小的数。
 * 第一步：将待离散化的一组数存入向量；
 * 第二步：去重，对向量进行排序，之后用unique函数去重，
 * unique函数是将不重复的元素都移动到向量前面，向量后面的元素维持不变，
 * 然后返回不重复序列的后一个位置，所以可以用alls.erase(unique(alls.begin(),alls.end()), alls.end());语句来实现对向量alls去重的目的；
 * 第三步：离散化，利用二分将向量中的元素映射为其在向量中的位置，
 * 这里第一个位置从1开始，为了后续前缀和的使用方便。
 * 离散化完数组后直接用前缀和即可求得指定区间内数的和了。
 * 这里离散化过程中为什么要每次都二分去查找元素映射的值，
 * 而不是一劳永逸的用哈希表存储下映射关系呢？因为元素过大，
 * 使用哈希表的话效率很低，而二分查找一个的速度为log100000，
 * 最多十几次二分操作即可得到元素映射的位置，速度非常快。
 *
 * 保序离散化 值域0~10^9
 * 个数只有1e5
 * a[i]  1   3   19   20000   600000
 * 映射  0   1    2     3       4
 * <p>
 * 一开始a中可能有重复元素,所以需要去重
 * 如何算出a[i]离散化之后的值是多少
 * 可以使用二分来求出离散化之后的值
 * 数组开30w
 * 插入1个坐标,删除2个坐标,所以一共是2n+m个坐标开30w
 */
public class 区间和 {
    static ArrayList<Integer> alls = new ArrayList<>();
    static ArrayList<node> adds = new ArrayList<node>();
    static ArrayList<node> query = new ArrayList<>();

    static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, N = (int) (3e5 + 10);
    static int[] a = new int[N], s = new int[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int c = sc.nextInt();
            adds.add(new node(x, c));

            alls.add(x);//把所有的下标都存上
        }
        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            query.add(new node(l, r));

            alls.add(l);//把所有的下标都存上
            alls.add(r);//把所有的下标都存上
        }
        Collections.sort(alls);
        unqiue();

        //处理增加
        for (node it : adds) {
            int x = find(it.x);
            a[x] += it.y;
        }

        //前缀和
        for (int i = 1; i <= alls.size(); i++) {
            s[i] = s[i - 1] + a[i];
        }

        for (node it : query) {
            int l = find(it.x);
            int r = find(it.y);
            System.out.println(s[r] - s[l - 1]);
        }
    }

    //映射到从1开始的自然数
    static int find(int x) {
        int l = 0, r = alls.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            //找到大于等于alls[mid]的最小的那个值
            if (alls.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return l + 1;
    }

    static void unqiue() {
        System.out.println(alls);
        int j = 0, size = alls.size();
        for (int i = 0; i < size; i++) {
            if (j == 0 || !alls.get(i - 1).equals(alls.get(i))) {
                alls.set(j++, alls.get(i));
            }
        }
        System.out.println(j);
        for (int i = j; i < size; i++) {
            alls.remove(alls.size() - 1);
        }
        System.out.println(alls);
    }
}
