package greedy;

public class 贪心 {
    public static void main(String[] args) {
        System.out.println(yingBi(124));

        System.out.println(yingBiN(124, 4));
    }

    private static final int N = 5; //一共多少种纸币。
    private static int[] Count = {5, 2, 2, 3, 5};
    private static int[] Value = {1, 5, 10, 50, 100};

    private static int yingBi(int money) {
        int num = 0;
        for (int i = N - 1; i >= 0; i--) {//优先选择大额纸币，的索引
            int c = Math.min(money / Value[i], Count[i]);//每一类纸币，需要多少个
            money = money - c * Value[i];//减去上面的钱
            num += c;//统计需要多少张
        }
        if (money > 0)
            num = -1;//找不到，返回-1，也就是无法刚好凑到这么多钱
        return num;
    }

    /**
     * 1.硬币问题
     * 有1元，5元，10元，50元，100元，500元的硬币各c1，c5，c10，c50，c100，c500枚
     * 要用这些硬币支付A元，最少需要多少硬币，假定本题最少存在一种支付方案
     * 0<=ci<=10^9
     * 0<=A<=10^9
     * <p>
     * 输入
     * 第一行有6个数字，分别代表从小到大6种面值硬币的个数
     * 第二行为A，代表需要支付的A元
     * 样例:
     * 输入
     * 3  2  1  3  0  2
     * 620
     * 输出
     * 6
     * <p>
     * 尽量优先先用大面值，就能得到最优解
     *
     * @param money 当前剩余钱
     * @param cur   当前剩余最大硬币的数组下标
     * @return
     */
    public static int yingBiN(int money, int cur) {
        if (money <= 0)
            return 0;
        if (cur == 0)
            return money;
        int coinValue = Value[cur];
        int x = money / coinValue;  //求剩余的钱能消耗几枚最大币值
        int cut = Count[cur];    //求该种硬币有几枚
        int t = Math.min(x, cut);//计算谁更小
        return t + yingBiN(money - t * Value[cur], cur - 1);
    }

}