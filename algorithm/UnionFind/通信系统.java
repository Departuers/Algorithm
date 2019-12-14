package UnionFind;

import java.util.Scanner;

/**
 * 通信系统
 * 某市计划建设一个通信系统。按照规划，这个系统包含若干端点，这些端点由通信线缆链接。消息可以在任何一个端点产生，并且只能通过线缆传送。
 * 每个端点接收消息后会将消息传送到与其相连的端点，除了那个消息发送过来的端点。如果某个端点是产生消息的端点，那么消息将被传送到与其相连的每一个端点。
 * 为了提高传送效率和节约资源，要求当消息在某个端点生成后，其余各个端点均能接收到消息，并且每个端点均不会重复收到消息。
 * 现给你通信系统的描述，你能判断此系统是否符合以上要求吗？
 * 输入
 * 输入包含多组测试数据。每两组输入数据之间由空行分隔。
 * 每组输入首先包含2个整数N和M，N（1<=N<=1000）表示端点个数，M（0<=M<=N*(N-1)/2）表示通信线路个数。
 * 接下来M行每行输入2个整数A和B（1<=A，B<=N），表示端点A和B由一条通信线缆相连。两个端点之间至多由一条线缆直接相连，并且没有将某个端点与其自己相连的线缆。
 * 当N和M都为0时，输入结束。
 * 输出
 * 对于每组输入，如果所给的系统描述符合题目要求，则输出Yes，否则输出No。
 * 样例输入
 * 4 3
 * 1 2
 * 2 3
 * 3 4
 * 3 1
 * 2 3
 * 0 0
 * 样例输出
 * Yes
 * No
 * 思路:魔改并查集,给定几个端点,端点之间不能有环,有且只能有n-1条边
 */
public class 通信系统 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        UnionFindT u = new UnionFindT(a);
        for (int i = 0; i < b; i++) {
            int c = sc.nextInt();
            int d = sc.nextInt();
            if (b!=a-1)
                continue;
            u.Union(c, d);
        }
        System.out.println(u.sum);
        if (u.sum==a-1)//有且只能有n-1条合法边就是对的
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

class UnionFindT {
    public int sum = 0;
    private int[] parent;

    UnionFindT(int n) {
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
    }

    public void Union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot){
            sum++;//计算边的数量,如果两个端点的根相同,那就是非法边
            parent[pRoot] = qRoot;
        }
    }


    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

}
