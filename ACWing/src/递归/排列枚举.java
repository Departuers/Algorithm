package 递归;

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
 */
public class 排列枚举 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        dfs(0, 0);
    }

    static int n = 3;
    static ArrayList<Integer> path = new ArrayList<Integer>();

    //u代表结果path有多少个
    static void dfs(int u, int state) {//state通过位运算作为vis数字,
        if (u == n) {
            for (Integer w : path) {
                System.out.print(w + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < n; i++) {//枚举n种平行选择
            if (!(((state >> i) & 1) == 1)) {//如果这个数字没被选,就选
                path.add(i + 1);
                dfs(u + 1, state | (1 << i));//选上
                path.remove(path.size() - 1);//恢复状态
            }
        }
    }
}
