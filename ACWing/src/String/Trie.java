package String;

import java.util.Scanner;

/**
 * Trie 高效得存储字符串集合的数据结构
 * https://blog.csdn.net/qq_30277239/article/details/100917923
 * 本题考察字典树Trie，又称单词查找树，百科的定义是：Trie树，
 * 是一种树形结构，是一种哈希树的变种。典型应用是用于统计，
 * 排序和保存大量的字符串（但不仅限于字符串），
 * 所以经常被搜索引擎系统用于文本词频统计。它的优点是：
 * 利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。
 * 我们给字典树定义一个根节点，编号为0，定义一个数组son[maxn][26]，
 * son[i][j]表示第i个节点的一个孩子节点是j。比如插入abc，
 * 插入a的时候将a作为0号节点的孩子，a编号为1，然后插入b作为a的孩子，编号为2，
 * 插入c作为b的孩子，编号为3，此时字典树中存在了一个以c结尾的单词，
 * 用cnt数组存储单词的末尾位置，cnt[3]++。表示编号为3的节点（c）是一个单词的结尾。
 * 再插入ab，发现0以及有a这个孩子了，遂深入到a，发现a有b这个孩子，遂不用插入，
 * 只用在b上做个单词结尾的标记即可。
 * 至于查询操作同样简单，同样是从根节点逐步找孩子节点，如果在某个位置失配，
 * 即son[p][u]为0，说明p节点没有孩子u，比如查询abd，查到b节点后发现b没有孩子d，
 * 遂未找到abd这个单词。
 */
public class Trie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String st = sc.next();
            str = sc.next();
            if (st.charAt(0) == 'I') insert();
            else System.out.println(query());
        }
    }

    static int N = 1001010, n, idx = 1;
    static int[][] son = new int[N][26];
    static int[] cnt = new int[N];//存储一个单词出现了多少次
    static String str;

    static void insert() {
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            int u = str.charAt(i) - 'a';//把字母映射为0-25内的数
            if (son[p][u] == 0) son[p][u] = idx++;
            //有路就走过去,没有路创造路都要走过去
            p = son[p][u];
        }
        cnt[p]++;
    }

    static int query() {
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            int u = str.charAt(i) - 'a';
            if (son[p][u] == 0) return 0;//找不到单词
            p = son[p][u];
        }
        return cnt[p];//找到单词
    }
}
