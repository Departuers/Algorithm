package dp.数位dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 科协里最近很流行数字游戏。
 * 某人命名了一种不降数，这种数字必须满足从左到右各位数字呈非下降关系，如 123，446。
 * 现在大家决定玩一个游戏，指定一个整数闭区间 [a,b]，问这个区间内有多少个不降数。
 * 输入格式
 * 输入包含多组测试数据。
 * 每组数据占一行，包含两个整数 a 和 b。
 * 输出格式
 * 每行给出一组测试数据的答案，即 [a,b]之间有多少不降数。
 * 数据范围
 * 1≤a≤b≤2^31−1
 * 输入样例：
 * 1 9
 * 1 19
 * 输出样例：
 * 9
 * 18
 */
public class 数字游戏 {
    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            System.out.println(get(t) - get(s - 1));
        }
    }

    static int N = 12;
    static int[][] f = new int[N][N];

    /**
     * 可以包含前导0
     * 树的左侧分支用dp预处理
     * f[i,j]表示最高位是j,且一共有i位的不降数的集合
     * 属性;count,计数
     * 状态计算划分,f[i,j]
     * 考虑last,找到一个分界线,最高位是j已经固定,枚举第二位的填法来分类
     * j _ _ _ _... 第一位填j,第二位可以选填j,j+1,j+2...9
     * 假设第2位填k,组成不降数,那么变成最高位为k,且只用填i-1位,恰好为f[i-1,k]
     * f[i,j]=f[i-1,j+1]+f[i-1,j+2]+...f[i-1,9]
     * 综合起来即为:f[i,j]=f[i-1,k   | j<=k<=9 ] k的取值范围是j<=k<=9求和
     */
    static void init() {
        for (int i = 0; i <= 9; i++) {
            f[1][i] = 1;
        }//最高位填i且只有1位,那就只有1位

        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {//枚举第二位
                    f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    static int get(int n) {
        if (n == 0) return 1;//如果n=0那么while不会执行导致错误

        ArrayList<Integer> num = new ArrayList<Integer>();
        while (n != 0) {//取出n的每一位
            num.add(n % 10);
            n /= 10;
        }

        int res = 0, last = 0;//每一个数字比上一个数字大即可,存的就是上一个数字的值,
        // last初始化为0,是因为第一位0~9都可以选,满足不降条件
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            for (int j = last; j < x; j++) {//左侧分支,last<=j<x的值
                res += f[i + 1][j];//最高位填j,且有i+1位
            }
            if (x < last) break;//右侧分支
            //如果该分支,不能构成下降数,就break
            last = x;//更新last
            if (i == 0) res++;//能安全到最后一个分支,说明每个数都大于前一个数,就是合法的
        }
        return res;
    }
}
