package 树状数组;

import java.util.Scanner;

/**
 * https://www.acwing.com/solution/content/7501/
 * 题目要求求某一个点(x,y)左下方星星的个数(不包括自己)，
 * 且星星按y坐标增序给出，y 坐标相同的按x坐标增序给出,
 * 因此对于每个新来的点(x,y),y是当前纵坐标的最大值，
 * 只需要求[1,x]中星星出现的数量即可
 * 通过树状数组完成单点修改，区间查询操作
 * 注意：树状数组是从1开始的，而题目的给定的x范围是0≤x≤32000,
 * 因此需要将所有的x赋值成x + 1(相对位置不变)
 */
public class 数星星 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt() + 1;//相对位置不变
            int y = sc.nextInt();
            level[ask(x)]++;//如下显然就是 ask(x)显然就是x的层级
            add(x, 1);//记录x出现的次数,离散化需要离线
        }

    }

    static int N = 32001, n;
    static int[] tr = new int[N];
    static int[] a = new int[N];
    static int[] level = new int[N];

    static void add(int a, int b) {
        for (; a <= n; a += a & -a) {
            tr[a] += b;
        }
    }

    static int ask(int a) {
        int res = 0;
        for (; a != 0; a -= a & -a) {
            res += tr[a];
        }
        return res;
    }

}
