package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 是否存在取4个数和为m
 * n=3
 * m=10
 * k=1 3 5
 * 输出yes 1 1 3 5结果为10
 */
public class 抽签 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            k[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                list.add(k[i] + k[j]);
            }
        }
        Collections.sort(list);
        boolean f=false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Collections.binarySearch(list,m-k[i]-k[j])>0){
                    f=true;
                    break;
                }
            }
        }
        if (f) System.out.println("yes");
        else System.out.println("no");

    }

    static ArrayList<Integer> list = new ArrayList<Integer>();
    static int[] k = new int[1000];
    static int n, m;
}
