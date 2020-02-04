package com.shiyu;

import java.util.*;

public class Main {
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

    public static void main(String[] args) {
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
                if (pq.size() == 0)//从优先队列里面没有加油站,没有刻
                    System.out.println("-1");
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
