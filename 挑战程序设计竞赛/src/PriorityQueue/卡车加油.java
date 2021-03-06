package PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * POJ 2431
 * 题目大意：一辆卡车，初始时，距离终点L，油量为P，
 * 在起点到终点途中有N个加油站，每个加油站油量有限，
 * 而卡车的油箱容量无限，卡车在行车途中，
 * 每走一个单位的距离消耗一个单位的油量，
 * 给定n个加油站距离起点的距离以及油存储量。
 * A数组代表距离起点
 * B数组代表每个加油站最多加多少油
 * 假设油箱容量无限大
 * 问卡车是否能到达终点，如果可达，最少需要加多少次油，否则输出-1.
 * 1≤N≤10000
 * 输入：
 * N=4 L=25 P=10
 * A={10,14,20,21}
 * B={10,5,2,4}
 * 输出：
 * 2(在第一个和第二个加油站加油)
 */
public class 卡车加油 {
    public static void main(String[] args) {
        int N = 4, L = 25, P = 10;
        int[] A = {10, 14, 20, 21};
        int[] B = {10, 5, 2, 4};
        System.out.println(Kache(N, L, P, A, B));
    }

    public static int Kache(int N, int L, int P, int[] A, int[] B) {
        A = Arrays.copyOf(A, A.length + 1);
        B = Arrays.copyOf(B, B.length + 1);
        A[N] = L;//把终点也设为加油站
        B[N] = 0;//只能加0的油
        System.out.println(Arrays.toString(A));
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10005, new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2 - t1;
            }
        });
        int res = 0;//加油次数
        int pos = 0;//当前所在位置
        int tank = P;//油箱剩的油的数量
        for (int i = 0; i <= N; i++) {//把终点看做加油站,看能不能到这个加油站
            int d = A[i] - pos;//卡车的位置从0开始,到达第i个加油站需要多少油
            while (tank - d < 0) {//如果无法到达第i个加油站
                if (pq.size() == 0)//从优先队列里面没有加油站,没有刻
                    return -1;//无法到达
                tank += pq.poll();
                res++;
            }
            tank -= d;//减去走到第i个加油站需要的油,区间是什么呢,
            // 第一次区间(0,A[i])需要这么多油,第二次及以后区间(A[i-1],A[i])
            // 每次都要维护油的数量,区间消耗的油
            pos = A[i];//卡车开到第i个加油站
            pq.offer(B[i]);//每到一个加油站,就把能加的油加进优先队列
        }
        return res;
    }

    private static class Node implements Comparable<Node> {
        public int Juli;
        public int You = 0;

        public Node(int juli) {
            Juli = juli;
        }

        @Override
        public int compareTo(Node node) {
            return this.Juli - node.Juli;
        }
    }

    /**
     * POJ 2431 题解通过
     */
    public static void Tijie() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//几个加油站
        Node[] node = new Node[N + 1];
        for (int i = 0; i < N; i++) {
            node[i] = new Node(sc.nextInt());
            node[i].You = sc.nextInt();
        }
        int L = sc.nextInt();
        int P = sc.nextInt();
        for (int i = 0; i < N; i++) {
            node[i].Juli = L - node[i].Juli;//更改为加油站距离起点的位置
        }
        node[N] = new Node(L);
        Arrays.sort(node);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(N + 10, new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2 - t1;
            }
        });
        int res = 0;//加油次数
        int pos = 0;//当前所在位置
        int tank = P;//油箱剩的油的数量
        for (int i = 0; i <= N; i++) {//把终点看做加油站,看能不能到这个加油站
            int d = node[i].Juli - pos;//卡车的位置从0开始,到达第i个加油站需要多少油
            while (tank - d < 0) {//如果无法到达第i个加油站
                if (pq.isEmpty()) {//从优先队列里面没有加油站,没有刻
                    System.out.println("-1");
                } else
                    tank += pq.poll();
                res++;
            }
            tank -= d;//减去走到第i个加油站需要的油,区间是什么呢,
            // 第一次区间(0,A[i])需要这么多油,第二次及以后区间(A[i-1],A[i])
            // 每次都要维护油的数量,区间消耗的油
            pos = node[i].Juli;//卡车开到第i个加油站
            pq.offer(node[i].You);//每到一个加油站,就把能加的油加进优先队列
        }
        System.out.println(res);
    }
}
