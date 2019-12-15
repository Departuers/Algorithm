package greedy;

import java.util.Scanner;

/**
 * 小明玩了将近整个寒假，马上就要开学了，现在他有很多寒假作业要做。每一位老师给了他一个交作业的期限。如果在截止日期后交作业,老师就会减少他的期末考试成绩。而现在我们假设每科作业总是做一天。小明让你来帮他安排做作业的计划减少的得分达到最小。
 * Input
 * 输入包含多组测试用例。输入的第一行是一个整数T,测试用例的数目。T测试用例跟随。每个测试用例开始以一个正整数N(1 < = N < = 1000),表明了寒假作业的数量。.第一行含有N整数代表每个作业应交的期限,下一行中含有N个整数表明被老师降低的分数。
 *
 * Output
 * 在每一行里，你应该降低输出最小的应被老师减少的分数。
 *
 * Sample Input
 * 3
 * 3
 * 3 3 3
 * 10 5 1
 * 3
 * 1 3 1
 * 6 2 3
 * 7
 * 1 4 6 4 2 4 3
 * 3 2 1 7 6 5 4
 * Sample Output
 * 0
 * 3
 * 5
 * 题目大意：小明写作业，每份作业都有各自的deadline，超过deadline会有不同的惩罚，而且小明一天只能写一份作业，所以问如何安排写作业的顺序，使小明被惩罚的力度最小。
 * 惩罚力度有大有小，那么将其按降序排序，从最大的日期向前推，每天都去解决当日仍存的最大惩罚力度的作业，那么子问题就是每天处理哪一份作业了。由于每份作业都有绑定的deadline和惩罚力度，
 */
public class 做作业 {
    class Node {
        int day;
        int score;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Node() {
        }

        public Node(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();

    }
}
//#include<stdio.h>
//#include<algorithm>
//using namespace std;
//struct ss
//{
//    int score;
//    int time;
//    bool operator< (const ss & b)const
//    {
//        return (score > b.score);
//    }//重载“<”,使得结构体与sort可以结合使用
//}b[1005];
//int main()
//{
//    int t;
//    scanf("%d",&t);
//    while(t--)
//    {
//        int a[1005];
//        int i,j,e=0;
//        for(i=0;i<1005;i++)
//        a[i]=0;
//        int n;
//        scanf("%d",&n);
//        for(i=0;i<n;i++)
//        scanf("%d",&b[i].time);
//        for(i=0;i<n;i++)
//        scanf("%d",&b[i].score);
//        sort(b,b+n);//降序排序
//        for(i=0;i<n;i++)
//            {
//            for(j=b[i].time;j>0;j--)//从截止日期向前推
//            if(a[j]==0)//有空的日期就标记为非空，然后跳出
//            {
//                a[j]=1;
//                break;
//            }
//            if(j==0) e=e+b[i].score;//找不到就加上相应的分值
//            }
//        printf("%d\n",e);
//    }
//}
