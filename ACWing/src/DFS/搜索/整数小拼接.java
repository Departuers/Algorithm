package DFS.搜索;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 */
public class 整数小拼接 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        int mid = k;
        int lenk = 0;
        while (mid != 0) {
            lenk++;
            mid /= 10;
        }//求出K的位数
        ArrayList<node> str = new ArrayList<node>();
        for (int i = 0; i < n; i++) {
            mid = sc.nextInt();
            int lenmid = getlen(mid);
            if (lenmid < lenk) {//忽略位数比K多的数
                str.add(new node(mid, lenmid));
            }
        }
        Collections.sort(str);
        int i = 0, num = 0;
        // 找出长度小于k长度1/2的，两两组合一定可以小于k,之前全排列
        while (str.get(i).len < lenk / 2) {
            num++;
            i++;
        }
        for (int j = 0; j < num; j++) {
            str.remove(j);
        }
        int sum = 0;
        if (num != 0) {
            sum = 1;
            while (num != 0) {//阶乘...任意两个都可以构成小于k的
                sum *= num;
                num--;
            }
        }
        int lenstrore = str.size();
        for (int j = 0; j < lenstrore; j++) {//起点
            for (int l = 0; l < j; l++) {//j和j前面的全部
                mid = str.get(j).data * pow(str.get(l).len) + str.get(l).data;
                if (mid < k)
                    sum++;
            }
            for (int l = j + 1; l < lenstrore; l++) {//j和j后面的全部
                mid = str.get(j).data * pow(str.get(l).len) + str.get(l).data;
                if (mid < k) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

    static int pow(int x) {
        return (int) Math.pow(10, x);
    }

    static int n, k;

    static int getlen(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }

    static class node implements Comparable<node> {
        int data, len;

        public node(int data, int len) {
            this.data = data;
            this.len = len;
        }

        @Override
        public int compareTo(node node) {
            return len - node.len;
        }
    }
}
