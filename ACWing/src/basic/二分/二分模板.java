package basic.二分;

/**
 * 整数二分
 * 二分的本质并不是单调性
 * 二分的本质是边界
 * 有一个整数区间,在这个区间上有一个性质,这个性质左半区间满足,右边区间不满足
 * 而二分可以找出这个分界点
 * 先写check函数,
 * 如果l=mid 补上l+r+1>>1
 */
@SuppressWarnings("all")
public class 二分模板 {
    public static void main(String[] args) {
    }

    /**
     * 区间[l,r] 被划分成[l,mid] 和[mid+1,r]时使用
     *
     * @param l
     * @param r
     * @return
     */
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
     * 区间[l,r] 被划分成[l,mid-1] 和[mid,r]时使用
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
