package string;

import java.util.ArrayList;

/**
 * RabinKarp,字符串匹配算法。
 */
public class RabinKarp {
    public static void main(String[] args) {
        String s = "qwer";
        String data = "pappqweasdfqweqwereqweqwer";
        long[] hashS = hashS(data, s.length());
        long hs = hash(s);
        System.out.println(match(hs, hashS));
    }

    public static final long seed = 31;

    /**
     * 可能会有hash冲突，不同的串算出同样的hash值。10万大概产生0-3个冲突，100万产生110个+
     * 算短的字符串(匹配规则)的可以用
     *
     * @param str
     * @return
     */
    public static long hash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = seed * hash + str.charAt(i);
        }//第一次hash为第一个字符，第二次hash是第一个字符值*31加上第二个字符
        //第三次是前面的hash值*31加上第四个字符,类推
        // 写起来是((((0+C0)*31)+C1)*31+C2)*31
        return hash % Long.MAX_VALUE;
    }

    /**
     * 每个字符都要算，性能非常非常低,滚动hash比较好
     *
     * @param p 匹配模式
     * @param s 源字符串
     */
    public static ArrayList<Integer> match(String p, String s) {
        long hash_p = hash(p);
        int p_len = p.length();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i + p_len <= s.length(); i++) {//最后不足p个元素
            long hash_i = hash(s.substring(i, i + p_len));
            if (hash_i == hash_p) {
                temp.add(i);
            }
        }
        return temp;
    }

    /**
     * 滚动hash
     *
     * @param s 源串
     * @param n 子串的长度
     * @return
     */
    public static long[] hashS(final String s, final int n) {
        long[] res = new long[s.length() - n + 1];//
        res[0] = hash(s.substring(0, n));
        for (int i = n; i < s.length(); i++) {
            char newChar = s.charAt(i);
            char oldChar = s.charAt(i - n);
            long value = (res[i - n] * seed + newChar - (long) (Math.pow(seed, n)) * oldChar) % Long.MAX_VALUE;
            res[i - n + 1] = value;
        }//减去旧的字符，加上新字符
        return res;
    }

    private static ArrayList<Integer> match(long hash_p, long[] hash_s) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < hash_s.length; i++) {
            if (hash_s[i] == hash_p) {
                res.add(i);
            }
        }
        return res;
    }
}