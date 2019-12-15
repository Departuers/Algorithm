package com.shiyu;

import java.util.Scanner;

/**
 * 开灯问题。 有n盏灯， 编号为1～ n。 第1个人把所有灯打开， 第2个人按下所有编号为2
 * 的倍数的开关（ 这些灯将被关掉） ， 第3个人按下所有编号为3的倍数的开关（ 其中关掉的灯
 * 将被打开， 开着的灯将被关闭） ， 依此类推。 一共有k个人， 问最后有哪些灯开着？ 输
 * 入n和k， 输出开着的灯的编号。 k≤n≤1000。
 * 样例输入：
 * 7 3
 * 样例输出：
 * 1 5 6 7
 * 【分析】
 * 用a[1]， a[2]， …， a[n]表示编号为1， 2， 3， …， n的灯是否开着。 模拟这些操作即可。
 */
public class 开灯 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        boolean[] arr = new boolean[n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j % i == 0)
                    arr[j] = !arr[j];
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (arr[i])
                System.out.print(i + " ");
        }
    }
}
