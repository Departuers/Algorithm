package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 给定两个整数 a 和 b，求 a 和 b 之间的所有数字中0~9的出现次数。
 * 例如，a=1024，b=1032，则 a 和 b 之间共有9个数如下：
 * 1024 1025 1026 1027 1028 1029 1030 1031 1032
 * 其中‘0’出现10次，‘1’出现10次，‘2’出现7次，‘3’出现3次等等…
 * 输入格式
 * 输入包含多组测试数据。
 * 每组测试数据占一行，包含两个整数 a 和 b。
 * 当读入一行为0 0时，表示输入终止，且该行不作处理。
 * 输出格式
 * 每组数据输出一个结果，每个结果占一行。
 * 每个结果包含十个用空格隔开的数字，第一个数字表示‘0’出现的次数，第二个数字表示‘1’出现的次数，以此类推。
 * 数据范围
 * 0<a,b<100000000
 * 输入样例：
 * 1 10
 * 44 497
 * 346 542
 * 1199 1748
 * 1496 1403
 * 1004 503
 * 1714 190
 * 1317 854
 * 1976 494
 * 1001 1960
 * 0 0
 * 输出样例：
 * 1 2 1 1 1 1 1 1 1 1
 * 85 185 185 185 190 96 96 96 95 93
 * 40 40 40 93 136 82 40 40 40 40
 * 115 666 215 215 214 205 205 154 105 106
 * 16 113 19 20 114 20 20 19 19 16
 * 107 105 100 101 101 197 200 200 200 200
 * 413 1133 503 503 503 502 502 417 402 412
 * 196 512 186 104 87 93 97 97 142 196
 * 398 1375 398 398 405 499 499 495 488 471
 * 294 1256 296 296 296 296 287 286 286 247
 * <p>
 * 状态表示,分情况讨论
 * 本题要统计a~b之间0到9出现的次数，暴力枚举需要O（n）的时间，
 * 寻找其中的规律可大幅提高效率。设count（n，x）可以统计1到n中x出现的次数，
 * 则a到b中x出现的次数为count(b) - count(a - 1)。
 * 下面要解决的问题就是如何求1到n中x出现的次数。
 * 小学数奥问题.
 * 分情况讨论
 * [a,b] 0~9每个数出现的次数
 * count(n,x)函数,1~n中x出现的次数,这个函数最重要
 * [a,b]中x出现的次数为count(b,x)-count(a-1,x)
 * 假设当前数字是,n = abcdefg
 * 分别求出1在每一位出现的次数
 * 比如求出1在第4位上出现的次数,为例
 * 求出1<= xxx1xxx <=abcdefg
 * 求出形如xxx1yyy这种的数字,在1~n之间
 * (1)前三位的范围是 xxx = 000~abc-1; 不会超过n 显然yyy=000~999任意取,有abc*1000种方案
 * (2)xxx==abc 第4位为1
 *      再划分:
 *          原数值的(d<1) abc1yyy 是非法  方案数是0
 *          (d==1) abc1yyy 这时候yyy只能取000~efg   有efg+1种情况
 *          原数值(d>1) abc1yyy 这时候yyy取值为000~999 有1000种情况
 * 所有情况加在一起
 * 边界条件,第一位,前导0 特殊处理
 */
public class 计数问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, t;
        while (true) {
            a = sc.nextInt();
            b = sc.nextInt();
            if (a == b && b == 0) break;
            if (a > b) {
                t = a;
                a = b;
                b = t;
            }
            for (int i = 0; i <= 9; i++) {
                System.out.print(count(b, i) - count(a - 1, i) + " ");
            }
            System.out.println();
        }
    }

    static int count(int n, int x) {
        if (n == 0) return 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        n = nums.size();
        int res = 0;
        for (int i = n - 1 - ((x == 0) ? 1 : 0); i >= 0; i--) {
            if (i < n - 1) {
                res += get(nums, n - 1, i + 1) * pow(i);
                if (x == 0) res -= Math.pow(10, i);
            }
            if (nums.get(i) == x) res += get(nums, i - 1, 0) + 1;
            else if (nums.get(i) > x) res += pow(i);
        }
        return res;
    }

    static int get(ArrayList<Integer> nums, int l, int r) {
        int res = 0;
        for (int i = l; i >= r; i--) {
            res = res * 10 + nums.get(i);
        }
        return res;
    }

    static int pow(int i) {
        return (int) Math.pow(10, i);
    }

}
