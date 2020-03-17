package wei;

/**
 * 实现一个整数，输入这个整数，输出该二进制表示1的个数，
 * 例如，9的二进制表示为1001，有2位1
 */
public class zhuanhuan {
    public static void main(String[] args) {
        System.out.println(totwo(1199));
        System.out.println(totwobywei(1199));
        System.out.println(totwowei(1199));
        System.out.println(totwoaa(1199));
    }

    //实现一个整数，输入这个整数，输出该二进制表示1的个数，
    public static int totwo(int da) {
        String x = Integer.toBinaryString(da);
        int size = 0;
        System.out.println(x);
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '1') {
                size++;
            }
        }
        return size;
    }

    //给出一个数字，返回一个数值表示其中1的个数
    //比如100100与100000判断等于==100000
    //(N & (1 << i)) == (1 << i)
    public static int totwobywei(int N) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((N & (1 << i)) == (1 << i))
                count++;
        }
        return count;
    }

    //给出一个数字，返回一个数值表示其中1的个数
    //二进制位往右移，移一次&1，判断是不是==1
    //((K >>> i) & 1) == 1
    public static int totwowei(int K) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((K >>> i) & 1) == 1)
                count++;
        }
        return count;
    }

    //给出一个数字，返回一个数值表示其中1的个数
    // (x-1)&x可以消除最低位的1
    public static int totwoaa(int K) {
        int count = 0;
        while (K != 0) {
            K = ((K - 1) & K);
            count++;
        }
        return count;
    }
}