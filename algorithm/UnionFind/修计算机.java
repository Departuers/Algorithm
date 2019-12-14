package UnionFind;

import java.util.Scanner;

/**
 * 已知有n台计算机给出计算机的坐标,因电缆受损,在距离为D的两台计算机才能通信,若两者超过这个距离
 * 则需要媒介计算机也能通信,初始状态所有计算机都是坏的
 * Op代表修复第p台计算机
 * Sqp代表询问qp两台计算机是否能够通信
 * 成功输出success,失败输出fail
 * 思路:要同时保证计算机被修复了,计算机之间的距离小于D,才能合并
 * 每修好一台计算机,就遍历所有修好的计算机,同时满足距离小于D就把它们合并
 */
public class 修计算机 {
    public static int dx[];
    public static int dy[];
    public static int par[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//计算机数量
        int d = sc.nextInt();//长度
        UnionFindE u = new UnionFindE(n);
        dx = new int[n];
        dy = new int[n];
        for (int i = 0; i < n; i++) {
            dx[i] = sc.nextInt();
            dy[i] = sc.nextInt();
        }
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.startsWith("O")){

            }
        }
    }

    public static double dis(int a, int b) {
        return Math.sqrt(Math.abs((dx[a] - dx[b])) * Math.abs((dx[a] - dx[b])) + Math.abs((dy[a] - dy[b])) * Math.abs((dy[a] - dy[b])));
    }
}

class UnionFindE {
    public int sum = 0;
    private int[] parent;

    UnionFindE(int n) {
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
    }

    public void Union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            sum++;//计算边的数量,如果两个端点的根相同,那就是非法边
            parent[pRoot] = qRoot;
        }
    }


    public int find(int p) {
        while (p != parent[p]) {
            p = find(parent[p]);
        }
        return parent[p];
    }

}
