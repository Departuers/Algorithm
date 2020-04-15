package string;

import java.util.Random;

/**
 * KMP字符串匹配...
 * https://blog.csdn.net/dark_cy/article/details/88698736
 */
public class KMP {
    public static void main(String[] args) {
        String s = "214131242";
        String t = "124";
        int[] next = nextt(t);
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else j = next[j];
            if (j >= t.length()) {
                System.out.println(i-t.length());
            }
        }

    }

    public static String randomStr(int len) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Random r = new Random();
            char random = (char) (r.nextInt(26) + 'a');
            s.append(random);
        }
        return s.toString();

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

    /**
     * 前缀 指除了最后一个字符以外，一个字符串的全部头部组合；
     * 后缀 指除了第一个字符以外，一个字符串的全部尾部组合。
     *
     * @param ps 模式串
     * @return 求解next数组
     */
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

    public static int[] nextt(String ps) {
        int pLength = ps.length();
        int[] next = new int[pLength];
        char[] p = ps.toCharArray();
        next[0] = -1;
        if (ps.length() == 1) {
            return next;
        }
        int j = 0, k = -1;
        while (j < ps.length() - 1) {
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else k = next[k];//此语句是这段代码最反人类的地方，如果你一下子就能看懂，那么请允许我称呼你一声大神！
        }
        return next;
    }

}
