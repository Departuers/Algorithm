package String;

import java.util.Scanner;

/**
 * Trie字典树
 * 给定N个字符串S1,S2…SN，接下来进行M次询问，每次询问给定一个字符串T，求S1～SN中有多少个字符串是T的前缀。
 * 输入字符串的总长度不超过106，仅包含小写字母。
 * 输入格式
 * 第一行输入两个整数N，M。接下来N行每行输入一个字符串Si。接下来M行每行一个字符串T用以询问。
 * 输出格式
 * 对于每个询问，输出一个整数表示答案。每个答案占一行。
 * 输入样例：
 * 3 2
 * ab
 * bc
 * abc
 * abc
 * efg
 * 输出样例：
 * 2
 * 0
 * 插入O(n)跟字符串长度有关  查询O(n)
 * 这里有个二维数组son，第一维p表示结点的编号，第二维s表示当前结点是什么字母，
 * 将a - z映射到0 - 25.son[p][s]的值表示其孩子结点的编号。
 * 这里格外要注意的是，除了第一次插入的字符外（比如插入abc，第一次插入的是a），
 * 字典树其它层次的结点son数组的第一维均表示其编号，
 * 为啥第一次插入的字符不用编号而是用0作为p值呢？是为了充当索引的作用，
 * 在查询时能够很快地找到。
 * 以本题样例来进行说明：首先str = “ab”，插入的第一个节点为a，值为a - a = 0，
 * 首先判断下son[0][0]是否存在，不存在则插入，
 * 同时声明如果紧接着插入它的孩子结点，编号应该是1，
 * 于是继续插入b，编号为1，值为1，son[1][1] = 2表示如果接下来有孩子结点，
 * 编号应该是2；接着插入bc，发现son[0][1]不存在，于是插入，
 * 其孩子结点编号为3也就是c结点；最后插入abc，发现son[0][0]和son[1][1]均存在，
 * 于是紧接着son[1][1]后面插入字符c。
 * 这里的cnt数组是给每次插入的字符串的最后一个字符做上一个结束标记，方便查询时统计有多少单词数。
 */
public class 前缀统计 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        while (n-- != 0) {
            str = sc.next().toCharArray();
            insert();
        }
        while (m-- != 0) {
            str = sc.next().toCharArray();
            System.out.println(serch());
        }
    }


    static int[][] son = new int[500000][26];
    //m代表
    static int[] cnt = new int[500000];
    //cnt[idx]以idx这个点单词结尾的个数
    static int n, m, idx = 1;
    static char[] str;

    /**
     * son[p][s]  p代表节点编号, a~z映射到0~25
     */
    static void insert() {
        int p = 0;//tire一般根节点是0节点
        for (int i = 0; i < str.length; i++) {
            int s = str[i] - 'a';//每一个字母
            if (son[p][s] == 0) son[p][s] = idx++;
            p = son[p][s];//指向下一个位置
        }
        cnt[p]++;
    }

    /**
     * 比如查询abc，从第一个字符开始，发现son[0][0]存在，
     * 于是判断下是不是有以当前遍历到的字符为结尾的单词，如果有，说明就是abc的前缀了，
     * 加上单词数；接着查询son[1][1]，发现依然存在，而且有ab字符串，以b为结尾的字符有cnt数组的标记，
     * 于是加上单词数1；接着发现c在字典树里也有，
     * 于是又加上了单词数，此次查询找到了abc的前缀ab和abc。第二次查询efg，
     * 因为son[0][4] = 0.不存在索引结点为e，所以查询终止。
     *
     * @return
     */
    static int serch() {
        int p = 0, res = 0;
        for (int i = 0; i < str.length; i++) {
            int s = str[i] - 'a';
            if (son[p][s] == 0) break;//没有这个单词
            p = son[p][s];
            res += cnt[p];
        }
        return res;
    }
}
