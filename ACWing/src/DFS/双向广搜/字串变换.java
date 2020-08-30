package DFS.双向广搜;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 已知有两个字串 A, B 及一组字串变换的规则（至多6个规则）:
 * A1 -> B1
 * A2 -> B2
 * …
 * 规则的含义为：在 A 中的子串 A1 可以变换为 B1、A2 可以变换为 B2 …。
 * 例如：A＝’abcd’ B＝’xyz’
 * 变换规则为：
 * ‘abc’->‘xu’ ‘ud’->‘y’ ‘y’->‘yz’
 * 则此时，A 可以经过一系列的变换变为 B，其变换的过程为：
 * ‘abcd’->‘xud’->‘xy’->‘xyz’
 * 共进行了三次变换，使得 A 变换为B。
 * 输入格式
 * 输入格式如下：
 * A B
 * A1 B1
 * A2 B2 |-> 变换规则
 * … …
 * 所有字符串长度的上限为 20。
 * 输出格式
 * 若在 10 步（包含 10步）以内能将 A 变换为 B ，则输出最少的变换步数；否则输出”NO ANSWER!”
 * 输入样例：
 * abcd xyz
 * abc xu
 * ud y
 * y yz
 * 输出样例：
 * 3
 * https://blog.csdn.net/qq_30277239/article/details/104723891
 * 双向广搜,显然直接bfs会超时
 * 每个字符串及其拓展的字符串都可以应用6个规则
 * 最坏 20*6=120种扩展
 * 最大达到120^10级别
 * 本题如果采用普通的BFS求状态转换的最小步数，字符串最长长度是20，
 * 假设每一个字符上最多只能进行一种变换，
 * 十步以内最多可以进行20^10也就是1024*10^10种状态的搜索，
 * 显然这么大的数字会超时或者超空间。即使每个字符串只会做一次变换，
 * 也一共要做6^10也就是六千多万种状态的搜索。因为BFS搜索过程中状态的数量是指数级递增的，
 * 所以时间增长的特别快。假设从A到B状态一共要做十次转化，C是A到B的一个中间状态，
 * 如果A到C要做5次转化，则C到B还要做5次转化。我们同时从A和B进行BFS，
 * 各进行五次搜索都搜到C的时候停止，
 * 则在两种情况下涉及的状态分别是2 * 20^5=640w以及2 * 6^5 约等于1.54w个状态，
 * 显然，这两个状态数都要远小于之前的状态数，或者说只有之前状态数开根号后这么多状态。
 * 因此，本题分别从起点和终点同时进行BFS的这种双向BFS的方法能够很好的解决问题。
 *
 * 需要解决的就是如何同时进行BFS搜索，可以先将开始状态和终点状态分别加入两个不同的队列，
 * 如何当两个队列都非空的时候，先对较小的那个队列出队做BFS，以减小状态的增加数量，
 * 并且如果两个队头元素的步数之和超过了10，就可以剪枝直接返回了。
 * 状态的转换需要考虑从状态x能够转换到哪些状态，因为字符串较短这里直接暴力搜索就可以解决了，
 */
//@SuppressWarnings("all")
public class 字串变换 {
    public static void main(String[] args) {
        String A, B;
        Scanner sc = new Scanner(System.in);
        A = sc.next();
        B = sc.next();
        n = 0;
        while (sc.hasNext()) {
            aaa[n] = sc.next();
            bbb[n] = sc.next();
            n++;
        }
        int step = bfs(A, B);
        if (step > 10) System.out.println("No");
        else {
            System.out.println(step);
        }
    }

    static int n;

    private static int bfs(String a, String b) {
        ArrayDeque<String> qa = new ArrayDeque<String>(), qb = new ArrayDeque<String>();
        HashMap<String, Integer> da = new HashMap<String, Integer>();
        HashMap<String, Integer> db = new HashMap<String, Integer>();
        qa.add(a);//起点往终点搜的队列
        qb.add(b);
        da.put(a, 0);
        db.put(b, 0);
        //两个数组不能为空
        while (!qa.isEmpty() && !qb.isEmpty()) {
            int t = 0;
            if (qa.size() <= qb.size()) {
                t = extend(qa, da, db, a, b);
            } else t = extend(qb, da, db, b, a);
            if (t <= 10) return t;
        }
        return 11;
    }

    private static int extend(ArrayDeque<String> q, HashMap<String, Integer> da, HashMap<String, Integer> db, String a, String b) {
        String t = q.poll();
        for (int i = 0; i < (t != null ? t.length() : 0); i++) {
            for (int j = 0; j < n; j++) {
                if (!t.substring(i, aaa[j].length()).equals(aaa[j])) continue;
                String u = t.substring(0, i) + bbb[j] + t.substring(i + bbb[j].length());
                if (db.containsKey(u)) return da.get(t) + 1 + db.get(u);
                if (da.containsKey(u)) continue;
                da.put(u, da.get(t) + 1);
//                if ()
            }
        }
        return -1;
    }

    static int e;
    static int N = 6;
    static String[] aaa = new String[N], bbb = new String[N];
}
