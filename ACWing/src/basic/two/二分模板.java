package basic.two;

import java.util.TreeSet;

public class 二分模板 {
    public static void main(String[] args) {
        TreeSet<Integer> a = new TreeSet<Integer>();
        a.add(5);
        a.add(4);
        a.add(6);
        System.out.println(a);
    }

    static int bsearch(int l, int r) {
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private static boolean check(int mid) {
        return false;
    }

}
