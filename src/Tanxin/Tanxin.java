package Tanxin;

public class Tanxin {
    public static void main(String[] args) {
        System.out.println(yingbu(449));
        for (int i = 0; i < a.length; i++) {
            a[i]++;
        }
    }
static int[] a=new int[1000000];
    private static final int N = 5; //一共多少种纸币。
    private static int[] Count = {5, 2, 2, 3, 5};
    private static int[] Value = {1, 5, 10, 50, 100};

    private static int yingbu(int money) {
        int num = 0;
        for (int i = N - 1; i >= 0; i--) {//优先选择大额纸币从
            int c = Math.min(money / Value[i], Count[i]);//每一类纸币，需要多少个
            money = money - c * Value[i];//减去上面的钱
            num += c;//统计需要多少张
        }
        if (money > 0)
            num = -1;//找不到，返回-1，也就是无法刚好凑到这么多钱
        return num;
    }
}