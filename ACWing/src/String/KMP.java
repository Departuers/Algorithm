package String;

import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/100881221
 * <p>
 * T是匹配串,P是模式串
 * 假设T[s+1,...s+k]和P[1..k]匹配上了,
 * 此时T[s+k+1]!=P[k+1]
 * 朴素的做法是回到T[s+2]的位置,和P[1]的位置重新开始比较,
 * KMP找到一个最大的x,使得T[s+1,...s+k]的后x个字符和P的前x的字符相同
 * 这部分就是可以匹配上的,O(n+m)
 * 又注意到,T[s+1...s+k]=P[1..k]
 * 那么我们要求就是一个最大的x,满足P[1..k]的前x个字符,等于它的后x个字符,当然x要小于k
 * 这个x记为next[k],这个只与模式串有关
 * <p>
 * 样例
 * 3
 * aba
 * 5
 * ababa
 * out:
 * 0
 * 2
 */
public class KMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        p = sc.next().toCharArray();//长度为n的模式串
        m = sc.nextInt();
        a = sc.next().toCharArray();//长度为m的文本串
        int i = 0, j = 0;
        init();
        //i是源串,j是匹配串
        while (i < m && j < n) {
            if (j == -1 || a[i] == p[j]) {
                //到了开头或者匹配成功
                i++;
                j++;
            } else j = ne[j];//回到适配位置
            if (j == n) {//匹配成功!找到了
                j = ne[j];//又开始找下一个
                System.out.println(i - n);
                //找到每个匹配成功的起始位置
            }
        }
    }

    static char[] a, p;
    static int n, m;
    static int[] ne = new int[100005];

    //next数组
    static void init() {
        ne[0] = -1;
        int t = 0;
        for (int i = 1; i <= n; i++) {
            t = ne[i - 1];
            while (t != -1 && p[i - 1] != p[t]) t = ne[t];
            ne[i] = t + 1;
        }
    }
}
