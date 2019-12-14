package UnionFind;

import java.util.Arrays;
import java.util.Scanner;

/**
 *某人开生日聚会,邀请朋友参加,需要准备桌子,朋友不愿意和陌生人坐在一起,
 * 给出朋友认识谁,求准备多少桌子
 * 输入
 * 5 3
 * 1 2
 * 2 3
 * 4 5
 * 输出
 * 2
 * 输入
 * 使用并查集,把朋友认识的放在一起,后面遍历看有多少集合,就准备多少桌子
 */
public class 多少桌子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        UnionFind u = new UnionFind(n);
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            u.Union(a, b);
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i == u.find(i))
                ans++;
        }
        System.out.println(Arrays.toString(u.parent));
        System.out.println(ans);
    }
}
