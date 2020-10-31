package basic.stack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 给定一个长度为N的整数数列，输出每个数左边第一个比它小的数，如果不存在则输出-1。
 * 输入格式
 * 第一行包含整数N，表示数列长度。
 * 第二行包含N个整数，表示整数数列。
 * 输出格式
 * 共一行，包含N个整数，其中第i个数表示第i个数的左边第一个比它小的数，如果不存在则输出-1。
 * 数据范围
 * 1≤N≤105
 * 1≤数列中元素≤109
 * 输入样例：
 * 5
 * 3 4 2 7 5
 * 输出样例：
 * -1 3 -1 2 2
 * 其实可以是双指针,
 * for i=0 i<n i++
 * for j=i=1  j>=0 j--
 * if( a[j]<a[i]){
 * print(a[i-1])
 * break
 * }
 * 把之前的元素放进栈中,查看有什么性质
 * 考虑要插入的数字是A3>=A5
 * 是不是A3永远不会输出出来,可以删除A3
 * 假设是否后面可以构造用到A3的情况的,
 * 考虑A6<=A5 显然A3>=A5也不符合要求
 * 又考虑A6>=A5 显然A5作为输出可以是符合题意的
 * 拥有如上性质
 * 栈中的元素都是严格单调上升的了
 */
public class 单调栈 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {//双指针算法,最慢退化为O(n^2)
            a[i] = sc.nextInt();
            if (i == 0) {
                bw.write(-1 + " ");
            } else {
                boolean f = true;
                for (int j = i - 1; j >= 0; j--) {
                    if (a[j] < a[i]) {
                        bw.write(a[j] + " ");
                        f = false;
                        break;
                    }
                }
                if (f) bw.write(-1 + " ");

            }
        }
        bw.flush();
    }

    static void stk() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int x = 0;
        int tt = 0;
        while (n-- != 0) {
            x = sc.nextInt();
            //x要是来了,比x更大的元素咱就不要了
            while (tt != 0 && stk[tt] >= x) tt--;//这时候,x要进栈,没用的渣渣赶紧走开,给x腾出位置
            //tt==0代表找不到比它小的数
            if (tt == 0) System.out.print(-1 + " ");
            else System.out.print(stk[tt] + " ");
            stk[++tt] = x;
            //stk[0]这个位置什么都不存,到了这个位置代表找不到!!!
        }
    }

    static int[] stk = new int[100010];
    static int n;
}
