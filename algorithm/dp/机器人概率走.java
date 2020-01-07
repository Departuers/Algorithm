package dp;

import java.util.Scanner;

/**
 * Robot
 * Time Limit:4000MS     Memory Limit:102400KB     64bit IO Format:%I64d & %I64u
 * Submit
 * Status
 * Description
 * Michael has a telecontrol robot. One day he put the robot on a loop with n cells. The cells are numbered from 1 to n clockwise. 
 * At first the robot is in cell 1. Then Michael uses a remote control to send m commands to the robot. A command will make the robot walk some distance. Unfortunately the direction part on the remote control is broken, so for every command the robot will chose a direction(clockwise or anticlockwise) randomly with equal possibility, and then walk w cells forward. 
 * Michael wants to know the possibility of the robot stopping in the cell that cell number >= l and <= r after m commands. 
 * Input
 * There are multiple test cases. 
 * Each test case contains several lines. 
 * The first line contains four integers: above mentioned n(1≤n≤200) ,m(0≤m≤1,000,000),l,r(1≤l≤r≤n). 
 * Then m lines follow, each representing a command. A command is a integer w(1≤w≤100) representing the cell length the robot will walk for this command.   
 * The input end with n=0,m=0,l=0,r=0. You should not process this test case. 
 * Output
 * For each test case in the input, you should output a line with the expected possibility. Output should be round to 4 digits after decimal points.
 * Sample Input
 * 3 1 1 2
 * 1
 * 5 2 4 4
 * 1
 * 2
 * 0 0 0 0
 * Sample Output
 * 0.5000
 * 0.2500
 * 有标记着1~n的环，初始时有个机器人在1的位置，有m个操作，每个操作是使机器人随机的顺时针或逆时针的走w步，求所有操作过后机器人停在区间[l,r]内的概率；
 * 思路：挺明显的概率dp，首先可以很直观的定义出状态dp[i][j]，表示第j次操作后停留在第i位置的概率，转移方程是
 * dp[(i + w) % n][j] = dp[i[j-1] * 0.5；
 * dp[i-w][j]=dp[i][j-1]*0.5;其中i-w不断的加n使它大于等于0；
 * 但是题目操作数比较大，这样会超内存，观察发现每次转移只和上一次操作有关，所以可以用个滚动数组来节省空间；优化空间后转移方程是
 * dp[(i + w) % n][j&1] += dp[i][!(j&1)] * 0.5；
 * dp[x][j&1] += dp[i][!(j&1)] * 0.5；x=i-w，x也要叠加到非负；
 * 最后答案就是所有操作完后，位置l到r的概率总和。
 * #include <iostream>
 * #include <cstdio>
 * #include <cstring>
 * #include <algorithm>
 * #include <cmath>
 * #include <vector>
 * using namespace std;
 * const int N = 2100;
 * const int inf = 0x3f3f3f3f;
 * double dp[N][2];
 * int main()
 * {
 *     int n, m, l, r;
 *     while(scanf("%d %d %d %d", &n, &m, &l, &r)!=EOF)
 *     {
 *         if(n==0 && m==0 && l==0 && r==0)
 *             break;
 *         for(int i=1;i<=n;i++)
 *             dp[i][1]=dp[i][0]=0;
 *         dp[1][0]=1;
 *         for(int i=1;i<=m;i++)
 *         {
 *             int k;
 *             scanf("%d", &k);
 *             for(int j=1;j<=n;j++)
 *             {
 *                 int x=(j+k)%n, y=(j-k);
 *                 while(y < 0)
 *                     y += n;
 *                 if(x==0) x=n;
 *                 if(y==0) y=n;
 *                 dp[x][i&1]+=dp[j][!(i&1)]*0.5;
 *                 dp[y][i&1]+=dp[j][!(i&1)]*0.5;
 *             }
 *             for(int j=1;j<=n;j++)
 *                 dp[j][!(i&1)]=0;
 *         }
 *         double sum=0;
 *         for(int i=l;i<=r;i++)
 *         {
 *             sum+=dp[i][m&1];
 *         }
 *         printf("%.4f\n",sum);
 *     }
 *     return 0;
 * }
 */
public class 机器人概率走 {
    public static void main(String[] args) {
        int n = 0, m = 0, l = 0, r = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        if (n == 0 && m == 0 && l == 0 && r == 0)
            System.out.println(0);

    }

}
