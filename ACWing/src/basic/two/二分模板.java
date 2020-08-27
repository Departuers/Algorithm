package basic.two;

public class 二分模板 {
    public static void main(String[] args) {
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

    static int bin(int l, int r) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid;
        }
        return r;
    }

}
