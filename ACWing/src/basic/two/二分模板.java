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

    /*
     * 如果r=mid-1 那么mid使用l+r=1>>1
     * @param l
     * @param r
     * @return
     */
    static int bin(int l, int r) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

}
