package DFS;

import java.util.ArrayList;
import java.util.Scanner;

public class DFS {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 15, 23, 13, 12};
//        buFenHe(arr, 46, 0, new ArrayList<>());
//        int[] data = new int[6];
//        data[0] = 1;
//        suShuHuan(6,data,1);
        ShuiWa(N, M);
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
        intS.remove(intS.size() - 1);
    }

    /**
     * @param n
     * @param arr
     * @param cur
     */
    public static void suShuHuan(int n, int[] arr, int cur) {
        if (cur == n && isP(arr[0] + arr[n - 1])) {
            print(arr);
            return;
        }
        for (int i = 2; i <= n; i++) {
            if (check(arr, i, cur)) {
                arr[cur] = i;
                suShuHuan(n, arr, cur + 1);
                arr[cur] = 0;
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
     * 判断是否
     *
     * @param arr
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

    public static int N = 10;
    public static int M = 12;

    /**
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
                if (i + k >= 0 && i + k <= N - 1 && j + l >= 0 && j + l <= M - 1) {
                    if (data[i + k][j + l] == 'W')
                        Shuiwa(data, i + k, j + l);
                }
            }
        }
    }

    public static int[] rec;
    public static int cnt;

    public static void NhuangHou(int row, int n) {
        if (row == n) {
            cnt++;
            return;
        }
        for (int col = 0; col < n; col++) {
            boolean ok = true;
            for (int i = 0; i < row; i++) {
                if (rec[i] == col || i + rec[i] == row + col || rec[i] - i == col - row) {
                    ok=false;
                    break;
                }
            }
            if (ok){
                rec[row]=col;
                NhuangHou(row+1,n);
                //rec[row]=0  //写不写回溯都可以,
            }
        }
    }
}