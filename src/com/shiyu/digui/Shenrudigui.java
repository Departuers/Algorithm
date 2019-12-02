package com.shiyu.digui;

import java.util.HashSet;
import java.util.Set;

public class Shenrudigui {
    private static int mod = 1000000007;
    private static int[] data = {1, 5, 10, 25};

    public static void main(String[] args) {
//        long start = System.nanoTime();
//        System.out.println(count(100, data));
//        long end = System.nanoTime();
//        System.out.println(end-start);
//        long starts = System.nanoTime();
//        System.out.println(countWays(100, data));
//        long ends = System.nanoTime();
//        System.out.println(ends-starts);

//        System.out.println(check(3));


    }


    /**
     * 小白上楼梯，假设有n阶台阶，小白一次走1阶，或者2阶，或者3阶，
     * 计算有多少种走完楼梯的方式，
     * 防止溢出将结果mod 1000000007
     * 非递归实现，性能高
     *
     * @param n
     * @return
     */
    public static int fs(int n) {
        if (n < 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        int x1 = 1, x2 = 2, x3 = 4;
        for (int i = 4; i <= n; i++) {
            int x = x1;
            x1 = x2 % mod;
            x2 = x3 % mod;
            x3 = ((x + x1) % mod + x2) % mod;
        }
        return x3;
    }

    /**
     * 小白上楼梯，假设有n阶台阶，小白一次走1阶，或者2阶，或者3阶，
     * 计算有多少种走完楼梯的方式，
     * 防止溢出将结果mod 1000000007
     * 思路:递归写法
     *
     * @param n
     * @return
     */
    public static long rec(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        return rec(n - 1) % mod + rec(n - 2) % mod + rec(n - 3) % mod;
    }

    /**
     * 2.机器人走方格
     *
     * @param x
     * @param y
     * @return
     */
    public static int jiqiren(int x, int y) {
        if (x == 1 || y == 1) return 1;
        return jiqiren(x - 1, y) + jiqiren(x, y - 1);
    }

    /**
     * 2.机器人走方格,非递归写法
     *
     * @param x
     * @param y
     * @return
     */
    public static int jiqire(int x, int y) {
        int[][] state = new int[x + 1][y + 1];
        for (int i = 1; i <= x; i++) {
            state[1][i] = 1;
        }
        for (int i = 1; i <= x; i++) {
            state[i][1] = 1;
        }
        for (int i = 2; i <= x; i++) {
            for (int j = 2; j <= y; j++) {
                state[i][j] = state[i][j - 1] + state[i - 1][j];
            }
        }
        return state[x][y];
    }

    /**
     * 1分，2分，5分的硬币三种，问组合成1角，共有多少种组合
     * 1*x+2*y+5*z=10
     */
    public static void yingbi() {
        int count = 0;
        //i代表1分，j代表2分，k代表5分
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                for (int k = 0; k <= 10; k++) {
                    if (i + j * 2 + k * 5 == 10) {
                        count++;
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
        System.out.println(count);
    }

    /**
     * --3.硬币表示
     * <p>
     * 假设有1元，2元，5元，10元，20元，50元，100元，200元
     * 用给定硬币构成一个数值n
     * 假如n=200,3*1+1*2+5+2*20+50+100
     * 问一共有多少种可能的组合方式
     * <p>
     * 思路:把工作交给下一层循环递归写法，重点！！！
     *
     * @param n
     * @param coins
     * @return
     */
    public static int countWays(int n, int[] coins) {
        if (n <= 0)
            return 0;
        return countWaysCore(n, coins, coins.length - 1);
    }

    public static int countWaysCore(int n, int[] coins, int cur) {
        if (n <= 0) return 0;
        if (cur == 0) return 1;
        int res = 0;
        for (int i = 0; i * coins[cur] <= n; i++) {
            int shengyu = n - i * coins[cur];
            res += countWaysCore(shengyu, coins, cur - 1);
        }
        return res;
    }

    /**
     * 重点！！！
     *
     * @param n
     * @param coins
     * @return
     */
    public static int count(int n, int[] coins) {
        int help[][] = new int[4][n + 1];
        for (int i = 0; i < help.length; i++) {
            help[i][0] = 1;//第一列初始化为1
        }
        for (int i = 0; i < help[1].length; i++) {
            help[0][i] = 1;//第一行初始化为1
        }
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i].length; j++) {
                System.out.print(help[i][j]);
            }
            System.out.println();
        }
        for (int i = 1; i < help.length; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 0; k <= j / coins[i]; k++) {
                    help[i][j] += help[i - 1][j - k * coins[i]];
                }
            }
        }
        System.out.println();
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i].length; j++) {
                System.out.print(help[i][j]);
            }
            System.out.println();
        }
        return help[3][n - 1];
    }

    public static Set<String> check(int n) {
        Set<String> s_n = new HashSet<>();
        if (n == 1) {
            s_n.add("()");
            return s_n;
        }
        Set<String> s_n_1 = check(n - 1);
        for (String s : s_n_1) {
            s_n.add("()" + s);
            s_n.add(s + "()");
            s_n.add("(" + s + ")");
        }
        return s_n;
    }

}
