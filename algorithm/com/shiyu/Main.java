package com.shiyu;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            pq.add(a);
        }
        long ans = 0;
        int c = 0;
        if (pq.size() == 1)
            ans += pq.poll();
        while (pq.size() > 1) {
            Integer p1 = pq.poll();
            Integer p2 = pq.poll();
            c = p1 + p2;
            ans += c;
            pq.add(c);
        }
        System.out.println(ans);
    }
}