package com.shiyu.digui;

public class Shenrudigui {
    private static int mod = 1000000007;

    public static void main(String[] args) {
        System.out.println(fs(100000));
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
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                for (int k = 0; k <= 10; k++) {
                    if (i * 1 + j * 2 + k * 5 == 10) {
                        count++;
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
        System.out.println(count);
    }
}
