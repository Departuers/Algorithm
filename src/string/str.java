package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class str {
    public static void main(String[] args) {
        // System.out.println(replace("12 34 "));
//        System.out.println(tj("aadddd"));
//        System.out.println(check("123SFD", "1112222233333FFSSD"));
//        System.out.println(change("here you are"));
//        System.out.println(qua("2999000,a000", 3));
        //  hui();

//        String[] a = {"123", "sff", "ass", "sfa", "123"};
//        String[] n = {"sff", "ass"};
//        search(a, n);

        match();
    }

    /**
     * 1.有无重复字符
     * 比如给定一串字符abcdedfa1321.，、,返回有无重复字符
     * 哈希函数，把key值转换成数组下标索引
     *
     * @param str
     * @return
     */
    public static boolean cf(String str) {
        if (str == null || str.length() == 0) {
            System.out.println("空");
            return true;
        }
        int[] flag = new int[128];
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (flag[c] > 0)
                return false;
            else flag[c]++;
        }
        return true;
    }


    //2.翻转字符串
    public static String reverse(String a) {
        return new StringBuilder(a).reverse().toString();
    }

    /**
     * 3.变形词，给定2个字符串，其中一个能否变成另一个字符串考虑空格与大小写
     * 思路一，转换成数组排序
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean bi(String a, String b) {
        int lena = a.length();
        int lenb = b.length();
        if (lena != lenb)
            return false;
        char[] chars = a.toCharArray();
        char[] chars1 = b.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        return Arrays.equals(chars, chars1);
    }

    /**
     * 3.变形词，给定2个字符串，其中一个能否变成另一个字符串考虑空格与大小写
     * 思路三，哈希函数，字符转数组下标,a和b遍历添加进数组，不为偶数就返回false
     * 思路三，哈希函数，字符转数组下标,a遍历添加进去，b遍历一遍减出来，为负数返回false
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean b(String a, String b) {
        if (a.length() != b.length())
            return false;
        int[] arr = new int[128];
        for (int i = 0; i < a.length(); i++) {
            int c = a.charAt(i);
            arr[c]++;
        }
        for (int i = 0; i < b.length(); i++) {
            int c = b.charAt(i);
            arr[c]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1)
                return false;
        }
        return true;
    }

    /**
     * 4.替换空格
     * 将字符串中的空格全部换成%20
     * 思路 先遍历找到所有空格，2个指针，p1指向str的长度，p2指向str加上空格的长度，
     * p1的位置是空格，p2从字符串末尾添加%20，p1左移一位，直到p1指向0
     *
     * @param str
     * @return
     */
    public static String replace(String str) {
        char[] chars = str.toCharArray();
        int count = str.length();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 32)
                count += 2;
        }
        char[] chars1 = new char[count];
        System.arraycopy(chars, 0, chars1, 0, chars.length);
        int p1 = str.length() - 1;
        int p2 = count - 1;
        while (p1 >= 0) {
            if (chars1[p1] == ' ') {
                chars1[p2--] = '0';
                chars1[p2--] = '2';
                chars1[p2--] = '%';
            } else
                chars1[p2--] = chars[p1];
            p1--;
        }
        return new String(chars1);
    }

    /**
     * 5.字符串压缩，或者字符串统计
     * aaabccc压缩成a3b1c3
     * 压缩后的字符串没有变短，就返回原来的字符串
     * 思路就是遍历每次记录上一个字符,及出现次数，对stringbuilder第一个最后一个字符特殊处理
     * 第一个先添加进sb，最后一个手动添加字符出现次数
     * 遍历过程中，如果不同count重置为1，如果相同count++；
     *
     * @param data
     * @return
     */
    public static String tj(String data) {
        int count = 0;
        char last = data.charAt(0);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char charAt = data.charAt(i);
            if (sb.length() == 0) {
                sb.append(charAt);
                count++;
            } else if (last == charAt) {//和上一个字符相同
                count++;
            } else {//和上一个字符不同
                sb.append(count).append(charAt);
                count = 1;//遍历走到了新字符前面的逻辑没走，所以重置为1
            }
            last = charAt;
        }
        //处理最后一个字符重复次数
        if (count >= 1) {
            sb.append(count);
        }
        return sb.toString().length() > data.length() ? data : sb.toString();
    }

    /**
     * 6.两个字符串是否由相同的字符组成
     * 遍历第一个字符串，使用map，key为字符，value作为出现次数，有就行不用加，
     * 遍历第二个字符串，看key有没有，没有就是false
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean check(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s1.length(); i++) {
            if (map.get(s1.charAt(i)) == null)
                map.put(s1.charAt(i), 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            char charAt = s2.charAt(i);
            if (map.get(charAt) == null) {//说明s2此处不在key列表中
                return false;
            }
        }
        return true;
    }

    /**
     * 判断A串是否是B的旋转字符串，
     * 给定两个字符串，判定s2能否s1通过作循环移位rotate得到的字符串包括
     * 比如
     * AEBCD -> EBCD A  ->  BCD AE  ->CD AEB  ->D AEBC  -> AEBCD
     * 后面移位产生的字符串，s2是否是其旋转产生的子串。
     * a->b
     * b+b是否包含a
     * 思路，b+b把移位的部分，移到前面去了，其中如果包含a，就说明可以通过循环移位得到
     *
     * @param A
     * @param B
     * @return
     */
    public static boolean xunhuan(String A, String B) {
        if (A.length() != B.length())
            return false;
        StringBuilder sb = new StringBuilder(B).append(B);
        return sb.toString().contains(A);
    }

    /**
     * 判断A串是否是B的旋转字符串的子串，
     * 给定两个字符串，判定s2能否s1通过作循环移位rotate得到的字符串包括
     *
     * @param A
     * @param B
     * @return
     */
    public static boolean xuanz(String A, String B) {
        StringBuilder sb = new StringBuilder(B).append(B);
        return sb.toString().contains(A);
    }

    /**
     * 8.将字符串按单词翻转，如将：here you are翻转城are you here
     * 思路：将整个字符串翻转，再根据空格分割成string数组，string数组就是存的单词的翻转形式
     * 用stringbuilder，遍历一个单词，翻转一个单词添加进stringbuilder，再加上空格
     *
     * @param data
     * @return
     */
    public static String change(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(data).reverse();
        String[] split = sb.toString().split("\\s");
        sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(temp.append(split[i]).reverse());
            if (i != split.length - 1)
                sb.append(" ");
            temp.delete(0, temp.length());
        }
        return sb.toString();
    }

    /**
     * 9.去掉字符串中连续出现k次的0，正则。。。
     * 思路一 0{3}代表匹配连续出现3次的0
     */
    public static String qu(String s, int k) {
        String regex = "0{" + k + "}";
        return s.replaceAll(regex, "");
    }

    /**
     * 9.去掉字符串中连续出现k次的0，
     * 思路二，使用字符数组，遍历，判断找到0计数，else就往stringbuilder后面加count%k个0，
     * 连续出现k次，多的次数，再stringbuilder后面加上
     * 最后遍历数组的循环结束时，只走了if，else里面加的0但没走，所以最后要循环添加count%k个0
     */
    public static String qua(String s, int k) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '0')
                count++;
            else {
                for (int j = 0; j < count % k; j++) {
                    sb.append('0');
                }
                sb.append(aChar);
                count = 0;
            }
        }
        for (int j = 0; j < count % k; j++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 10.回文串
     * 翻转后和自己对比看是否相等
     */
    public static boolean hui(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    /**
     * 10.回文数字，比如1001,1221
     * 枚举ijji，回文数字都是ijji的形式
     * 不能暴力模拟分析会超时爆栈
     */
    public static void hui() {
        //90个大概
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i * 1000 + j * 100 + j * 10 + i);
            }
        }
    }

    /**
     * 搜索最短摘要
     */
    public static void search(String[] w, String[] keys) {
        int begin = -1;
        int end = -1;
        int p2 = -1;

        int minlen = Integer.MAX_VALUE;
        int[] keyFound = new int[keys.length];

        for (int i = 0; i < w.length; i++) {
            Arrays.fill(keyFound, 0);
            String word = w[i];
            int index = indexof(w, word);
            if (index == -1)
                continue;
            else {
                keyFound[index] = 1;
            }
            int j;
            if (p2 != -1) {
                j = p2;
            } else {
                j = i + 1;
            }
            for (; j < w.length; j++) {
                String word2 = w[j];
                int index1 = indexof(keys, word2);
                if (index1 == -1 || keyFound[index1] == 1)
                    continue;
                else {
                    keyFound[index1] = 1;
                    if (sum(keyFound) == keys.length) {
                        p2 = j;
                        if (j - i + 1 < minlen) {
                            minlen = j - i + 1;
                            begin = i;
                            end = j;
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(begin);
        System.out.println(end);
        System.out.println(minlen);
    }

    //查看单词在数组中的位置
    private static int indexof(String[] q, String word) {
        for (int i = 0; i < q.length; i++) {
            if (q[i].equals(word))
                return i;
        }
        return -1;
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    public static void match() {
        String s = "ADAABADSCccCC";
        String p = "AAB";
        Suff[] sa = getSa(s);
        int l = 0;
        int r = s.length() - 1;
        while (r >= 1) {
            int mid = l + ((r - l) >> 1);
            Suff midSuff = sa[mid];
            String tempmid = midSuff.str;
            int compareRes;
            if (tempmid.length() >= p.length()) {
                compareRes = tempmid.substring(0, p.length()).compareTo(p);
            } else
                compareRes = tempmid.compareTo(p);
            if (compareRes == 0) {
                System.out.println(midSuff.index);
                break;
            } else if (compareRes < 0)
                l = mid + 1;
            else
                r = mid - 1;
        }
    }

    public static Suff[] getSa(String src) {
        int strlength = src.length();
        Suff[] suffs = new Suff[strlength];
        for (int i = 0; i < strlength; i++) {
            String suffi = src.substring(i);//截取后缀
            suffs[i] = new Suff(suffi, i);
        }
        Arrays.sort(suffs);
        return suffs;
    }

    private static class Suff implements Comparable<Suff> {
        String str;
        int index;

        Suff(String str, int index) {
            this.index = index;
            this.str = str;
        }

        @Override
        public int compareTo(Suff o) {
            return this.str.compareTo(o.str);
        }

        @Override
        public String toString() {
            return "Suff{" +
                    "str='" + str + '\'' +
                    ", index=" + index +
                    '}';
        }
    }
}
