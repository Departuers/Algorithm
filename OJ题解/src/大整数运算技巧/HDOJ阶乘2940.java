package 大整数运算技巧;

/**
 * HDOJ 2940
 * 题意:给你一个10进制数n 计算n的阶乘转化为16进制后中有多少个0(其中n<=100)
 * 思路:由于有多组测试数据,直接打表
 */
public class HDOJ阶乘2940 {
    public static int[] pre = new int[200];//代表n!的16进制结果
    public static int[] num = new int[200];//n!

    public static void main(String[] args) {
        init();
        System.out.println(num[10]);
        int c = 1;
        for (int i = 2; i <= 10; i++) {
            c *= i;
        }
        System.out.println(Integer.toHexString(c));
    }

    /**
     * 不用大数类型实现,打表算阶乘,需认真读题
     */
    public static void init() {
        pre[0] = 1;
        for (int i = 2; i < 110; i++) {
            int c = 0;//进位
            for (int j = 0; j < 200; j++) {
                int sum = pre[j] * i + c;
                pre[j] = sum % 16;
                c = sum / 16;
            }

            int k = 0;
            for (k = 199; k >= 0; k--) {
                if (pre[k] != 0) break;//找到最后一位非0数字
            }

            for (int j = 0; j < k; j++) {
                if (pre[j] == 0) num[i]++;
            }
        }
    }

}
