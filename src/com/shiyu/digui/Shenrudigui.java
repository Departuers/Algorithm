package com.shiyu.digui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Shenrudigui {
    private static int mod = 1000000007;
    private static int[] data = {1, 5, 10, 25};

    public static void main(String[] args) {
//        long start = System.nanoTime();
//        System.out.println(count(100, data));
//        long end = System.nanoTime();
//        System.out.println(end-start);
//        long starts = System.nanoTime();
//        System.out.println(countWays(100, data));
//        long ends = System.nanoTime();
//        System.out.println(ends-starts);

//        System.out.println(check(3));

        //   System.out.println(getSubset2(new int[]{1, 2, 3}, 3));
  //      System.out.println(getPermutation("abcd"));
        System.out.println(getPer("ABC"));
    }

    /**
     * 小白上楼梯，假设有n阶台阶，小白一次走1阶，或者2阶，或者3阶，
     * 计算有多少种走完楼梯的方式，
     * 防止溢出将结果mod 1000000007
     * 非递归实现，性能高
     *
     * @param n
     * @return
     */
    public static int fs(int n) {
        if (n < 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        int x1 = 1, x2 = 2, x3 = 4;
        for (int i = 4; i <= n; i++) {
            int x = x1;
            x1 = x2 % mod;
            x2 = x3 % mod;
            x3 = ((x + x1) % mod + x2) % mod;
        }
        return x3;
    }

    /**
     * 小白上楼梯，假设有n阶台阶，小白一次走1阶，或者2阶，或者3阶，
     * 计算有多少种走完楼梯的方式，
     * 防止溢出将结果mod 1000000007
     * 思路:递归写法
     *
     * @param n
     * @return
     */
    public static long rec(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        return rec(n - 1) % mod + rec(n - 2) % mod + rec(n - 3) % mod;
    }

    /**
     * 2.机器人走方格
     *
     * @param x
     * @param y
     * @return
     */
    public static int jiqiren(int x, int y) {
        if (x == 1 || y == 1) return 1;
        return jiqiren(x - 1, y) + jiqiren(x, y - 1);
    }

    /**
     * 2.机器人走方格,非递归写法
     *
     * @param x
     * @param y
     * @return
     */
    public static int jiqire(int x, int y) {
        int[][] state = new int[x + 1][y + 1];
        for (int i = 1; i <= x; i++) {
            state[1][i] = 1;
        }
        for (int i = 1; i <= x; i++) {
            state[i][1] = 1;
        }
        for (int i = 2; i <= x; i++) {
            for (int j = 2; j <= y; j++) {
                state[i][j] = state[i][j - 1] + state[i - 1][j];
            }
        }
        return state[x][y];
    }

    /**
     * 1分，2分，5分的硬币三种，问组合成1角，共有多少种组合
     * 1*x+2*y+5*z=10
     */
    public static void yingbi() {
        int count = 0;
        //i代表1分，j代表2分，k代表5分
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                for (int k = 0; k <= 10; k++) {
                    if (i + j * 2 + k * 5 == 10) {
                        count++;
                        System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
        System.out.println(count);
    }

    /**
     * --3.硬币表示
     * <p>
     * 假设有1元，2元，5元，10元，20元，50元，100元，200元
     * 用给定硬币构成一个数值n
     * 假如n=200,3*1+1*2+5+2*20+50+100
     * 问一共有多少种可能的组合方式
     * <p>
     * 思路:把工作交给下一层循环递归写法，重点！！！
     *
     * @param n
     * @param coins
     * @return
     */
    public static int countWays(int n, int[] coins) {
        if (n <= 0)
            return 0;
        return countWaysCore(n, coins, coins.length - 1);
    }

    public static int countWaysCore(int n, int[] coins, int cur) {
        if (n <= 0) return 0;
        if (cur == 0) return 1;
        int res = 0;
        for (int i = 0; i * coins[cur] <= n; i++) {
            int shengyu = n - i * coins[cur];
            res += countWaysCore(shengyu, coins, cur - 1);
        }
        return res;
    }

    /**
     * 重点！！！
     *
     * @param n
     * @param coins
     * @return
     */
    public static int count(int n, int[] coins) {
        int help[][] = new int[4][n + 1];
        for (int i = 0; i < help.length; i++) {
            help[i][0] = 1;//第一列初始化为1
        }
        for (int i = 0; i < help[1].length; i++) {
            help[0][i] = 1;//第一行初始化为1
        }
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i].length; j++) {
                System.out.print(help[i][j]);
            }
            System.out.println();
        }
        for (int i = 1; i < help.length; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 0; k <= j / coins[i]; k++) {
                    help[i][j] += help[i - 1][j - k * coins[i]];
                }
            }
        }
        System.out.println();
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i].length; j++) {
                System.out.print(help[i][j]);
            }
            System.out.println();
        }
        return help[3][n - 1];
    }

    /**
     * 1.合法括号
     * 实现一种算法:打印出n对括号的所有有效组合
     * 如:输入3
     * ((()))  (()()) (())() ()(()) ()()()
     * 一生三，放进set里面去重
     * 新增的括号可以放在左边，右边，里面
     * n   |  组合
     * 1      ()
     * 2      (()) ()()
     * 3    ((()))  (()()) (())() ()(()) ()()()
     *
     * @param n
     * @return
     */
    public static Set<String> check(int n) {
        Set<String> s_n = new HashSet<>();
        if (n == 1) {
            s_n.add("()");
            return s_n;
        }
        Set<String> s_n_1 = check(n - 1);
        for (String s : s_n_1) {
            s_n.add("()" + s);
            s_n.add(s + "()");
            s_n.add("(" + s + ")");
        }
        return s_n;
    }

    /**
     * 编写一个方法，确定某集合的所有子集。
     * {A,B,C}  有限集合
     * {A} {B} {C} {A,B} {A,C} {B,C} {A,B,C}自身也是自身的子集
     * <p>
     * C3/1  C3/2  C3/3
     * 子集生成就是选和不选的问题，要或者不要
     *
     * @param arr
     * @param n
     * @param cur
     * @return
     */
    public static Set<Set<Integer>> getSubset(int[] arr, int n, int cur) {
        Set<Set<Integer>> res = new HashSet<>();
        if (cur == 0) {
            Set<Integer> nil = new HashSet<>();//空集
            Set<Integer> first = new HashSet<>();//包含第一个元素的集合
            first.add(arr[0]);
            res.add(nil);
            res.add(first);
            return res;
        }
        Set<Set<Integer>> oldSet = getSubset(arr, n, cur - 1);
        for (int i = 0; i < n; i++) {
            Set<Set<Integer>> resNew = new HashSet<>(res);
            for (Set s : oldSet) {
                Set clone = (Set) ((HashSet) s).clone();
                clone.add(arr[i]);
                resNew.add(clone);
            }
            res = resNew;
        }
        return res;
    }

    /**
     * 子集生成之二进制        {A,B,C}
     * 对于第一个值，选或者不选。
     * 对于第二个值，选或者不选。
     * 对于第三个值，选或者不选。
     * 会形成一个选择树
     * 去掉空集就是  2^n-1
     * 从1-2^n-1把其中的二进制写出来
     * 0   0   1
     * 0   1   0
     * 0   1   1
     * 1   1   0
     * C   B   A
     * 只能这么对应
     * 0代表不选，1代表选。映射成A，B，C
     * 只能二进制高位开始对应A,B,C对应，数组低位
     *
     * @param arr
     * @param n
     * @return
     */
    public static ArrayList<ArrayList<Integer>> getSubset2(int[] arr, int n) {
        //  Arrays.sort(arr);//正序排序
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();//大集合
        for (int i = (int) (Math.pow(2, n) - 1); i > 0; i--) {
            ArrayList<Integer> list = new ArrayList<>();//每一个i建立一个list
            for (int j = n - 1; j >= 0; j--) {
                if (((i >> j) & 1) == 1) {//检查哪个位上的二进制为1，从高位开始检查，高位对应着数组靠后的元素
                    list.add(arr[j]);
                }
            }
            res.add(list);
        }

        return res;
    }

    /**
     * 全排列(N!)
     * 全排列就没有要和不要的概念，全都要，对排列顺序有要求
     * N!
     * 某一位上，选谁的概念。
     * {A,B,C}的全排列
     * {A,B,C}     {A,C,B}
     * {B,A,C}     {B,C,A}
     * {C,A,B}     {C,B,A}
     * 第一位有3个选择，第二位有2个选择，第一位只有一个选择
     * 3*2*1=6
     * <p>
     * 整体上来说N!比2^n-1增长速度快
     * 在4的时候，N!就超过2^n-1，超过子集
     * <p>
     * 1           A
     * 2      B A     A  B      把A放在B的左边，右边
     * 3 {A,B,C}     {A,C,B}    把C放在前一排元素的左边，右边，中间
     * {B,A,C}     {B,C,A}
     * {C,A,B}     {C,B,A}
     *
     * @param A
     * @return
     */
    public static ArrayList<String> getPermutation(String A) {
        int n = A.length();
        ArrayList<String> res = new ArrayList<>();
        res.add(A.charAt(0) + "");
        for (int i = 1; i < n; i++) {
            ArrayList<String> res_new = new ArrayList<>();
            char c = A.charAt(i);
            for (String str : res) {
                String newStr = c + str;
                res_new.add(newStr);
                newStr = str + c;
                res_new.add(newStr);
                for (int j = 1; j < str.length(); j++) {
                    newStr = str.substring(0, j) + c + str.substring(j);
                    res_new.add(newStr);
                }
            }
            res = res_new;
        }
        return res;
    }

    /**
     * 回溯 全排列
     * {A,B,C}  全排列就是打乱顺序
     * 把每一个元素都放到第一个，
     * nil
     * A    B   C
     * B C
     * C   B
     * 数组是共享的，因为不停的交换，已经乱了，所以每棵子树遍历结束，要回溯
     *
     * @param arr
     * @param k
     * @return
     */
    private static ArrayList<String> res=new ArrayList<>();

    public static ArrayList<String> getPer(String A) {
        char[] arr = A.toCharArray();
        Arrays.sort(arr);
        getPer(arr, 0);
        return res;
    }
    public static void getPer(char[] arr, int k) {
        if (k == arr.length) {//排好了一种情况，一条子树走到底了
            res.add(new String(arr));
        }
        for (int i = k; i < arr.length; i++) {
            swap(arr, k, i);
            getPer(arr, k + 1);
            swap(arr, k, i);//回溯
        }
    }
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
