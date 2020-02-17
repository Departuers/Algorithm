package Math;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class 数学相关例题 {

    public static void main(String[] args) {
        AIshi(2019);

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

//        long start = System.nanoTime();
//        System.out.println(jiqiren(6, 6));
//        long end = System.nanoTime();
//        System.out.println(end - start);
//        long starts = System.nanoTime();
//        System.out.println(jiqire(6, 6));
//        long ends = System.nanoTime();
//        System.out.println(ends - starts);


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
        List<Integer> list = new ArrayList<Integer>();
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
     * 判断是不是一个素数，
     *
     * @param x
     * @return
     */
    public static boolean isPrimer(int x) {
        if (x <= 1) return false;
        int m = (int) Math.floor(Math.sqrt(x) + 0.5);
        for (int i = 0; i * i <= x; i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    /**
     * 埃氏筛法,判断一群数是不是质数,或者求第多少个是素数
     * 剩余定理:非常牛逼
     */
    public static void AIshi(int N) {
        int n = 1000;
        while ((n / Math.log(n)) < N) {
            n++;
        }//在整数X范围内有X/log(X)个素数,
        //逆推第N个素数的范围
        int[] arr = new int[n];
        int x = 2;
        while (x < n) {
            if (arr[x] != 0) {
                x++;
                continue;
            }
            int k = 2;
            while (x * k < n) {
                arr[x * k] = -1;
                k++;
            }
            x++;
        }
        int res = 0;
        for (int i = 2; i < arr.length; i++) {

            if (arr[i] != -1) {
                res++;
            }
            if (res == N) {
                System.out.println(i);
                break;
            }
        }

    }
}
