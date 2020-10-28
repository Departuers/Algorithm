package String;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://blog.csdn.net/mwl000000/article/details/109260470
 * KMP解决单模式串,AC自动机=trie+kmp 可以解决多模式匹配串
 * https://blog.csdn.net/bestsort/article/details/82947639
 * 可以优化为Trie图
 * Trie是一个字典树,kmp字符串匹配算法
 * kmp中有一个next数组
 * 有一个S字符串,P是一个模式串,很短,
 * 求S中出现P的位置
 * 暴力做法O(n^2)
 * for (i=1;i<=S.size();i++){//枚举起点
 *     for(int j=1;j<=p.size();j++){
 *         if(S(i+j-1)!=p[j])
 *         break;
 *     }
 *     if(j>m)匹配成功
 * }
 * 朴素的说,如果在某个点失配了,可以回到什么位置
 * kmp核心next数组
 * next存的是一个长度,在模式串P中,能够以P[i]结尾的后缀,
 * 能够匹配的从1开始的非平凡前缀的最大长度,非平凡指的是不能是自己
 * 1. 求解next数组,S,P的下标从1开始
 * next[0]=next[1]=0
 * for(i=2,j=0;j<=n;i++){
 *     while(j!=0&&s[i]!=p[j+1])j=next[j]
 *     if(s[i]==p[j+1])j++
 *     next[i]=j;
 * }
 *  for(i=2;j<=n;i++){
 *      int j=next[i-1],等价写法,因为next[i]=j之后下一层循环就i++了,
 *      把j放在循环内部
 *      while(j!=0&&s[i]!=p[j+1])j=next[j]
 *      if(s[i]==p[j+1])j++
 *      next[i]=j;
 *  }
 * 最大的后缀,和前缀相等的最大长度,可以避开必然失败的移动
 * 所有以P[i]结尾的,找一个最长的后缀,和前缀相等,存的是这个最长后缀的长度,
 * 也可以说是存的是前缀的尾端点下表
 * kmp就是在一维数组上建立一个next数组
 * 推广到tire中,对trie建立next数组,每一个点都有一个next数组
 * 所有后缀都找最长前缀对应的尾端点
 * trie中0代表根节点
 * next[0]=0根节点指向自己
 * next[1]=0第一层,但要求最长后缀的长度小于自己,只能是0
 * 又考虑如何求trie的next,通过kmp的next求法推广
 * 求长度为i-1的next数组信息,去求长度为i的next数组信息
 * 一层一层来做,用bfs来做
 * 一个后缀指向最长前缀一定是指向上面的层数
 * ac自动机,可以求出每一个单词,在什么位置出现过,出现过多少次
 * 给定n个长度不超过50的由小写英文字母组成的单词
 * 以及一篇长为m的篇章
 * 请问有多少个单词在文章中出现了
 * t组数据
 * 第一行一个整数n
 * 接下来n行n个单词
 * 最后一行输入文章
 * 1
 * 5
 * she
 * he
 * say
 * shr
 * her
 * yasherhs
 * out:3
 */
public class 搜索关键词AC自动机 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 0;
        t = sc.nextInt();
        while (t-- != 0) {
            for (int i = 0; i < tr.length; i++) {
                Arrays.fill(tr[i], 0);
            }
            Arrays.fill(cnt,0);
            Arrays.fill(ne,0);
            idx=0;
            n=sc.nextInt();
            for (int i = 0; i < n; i++) {
                str = sc.next();
                insert();
            }
            build();
            int res = 0;
            String m = sc.next();
            for (int i = 0, j = 0; i < m.length(); i++) {
                int u=m.charAt(i)-'a';
                j=tr[j][u];
                int p=j;
                while (p!=0){
                    res+=cnt[p];
                    cnt[p]=0;
                    p=ne[p];
                }
            }
            System.out.println(res);
        }
    }

    static void insert() {
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            int t = str.charAt(i) - 'a';
            if (tr[p][t]==0)tr[p][t]=++idx;
            p=tr[p][t];
        }
        cnt[p]++;
    }
    static void build() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (tr[0][i] != 0) {
                q.add(tr[0][i]);
            }
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = 0; i < 26; i++) {
                int p = tr[t][i];
                if (p== 0) {
                    tr[t][i]=tr[ne[t]][i];
                }else {
                    ne[p]=tr[ne[t]][i];
                    q.add(p);
                }
            }
        }
    }

    static int idx, n, m, N = 10010, S = 55, M = 1000010;
    static int[][] tr = new int[N * S][26];
    static String str;
    static int[] ne = new int[N * S], cnt = new int[N * S];
}
