package Math;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class mathtest {
    private static int mod = 1000000007;

    public static void main(String[] args) {
        //ex(19);
//        int[] arr = {2, 3, 2};

//        long start = System.nanoTime();
//        System.out.println(fs(100));
//        long end = System.nanoTime();
//        System.out.println(end - start);
//        long starts = System.nanoTime();
//        System.out.println(rec(20));
//        long ends = System.nanoTime();
//        System.out.println(ends - starts);

//        boolean nim = Nim(arr);
//        System.out.println(nim);
//        System.out.println(119 - 73);
        long start = System.nanoTime();
        System.out.println(jiqiren(6, 6));
        long end = System.nanoTime();
        System.out.println(end - start);
        long starts = System.nanoTime();
        System.out.println(jiqire(6, 6));
        long ends = System.nanoTime();
        System.out.println(ends - starts);

    }

    /**
     * 1.巧用进制
     * <p>
     * 天平称重，我们希望用尽可能少的砝码称出尽可能多的重量
     * 如果有无限个砝码，但他们的重量分别是1,3，9,27，81等3的指数幂，
     * 神奇之处在于它们的组合可以称出任意的重量（砝码允许放在左右两边的盘中）
     * <p>
     * 重量<1000000
     * 例如
     * 用户输入
     * 5
     * 程序输出
     * 9-3-1
     * <p>
     * 思路:题目指出是3的指数次幂，可以将用户输入的数字转换成3进制
     * 比如，19转换成三进制201，1代表选，0代表不选，2代表需要进位，201转换成1-101
     * 1*3^3-1*3^2-1=27-9+1=19
     * <p>
     * 同理如果是2的指数次幂，转换成2进制，1代表选，0代表不选，
     *
     * @param n
     * @return
     */
    public static String ex(int n) {
        final String s = Integer.toString(n, 3);
        char[] arr = new StringBuilder(s).reverse().toString().toCharArray();
        System.out.println(arr);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '2') {
                list.add(0, -1);//-1插在开头
                if (i == arr.length - 1) {
                    list.add(0, 1);//最后一个字符进位
                } else
                    ++arr[i + 1];
            } else if (arr[i] == '3') {
                list.add(0, 0);
                if (i == arr.length - 1) {
                    list.add(0, 1);
                } else {
                    ++arr[i + 1];
                }
            } else {
                list.add(0, arr[i] - '0');
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) == 1)
                sb.append("+").append((int) (Math.pow(3, list.size() - j - 1)));
            if (list.get(j) == -1)
                sb.append("-").append((int) (Math.pow(3, list.size() - j - 1)));
        }
        System.out.println(sb.substring(1));
        return sb.substring(1);
    }

    /**
     * 2.Nim游戏
     * 一共有N堆石子，编号1-n,第i堆有a[i]个石子，每次操作A和B可以从任意一堆石子中，
     * 取出任意数量的石子，至少取一颗，至多取出这一堆剩下所有的石子，两个人轮流行动，
     * 取光所有石子的人获胜，A先手
     * <p>
     * 给定a，假设2人都使用最优策略，谁会获胜
     * 给定一个数组，每个堆中的石子数量都指定，
     * <p>
     * 思路:对a[]数组的所有元素进行^异或操作，其结果为0，最优策略谁先手就必输，
     *
     * @param arr
     * @return
     */
    public static boolean Nim(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        return res != 0;
    }

    /**
     * 最大公约数(辗转相除法)
     */
    public static int zda(int a, int b) {
        return b == 0 ? a : zda(b, a % b);
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
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        int x1 = 1;
        int x2 = 2;
        int x3 = 4;
        //n为1,2,3的时候直接返回，所以从4开始算，
        for (int i = 4; i <= n; i++) {
            int x_1 = x1;
            x1 = x2 % mod;
            x2 = x3 % mod;
            x3 = ((x1 + x2) % mod + x_1) % mod;
            //根据前面三个的数，推出第四个，
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
     * 2.机器人走方格
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
}
