package 数学;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103689211
 * 给定n个正整数ai，对于每个整数ai,请你按照从小到大的顺序输出它的所有约数。
 * 输入格式
 * 第一行包含整数n。接下来n行，每行包含一个整数ai。
 * 输出格式
 * 输出共n行，其中第 i 行输出第 i 个整数ai的所有约数。
 * 数据范围
 * 1≤n≤100,
 * 2≤ai≤2∗10^9
 * 输入样例：
 * 2
 * 6
 * 8
 * 输出样例：
 * 1 2 3 6
 * 1 2 4 8
 */
public class 求约数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int u = 0; u < n; u++) {
            res.clear();
            int t = sc.nextInt();
            for (int i = 1; i <= t / i; i++) {
                if (t % i == 0) {
                    res.add(i);
                    //不是完全平方数
                    if (i != t / i) res.add(t / i);//一个数约数是成对出现的,我们可以成对整进去
                }
            }
            Collections.sort(res);
            for (Integer w : res) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
