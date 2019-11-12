package com.shiyu.digui;

public class di {
    public static void main(String[] args) {
//        System.out.println(g(5));
//        f(12, 155);
//        int[] arr = {12, 12, 12, 13};
//        System.out.println(he(arr, 0));
//        System.out.println(fan("1234", 3));
        System.out.println(fei(6));

    }

    /**
     * 1.求阶乘
     * <p>
     * 1.找重复，子问题划分，n*(n-1)的阶乘，
     * 求n-1的阶乘是原问题的重复，规模更小－－子问题
     * 2.找重复中的变化量->参数
     * 3.找参数变化趋势->设计出口
     * <p>
     * ｇ()方法完成问题一部分，剩下的交给别的方法，还是交给g()方法
     */
    public static int g(int a) {
        if (a == 1)
            return 1;
        return a * g(a - 1);
    }

    /**
     * 2.打印i->j
     *
     * @param i
     * @param j
     */
    public static void f(int i, int j) {
        if (i > j)
            return;
        System.out.println(i);
        f(i + 1, j);
    }

    /**
     * 3.数组求和
     */
    public static int he(int[] arr, int k) {
        if (arr.length - 1 == k)
            return arr[k];
        return arr[k] + he(arr, k + 1);
    }

    /**
     * 4.字符翻转
     *
     * @param str
     * @param end
     * @return
     */
    public static String fan(String str, int end) {
        if (0 == end)
            return "" + str.charAt(0);
        return str.charAt(end) + fan(str, end - 1);
    }

    /**
     * 5.斐波那契数列
     * f(N-1)+f(N-2)=f(N)
     */
    public static int fei(int K) {
        if (K==1||K==2)
            return 1;
        return fei(K-1)+fei(K-2);
    }
}
