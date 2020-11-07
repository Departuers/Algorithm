package 基础练习;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 哈夫曼树 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            q.add(sc.nextInt());
        }
        int res = 0;
        while (q.size() >= 2) {
            int t1 = q.poll();
            int t2 = q.poll();
            res += t1 + t2;
            q.add(t1 + t2);
        }
        System.out.println(res);
    }
}
