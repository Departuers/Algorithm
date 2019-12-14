package greedy;

import java.util.Scanner;

/**
 * 给定一个非负数n,判断n是不是一些数的阶乘(这些数不允许重复使用,且为正数)的阶乘之和
 * 比如9=1!+2!+3!     如果n满足条件输出Yes,否则No
 * 输入
 * n<100万
 * 思路:求出最接近n的阶乘,10的阶乘就是36万,11的阶乘就是3900万,n最大100万
 * 所以求出1-10的阶乘存在数组里面,
 * 第一步求出最接近n的阶乘,每次找到最接近n的阶乘 执行n -= temp[i];
 * 第二步重复找最接近n的阶乘数,重复第一步
 * 若最终n=0,则n可以分解成阶乘之和,反之不能
 */
public class 阶乘之和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean f = false;
//        int temp[]=new int[10];
//        for (int i = 0; i < 10; i++) {
//            temp[i]=j(i+1);
//        }
        int temp[] = {1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800};
        for (int i = 9; i >= 0; i--) {
            if (n >= temp[i] && n > 0) {
                n -= temp[i];
                System.out.println(i);
            }
            if (n == 0) {
                f = true;
            }
        }
        if (f) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static int j(int n) {
        if (n == 0)
            return 1;
        return n * j(n - 1);
    }
}
