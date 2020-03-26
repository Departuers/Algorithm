package 递归;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * 92. 递归实现指数型枚举
 * 从 1~n 这 n 个整数中随机选取任意多个，输出所有可能的选择方案。
 * 输入格式
 * 输入一个整数n。
 * 输出格式
 * 每行输出一种方案。
 * 同一行内的数必须升序排列，相邻两个数用恰好1个空格隔开。
 * 对于没有选任何数的方案，输出空行。
 * 本题有自定义校验器（SPJ），各行（不同方案）之间的顺序任意。
 * 数据范围
 * 1≤n≤15
 * 输入样例：
 * 3
 * 输出样例：
 * 3
 * 2
 * 2 3
 * 1
 * 1 3
 * 1 2
 * 1 2 3
 */
public class 随机选数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        n = sc.nextInt();
        dfs(0,0);
    }

    static int n;

    static void dfs(int u, int state) {
        if (u == n) {//经过所有的数
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1)//为1说明用这个数
                    System.out.print(i + 1+" ");//枚举的0~(n-1)所以i+1
            }
            System.out.println();
            return;
        }
        dfs(u + 1, state);//不用第u个数字
        dfs(u + 1, state | (1 << u));//用第u个数字,把state的第u位变成为1
    }
}
