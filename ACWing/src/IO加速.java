import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class IO加速 {
    public static void main(String[] args) throws IOException {

        tokenizer=new StringTokenizer("123123   15412  4312412");
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.hasMoreTokens());

        System.out.println(tokenizer.nextToken());

        System.out.println(tokenizer.hasMoreTokens());


        //标准输出流,只能输出字符串,不能输出数字!!!
        bw.write(1 + " ");
        bw.flush();
    }

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
}
