package 第七章枚举;

import java.util.Arrays;
import java.util.HashSet;

/**
 * l7.1除法（ Division, UVa 725）
 * 输入正整数n， 按从小到大的顺序输出所有形如abcde/fghij = n的表达式， 其中a～ j恰好
 * 为数字0～ 9的一个排列（ 可以有前导0） ， 2≤n≤79。
 * 样例输入：
 * 62
 * 样例输出：
 * 79546 / 01283 = 62
 * 94736 / 01528 = 62
 * 【分析】
 * 枚举0～ 9的所有排列？ 没这个必要。 只需要枚举fghij就可以算出abcde， 然后判断是否
 * 所有数字都不相同即可。 不仅程序简单， 而且枚举量也从10!=3628800降低至不到1万， 而且
 * 当abcde和fghij加起来超过10位时可以终止枚举。 由此可见， 即使采用暴力枚举， 也是需要认
 * 真分析问题的。
 */
public class l1除法 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        f(62);
    }

    static int[] arr = new int[10];
    static HashSet<Integer> set = new HashSet<Integer>();

    //全排列做法
    static void dfs(int[] arr, int k, int N) {
        if (k == 10) {
            int t = arr[0] * 10000 + arr[1] * 1000 + arr[2] * 100 + arr[3] * 10 + arr[4];
            int s = arr[5] * 10000 + arr[6] * 1000 + arr[7] * 100 + arr[8] * 10 + arr[9];
            if (t % s == 0 && t / s == N)
                System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = k; i < 10; i++) {
            int t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
            dfs(arr, k + 1, 62);
            t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
        }
    }

    //只枚举fghij
    static void f(int N) {
        for (int i = 1234; i < 100000; i++) {
            if (check(i, i * N))
                System.out.println(i * N + "/" + i + "=" + N);
        }
    }

    static boolean check(int A, int E) {
        if (E >= 100000) return false;
        set.clear();
        while (A != 0) {
            set.add(A % 10);
            A /= 10;
        }
        if (set.size() == 4) set.add(0);
        while (E != 0) {
            set.add(E % 10);
            E /= 10;
        }
        return set.size() == 10;
    }
}
