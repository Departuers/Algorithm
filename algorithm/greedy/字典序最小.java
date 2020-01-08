package greedy;

/**
 * 字典序最小问题
 * https://blog.csdn.net/weixin_41676901/article/details/80615415
 * 给定长度为N的字符串为S，要构造一个长度为N的字符串T。起初，T 是一个空串，随后反复进行下列任意操作。
 * ①：从S的头部删除一个字符串，加到T的尾部,
 * ②：从S的尾部删除一个字符，加到T的尾部
 * 目标是要构造字典序尽可能小的字符串T。（字典序是指从前到后比较两个字符串大小的方法。首先比较第1个字符，如果不同则第1个字符较小的字符串更小，如果相同则继续比较第2个字符.......如此继续，来比较整个字符串的大小）
 * 限制条件：
 * 1<=N<=2000
 * 字符串S只包含大写英文字母.
 * 输入样例：
 * 6
 * ACDBCB
 * 输出样例：
 * ABCBCD
 * <p>
 * 大佬思路:从字典序的性质上看，无论T的末尾有多大，只要前面部分的较小就可以了！
 * 所以我们可以初步得到下面的这种贪心算法：
 * 不断取S的头部和尾部中较小的一个字符然后放到T的末尾即可。
 * 这个算法确实还不错，但是还需考虑一点：没有考虑到S的开头和末尾字符相同情况下的处理方法。
 * 在这种情况下，我们需要尽早地使用更小的字符，所以就要比较下一个字符的大小，
 * 下一个字符也有可能会是相同的，所以就会得到如下算法：
 * 1.按照字典序比较将S和将S反转后的S'、
 * 2.如果S较小，就从S的开头取出一个字符然后放到T的末尾、
 * 3.如果S较小，就从T的开头取出一个字符然后放到T的末尾、
 * c b d c
 * c d b c
 * c
 */
public class 字典序最小 {
    public static void main(String[] args) {
        Z(6, "ACDBCB");
    }

    public static void Z(int n, String s) {
        String Cp = new StringBuilder(s).reverse().toString();
        int p1 = 0;
        int p2 = 0;
        int Strlen = 0;
        StringBuilder result = new StringBuilder();
        while (Strlen < n) {
            if (s.substring(p1, s.length()).compareTo
                    (Cp.substring(p2, Cp.length())) > 0) {
                Strlen++;
                result.append(Cp.charAt(p2));
                p2++;
            } else {
                Strlen++;
                result.append(s.charAt(p1));
                p1++;
            }
        }
        System.out.println(result);
    }
}
