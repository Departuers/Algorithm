package 线性dp.数字三角形模型;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数字三角形模板题
 * 首先定义状态,f[i][j] 就是所有从(1,1)走到(i,j)所有路线的集合
 * 属性是 max / min /数量 ...之一
 * 题目自然是求所有路径和最大值
 * 状态思考,last,上一个方式
 * f[i][j]是由f[i-1][j]或者f[i][j-1]转移过来的,
 * 因为只能往右或者下走,所以所有路线是由上面或者左边转移过来的
 * 状态计算:集合如何划分,划分依据last-最后
 * 根据最后一步划分
 * 把f[i][j] 分成两个部分,一种是从f[i][j-1]也就是从左边走过来的
 * 另一种f[i-1][j]也就是从上面走过来的
 * 集合划分依据,一般有2个,不重不漏,
 * 在求取count数量的时候,一定不能重复,
 * 在求取min和max的话,重复没有关系
 * 什么时候都不能漏
 */
public class 摘花生 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        while (t-- != 0) {
            R = sc.nextInt();
            C = sc.nextInt();
            for (int i = 0; i < arr.length; i++) {
                Arrays.fill(arr[i], 0);
            }
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    arr[i][j] = arr[i][j] + Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
            System.out.println(arr[R][C]);
        }
    }

    static int t, R, C;
    static int[][] arr = new int[105][105];
}
