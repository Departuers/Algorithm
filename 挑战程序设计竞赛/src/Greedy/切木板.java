package Greedy;

import java.util.PriorityQueue;

/**
 * POJ 3253
 * Rence Repair
 * https://blog.csdn.net/qq_40421671/article/details/83274031
 * 农夫约翰为了修理栅栏，要将一块很长的木板分割成N块。
 * 准备切成的木板的长度为L1、L2、……、Ln. 未切割嗯木板的长度恰好为
 * 切割木板的长度和。每次切断木板时，需要的开销为这块木板的长度。
 * 例如，长度为21的木板切割成5、8、8的三块木板。
 * 长为21的木板切割成13、8时，开销为21.
 * 再将长度为13的木板切割成长度5、8时，开销为13.
 * 于是合计开销为34。于是按题目要求将木板切割完，最小的开销是多少？
 * 限制条件：
 * 1<=N<=2000
 * 0<=Li<=5000
 * 输入样例：
 * N=3, L={8, 5, 8}
 * 输出样例：
 * 34
 *  ，要使总费用最小，那么每次只选取最小长度的两块木板相加，再把这些“和”累加到总费用中即可
 * 本题虽然利用了Huffman思想，但是直接用HuffmanTree做会超时，可以用优先队列做
 * 因为朴素的HuffmanTree思想是：
 * （1）先把输入的所有元素升序排序，再选取最小的两个元素，把他们的和值累加到总费用
 * （2）把这两个最小元素出队，他们的和值入队，重新排列所有元素，重复（1），直至队列中元素个数<=1，
 * 则累计的费用就是最小费用
 */
public class 切木板 {
    public static void main(String[] args) { 
        int[] arr = {8, 5, 8, 4, 3};
        System.out.println(Zui(3, arr));
    }

    public static int Zui(int N, int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int ans = 0;
        while (pq.size() > 1) {
            Integer p1 = pq.poll();
            Integer p2 = pq.poll();
            ans += p1 + p2;
            pq.add(p1 + p2);
        }
        return ans;
    }
}
/**
 * #include <iostream>
 * #include <algorithm>
 * #include <queue>
 * <p>
 * using namespace std;
 * <p>
 * int n;
 * int l[20001];
 * <p>
 * int main() {
 * <p>
 * while(~(scanf("%d", &n))) {
 * <p>
 * priority_queue<int, vector<int>, greater<int> > q;
 * <p>
 * for(int i=0; i<n; i++) {
 * cin >> l[i];
 * q.push(l[i]);
 * }
 * <p>
 * long long ans = 0;
 * while(q.size() > 1) {
 * <p>
 * int m1, m2;
 * m1 = q.top();
 * q.pop();
 * m2 = q.top();
 * q.pop();
 * <p>
 * int t = m1 + m2;
 * ans += t;
 * <p>
 * q.push(t);
 * }
 * <p>
 * cout << ans << endl;
 * }
 * <p>
 * return 0;
 * }
 **/