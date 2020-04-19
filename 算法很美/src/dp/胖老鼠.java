package dp;

import java.util.*;

/**
 * FatMouse's Speed
 * Time Limit: 2000/1000 MS (Java/Others)    Memory Limit: 65536/32768 K (Java/Others)
 * Total Submission(s): 9174    Accepted Submission(s): 4061
 * Special Judge
 * <p>
 * Problem Description
 * FatMouse believes that the fatter a mouse is, the faster it runs. To disprove this, you want to take the data on a collection of mice and put as large a subset of this data as possible into a sequence so that the weights are increasing, but the speeds are decreasing.
 *  
 * <p>
 * Input
 * Input contains data for a bunch of mice, one mouse per line, terminated by end of file.
 * <p>
 * The data for a particular mouse will consist of a pair of integers: the first representing its size in grams and the second representing its speed in centimeters per second. Both integers are between 1 and 10000. The data in each test case will contain information for at most 1000 mice.
 * <p>
 * Two mice may have the same weight, the same speed, or even the same weight and speed.
 *  
 * <p>
 * Output
 * Your program should output a sequence of lines of data; the first line should contain a number n; the remaining n lines should each contain a single positive integer (each one representing a mouse). If these n integers are m[1], m[2],..., m[n] then it must be the case that
 * <p>
 * W[m[1]] < W[m[2]] < ... < W[m[n]]
 * <p>
 * and
 * <p>
 * S[m[1]] > S[m[2]] > ... > S[m[n]]
 * <p>
 * In order for the answer to be correct, n should be as large as possible.
 * All inequalities are strict: weights must be strictly increasing, and speeds must be strictly decreasing. There may be many correct outputs for a given input, your program only needs to find one.
 *  
 * <p>
 * Sample Input
 * 6008 1300        老鼠重量,   老鼠速度
 * 6000 2100
 * 500 2000
 * 1000 4000
 * 1100 3000
 * 6000 2000
 * 8000 1400
 * 6000 1200
 * 2000 1900
 *  
 * <p>
 * Sample Output
 * 4 4 5 9 7
 * 需要从输入的数据中找出一组数据,满足老鼠的重量增大,而老鼠的速度递减
 * <p>
 * 这题是一道简单的DP题，是dp中叫最长有序子序列的问题。和数塔类似。
 * 先将重量从小到大排，再找速度的最长递减子序列。
 * 代码：0MS
 * #include <cstdio>
 * #include <iostream>
 * #include <cstring>
 * #include <algorithm>
 * using namespace std;
 * #define M 2050
 * struct node {
 * int w,s,index;  //w:重量，s:速度，index:第几个数。
 * }map[M];
 * bool cmp(node x,node y){   //如果重量相等，速度大的排前面。
 *  if(x.w==y.w)
 * return x.s>y.s;
 * return x.w<y.w;      //如果重量不相等，重量小的排前面。
 * }
 * int res[M],dp[M],pre[M];
 * int main()
 * {
 * int i=1,j,k,n,m,maxlen;
 * while(cin>>n>>m)
 * {
 * map[i].w=n;map[i].s=m;map[i].index=i;
 * dp[i]=1;pre[i]=0;
 * i++;             //这道题输入有点问题，要强制结束才有输出。
 * }
 * sort(map+1,map+i,cmp); //对数据进行排序。
 * n=i-1;maxlen=0;        //n表示元素的个数。maxlen表示当前的最长的长度。
 * int maxi;              //maxi记录最长递减子序列的末元素。
 * dp[1]=1;
 * for(i=1;i<=n;i++)      //模板。
 * for(j=1;j<i;j++)
 * if(map[i].w>map[j].w && map[i].s<map[j].s && dp[j]+1>dp[i]) //发现以i结束的有更长的就更新。
 * {
 * dp[i]=dp[j]+1;
 * pre[i]=j;            //pre[]记录的就是i元素的前一个元素是j。
 * if(dp[i]>maxlen)     //判断是否比最长个还长。
 * {
 * maxi=i;
 * maxlen=dp[i];
 * }
 * }
 * int t=maxi;
 * i=0;
 * while(t!=0)  //通过回溯将元素反序输入res[]，同时i找到了元素的个数。
 * {
 * res[i++]=t;
 * t=pre[t];
 * }
 * printf("%d\n",i);
 * while(i>0)
 * {
 * i--;  //正序输出。
 * printf("%d\n",map[res[i]].index);
 * }
 * return 0;
 * }
 */
public class 胖老鼠 {
    private static class mouse {
        public int Weight;
        public int Speed;
        public int num;//编号

        public mouse(int weight, int speed, int num) {
            Weight = weight;
            Speed = speed;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<mouse> list = new ArrayList<mouse>();
        int num = 1;
        while (sc.hasNext()) {
            list.add(new mouse(sc.nextInt(), sc.nextInt(), num++));
        }
        Collections.sort(list, new Comparator<mouse>() {
            @Override
            public int compare(mouse x, mouse y) {
                if (x.Weight == y.Weight)
                    return x.Speed - y.Speed;
                return x.Weight - y.Weight;
            }//排列
        });
        int[] dp = new int[list.size() + 1];
        ArrayList<Integer> mem = new ArrayList<Integer>();
    }
}
