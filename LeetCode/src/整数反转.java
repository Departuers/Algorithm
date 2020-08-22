/**
 * LeetCode 7
 */
public class 整数反转 {
    public int reverse(int x) {
        if (x < 0) {
            return -get(-x);
        } else return get(x);
    }

    public int get(int a) {
        StringBuilder res = new StringBuilder().append(a).reverse();
        int i = 0;
        try {
            i = Integer.parseInt(res.toString());
        } catch (Exception e) {
            return 0;
        }
        return i;
    }
}
