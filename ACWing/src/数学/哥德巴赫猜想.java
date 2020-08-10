package 数学;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/acwing/content/8155/
 * 哥德巴赫猜想的内容如下：
 * 任意一个大于 4 的偶数都可以拆成两个奇素数之和。
 * 例如：
 * 8=3+5
 * 20=3+17=7+13
 * 42=5+37=11+31=13+29=19+23
 * 现在，你的任务是验证所有小于一百万的偶数能否满足哥德巴赫猜想。
 * 输入格式
 * 输入包含多组数据。
 * 每组数据占一行，包含一个偶数 n。
 * 读入以 0 结束。
 * 输出格式
 * 对于每组数据，输出形如 n = a + b，其中 a,b 是奇素数。
 * 若有多组满足条件的 a,b，输出 b−a 最大的一组。
 * 若无解，输出 Goldbach’s conjecture is wrong.。
 * 数据范围
 * 6≤n<106
 * 输入样例：
 * 8
 * 20
 * 42
 * 0
 * 输出样例：
 * 8 = 3 + 5
 * 20 = 3 + 17
 * 42 = 5 + 37
 * 预处理1e6以内的素数,从小到大枚举素数,则分解的两数相差最大
 */
public class 哥德巴赫猜想 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        f();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) break;
            for (int i = 1; ; i++) {
                int a = primer[i];
                int b = n - a;
                if (!vis[b]) {
                    System.out.println(n + " " + a + "+" + b);
                    break;
                }
            }
        }
    }

    static int[] primer = new int[(int) (1e6 + 10)];
    static boolean[] vis = new boolean[(int) (1e6 + 10)];

    static void f() {
        int cnt = 0;
        for (int i = 2; i <= 1e6; i++) {
            if (!vis[i]) primer[cnt++] = i;
            for (int j = 0; primer[j] * i <= 1e6; j++) {
                vis[primer[j] * i] = true;
                if (i % primer[j] == 0) break;
                //如果这个质数是i的最小质因子,就break
            }
        }
    }
}
