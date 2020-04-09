package 线性dp.背包模型;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 金明今天很开心，家里购置的新房就要领钥匙了，新房里有一间金明自己专用的很宽敞的房间。
 * 更让他高兴的是，妈妈昨天对他说：“你的房间需要购买哪些物品，怎么布置，你说了算，只要不超过N元钱就行”。
 * 今天一早，金明就开始做预算了，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。
 * 每个主件可以有0个、1个或2个附件。
 * 附件不再有从属于自己的附件。
 * 金明想买的东西很多，肯定会超过妈妈限定的N元。
 * 于是，他把每件物品规定了一个重要度，分为5等：用整数1~5表示，第5等最重要。
 * 他还从因特网上查到了每件物品的价格（都是10元的整数倍）。
 * 他希望在不超过N元（可以等于N元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * 设第j件物品的价格为v[j]，重要度为w[j]，共选中了k件物品，编号依次为j1，j2，…，jk，则所求的总和为：
 * v[j1]∗w[j1]+v[j2]∗w[j2]+…+v[jk]∗w[jk]（其中*为乘号）
 * 请你帮助金明设计一个满足要求的购物单。
 * 输入格式
 * 输入文件的第1行，为两个正整数，用一个空格隔开：N m，其中N表示总钱数，m为希望购买物品的个数。
 * 从第2行到第m+1行，第j行给出了编号为j-1的物品的基本数据，每行有3个非负整数v p q，其中v表示该物品的价格，p表示该物品的重要度（1~5），q表示该物品是主件还是附件。
 * 如果q=0，表示该物品为主件，如果q>0，表示该物品为附件，q是所属主件的编号。
 * 输出格式
 * 输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（<200000）。
 * 数据范围
 * N<32000,m<60,v<10000
 * 输入样例：
 * 1000 5
 * 800 2 0
 * 400 5 1
 * 300 5 1
 * 400 3 0
 * 500 2 0
 * 输出样例：
 * 2200
 * 分析：
 * 本题考察AcWing 9 分组背包问题的应用，分组背包问题是每组物品最多只能选一个，
 * 而本题是每组物品可以选任意个，并且组内物品的体积，价值都不同。
 * 简单描述下题意，要买不超过N元钱的物品，物品分主件和附件，每个主件可能有多个附件，
 * 要想买附件必须先买其附属的主件，要找出花钱不超过N元买的物品的最大价值，
 * 一件物品的价值等于该物品的价格与重要度的乘积。首先考虑属性的存储，
 * 主件附件都要考虑物品的价格及价值（价格*重要度），所以可以用个pair存储。
 * 在进行状态转移时，遍历一个主件时考虑买几个附件，设一共有x个附件，
 * 则买附件的情况一共有2^x种，可以用状态压缩表示对这x种附件的购买方式，比如有2个附件，
 * 10表示只买第二个附件。其它过程与分组背包完全一致，只需要注意价格和价值的累加即可。
 * 分组背包:把每一个主件与附件的组合看成一个组
 * 状态定义:f[i,j]代表前i种主件可选价值不超过j的所有方案
 */
public class 金明的预算方案 {
    static class node {
        int v, m;

        public node(int v, int m) {
            this.v = v;
            this.m = m;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int v, p, q;
        for (int i = 1; i <= n; i++) {
            v = sc.nextInt();
            p = sc.nextInt();
            q = sc.nextInt();
            p *= v;
            if (q != 0) {
                fu[q].add(new node(v, p));
            } else {
                zhu[i] = new node(v, p);
            }
        }
        int t, e;
        for (int i = 1; i <= n; i++) {
            if (zhu[i] != null) {//是主件
                for (int j = m; j >= 0; j--) {
                    for (int k = 0; k < 1 << fu[i].size(); k++) {//有哪些附件,二进制枚举所有选和不选的情况
                        t = zhu[i].v;
                        e = zhu[i].m;
                        for (int u = 0; u < fu[i].size(); u++) {
                            if ((k >> u & 1) == 1) {
                                t += fu[i].get(u).v;
                                e += fu[i].get(u).m;
                            }
                        }
                        if (j >= t) f[j] = Math.max(f[j], f[j - t] + e);
                    }
                }
            }
        }
        System.out.println(f[m]);

    }

    static int[] f = new int[32009];
    static node[] zhu = new node[70];
    static ArrayList<node>[] fu = new ArrayList[70];

    static {
        for (int i = 0; i < fu.length; i++) {
            fu[i] = new ArrayList<node>();
        }
    }

    static int n, m;
}
