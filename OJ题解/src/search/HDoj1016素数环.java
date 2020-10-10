package search;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 素数环:
 * 一个环由n个数组成,这n个数,是自然数,1,2,3,4,5...n
 * 并且相邻2个数字之和,应该是素数
 * <p>
 * 解空间树思想求解，DFS.递归、回溯。相邻的每一个数都要求是素数，
 * 第一数必须为1 然后,后面的n-1种选择，
 * 先从2开始往后选，符合条件放入a数组，由符合条件的这个数重复以上操作，
 * 直到最后一个确定出来则打印。
 * 原来对DFS的理解仅仅局限于图，现在发现这只是最基础的。
 * DFS更多的表示的是一种状态，然后利用某中很简单的思维进行一次次的尝试，
 * 每次尝试成功了，就深入一层递归进行下一次尝试，直到之后的尝试表明已经失败了不会成功，
 * 则回溯到这里。取消这次的尝试，去尝试其他的操作。简单地说，就是暴搜。
 * 只不过利用了递归来实现尝试失败时的回溯，从而进行新的尝试。
 */
public class HDoj1016素数环 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();//素数环个数
        circle = new int[n];
        vis = new boolean[n + 1];
        circle[0] = 1;
        if (n % 2 == 0) dfs(1);//素数环必须是偶数个,奇数个无法实现,我也看不懂,书上这么说

    }

    static int n;
    static int[] circle;
    static boolean[] vis;

    /**
     * 显然能被2的倍数整除,就一定能被2整除
     * 就少枚举一半的约数
     * 直接快一半!!!
     *
     * @param x
     * @return
     */
    static boolean isPrimer(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if ((x & 1) == 0) return false;
        int t = (int) Math.sqrt(x);
        for (int i = 3; i <= t; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static void dfs(int cur) {
        if (cur == n && isPrimer(circle[0] + circle[n - 1])) {
            System.out.println(Arrays.toString(circle));
            return;
        } else {
            for (int i = 2; i <= n; i++) {
                if (!vis[i] && isPrimer(circle[cur - 1] + i)) {
                    //i未被使用,在circle[cur]位置放i
                    circle[cur] = i;
                    vis[i] = true;
                    dfs(cur + 1);//搜索下一层
                    vis[i] = false;
                }
            }
        }
    }
}
