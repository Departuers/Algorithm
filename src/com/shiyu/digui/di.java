package com.shiyu.digui;

public class di {
    public static void main(String[] args) {
//        System.out.println(g(5));
//        f(12, 155);
//        int[] arr = {11, 14, 12, 13};
//        System.out.println(he(arr, 0));
//        System.out.println(fan("1234", 3));
//        System.out.println(fei(6));
//        System.out.println(zd(14, 7));
//        ca(arr, 3);
//        for (int i : arr) {
//            System.out.println(i);
//        }
//        han(3, "A", "B", "C");
//        int[] arr = {1, 4, 6, 7, 8, 23, 64, 88, 99};
//        System.out.println(binSearch(arr, 0, arr.length - 1, 4));
        //   System.out.println(lou(3));
//        System.out.println(zda(4, 12));

//        int arr[] = {5, 1, 2, 3, 4};
//        System.out.println(min(arr));

//        int arr[] = {-2, 0, 1, 2, 4};
//        System.out.println(moshu(arr));

//        String[] arr = {"ab", "", "ac", "ad", "", "ae", ""};
//        System.out.println(indexOf(arr, "ae"));
//        int[] arr = {1, 9, 10, 11, 12, 13, 2, 5, 6, 3, 4, 6, 8, 0};
//        zui(arr);

//        long l = System.nanoTime();
//        System.out.println(cimi(3, 18));
//        long r = System.nanoTime();
//        long time = r - l;
//        System.out.println(time);
//
//
//        long ll = System.nanoTime();
//        System.out.println(Math.pow(3, 18));
//        long rr = System.nanoTime();
//        long time1 = rr - ll;
//        System.out.println(time1);

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
        if (K == 1 || K == 2)
            return 1;
        return fei(K - 1) + fei(K - 2);
    }

    /**
     * 6.最大公约数(辗转相除法)
     */
    public static int zd(int a, int b) {
        if (a % b == 0)
            return b;
        return zd(b, a % b);
    }

    /**
     * 6.最大公约数(辗转相除法)
     */
    public static int zda(int a, int b) {
        return b == 0 ? a : zda(b, a % b);
    }

    /**
     * 3.插入排序递归写法...
     */
    public static void ca(int arr[], int k) {
        if (k == 0)
            return;
        ca(arr, k - 1);
        int x = arr[k];
        int index = k - 1;
        while (index > -1 && x < arr[index]) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[++index] = x;
    }

    /**
     * 3.汉诺塔
     */
    public static void han(int N, String from, String to, String help) {
        if (N == 1)
            System.out.println(N + from + to);
        else {
            han(N - 1, from, help, to);
            System.out.println(N + from + to);
            han(N - 1, help, to, from);
        }

    }

    public static int binSearch(int[] arr, int left, int right, int key) {
        if (left > right)
            return -1;
        if (left == right)
            return left;
        int mid = left + ((right - left) >> 1);
        int midValue = arr[mid];
        if (midValue > key)
            return binSearch(arr, left, mid - 1, key);
        else if (midValue < key)
            return binSearch(arr, mid + 1, right, key);
        else
            return mid;
    }

    /**
     * 小白上楼梯，假设有n阶台阶，小白一次走1阶，或者2阶，或者3阶，
     * 计算有多少种走完楼梯的方式，
     * 倒着设计，最后上去，跨1阶，2阶，或者3阶
     * f(n)=f(n-1)+f(n-2)+f(n-3)
     * 1阶有一种方法，2阶2种，3阶4种
     * 设计出口比较重要
     *
     * @param n
     * @return
     */
    public static int lou(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        return lou(n - 1) + lou(n - 2) + lou(n - 3);
    }

    /**
     * 7.旋转数组的最小数字(改造二分法)有重复元素就失效，不能用，只能顺序扫描
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转，
     * 输入一个递增排序数组的一个旋转，输出该数组的最小元素，例如，数组3,4,5,1,2是
     * 1,2,3,4,5的一个旋转，该数组的最小值为1
     * <p>
     * 比如5,1,2,3,4
     * <p>
     * 思路:观察发现，最小元素在无序的那一方，把无序那一方取出来，再找无序的那一方，
     * 当只有2个元素的时候，一般是右边的那个元素是最小值
     * 最大值右边一般是最小值
     * <p>
     * 精髓:通过中间元素与第一个元素对比，看哪方无序，缩小范围，找到最小值。
     *
     * @param arr
     * @return
     */
    public static int min(int[] arr) {
        int begin = 0;
        int end = arr.length - 1;
        if (arr[begin] < arr[end])
            return arr[begin];
        while (begin + 1 < end) {//begin和end相邻
            int mid = begin + ((end - begin) >> 1);
            if (arr[mid] >= arr[begin])//左侧有序
                begin = mid;
            else end = mid;
        }
        return arr[end];
    }

    /**
     * 8.魔术索引
     * 在数组A[0,1，...，n-1]中A[i]=i,满足数组下标，跟对应下标存的值相同，
     * 给定一个有序整数数组，元素值各不相同，编写一个方法，在数组A中找出一个魔术索引，若存在的话，不存在返回-1。
     */
    public static int moshu(int arr[]) {
        int begin = 0;
        int end = arr.length - 1;
        while (begin < end) {
            if (arr[begin] == begin)
                return begin;
            if (arr[end] == end)
                return end;
            int mid = begin + ((end - begin) >> 1);
            if (arr[mid] == mid) {
                return mid;
            } else if (arr[mid] < mid) {
                begin = mid + 1;
            } else
                end = mid - 1;
        }
        return -1;
    }

    /**
     * 9.在有空字符串的有序数组中查找(魔改二分查找)
     * 有个排序后的字符串数组，其中散布着空字符串，
     * 编写一个方法，找出给定字符串，(肯定不是空字符串)的索引
     * 思路:先找中间元素，while判断如果中间元素是空串，往右走。
     * 如果中间元素比给定元素大，说明在左半区间，
     * 如果中间元素比给定元素小，说明在右半区间
     *
     * @param arr
     * @param p
     * @return
     */
    public static int indexOf(String[] arr, String p) {
        int begin = 0;
        int end = arr.length - 1;
        while (begin <= end) {
            int mid = begin + ((end - begin) >> 1);
            while (arr[mid].equals(""))
                mid++;
            if (arr[mid].compareTo(p) > 0) {
                end = mid - 1;
            } else if (arr[mid].compareTo(p) < 0)
                begin = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    /**
     * 10.求最长连续递增子序列
     * 比如(1,9,2,5,7,3,4,6,8,0)的最长连续递增子序列是(3,4,6,8)
     * 未完成...
     */
    public static void zui(int[] arr) {
        int shao = 0;
        int you = 0;
        int count = -1;
        int qi = 0;
        for (int i = 0; i <= arr.length; i++) {
            if (i == arr.length - 1) {
                if (you + 1 - shao > count) {
                    qi = shao;
                    System.out.println(count);
                    count = i - shao;
                }
                break;
            }
            if (arr[you] < arr[i]) {
                you++;
            } else {
                if (you + 1 - shao > count) {
                    qi = shao;
                    System.out.println(count);
                    count = i - shao;
                }
                shao = you + 1;
                you = i;
            }
        }
        System.out.println(qi);
        System.out.println(count);
    }

    /**
     * 11.设计一个高效的求a的n次幂的算法
     */
    public static int cimi(int a, int n) {
        if (n == 0)
            return 1;
        int res = a;
        int ex = 1;
        while ((ex << 1) <= n) {
            //能翻
            res = res * res;
            ex <<= 1;
        }
        //翻不了交给下一次递归
        return res * cimi(a, n - ex);
    }

}
