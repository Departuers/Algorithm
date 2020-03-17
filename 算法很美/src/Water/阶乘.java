package Water;

import java.math.BigInteger;
import java.util.Scanner;

public class 阶乘 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nextInt = sc.nextInt();
        BigInteger b = BigInteger.ONE;
        for (long i = 1; i <= nextInt; i++) {
            b = b.multiply(BigInteger.valueOf(i));
        }
        System.out.println(s(b.toString()));
    }


    public static char s(String n) {
        for (int i = n.length() - 1; i >= 0; i--) {
            if (n.charAt(i) != '0')
                return n.charAt(i);
        }
        return 0;
    }
}
