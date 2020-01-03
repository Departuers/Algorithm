package com.shiyu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class tsdt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> jishu = new ArrayList<>();
        ArrayList<Integer> oshu = new ArrayList<>();
        Iterator<Integer> iterator = jishu.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }
        while (sc.hasNext()) {
            int a = sc.nextInt();
            if (a == 1111)
                break;
            if (a % 2 == 0) {
                oshu.add(a);
            } else {
                jishu.add(a);
            }
        }
        System.out.println(jishu);
        System.out.println(oshu);
    }

}
