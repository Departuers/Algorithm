package UnionFind;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description
 * <p>
 * An earthquake takes place in Southeast Asia. The ACM (Asia Cooperated Medical team) have set up a wireless network with the lap computers, but an unexpected aftershock attacked, all computers in the network were all broken. The computers are repaired one by one, and the network gradually began to work again. Because of the hardware restricts, each computer can only directly communicate with the computers that are not farther than d meters from it. But every computer can be regarded as the intermediary of the communication between two other computers, that is to say computer A and computer B can communicate if computer A and computer B can communicate directly or there is a computer C that can communicate with both A and B. 
 * <p>
 * In the process of repairing the network, workers can take two kinds of operations at every moment, repairing a computer, or testing if two computers can communicate. Your job is to answer all the testing operations. 
 * Input
 * <p>
 * The first line contains two integers N and d (1 <= N <= 1001, 0 <= d <= 20000). Here N is the number of computers, which are numbered from 1 to N, and D is the maximum distance two computers can communicate directly. In the next N lines, each contains two integers xi, yi (0 <= xi, yi <= 10000), which is the coordinate of N computers. From the (N+1)-th line to the end of input, there are operations, which are carried out one by one. Each line contains an operation in one of following two formats: 
 * 1. "O p" (1 <= p <= N), which means repairing computer p. 
 * 2. "S p q" (1 <= p, q <= N), which means testing whether computer p and q can communicate. 
 * <p>
 * The input will not exceed 300000 lines. 
 * Output
 * <p>
 * For each Testing operation, print "SUCCESS" if the two computers can communicate, or "FAIL" if not.
 * Sample Input
 * <p>
 * 4 1
 * 0 1
 * 0 2
 * 0 3
 * 0 4
 * O 1
 * O 2
 * O 4
 * S 1 4
 * O 3
 * S 1 4
 * Sample Output
 * <p>
 * FAIL
 * SUCCESS
 * 已知有n台计算机给出计算机的坐标,因电缆受损,在距离为D的两台计算机才能通信,若两者超过这个距离
 * 则需要媒介计算机也能通信,初始状态所有计算机都是坏的
 * Op代表修复第p台计算机
 * Sqp代表询问qp两台计算机是否能够通信
 * 成功输出success,失败输出fail
 * 思路:要同时保证计算机被修复了,计算机之间的距离小于D,才能合并
 * 每修好一台计算机,就遍历所有修好的计算机,同时满足距离小于D就把它们合并
 */
public class 修计算机 {
    public static int[] dx;
    public static int[] dy;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//计算机数量
        int d = sc.nextInt();//可通信距离
        UnionFind u = new UnionFind(n);
        dx = new int[n];
        dy = new int[n];
        ArrayList<Integer> list = new ArrayList<Integer>();//已经修好的计算机
        for (int i = 0; i < n; i++) {
            dx[i] = sc.nextInt();
            dy[i] = sc.nextInt();
        }
        while (sc.hasNext()) {
            String s = sc.next();
            int p = 0;
            if (s.startsWith("O")) {
                p = sc.nextInt();
                p--;
                list.add(p);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != p && dis(list.get(i), p) <= d) {
                        u.Union(list.get(i), p);
                        continue;
                    }
                }
            }

            if (s.startsWith("S")) {
                int tm = sc.nextInt();
                int ts = sc.nextInt();
                tm--;
                ts--;
                if (u.find(tm) == u.find(ts))
                    System.out.println("SUCCESS");
                else System.out.println("FAIL");
            }
        }
    }

    public static double dis(int a, int b) {
        return Math.sqrt(Math.abs((dx[a] - dx[b])) * Math.abs((dx[a] - dx[b])) + Math.abs((dy[a] - dy[b])) * Math.abs((dy[a] - dy[b])));
    }
}