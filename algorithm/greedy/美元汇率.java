package greedy;

import java.util.Scanner;

/**
 * 如何买卖马克或者美元,使得从100美元开始,最后获得最高的价值
 * 【输入】
 * 第一行是一个自然数 N，1≤N≤100，表示戴维学习汇率的天数。接下来的 N 行中每行是一个自然数 A，1≤A≤1000。第 i+1 行的 A 表示预先知道的第 i+1 天的平均汇率，在这一天中，戴维既能用 100 美元买 A 马克也能用 A 马克购买 100 美元。
 * 【输出】
 * 第一行也是唯一的一行应输出要求的钱数(单位为美元，保留两位小数)。
 * 注意：考虑到实数算术运算中进位的误差，结果在正确结果 0.05 美元范围内的被认为是正确的，戴维必须在最后一天结束之前将他的钱都换成美元。
 * 【样例输入】
 * 5
 * 400
 * 300
 * 500
 * 300
 * 250
 * 【样例输出】
 * 266.67
 * 思路:设a[i]是第i天的汇率,如果是手中的是美元,应该在a[i+1]<a[i]时兑换,因为这样明天就可以兑换到更多的美元,
 * 如果是手中的是马克,应该在a[i+1]>a[i]时兑换,最后一天手里是马克,一定要换成美元,所以
 * 只需要从美元考虑问题就可以了
 */
public class 美元汇率 {
    public static void main(String[] args) {
        double money = 100;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int tem[] = new int[n + 1];
        tem[1] = sc.nextInt();
        for (int i = 2; i <= n; i++) {
            tem[i] = sc.nextInt();
            if (tem[i - 1] > tem[i]) {
                money = money * tem[i - 1] / tem[i];
            }
        }
        System.out.println(String.format("%.2f", money));//保留2位小数
    }
}
