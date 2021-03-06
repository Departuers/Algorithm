import java.io.*;
import java.util.*;

public class IO加速 {
    static void f() {
        long s = System.nanoTime();
        LinkedList<Integer> l = new LinkedList<Integer>();
        for (int i = 0; i < 5000000; i++) {
            l.add(i);
        }
        for (int i = 0; i < 5000000; i++) {
            if ((i & 1) == 1) l.pollLast();
            else l.pollFirst();
        }
        long t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        s = System.nanoTime();
        ArrayDeque<Integer> r = new ArrayDeque<Integer>(5000000);
        for (int i = 0; i < 5000000; i++) {
            r.addFirst(i);
        }
        for (int i = 0; i < 5000000; i++) {
            if ((i & 1) == 1) r.pollLast();
            else r.pollFirst();
        }
        t = System.nanoTime();
        System.out.println((t - s) / 1e8);
    }

    public static void main(String[] args) throws IOException {
        //       fff();
        //   sw();
        ///dfs(4, 1, new ArrayList<Integer>());
        f();
//        tokenizer = new StringTokenizer("123123   15412  4312412");
//        System.out.println(tokenizer.nextToken());
//        System.out.println(tokenizer.nextToken());
//        System.out.println(tokenizer.hasMoreTokens());
//
//        System.out.println(tokenizer.nextToken());
//
//        System.out.println(tokenizer.hasMoreTokens());
//
//
//        //标准输出流,只能输出字符串,不能输出数字!!!
//        bw.write(1 + " ");
//        bw.flush();
    }

    static void fff() {
        ArrayList<Integer> e = new ArrayList<Integer>();
        Random r = new Random();
        int te;
        long s = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            te = r.nextInt(100000);
            e.add(te);
        }
        Collections.sort(e);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        s = System.nanoTime();
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(e);
        while (!q.isEmpty()) q.poll();
        t = System.nanoTime();
        System.out.println((t - s) / 1e8);
    }

    static void dfs(int sum, int n, ArrayList<Integer> q) {
        if (sum == 0) {
            System.out.println(q);
            return;
        }
        System.out.println(q + " " + n);
        for (int i = n; i < sum; i++) {
            if (sum - i >= 0) {
                q.add(i);
                dfs(sum - i, i, q);
                q.remove(q.size() - 1);
            }
        }
    }


    static int[] par = new int[11000000];

    static void ff() {
        int a = 5, b = 5;
        Random r = new Random();
        int[] t = new int[3];
        while (a > 0 && b > 0) {
            for (int i = 0; i < 3; i++) {
                int e = r.nextInt(13923);
                System.out.println(e);
                t[i] = e & 1;
            }
            if (t[0] == t[1] && t[1] == t[2]) {
                a++;
                b--;
            } else {
                b++;
                a--;
            }
            System.out.println(a + "  " + b);
        }
        System.out.println(a);
        System.out.println(b);
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
}
