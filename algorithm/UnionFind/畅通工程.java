package UnionFind;

import java.util.Scanner;

/**
 * 某个省,有a个城镇,b条路,需要每个城镇都相互连通,需要修多少条路
 * 思路:城镇两两相连通,肯定总共需要a-1条路
 * 输入示例
 * 4 2      代表4个城镇,只有2条路,
 * 1 3
 * 4 3
 * 输出
 * 1
 */
public class 畅通工程 {
    public static void main(String[] args) {
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        UnionFind u = new UnionFind(a + 1);
        for (int i = 0; i < b; i++) {
            int c = sc.nextInt();
            int d = sc.nextInt();
            u.Union(c, d);
        }
        for (int i = 1; i < a; i++) {
            if (i == u.find(i))
                sum++;
        }
        System.out.println(sum - 1);
    }
}
