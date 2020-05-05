package basic.queue;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://www.acwing.com/problem/content/156/
 * 滑动窗口
 * 给定一个大小为n≤106
 * 的数组。
 * 有一个大小为k的滑动窗口，它从数组的最左边移动到最右边。
 * 您只能在窗口中看到k个数字。
 * 每次滑动窗口向右移动一个位置。
 * 以下是一个例子：
 * 该数组为[1 3 -1 -3 5 3 6 7]，k为3。
 * 窗口位置 	最小值 	最大值
 * [1 3 -1] -3 5 3 6 7 	-1 	3
 * 1 [3 -1 -3] 5 3 6 7 	-3 	3
 * 1 3 [-1 -3 5] 3 6 7 	-3 	5
 * 1 3 -1 [-3 5 3] 6 7 	-3 	5
 * 1 3 -1 -3 [5 3 6] 7 	3 	6
 * 1 3 -1 -3 5 [3 6 7] 	3 	7
 * 您的任务是确定滑动窗口位于每个位置时，窗口中的最大值和最小值。
 * 输入格式
 * 输入包含两行。
 * 第一行包含两个整数n和k，分别代表数组长度和滑动窗口的长度。
 * 第二行有n个整数，代表数组的具体数值。
 * 同行数据之间用空格隔开。
 * 输出格式
 * 输出包含两个。
 * 第一行输出，从左至右，每个位置滑动窗口中的最小值。
 * 第二行输出，从左至右，每个位置滑动窗口中的最大值。
 * 输入样例：
 * 8 3
 * 1 3 -1 -3 5 3 6 7
 * 输出样例：
 * -1 -3 -3 -3 3 3
 * 3 3 5 5 6 7
 * 这道题目,我们就维护两个队列,一个是最小值,一个是最大值.
 * 这里唯一的重点就是,每一次入队的时候,不需要管是不是比队头小,
 * 因为也许他现在小,但是在队头出队列后,他还在,而且是最小的值.
 * 然后我们可以发现一个性质：
 * 如果队列中存在两个元素，满足 a[i] >= a[j] 且 i < j，
 * 那么无论在什么时候我们都不会取 a[i] 作为最小值了，
 * 所以可以直接将 a[i] 删掉；
 * 也就是下标小,存的值大,不选,因为起码都有2个
 */
public class 单调队列 {
    public static void main(String[] args) throws IOException {
        n = nextInt();
        k = nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
//        List();
        int head = 0, end = -1;
        for (int i = 0; i < n; i++) {
            if (head <= end && q[head] < i - k + 1) head++;
            //队列里面的元素不在窗口内,就删除该元素
            //i-k+1是当前窗口的第一个值的下标
            while (head <= end && a[q[end]] >= a[i]) end--;
            //如果队列尾部的值大于新加进来的值,就删除队尾元素,
            //对应的是队尾指针前移
            q[++end] = i;
            //把新元素插入到队尾
            if (i >= k - 1) System.out.println(a[q[head]]);
        }

    }

    //链表实现队列
    static void List() throws IOException {
        for (int i = 0; i < n; i++) {
            if (!queue.isEmpty() && a[queue.peekFirst()] < i - k + 1) queue.removeFirst();
            while (!queue.isEmpty() && a[queue.peekLast()] > a[i]) queue.removeLast();
            queue.add(i);
            if (!queue.isEmpty() && i >= k - 1) bw.write(a[queue.peekFirst()] + " ");
        }
        bw.write("\n");
        queue.clear();
        for (int i = 0; i < n; i++) {
            if (!queue.isEmpty() && queue.peekFirst() < i - k + 1) queue.removeFirst();
            while (!queue.isEmpty() && a[queue.peekLast()] <= a[i]) queue.removeLast();
            queue.add(i);
            if (i >= k - 1 && !queue.isEmpty()) bw.write(a[queue.peekFirst()] + " ");
        }
        bw.flush();
    }

    static int[] a = new int[(int) (1e6 + 10)];
    static int[] q = new int[(int) (1e6 + 10)];
    static int n, k;

    static ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }
}
