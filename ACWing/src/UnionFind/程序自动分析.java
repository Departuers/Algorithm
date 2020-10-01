package UnionFind;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * 在实现程序自动分析的过程中,常常需要判定一些约束条件是否能被同时满足。
 * 考虑一个约束满足问题的简化版本：假设x1,x2,x3,…代表程序中出现的变量，给定n个形如xi=xj或xi≠xj的变量相等/不等的约束条件，请判定是否可以分别为每一个变量赋予恰当的值，使得上述所有约束条件同时被满足。
 * 例如，一个问题中的约束条件为：x1=x2，x2=x3，x3=x4，x1≠x4，这些约束条件显然是不可能同时被满足的，因此这个问题应判定为不可被满足。
 * 现在给出一些约束满足问题，请分别对它们进行判定。
 * 输入格式
 * 输入文件的第1行包含1个正整数t，表示需要判定的问题个数，注意这些问题之间是相互独立的。
 * 对于每个问题，包含若干行：
 * 第1行包含1个正整数n，表示该问题中需要被满足的约束条件个数。
 * 接下来n行，每行包括3个整数i,j,e，描述1个相等/不等的约束条件，相邻整数之间用单个空格隔开。若e=1，则该约束条件为xi=xj；若e=0，则该约束条件为xi≠xj。
 * 输出格式
 * 输出文件包括t行。
 * 输出文件的第k行输出一个字符串“YES”或者“NO”（不包含引号，字母全部大写），“YES”表示输入中的第k个问题判定为可以被满足，“NO”表示不可被满足。
 * 数据范围
 * 1≤n≤1000000
 * 1≤i,j≤1000000000
 * 输入样例：
 * 2
 * 2
 * 1 2 1
 * 1 2 0
 * 2
 * 1 2 1
 * 2 1 1
 * 输出样例：
 * NO
 * YES
 * 离散化:
 * 10^9的范围,但最多只有2e6个数
 * 涉及到的范围比较大,而用到的范围比较小
 * 所有用到的变量映射到值较小的一个区域里面去
 * 保序:排序,判重+二分
 * 不需要保序:map
 * 1.约数条件顺序使无所谓的,不影响结果
 * 先考虑所有的相等约束
 * Xi=Xj就把i和j合并
 * 考虑不等条件:可能有矛盾,
 * 如果Xi不等于Xj这个条件,如果Xi与Xj已经在同一个集合中,那么就矛盾
 * 1.离散化
 * 2.所有相等条件合并  对应并查集合并
 * 3.依次判断每个不等条件,Xi和Xj   对应并查集查询
 * O(n)
 * 也可以用传递闭包,O(n^3)太高
 * 但并查集,只适用于无向图
 */
public class 程序自动分析 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
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

    public static void main(String[] args) throws IOException {
        T = nextInt();
        int x, y, e, pa, pb;
        while (T-- != 0) {
            n = 0;//n作为一个离散化后的索引
            m = nextInt();
            map.clear();
            for (int i = 0; i < m; i++) {
                x = nextInt();
                y = nextInt();
                e = nextInt();
                q[i] = new query(get(x), get(y), e);
            }
            for (int i = 1; i <= n; i++) p[i] = i;
            //合并所有相等约束条件
            for (int i = 0; i < m; i++) {
                if (q[i].e == 1) {
                    pa = find(q[i].x);
                    pb = find(q[i].y);
                    p[pa] = pb;//如果已经在一个集合也没问题
                }
            }
            //检查所有的不等条件
            boolean has = false;
            for (int i = 0; i < m; i++) {
                if (q[i].e == 0) {
                    pa = find(q[i].x);
                    pb = find(q[i].y);
                    if (pa == pb) {
                        has = true;
                        break;
                    }
                }
            }
            if (has) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }


    static query[] q = new query[(int) 2e6];

    //无序离散化
    static int get(int x) {
        if (!map.containsKey(x)) map.put(x, ++n);
        return map.get(x);
    }

    static class query {
        int x, y, e;

        public query(int x, int y, int e) {
            this.x = x;
            this.y = y;
            this.e = e;
        }
    }

    static int n, m, T;
    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    static int[] p = new int[(int) 2e6];

}
