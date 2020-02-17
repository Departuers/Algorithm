package 大整数运算技巧;

import java.math.BigInteger;

public class ZJU火星算数1205 {
    public static void main(String[] args) {
        System.out.println(vs(10, 10));
    }

    public static String vs(int a, int b) {
        BigInteger c = new BigInteger(String.valueOf(a));
        BigInteger d = new BigInteger(String.valueOf(b));
        BigInteger add = c.add(d);
        add.toString(10);
        return add.toString() ;
    }
}
