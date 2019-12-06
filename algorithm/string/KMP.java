package string;

/**
 * KMP字符串匹配...未完
 */
public class KMP {
    public static void main(String[] args) {

    }

    //暴力匹配，比较低效O(m*n)
    public static int indexOf(String str, String key) {
        int i = 0;//指向源串开始匹配的第一个元素
        int sc = i; //指针从i开始匹配
        int j = 0;  //key数组指针
        while (sc < str.length()) {
            if (str.charAt(sc) == key.charAt(j)) {
                sc++;
                j++;
                if (j == key.length()) {
                    return i;
                } else {
                    i++;
                    sc = i;
                    j = 0;
                }
            }
        }
        return -1;
    }

    public static int[] next(String ps) {
        int pLength = ps.length();
        int[] next = new int[pLength];
        char[] p = ps.toCharArray();
        next[0] = -1;
        if (ps.length() == 1) {
            return next;
        }
        next[1] = 0;
        int j = 1;
        int k = next[j];//看看j的最长匹配前缀在哪里
        while (j < pLength - 1) {
            if (k < 0 || p[j] == p[k])
                next[++j] = ++k;
            else k = next[k];
        }
        return next;
    }

    public static void m() {
            
    }

}
