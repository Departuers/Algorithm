package basic.stack;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.acwing.com/blog/content/132/
 * 第一题
 * 题目描述
 * 约翰有N头奶牛，编号为1到N。
 * 现在这N头奶牛按编号从小到大的顺序站成了一排，
 * 其中奶牛 i 的身高为Hi。
 * 现在，每头奶牛都向它的右侧望向那些编号较大的奶牛，
 * 对于奶牛 i 如果存在一头奶牛 j 满足 i<j
 * 并且 Hi<Hj
 * ，那么我们称奶牛 i 需要仰视奶牛 j。
 * 请你求出每头奶牛的最近仰视对象。
 * 输入格式
 * 第一行包含整数N。
 * 接下来N行，每行包含一个整数Hi
 * ，其中第 i 行的数为编号为 i 的奶牛的高度。
 * 输出格式
 * 共 N 行，每行输出一个整数，其中第 i 行的输出整数表示编号为 i 的奶牛的最近仰视对象的编号，如果不存在仰视对象，则输出0。
 * 数据范围
 * 1≤N≤105
 * 1≤Hi≤106
 * 输入样例：
 * 6
 * 3
 * 2
 * 6
 * 1
 * 1
 * 2
 * 输出样例：
 * 3
 * 3
 * 0
 * 6
 * 6
 * 0
 * 我们可以一步步读入奶牛,对于每一头奶牛而言,
 * 判断这一头奶牛可以成为哪些奶牛的仰视对象.
 * 于是,我们可以将当前奶牛,不断地和栈顶奶牛比较,如果说它身高大于栈顶奶牛,
 * 那么栈顶奶牛的仰视对象一定是当前奶牛,然后将栈顶奶牛出栈,进行下一次比较,
 * 直到栈为空或者栈顶奶牛身高高于它.最后再将我们当前奶牛的身高入栈.
 * 之所以仰视对象是当前奶牛,因为它是离栈顶奶牛最近的奶牛,而且满足身高大于它.
 * 可以略微证明一下,因为如果说栈顶奶牛的仰视对象不是当前这头奶牛,
 * 那么在这头奶牛之前,栈顶奶牛肯定已经出栈了,因为必然在此之前,
 * 会有奶牛成为栈顶奶牛的仰视对象,然而现在它还在栈中,那么栈顶奶牛的仰视对象,必然是当前这头奶牛.
 *
 * 这道题目,最有用的性质,就是离自己最近,而且比自己身高高.
 *    1. 离自己最近:这个性质其实就是我们所谓的栈的必备性质.
 *    2. 身高高:看到这种类型的词汇,一定要第一时间反应,这道题目是不是拥有单调性.
 * 经过上面的讨论,我们大致可以确定,这道题目的确拥有单调性,那么想让我们的数据结构栈,就进化成为了单调栈.
 */
public class 奶牛单调栈 {
    static class node {
        public int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        /**
         * 单调栈!
         */
        for (int i = 1; i <= n; i++) {
            while (st.size() != 0 && a[i] > st.peek().x) {
                s[st.pop().y] = i;//i是索引,依照题意写
            }
            st.push(new node(a[i], i));
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(s[i]);
        }
    }

    static int n;
    static int[] s = new int[100005], a = new int[100005];
    static ArrayDeque<node> st = new ArrayDeque<node>();
}
