package doublePointer;

import java.util.Scanner;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 假设字符串中只包含从’a’到’z’的字符。
 * 样例
 * 输入："abcabc"
 * 输出：3
 * 分析：
 * 双指针法，第一个指针i从头开始遍历，同时将哈希表的值++，
 * 一旦遇见重复的，则从j开始遍历，同时哈希表值--，
 * 直到遍历到重复的那个值位置，j为该位置++，重复上述过程。
 * 这一方法与数组连续子序列最大和有点像，不过那个是遇见累加和为负数就重新规划起点，
 * 本题是遇见重复的就重新规划起点。
 */
public class 最长不含重复字符的子字符串 {
    static int[] a = new int[100100];
    static int[] ha = new int[26];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int sl = s.length();
        int ans = 0;
        for (int i = 0, j = 0; i < sl; i++) {
            ha[s.charAt(i) - 'a']++;
            while (ha[s.charAt(i) - 'a'] > 1) {
                ha[s.charAt(j) - 'a']--;
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        System.out.println(ans);
    }
}
