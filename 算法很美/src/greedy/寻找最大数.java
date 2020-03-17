package greedy;

import java.util.ArrayList;
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
 * 查找第i次必须保证后面还剩下m-i个字符不能查找,这样才能保证位数最大,只有这样才能保证原序列
 * 循环len-m次,每次循环从查找到最大值的下一个字符开始查找
 * 范围必须保证位数不能小于len-m
 * 简单来说:
 * 循环len-m次,(起点)每次从最大值的下一个元素开始找,第i次就是从第i个,查找到,查找范围就是m+i,查找的最大索引为m+i
 * 非常重要,但还是不太理解
 */
public class 寻找最大数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T != 0) {
            char[] s = sc.next().toCharArray();
            ArrayList<Character> ch = new ArrayList<Character>();
            int m = sc.nextInt();
            int n = s.length - m;//需要保留的长度
            int k = -1;
            for (int i = 0; i < n; i++) {
                int max = 0;
                for (int j = k + 1; j <= m + i; j++) {//m+i是寻找的终点的索引,最后肯定有m-i个没被查找,只有这样才能保证原序列
                    int t = s[j];
                    if (t > max) {
                        max = t;
                        k = j;
                    }
                }
                ch.add((char) max);
            }
            System.out.println(ch);
            T--;
        }

    }
}
