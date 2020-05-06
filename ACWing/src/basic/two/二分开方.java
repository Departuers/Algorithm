package basic.two;

import java.math.BigDecimal;
import java.util.Scanner;

public class 二分开方 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double l = 0, r = n;
        while (r - l > 1e-9) {
            double mid = (l + r) / 2;
            if (mid * mid >= n) r = mid;
            else l = mid;
        }
        System.out.printf("%.3f", l);
    }

    static void dk(BigDecimal b) {
        BigDecimal r = b.abs();
        BigDecimal l = BigDecimal.ZERO;
        for (int i = 0; i < 100; i++) {
            BigDecimal mid = l.add(r).divide(BigDecimal.valueOf(2));
            if (mid.multiply(mid).compareTo(b) > 0) r = mid;
            else l = mid;
        }
        System.out.println(l.toPlainString());
    }
}
