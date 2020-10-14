package doublePointer;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 给定一个长度为n的整数序列，请找出最长的不包含重复数字的连续区间，输出它的长度。
 * 输入格式
 * 第一行包含整数n。
 * 第二行包含n个整数（均在0~100000范围内），表示整数序列。
 * 输出格式
 * 共一行，包含一个整数，表示最长的不包含重复数字的连续子序列的长度。
 * 数据范围
 * 1≤n≤100000
 * 输入样例：
 * 5
 * 1 2 2 3 5
 * 输出样例：
 * 3
 * 随着问题规模的增大，不含重复数字的最大连续区间的长度必然是非递减的，
 * 而作为解的子区间要么不变，要么右移，可以看出解的单调性，
 * 由此考虑采用双指针算法。用一个指针指向当前遍历到的元素，
 * 也就是当前不包含重复数字区间的末尾，另一个指针则指向区间开头。
 * 为了快速的知道是否某元素在区间内是否出现了超过一次，
 * 可以用哈希表存储每个元素出现的位置。遍历到某元素时，
 * 若map中之前没有该元素，则区间长度++，
 * 否则看下上次出现该元素的位置是否在区间内，
 * 也就是比较map记录的下标是否小于区间的开头指针，
 * 如果恰好在区间内，则更新map中当前元素的值，也就是说，
 * 对于某元素x，m[x]永远存储的是该元素最新出现的位置。
 * 以样例1 2 2 3 5为例，设res为区间长度，初始情况头指针p，
 * 尾指针i均指向下标0，第一个元素1加入区间，m[1]=0,res++，
 * 第二个元素2加入，m[2]=1，res++，
 * 第三个元素2加入发现m[2] = 1 > p = 0,在区间内，
 * 于是这段无重复元素的区间就结束了，更新p为重复元素2出现的后一个位置，
 * 也就是2，同时更新计数变量res为2-1=1，此时区间内只剩2一个元素了，
 * i继续右移直至最后一个元素5，得到区间最大长度为3。
 */
public class 最长连续不重复子序列 {
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        f();
    }

    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    static void f() {
        int ans = 0;

        for (int i = 0, j = 0; i < n; i++) {
            if (!map.containsKey(a[i])) {
                map.put(a[i], 1);
            } else map.put(a[i], map.get(a[i]) + 1);
            while (map.get(a[i]).compareTo(1) > 0) {
                map.put(a[j], map.get(a[j]) - 1);
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        System.out.println(ans);
    }

    static void hash() {
        int ans = 0;
        for (int i = 0, j = 0; i < n; i++) {
            //i往前走
            hash[a[i]]++;
            while (hash[a[i]] > 1) {
                //新加入的元素导致重复,肯定是因为跟a[i]重复
                //可以缩小左边界j++,删除左边界元素
                hash[a[j]]--;
                j++;
            }
            ans = Math.max(ans, i - j + 1);
            //实际上是枚举i,j区间,双指针
        }
        System.out.println(ans);
    }

    static int[] hash = new int[100010];
    static int[] a = new int[100010];
}
