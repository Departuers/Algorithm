package greedy;

import java.util.Scanner;

/**
 * 寻找最大数
 * 请在整数 n 中删除m个数字, 使得余下的数字按原次序组成的新数最大，
 * 比如当n=92081346718538，m=10时，则新的最大数是9888
 * 输入
 * 第一行输入一个正整数T，表示有T组测试数据
 * 每组测试数据占一行，每行有两个数n,m（n可能是一个很大的整数，但其位数不超过100位，并且保证数据首位非0，m小于整数n的位数）
 * 输出
 * 每组测试数据的输出占一行，输出剩余的数字按原次序组成的最大新数
 * 样例输入
 * 2
 * 92081346718538 10
 * 1008908 5
 * 样例输出
 * 9888
 * 98
 * <p>
 * 思路:对字符串进行查找,每次查找最大值,查找len-m次就可以,
 * 但查找时需要考虑范围,比如9222225,第1次查询只能查询9到最后一个2这个范围,
 * 查找第i次必须保证后面还剩下m-i个字符不能查找
 *
 */
public class 寻找最大数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T != 0) {
            String s = sc.next();
            int m = sc.nextInt();
            int n = s.length() - m;//需要保留的长度
            for (int i = 0; i < n; i++) {
                int max = 0;

            }

            T--;
        }

    }
}
