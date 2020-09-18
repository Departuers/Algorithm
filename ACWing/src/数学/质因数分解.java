package 数学;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103686514
 * 由算术基本定理知，对任意一个正整数n，一定有n = p1^q1 * p2^q2 * ... pr^qr。
 * 其中pi是n的质因数，qi是pi出现的次数。
 * 给定n个正整数ai，将每个数分解质因数，并按照质因数从小到大的顺序输出每个质因数的底数和指数。
 * 输入格式
 * 第一行包含整数n。接下来n行，每行包含一个正整数ai。
 * 输出格式
 * 对于每个正整数ai,按照从小到大的顺序输出其分解质因数后，每个质因数的底数和指数，每个底数和指数占一行。
 * 每个正整数的质因数全部输出完毕后，输出一个空行。
 * 数据范围
 * 1≤n≤100,1≤ai≤2∗10^9
 * 输入样例：
 * 2
 * 6
 * 8
 * 输出样例：
 * 2 1
 * 3 1
 * <p>
 * 2 3
 */
public class 质因数分解 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int u = 0; u < n; u++) {
            int t = sc.nextInt();
            for (int i = 2; i <= t / i; i++) {
                int res = 0;//质因数的指数
                while (t % i == 0) {
                    t /= i;
                    res++;
                }
                if (res != 0) System.out.println(i + " " + res);
            }
            if (t > 1) System.out.println(t + " " + 1);
        }
    }

    /**
     * 采取试除法，从2到n枚举除数i，如果i能整除n，则一直执行n /= i
     * 直至i不能整除n为止。这里由于是从第一个质数2开始枚举因子的，
     * 所以只有i为质数时才可能整除n。
     * 假设n有两个大于根号n的质因子a和b，则a | n且b | n，并且a与b互质，
     * 则ab | n，而ab > n，显然不会是n的因子，因此n最多只存在一个大于根号n的质因子。
     *
     * 我们就可以在试除法过程中只去枚举2到根号n中的数，最后再判断下n是否为1，
     * 为1则说明除尽了，不为1则n就是最后那个大于根号n的质因子。
     */
    static void div(int x) {
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {//
                System.out.println(i);
                int s = 0;
                while (x % i == 0) {
                    x /= i;
                    s++;
                }
                System.out.println(i + " " + s);
            }
        }
        //最后一个质因数
        if (x > 1) System.out.println(x + " " + 1);
    }
}
