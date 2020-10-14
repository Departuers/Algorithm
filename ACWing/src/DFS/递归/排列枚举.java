package DFS.递归;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * 94. 递归实现排列型枚举
 * 把 1~n 这 n 个整数排成一行后随机打乱顺序，输出所有可能的次序。
 * 输入格式
 * 一个整数n。
 * 输出格式
 * 按照从小到大的顺序输出所有方案，每行1个。
 * 首先，同一行相邻两个数用一个空格隔开。
 * 其次，对于两个不同的行，对应下标的数一一比较，字典序较小的排在前面。
 * 数据范围
 * 1≤n≤9
 * 输入样例：
 * 3
 * 输出样例：
 * 1 2 3
 * 1 3 2
 * 2 1 3
 * 2 3 1
 * 3 1 2
 * 3 2 1
 * 排列型枚举,n个数,有n个坑,第一个坑有n种选择,第二个坑有n-1中选择,第n个坑只有一种选择
 * 第一个坑的n种选择,是平行的,
 * 交换性枚举每一位,是效率最高的
 * 全排列
 */
public class 排列枚举 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        long s = System.nanoTime();
        d(0, 0);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        s = System.nanoTime();
        dfs(0, 0);
        t = System.nanoTime();
        System.out.println((t - s) / 1e8);

    }

    static int n = 4;
    static ArrayList<Integer> path = new ArrayList<Integer>();

    /**
     * 没有加pos,显然就是n个坑,每个坑里有n种平行选择
     * 所以就是全排列,path记录顺序,顺序相关
     *
     * @param u     u代表结果path有多少个
     * @param state state通过位运算作为vis数字,状态
     */
    static void dfs(int u, int state) {
        if (u == n) {
//            for (Integer w : path) {
//                System.out.print(w + " ");
//            }
//            System.out.println();
            return;
        }
        for (int i = 0; i < n; i++) {//每一位枚举n种平行选择
            if ((state >> i & 1) != 1) {//如果这个数字没被选,就选
                path.add(arr[i]);
                dfs(u + 1, state | (1 << i));//选上
                path.remove(path.size() - 1);//恢复状态
            }
        }
    }

    static int[] arr = {1, 2, 3, 4, 5, 4, 1, 4, 5, 1, 2};

    //最快
    static void d(int u, int k) {
        if (u == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = k; i < n; i++) {
            swap(i, k);
            d(u + 1, k + 1);
            swap(i, k);
        }
    }

    static void swap(int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
