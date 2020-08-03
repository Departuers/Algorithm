package dp.线性dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 线性dp是指阶段,状态的排布,是线性的
 * https://blog.csdn.net/azheng51714/article/details/8498041
 * https://blog.csdn.net/u012860063/article/details/40477059?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4.nonecase
 * 根据时间划分阶段
 * f[i]表示知道第i年的最小花费
 * 假设上一次买电脑是第j年,
 * 那么1~j-1年就是子问题,我们有f[i-1]的满足子问题的最优解,
 * 我们就不用考虑前j-1年的情况,第j年到第i年的维修费用为m(j,i)花费c
 * 所以可以用f[j-1]+m(i,j)+c来更新f[i]
 * f[i]=min{ f[j-1]+m(i,j)+c | 1<=j<=i }
 * <p>
 * 3
 * 3
 * 5 7 50
 * 6 8
 * 10
 * Sample Output
 * <p>
 * 19
 */
public class POJ3486电脑 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            n = sc.nextInt();
            c = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    m[i][j] = sc.nextInt();
                }
            }
            get();
        }
    }

    static void get() {

        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
//        f[1] = m[1][1] + c;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i] = Math.min(f[i], f[j - 1] + m[j][i] + c);
            }
        }
        System.out.println(f[n]);
    }

    static int n, c;
    static int[] f = new int[1000];
    static int[][] m = new int[1000][1000];
}
