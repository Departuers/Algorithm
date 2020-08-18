package UnionFind;

import java.util.Scanner;

/**
 * https://blog.csdn.net/lisong_jerry/article/details/80029967?utm_medium=distribute.pc_relevant.none-task-blog-baidulandingword-2&spm=1001.2101.3001.4242
 * 带权并查集
 * 精髓:只要两个元素在一个集合里面，
 * 通过它们与根节点的距离就能知道它们的相对关系
 */
public class 食物链 {

    static int[] p = new int[100005], dir = new int[1000005];

    static int find(int x) {
        if (p[x] != x) {
            int t = find(p[x]);
            //t代表最祖先节点
            dir[x] += dir[p[x]];
            //记录p[x]这个节点到祖先的距离
            p[x] = t;
        }
        return p[x];
    }

    static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        int res = 0;
        int t, x, y;
        while (m-- != 0) {
            t = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            //如果没有这种动物,肯定就是假话
            if (x > n || y > n) res++;
            else {
                int px = find(x), py = find(y);
                if (t == 1) {
                    if (px == py && (dir[x] - dir[y]) % 3 != 0) res++;
                    else {
                        p[px] = py;
                        dir[px] = dir[y] - dir[x];
                    }
                } else {
                    if (px == py && (dir[x] - dir[y] - 1) % 3 != 0) res++;
                    else if (px != py) {
                        p[px] = py;
                        dir[px] = dir[y] + 1 - dir[x];
                    }
                }
            }
        }
        System.out.println(res);
    }
}
