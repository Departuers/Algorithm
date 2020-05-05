package 数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 科协里最近很流行数字游戏。
 * 某人命名了一种不降数，这种数字必须满足从左到右各位数字呈非下降关系，如 123，446。
 * 现在大家决定玩一个游戏，指定一个整数闭区间 [a,b]，问这个区间内有多少个不降数。
 * 输入格式
 * 输入包含多组测试数据。
 * 每组数据占一行，包含两个整数 a 和 b。
 * 输出格式
 * 每行给出一组测试数据的答案，即 [a,b]之间有多少不降数。
 * 数据范围
 * 1≤a≤b≤2^31−1
 * 输入样例：
 * 1 9
 * 1 19
 * 输出样例：
 * 9
 * 18
 */
public class 数字游戏 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            System.out.println(get(t) - get(s - 1));
        }
    }

    static int N = 12;
    static int[][] f = new int[N][N];

    static void init() {
        for (int i = 0; i <= 9; i++) {
            f[1][i] = 1;
        }
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    static int get(int n) {
        if (n < 10) return n + 1;
        ArrayList<Integer> num = new ArrayList<Integer>();
        while (n != 0) {//取出n的每一位
            num.add(n % 10);
            n /= 10;
        }
        int res = 0, last = 0;
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            for (int j = last; j < x; j++) {
                res += f[i + 1][j];
            }
            if (x < last) break;
            last = x;
            if (i == 0) res++;
        }
        return res;
    }
}
