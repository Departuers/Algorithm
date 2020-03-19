package graph;

import java.util.Scanner;

/**
 * 分梨
 * 小明非常喜欢吃梨，有一天他得到了ACM CLUB送给他的一筐梨子。由于他比较仗义，就打算把梨子分给好朋友们吃。
 * 现在他要把M个梨子放到N个盘子里面（我们允许有的盘子为空），你能告诉小明有多少种分法吗？（请注意，例如有三个盘子，我们将5,1,1和1,1,5，视为同一种分法）
 * 输入
 * 输入包含多组测试样例。每组输入的第一行是一个整数t。
 * 接下来t行，每行输入两个整数M和N，代表有M个梨和N个盘子。（M和N均大于等于0）
 * 输出
 * 对于每对输入的M和N，输出有多少种方法。
 * 样例输入
 * 1
 * 7 3
 * 1
 * 2
 * 样例输出
 * 8
 * 组合数学分情况讨论:
 * 1. m和n为1或者为0的时候只有一种方法(用作递归出口)
 * 2. 若m<n,即为f(m,m)
 * 3. 当一个盘子为空的时候,实际是讲一个盘子不用,设f(m,n)表示m个梨,n个盘子,一个盘子为空等价于f(m,n-1)
 * 4. 当m>n>1时,也就是梨比盘子多,全部(每个)盘子都至少可以放一个梨,就是去掉最底层元素,即f(m-n,n)
 */
public class 分梨 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        for (int c = 0; c < count; c++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            long start = System.nanoTime();
            memDp = new int[m + 1][n + 1];
            System.out.println(fByMem(m, n));
            long end = System.nanoTime();
            System.out.println(end - start);
        }
    }

    public static int f(int m, int n) {
        if (m == 1 || n == 1 || m == 0) return 1;
        else if (m < n) return f(m, m);
        else return f(m - n, n) + f(m, n - 1);
    }

    public static int[][] memDp;
    //记忆化搜索
    public static int fByMem(int m, int n) {
        if (memDp[m][n] > 0) return memDp[m][n];
        else {
            if (m == 1 || n == 1 || m == 0) return 1;
            else if (m < n) return memDp[m][n] = f(m, m);
            else return memDp[m][n] = f(m - n, n) + f(m, n - 1);
        }
    }
}
