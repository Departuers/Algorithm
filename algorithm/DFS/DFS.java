package DFS;

import java.util.ArrayList;
import java.util.Scanner;

public class DFS {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 15, 23, 13, 12};
        ShuiWa(10,12);
        int k = 11;
        System.out.println(ABC(arr, arr.length, k, 0, 0));
        buFenHe(arr, k, 0, new ArrayList<Integer>());
//        int[] data = new int[6];
//        data[0] = 1;
//        suShuHuan(6,data,1);

//        ShuiWa(N, M);
//        NhuangHou(0);
//        System.out.println(cnt);
    }

    /**
     * 2.部分数之和
     * 给定一个数组a，长度为n，判断是否可以从中选择出若干数，使他们的和恰好为K
     * 输入: n=4
     * a={1,2,4,7}
     * K=13
     * <p>
     * 输出:Yes{13=2+4+7}
     * 思路:要这个a[0]，这个1，然后把12交给下一次DFS来凑，或者不要这个a[0],把13交给其他元素来凑
     * 平行状态这是
     * <p>
     * 13 ，cur=0   cur代表找到哪个上面了，a[cur]
     * 12，cur=1   13，cur=1
     * 10,cur=2
     * 6,cur=3  10,cur=3
     * 一条路走到完，发现没有找到解，回退，一步继续找
     *
     * @param arr 源数组
     * @param k   剩余多少交给下次递归,
     * @param cur 数组索引走到哪了
     */
    public static void buFenHe(int[] arr, int k, int cur, ArrayList<Integer> intS) {
        if (k == 0) {//出口
            for (Integer anInt : intS) {
                System.out.print(anInt + " ");
            }
            System.exit(0);
        }
        if (k < 0 || cur == arr.length)//剪枝
            return;
        buFenHe(arr, k, cur + 1, intS);
        intS.add(arr[cur]);
        buFenHe(arr, k - arr[cur], cur + 1, intS);
        intS.remove(intS.size() - 1);//回溯
    }

    /**
     * 部分数之和简化版,返回存在与否
     *
     * @param arr 源数组
     * @param n   数组长度
     * @param k   需要求出的和
     * @param i   第i个元素
     * @param sum 当前凑到的部分和
     * @return 返回存在与否
     * 每个状态按顺序决定每个数,加或者不加,
     * 在全部n个数分决定后再判断它们的和是否为k即可
     * 状态数是2^n+1所以复杂度是O(2^n)
     * 枚举所有的状态
     */
    public static boolean ABC(int[] arr, int n, int k, int i, int sum) {
        if (i == n) return sum == k;
        if (ABC(arr, n, k, i + 1, sum)) return true;
        if (ABC(arr, n, k, i + 1, sum + arr[i])) return true;
        return false;
    }


    public static int N = 10;
    public static int M = 12;

    /**
     * POJ.2386
     * 3.水洼数目
     * 有一个大小为 N*M 的园子，雨后积起了水。八连通的积水被认为是连接在一起的。请求出
     * 园子里总共有多少水洼？（八连通指的是下图中相对 W 的*的部分）
     * ***
     * *W*
     * ***
     * 限制条件
     * N, M ≤ 100
     * 样例:
     * 输入
     * N=10, M=12
     * 园子如下图（'W'表示积水， '.'表示没有积水）
     * <p>
     * W........WW.
     * .WWW.....WWW
     * ....WW...WW.
     * .........WW.
     * .........W..
     * ..W......W..
     * .W.W.....WW.
     * W.W.W.....W.
     * .W.W......W.
     * ..W.......W.
     * <p>
     * 输出 3
     * 一个点八个方向，寻找联通
     * OOO
     * OWO
     * OOO
     * <p>
     * OOOO
     * OWWO
     * OOOO
     * 如果一个点是水洼,往右找,又找到了水洼,不能再往左找了,会形成死循环
     * 新水洼的八个方向与前一个水洼有重叠的部分
     * 不适合使用迭代,因为不知道要走多少次
     * 使用DFS较为简便
     * 转移到下一个状态之后,下一个状态又朝着八个状态走,有可能又回到原来的状态
     * 走完一个点就把这个点的水洼变成没有积水的地方
     * 这个不能回溯所到之处变成没有积水
     *
     * @param N 行数
     * @param M 列数
     */
    public static void ShuiWa(int N, int M) {
        Scanner sc = new Scanner(System.in);
        char[][] data = new char[N][];
        for (int i = 0; i < N; i++) {
            data[i] = sc.nextLine().toCharArray();
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] == 'W') {//找一个起点开始挖
                    Shuiwa(data, i, j);//一次清除一个大水洼,就是第一个小水洼所连通的大水洼,
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 八连通,写法
     *
     * @param data 源数组
     * @param i    剩余中找到积水的行
     * @param j    剩余中找到积水的列
     */
    public static void Shuiwa(char[][] data, int i, int j) {
        data[i][j] = '.';
        //经典写法,八连通,展开就是八个DFS
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (k == 0 && l == 0)//不判断自身,因为开头已经把当前水抽干
                    continue;
                if (i + k >= 0 && i + k <= N - 1 && j + l >= 0 && j + l <= M - 1) {//边界条件
                    if (data[i + k][j + l] == 'W')
                        Shuiwa(data, i + k, j + l);
                }
            }
        }
    }

    public static int n = 8;
    public static int[] rec = new int[n];
    public static int cnt = 0;

    /**
     * 4.n皇后问题
     * 在一个n*n的棋盘上面放n个皇后,每行,每列,每条对角线上都只能有一个皇后
     * n=3的时候是无解的,
     * NOO
     * OON          N代表皇后,第二行的皇后和第三行的皇后冲突
     * ONO
     * <p>
     * ONO
     * OON          N代表皇后,第二行的皇后怎么放和第一行的皇后冲突
     * ONO
     * <p>
     * OON
     * NOO          N代表皇后,第二行的皇后和第三行的皇后冲突
     * ONO
     * <p>
     * N=4,有4个皇后只有2种摆法
     * 一直在变,只能用DFS枚举所有状态,但可以提前剪枝,很难把这个问题用迭代模拟,
     * 思路:每一行只能放一个皇后,每次只放一个皇后
     * 一行,一列一个对角线只能有一个皇后
     * 每一次都是处理一个新行,不用判断行,判断列也比较简单,主要是判断对角线
     * 1,1     1,2     1,3     1,4
     * 2,1     2,2     2,3     2,4
     * 3,1     3,2     3,3     3,4
     * 4,1     4,2     4,3     4,4
     * <p>
     * 判断对角线:比如3,2的对角线,与它处在同一条主对角线,他们之差相同
     * 比如,3-2=1  跟它同一条对角线的,2,1也是,2-1=1
     * 与3,2处在同一条次对角线,他们之和相同
     * 比如,3+2=5,跟它同一条对角线的,2+3=5,1+4=5
     * 他们处于同一条对角线,这样判断比较简便
     *
     * @param row 皇后的数量是n,row是辅助数组的行
     */
    public static void NhuangHou(int row) {
        if (row == n) {
            cnt++;
            return;
        }
        for (int col = 0; col < n; col++) {
            boolean ok = true;
            for (int i = 0; i < row; i++) {
                if (rec[i] == col || i + rec[i] == row + col || rec[i] - i == col - row) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                rec[row] = col;
                NhuangHou(row + 1);
                //rec[row]=0  //写不写回溯都可以,
            }
        }
    }

    /**
     * @param n   素数环有几个元素
     * @param arr 存储素数环元素的辅助数组
     * @param cur DFS搜索过程中变化的cur,也就是数组下标,初始化为1,因为第一个固定为1,只能从第二个开始
     */
    public static void suShuHuan(int n, int[] arr, int cur) {
        //n就是环的长度,当足够长,判断最后一个和最后一个元素之和是不是素数
        if (cur == n && isP(arr[0] + arr[n - 1])) {
            print(arr);
            return;
        }
        //核心DFS逻辑
        for (int i = 2; i <= n; i++) {//判断是否出现过,以及是否与之前元素之和为素数
            if (check(arr, i, cur)) {
                arr[cur] = i;
                suShuHuan(n, arr, cur + 1);
                arr[cur] = 0;//回溯
            }
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "" : " "));
        }
        System.out.println();
    }

    /**
     * 1.判断i没有在arr数组中出现过
     * 2.判断arr[cur-1]+k是不是一个素数
     *
     * @param arr 源数组
     * @param k
     * @param cur
     * @return
     */
    private static boolean check(int[] arr, int k, int cur) {
        for (int i : arr) {
            if (i == k || !isP(arr[cur - 1] + k)) return false;
        }
        return true;
    }

    /**
     * 判断是否为素数
     *
     * @param k 需要判断是不是为素数的数字
     * @return b
     */
    private static boolean isP(int k) {
        for (int i = 2; i * i <= k; i++) {
            if (k % i == 0)
                return false;
        }
        return true;
    }

    public static int n_KunNan = 10;
    public static int l_KunNan = 4;
    public static int count_KunNan = 0;

    /**
     * 6.困难的串
     * 问题描述:如果一个字符串包含两个相邻的重复子串，则称它为容易的串，其他串称为困难的串,
     * 如:BB，ABCDACABCAB,ABCDABCD都是容易的，A,AB,ABA,D,DC,ABDAB,CBABCBA都是困难的。
     * <p>
     * 输入正整数n,L，输出由前L个字符(大写英文字母)组成的，求字典序第n小的困难的串。
     * 例如，当L=3时，前7个困难的串分别为:
     * A,AB,ABA,ABAC,ABACA,ABACAB,ABACABA
     * n指定为4的话,输出ABAC
     * <p>
     * 思路:比如A  B  C    要保证字典序小,添加字典序比较小的字母
     * 找路径产生的字符串
     * 第一个抓A,第二个再抓A就有2个重复相邻子串了,不符合条件   {A}
     * 第二个抓B,符合条件,这时候维持着字典序最小             {A,B}
     * 第三个再抓A,还是符合条件                           {A,B,A}
     * 第四个抓A还是抓B都不行,只能抓C                      {A,B,A,C}
     * 第五个抓A,符合没有相邻重复子串                      {A,B,A,C,A}
     * 第六个抓B                                       {A,B,A,C,A,B}
     * 每次增加一个字符
     * 注意,能往深走,就不能横着走,减少困难的串的不必要判断
     *
     * @param l
     * @param n
     * @param prefix
     */
    public static void KunNan(int l, int n, String prefix) {
        //尝试在prefix后面加一个字符
        for (char i = 'A'; i < 'A' + l; i++) {
            if (isHard(prefix, i)) {
                String x = prefix + i;
                System.out.println(x);
                count_KunNan++;
                if (count_KunNan == n)
                    System.exit(0);
                KunNan(l, n, x);
            }
        }
    }

    /**
     * 判断prefix+i是否是一个困难的串
     * 1.遍历所有的长度为偶数的子串,看是否对称
     * 2.然而prefix是一个困难的串,比如ABA i
     * i与前面的若干(可以是0)个字符,看做一个后缀,后缀跟前缀比
     *
     * @param prefix
     * @param i
     * @return
     */
    public static boolean isHard(String prefix, char i) {
        int count = 0;//截取的长度
        for (int j = prefix.length(); j >= 0; j -= 2) {
            final String s1 = prefix.substring(j, j + count + 1);
            final String s2 = prefix.substring(j + count + 1) + i;
            if (s1.equals(s2))
                return false;
            count++;
        }
        return true;
    }
}